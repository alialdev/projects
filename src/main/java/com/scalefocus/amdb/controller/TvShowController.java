package com.scalefocus.amdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.service.TVShowService;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

	private final TVShowService tvShowService;

	@Autowired
	public TvShowController(TVShowService tvShowService) {
		this.tvShowService = tvShowService;
	}

	@GetMapping("/list")
	public ResponseEntity<Page<TVShowDto>> getAllTVShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<TVShowDto> result = tvShowService.getAllTVShows(pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TVShowDto> getTVShowById(@PathVariable Long id) {
		Optional<TVShowDto> tvShow = tvShowService.getTVShowById(id);

		return tvShow.isPresent() ? ResponseEntity.ok(tvShow.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<TVShowDto> addTvShow(@RequestBody TVShow tvShow) {
		TVShowDto savedTvShow = tvShowService.addTVShow(tvShow);
		
		return ResponseEntity.ok(savedTvShow);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TVShowDto> updateTVShow(@PathVariable Long id, @RequestBody TVShow tvShow) {
		Optional<TVShowDto> updatedTvShow = tvShowService.updateTVShow(id, tvShow);

		return updatedTvShow.isPresent() ? ResponseEntity.ok(updatedTvShow.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTVShow(@PathVariable Long id) {
		tvShowService.deleteTVShow(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Page<TVShowDto>> searchTVShows(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<TVShowDto> result = tvShowService.searchTVShows(title, pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

}
