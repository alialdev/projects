package com.scalefocus.amdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.repository.MovieRepository;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class MovieService {	
	
	@Autowired
	private MovieRepository movieRepository;


	public Page<Movie> getAllMovies(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	public Optional<Movie> getMovieById(Long id) {
		return movieRepository.findById(id);
	}

	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}

	public Page<Movie> searchMovies(String title, Pageable pageable) {
		return movieRepository.findByTitleContaining(title, pageable);
	}

}