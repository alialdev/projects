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

import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	// @Autowired - Field Injection
	// private MovieService movieService;

	// @Autowired - Setter Injection
	// public void setMovieService(MovieService movieService) {
	// this.movieService = movieService;
	// }

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping()
	public ResponseEntity<Page<MovieDto>> getAllMovies(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MovieDto> result = movieService.getAll(pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieDto> getMovie(@PathVariable String id) {
		Optional<MovieDto> movie;

		try {
			Long movieId = Long.parseLong(id);
			movie = movieService.get(movieId);
		} catch (NumberFormatException e) {
			movie = movieService.get(id);
		}

		return movie.isPresent() ? ResponseEntity.ok(movie.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping()
	public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie) {
		MovieDto savedMovie = movieService.add(movie);

		return ResponseEntity.ok(savedMovie);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDetails) {
		Optional<MovieDto> updatedMovie = movieService.update(id, movieDetails);

		return updatedMovie.isPresent() ? ResponseEntity.ok(updatedMovie.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{imdbId}")
	public ResponseEntity<Void> deleteTVShow(@PathVariable String imdbId) {
		movieService.delete(imdbId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteMovie(@RequestBody MovieDto movie) {
		movieService.delete(movie);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Page<MovieDto>> searchMovies(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MovieDto> result = movieService.searchByTitle(title, pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/toprated")
	public ResponseEntity<List<MovieDto>> getTopRatedMovies(@RequestParam(defaultValue = "5") int limit) {
		List<MovieDto> topRatedMovies = movieService.getTopRated(limit);
		return topRatedMovies.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(topRatedMovies);
	}

	@GetMapping("/bydirector")
	public ResponseEntity<List<MovieDto>> getMoviesByDirector(@RequestParam String director) {
		List<MovieDto> moviesByDirector = movieService.getByDirector(director);
		return moviesByDirector.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(moviesByDirector);
	}

	@GetMapping("/byyearrange")
	public ResponseEntity<List<MovieDto>> getMoviesByYearRange(@RequestParam int startYear, @RequestParam int endYear) {
		List<MovieDto> moviesByYearRange = movieService.getByYearRange(startYear, endYear);
		return moviesByYearRange.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(moviesByYearRange);
	}

	@GetMapping("/bygenreandtitle")
	public ResponseEntity<List<MovieDto>> getMoviesByGenreAndTitleStartingWith(@RequestParam String genre,
			@RequestParam Optional<String> titleStart) {
		List<MovieDto> moviesByGenreAndTitle = movieService.getByGenreAndTitleStartingWith(genre, titleStart);
		return moviesByGenreAndTitle.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(moviesByGenreAndTitle);
	}
	
    @PostMapping("/import")
    public ResponseEntity<String> importMovies() {
        movieService.insertMoviesFromApiAsync();
        return ResponseEntity.ok("Movies import started and will be processed in the background");
    }

}