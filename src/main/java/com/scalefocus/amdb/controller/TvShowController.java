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
	public Optional<TVShow> getTVShowById(@PathVariable Long id) {
		return tvShowService.getTVShowById(id);

	}

	@PostMapping("/add")
	public TVShow addTvShow(@RequestBody TVShow tvShow) {
		return tvShowService.addTVShow(tvShow);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TVShow> updateTVShow(@PathVariable Long id, @RequestBody TVShow tvShowDetails) {
		Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
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

			if (tvShowDetails.getSeasons() != null) {
				existingTVShow.setSeasons(tvShowDetails.getSeasons());
			}
			if (tvShowDetails.getGenres() != null) {
				existingTVShow.setGenres(tvShowDetails.getGenres());
			}

			existingTVShow.setId(id);

			return ResponseEntity.ok(tvShowService.addTVShow(existingTVShow));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTVShow(@PathVariable Long id) {
		tvShowService.deleteTVShow(id);
	}

	@GetMapping("/search")
	public Page<TVShow> searchTVShows(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return tvShowService.searchTVShows(title, pageable);
	}

}