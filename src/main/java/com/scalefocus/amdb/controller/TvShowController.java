package com.scalefocus.amdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.service.TvShowService;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

	private final TvShowService tvShowService;

	@Autowired
	public TvShowController(TvShowService tvShowService) {
		this.tvShowService = tvShowService;
	}

	@GetMapping("/list")
	public Page<TVShow> getAllTVShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return tvShowService.getAllTVShows(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TVShow> getTVShowById(@PathVariable Long id) {
		Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
		return tvShow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/add")
	public TVShow addTvShow(@RequestBody TVShow tvShow) {
		return tvShowService.addTVShow(tvShow);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TVShow> updateTVShow(@PathVariable Long id, @RequestBody TVShow tvShowDetails) {
		Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
		if (tvShow.isPresent()) {
			tvShowDetails.setId(id);
			return ResponseEntity.ok(tvShowService.addTVShow(tvShowDetails));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTVShow(@PathVariable Long id) {
		tvShowService.deleteTVShow(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public Page<TVShow> searchTVShows(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return tvShowService.searchTVShows(title, pageable);
	}

}