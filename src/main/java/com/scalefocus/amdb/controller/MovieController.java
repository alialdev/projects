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

	@GetMapping("/list")
	public ResponseEntity<Page<MovieDto>> getAllMovies(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MovieDto> result = movieService.getAllMovies(pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
		Optional<MovieDto> movie = movieService.getMovieById(id);

		return movie.isPresent() ? ResponseEntity.ok(movie.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<MovieDto> addMovie(@RequestBody Movie movie) {
		MovieDto savedMovie = movieService.addMovie(movie);
		
		return ResponseEntity.ok(savedMovie);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
		Optional<MovieDto> updatedMovie = movieService.updateMovie(id, movieDetails);
		
		return updatedMovie.isPresent() ? ResponseEntity.ok(updatedMovie.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Page<MovieDto>> searchMovies(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MovieDto> result = movieService.searchMovies(title, pageable);

		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

}