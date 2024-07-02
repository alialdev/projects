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
	public Page<Movie> getAllMovies(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return movieService.getAllMovies(pageable);
	}

	@GetMapping("/{id}")
	public Optional<Movie> getMovieById(@PathVariable Long id) {
		return movieService.getMovieById(id);
	
	}

	@PostMapping("/add")
	public Movie addMovie(@RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
		Optional<Movie> movie = movieService.getMovieById(id);
		if (movie.isPresent()) {
			Movie existingMovie = movie.get();
	    
            if (movieDetails.getTitle() != null) {
                existingMovie.setTitle(movieDetails.getTitle());
            }
            if (movieDetails.getDescription() != null) {
                existingMovie.setDescription(movieDetails.getDescription());
            }
            if (movieDetails.getRating() != null) {
                existingMovie.setRating(movieDetails.getRating());
            }
            if (movieDetails.getReleaseDate() != null) {
                existingMovie.setReleaseDate(movieDetails.getReleaseDate());
            }
            if (movieDetails.getDirector() != null) {
                existingMovie.setDirector(movieDetails.getDirector());
            }
            if (movieDetails.getWriter() != null) {
                existingMovie.setWriter(movieDetails.getWriter());
            }
            if (movieDetails.getStars() != null) {
                existingMovie.setStars(movieDetails.getStars());
            }
            if (movieDetails.getDuration() != null) {
                existingMovie.setDuration(movieDetails.getDuration());
            }
            if (movieDetails.getImdbId() != null) {
                existingMovie.setImdbId(movieDetails.getImdbId());
            }
            if (movieDetails.getYear() != null) {
                existingMovie.setYear(movieDetails.getYear());
            }
            existingMovie.setId(id);
			return ResponseEntity.ok(movieService.addMovie(existingMovie));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}

	@GetMapping("/search")
	public Page<Movie> searchMovies(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return movieService.searchMovies(title, pageable);
	}

}