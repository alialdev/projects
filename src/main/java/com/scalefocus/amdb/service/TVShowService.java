package com.scalefocus.amdb.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scalefocus.amdb.client.SimulatedApiClient;
import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.mapper.TVShowMapper;
import com.scalefocus.amdb.mapper.TVShowMapper;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class TVShowService implements MediaService<TVShowDto> {

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
		Optional<TVShow> tvShow = tvShowRepository.findById(id);
		if (tvShow.isPresent()) {
			TVShow existingTVShow = tvShow.get();

			if (tvShowDetails.getTitle() != null) {
				existingTVShow.setTitle(tvShowDetails.getTitle());
			}
			if (tvShowDetails.getDescription() != null) {
				existingTVShow.setDescription(tvShowDetails.getDescription());
			}
			if (tvShowDetails.getRating() != null) {
				existingTVShow.setRating(tvShowDetails.getRating());
			}
			if (tvShowDetails.getReleaseDate() != null) {
				existingTVShow.setReleaseDate(tvShowDetails.getReleaseDate());
			}
			if (tvShowDetails.getDirector() != null) {
				existingTVShow.setDirector(tvShowDetails.getDirector());
			}
			if (tvShowDetails.getWriter() != null) {
				existingTVShow.setWriter(tvShowDetails.getWriter());
			}
			if (tvShowDetails.getStars() != null) {
				existingTVShow.setStars(tvShowDetails.getStars());
			}
			if (tvShowDetails.getDuration() != null) {
				existingTVShow.setDuration(tvShowDetails.getDuration());
			}
			if (tvShowDetails.getImdbId() != null) {
				existingTVShow.setImdbId(tvShowDetails.getImdbId());
			}
			if (tvShowDetails.getYear() != null) {
				existingTVShow.setYear(tvShowDetails.getYear());
			}

			TVShow updatedTVShow = tvShowRepository.save(existingTVShow);

			TVShowDto updatedTVShowDto = tvShowMapper.tvShowToTVShowDto(updatedTVShow);

			return Optional.of(updatedTVShowDto);
		}
		return Optional.empty();
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
