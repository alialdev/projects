package com.scalefocus.amdb.service;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.client.SimulatedApiClient;
import com.scalefocus.amdb.dto.MediaDto;
import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.mapper.TVShowMapper;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class TVShowService implements MediaService<TVShowDto> {

	private static final Logger logger = LoggerFactory.getLogger(TVShowService.class);

	private TVShowRepository tvShowRepository;

	private TVShowMapper tvShowMapper;

	private SimulatedApiClient simulatedApiClient;

	@Autowired
	public TVShowService(TVShowRepository tvShowRepository, TVShowMapper tvShowMapper) {
		this.tvShowRepository = tvShowRepository;
		this.tvShowMapper = tvShowMapper;
	}

	@Autowired
	public void setSimulatedApiClient(SimulatedApiClient simulatedApiClient) {
		this.simulatedApiClient = simulatedApiClient;
	}

	public Page<TVShowDto> getAll(Pageable pageable) {
		Page<TVShow> tvShows = tvShowRepository.findAll(pageable);
		return tvShows.map(tvShowMapper::tvShowToTVShowDto);
	}

	@Override
	public Optional<TVShowDto> get(Long id) {
		Optional<TVShow> tvShow = tvShowRepository.findById(id);
		return tvShow.map(tvShowMapper::tvShowToTVShowDto);
	}

	public Optional<TVShowDto> get(String imdbId) {
		Optional<TVShow> tvShow = tvShowRepository.findByImdbId(imdbId);
		return tvShow.map(tvShowMapper::tvShowToTVShowDto);
	}

	@Override
	public TVShowDto add(TVShowDto tvShowDto) {
		TVShow tvShow = tvShowMapper.tvShowDtoToTVShow(tvShowDto);
		TVShow savedTVShow = tvShowRepository.save(tvShow);
		return tvShowMapper.tvShowToTVShowDto(savedTVShow);
	}

	@Override
	public Page<TVShowDto> searchByTitle(String title, Pageable pageable) {
		Page<TVShow> tvShows = tvShowRepository.findByTitleContaining(title, pageable);
		return tvShows.map(tvShowMapper::tvShowToTVShowDto);
	}

	@Override
	public Optional<TVShowDto> update(Long id, TVShowDto tvShowDetails) {
		Optional<TVShow> tvShowOptional = tvShowRepository.findById(id);
		if (tvShowOptional.isPresent()) {
			TVShow existingTVShow = tvShowOptional.get();

			// Update fields from TVShowDto
			updateFields(TVShowDto.class, existingTVShow, tvShowDetails);

			// Update fields from MediaDto
			updateFields(MediaDto.class, existingTVShow, tvShowDetails);

			TVShow updatedTVShow = tvShowRepository.save(existingTVShow);
			TVShowDto updatedTVShowDto = tvShowMapper.tvShowToTVShowDto(updatedTVShow);

			return Optional.of(updatedTVShowDto);
		}
		return Optional.empty();
	}

	private void updateFields(Class<?> dtoClass, TVShow existingTVShow, TVShowDto tvShowDetails) {
		for (Field dtoField : dtoClass.getDeclaredFields()) {
			dtoField.setAccessible(true);
			try {
				Object value = dtoField.get(tvShowDetails);

				if (value != null) {

					Field tvShowField = findFieldInClassHierarchy(TVShow.class, dtoField.getName());
					if (tvShowField != null) {
						tvShowField.setAccessible(true);
						tvShowField.set(existingTVShow, value);
					} else {
						logger.error("No such field '{}' in TVShow class for corresponding field in TVShowDto",
							dtoField.getName());
					}
				}
			} catch (IllegalAccessException e) {
				logger.error("Illegal access to field: {}", dtoField.getName(), e);
			}
		}
	}

	private Field findFieldInClassHierarchy(Class<?> dtoClass, String fieldName) {
		while (dtoClass != null) {
			try {
				return dtoClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				dtoClass = dtoClass.getSuperclass();
			}
		}
		return null;
	}

	@Override
	public void delete(TVShowDto tvShowDto) {
		TVShow tvShow = tvShowMapper.tvShowDtoToTVShow(tvShowDto);
		tvShowRepository.delete(tvShow);
	}

	public void delete(String imdbId) {
		tvShowRepository.deleteByImdbId(imdbId);
	}

	@Override
	public List<TVShowDto> getTopRated(int limit) {
		return tvShowRepository.findAll()
			.stream()
			.sorted(Comparator.comparing(TVShow::getRating).reversed())
			.limit(limit)
			.map(tvShowMapper::tvShowToTVShowDto)
			.collect(Collectors.toList());
	}

	@Override
	public List<TVShowDto> getByDirector(String director) {
		return tvShowRepository.findAll()
			.stream()
			.filter(tvShow -> director.equals(tvShow.getDirector()))
			.map(tvShowMapper::tvShowToTVShowDto)
			.collect(Collectors.toList());
	}

	@Override
	public List<TVShowDto> getByYearRange(int startYear, int endYear) {
		return tvShowRepository.findAll()
			.stream()
			.filter(tvShow -> tvShow.getYear() >= startYear && tvShow.getYear() <= endYear)
			.map(tvShowMapper::tvShowToTVShowDto)
			.collect(Collectors.toList());
	}

	@Override
	public List<TVShowDto> getByGenreAndTitleStartingWith(String genre, Optional<String> titleStart) {

		BiFunction<TVShow, Optional<String>, Boolean> filterByTitleStartingWith = (tvShow, firstLetter) -> firstLetter
			.map(letter -> tvShow.getTitle().startsWith(letter))
			.orElse(true);

		// Alternative
		/*
		 * BiFunction<TVShow, Optional<String>, Boolean> filterByTitleStartingWith = (tvShow, firstLetter) -> {
		 * boolean startsWith = true;
		 * 
		 * if (firstLetter.isPresent()) {
		 * startsWith = tvShow.getTitle().startsWith(firstLetter.get());
		 * }
		 * *
		 * return startsWith;
		 * };
		 */

		return tvShowRepository.findAll()
			.stream()
			.filter(tvShow -> tvShow.getGenres().stream().anyMatch(gnr -> gnr.getName().equals(genre))
					&& filterByTitleStartingWith.apply(tvShow, titleStart))
			.map(tvShowMapper::tvShowToTVShowDto)
			.collect(Collectors.toList());
	}

	@Async
	public void insertTvShowsFromApiAsync() {
		simulatedApiClient.insertTVShowsFromApi();
	}

}
