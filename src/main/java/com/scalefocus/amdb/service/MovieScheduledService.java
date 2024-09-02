package com.scalefocus.amdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.repository.MovieRepository;

@Service
public class MovieScheduledService {

	private static final Logger logger = LoggerFactory.getLogger(MovieScheduledService.class);

	private final MovieRepository movieRepository;

	@Autowired
	public MovieScheduledService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Scheduled(fixedRate = 2 * 60 * 1000)
	public void printMovieInfo() {

		List<Movie> movies = movieRepository.findAll();

		logger.info("Number of movies: {}", movies.size());

		movies.forEach(movie -> logger.info("Title: {}", movie.getTitle()));
	}

}