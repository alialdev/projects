package com.scalefocus.amdb.controller;

import java.util.List;
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
public class TVShowController {

	private final TVShowService tvShowService;

	@Autowired
	public TVShowController(TVShowService tvShowService) {
		this.tvShowService = tvShowService;
	}

	@GetMapping()
	public ResponseEntity<Page<TVShowDto>> getAllTVShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<TVShowDto> result = tvShowService.getAll(pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TVShowDto> getTVShow(@PathVariable String id) {
		Optional<TVShowDto> tvShow;

		try {
			Long tvShowId = Long.parseLong(id);
			tvShow = tvShowService.get(tvShowId);
		} catch (NumberFormatException e) {
			tvShow = tvShowService.get(id);
		}

		return tvShow.isPresent() ? ResponseEntity.ok(tvShow.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping()
	public ResponseEntity<TVShowDto> addTVShow(@RequestBody TVShowDto tvShow) {
		TVShowDto savedTVShow = tvShowService.add(tvShow);

		return ResponseEntity.ok(savedTVShow);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TVShowDto> updateTVShow(@PathVariable Long id, @RequestBody TVShowDto tvShowDetails) {
		Optional<TVShowDto> updatedTVShow = tvShowService.update(id, tvShowDetails);

		return updatedTVShow.isPresent() ? ResponseEntity.ok(updatedTVShow.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{imdbId}")
	public ResponseEntity<Void> deleteTVShow(@PathVariable String imdbId) {
		tvShowService.delete(imdbId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteTVShow(@RequestBody TVShowDto tvShow) {
		tvShowService.delete(tvShow);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Page<TVShowDto>> searchTVShows(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<TVShowDto> result = tvShowService.searchByTitle(title, pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/toprated")
	public ResponseEntity<List<TVShowDto>> getTopRatedTVShows(@RequestParam(defaultValue = "5") int limit) {
		List<TVShowDto> topRatedTVShows = tvShowService.getTopRated(limit);
		return topRatedTVShows.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(topRatedTVShows);
	}

	@GetMapping("/bydirector")
	public ResponseEntity<List<TVShowDto>> getTVShowsByDirector(@RequestParam String director) {
		List<TVShowDto> tvShowsByDirector = tvShowService.getByDirector(director);
		return tvShowsByDirector.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tvShowsByDirector);
	}

	@GetMapping("/byyearrange")
	public ResponseEntity<List<TVShowDto>> getTVShowsByYearRange(@RequestParam int startYear,
			@RequestParam int endYear) {
		List<TVShowDto> tvShowsByYearRange = tvShowService.getByYearRange(startYear, endYear);
		return tvShowsByYearRange.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tvShowsByYearRange);
	}

	@GetMapping("/bygenreandtitle")
	public ResponseEntity<List<TVShowDto>> getTVShowsByGenreAndTitleStartingWith(@RequestParam String genre,
			@RequestParam Optional<String> titleStart) {
		List<TVShowDto> tvShowsByGenreAndTitle = tvShowService.getByGenreAndTitleStartingWith(genre, titleStart);
		return tvShowsByGenreAndTitle.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(tvShowsByGenreAndTitle);
	}
	
    @PostMapping("/import")
    public ResponseEntity<String> importTVShows() {
        tvShowService.insertTvShowsFromApiAsync();
        return ResponseEntity.ok("TV shows import started and will be processed in the background.");
    }

}