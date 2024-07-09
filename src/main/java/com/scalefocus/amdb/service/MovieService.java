package com.scalefocus.amdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.mapper.MovieMapper;
import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieMapper movieMapper;

	public Page<MovieDto> getAllMovies(Pageable pageable) {
		Page<Movie> movies = movieRepository.findAll(pageable);
		return movies.map(movieMapper::movieToMovieDto);
	}

	public Optional<MovieDto> getMovieById(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return movie.map(movieMapper::movieToMovieDto);
	}

	public MovieDto addMovie(Movie movie) {
		Movie savedMovie = movieRepository.save(movie);
		return movieMapper.movieToMovieDto(savedMovie);
	}

	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}

	public Page<MovieDto> searchMovies(String title, Pageable pageable) {
		Page<Movie> movies = movieRepository.findByTitleContaining(title, pageable);
		return movies.map(movieMapper::movieToMovieDto);
	}

	public Optional<MovieDto> updateMovie(Long id, Movie movieDetails) {
		Optional<Movie> movie = movieRepository.findById(id);
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

			MovieDto updatedMovie = addMovie(existingMovie);
			return Optional.of(updatedMovie);
		}
		return Optional.empty();
	}

}