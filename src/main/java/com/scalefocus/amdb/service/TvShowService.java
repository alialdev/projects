package com.scalefocus.amdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class TvShowService {

	@Autowired
	private TVShowRepository tvShowRepository;

	public Page<TVShow> getAllTVShows(Pageable pageable) {
		return tvShowRepository.findAll(pageable);
	}

	public Optional<TVShow> getTVShowById(Long id) {
		return tvShowRepository.findById(id);
	}

	public TVShow addTVShow(TVShow tvShow) {
		return tvShowRepository.save(tvShow);
	}

	public void deleteTVShow(Long id) {
		tvShowRepository.deleteById(id);
	}

	public Page<TVShow> searchTVShows(String title, Pageable pageable) {
		return tvShowRepository.findByTitleContaining(title, pageable);
	}

}