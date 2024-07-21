package com.scalefocus.amdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MediaService<MediaDto> {

	MediaDto add(MediaDto mediaItem);

	Optional<MediaDto> get(Long id);

	Optional<MediaDto> update(Long id, MediaDto mediaItem);

	void delete(MediaDto mediaItem);

	Page<MediaDto> getAll(Pageable pageable);

	Page<MediaDto> searchByTitle(String title, Pageable pageable);

	List<MediaDto> getTopRated(int limit);

	List<MediaDto> getByDirector(String director);

	List<MediaDto> getByYearRange(int startYear, int endYear);

	List<MediaDto> getByGenreAndTitleStartingWith(String genre, Optional<String> titleStart);

}