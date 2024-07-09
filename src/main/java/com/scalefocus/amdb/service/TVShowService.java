package com.scalefocus.amdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.mapper.TVShowMapper;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class TVShowService {

	@Autowired
	private TVShowRepository tvShowRepository;

	@Autowired
	private TVShowMapper tvShowMapper;

	public Page<TVShowDto> getAllTVShows(Pageable pageable) {
		Page<TVShow> tvShows = tvShowRepository.findAll(pageable);
		return tvShows.map(tvShowMapper::tvShowToTvShowDto);
	}

	public Optional<TVShowDto> getTVShowById(Long id) {
		Optional<TVShow> tvShow = tvShowRepository.findById(id);
		return tvShow.map(tvShowMapper::tvShowToTvShowDto);
	}

	public TVShowDto addTVShow(TVShow tvShow) {
		TVShow savedTVShow = tvShowRepository.save(tvShow);
		return tvShowMapper.tvShowToTvShowDto(savedTVShow);
	}

	public void deleteTVShow(Long id) {
		tvShowRepository.deleteById(id);
	}

	public Page<TVShowDto> searchTVShows(String title, Pageable pageable) {
		Page<TVShow> tvShows = tvShowRepository.findByTitleContaining(title, pageable);
		return tvShows.map(tvShowMapper::tvShowToTvShowDto);
	}

	public Optional<TVShowDto> updateTVShow(Long id, TVShow tvShowDetails) {
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
			existingTVShow.setId(id);

			TVShowDto updatedTVShow = addTVShow(existingTVShow);
			return Optional.of(updatedTVShow);
		}
		return Optional.empty();
	}

}
