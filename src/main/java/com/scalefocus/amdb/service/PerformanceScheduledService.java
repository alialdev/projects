package com.scalefocus.amdb.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.MovieRepository;
import com.scalefocus.amdb.repository.TVShowRepository;

@Service
public class PerformanceScheduledService {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceScheduledService.class);

	private final MovieRepository movieRepository;

	private final TVShowRepository tvShowRepository;

	@Autowired
	public PerformanceScheduledService(MovieRepository movieRepository, TVShowRepository tvShowRepository) {
		this.movieRepository = movieRepository;
		this.tvShowRepository = tvShowRepository;
	}

	@Scheduled(fixedRate = 90 * 1000) // Every 30 seconds
	public void printPerformanceDifference() {
		Instant start = Instant.now();
		double sequentialAverageRating = averageRatingSequentially();
		Instant endSequential = Instant.now();
		Duration durationSequential = Duration.between(start, endSequential);

		Instant startParallel = Instant.now();
		double parallelAverageRating = averageRatingInParallel();
		Instant endParallel = Instant.now();
		Duration durationParallel = Duration.between(startParallel, endParallel);

		logger.info("Sequential Average Rating: {}", sequentialAverageRating);
		logger.info("Sequential Processing Time: {} milliseconds", durationSequential.toMillis());

		logger.info("Parallel Average Rating: {}", parallelAverageRating);
		logger.info("Parallel Processing Time: {} milliseconds", durationParallel.toMillis());

		logger.info("The Difference: {} milliseconds", (durationSequential.toMillis() - durationParallel.toMillis()));

	}

	private double averageRatingSequentially() {
		List<Movie> movies = movieRepository.findAll();
		List<TVShow> tvShows = tvShowRepository.findAll();

		double totalRating = movies.stream()
			.mapToDouble(Movie::getRating)
			.sum();

		totalRating = totalRating + tvShows.stream()
			.mapToDouble(TVShow::getRating)
			.sum();

		int totalCount = movies.size() + tvShows.size();

		return totalRating / totalCount;
	}

	private double averageRatingInParallel() {
		List<Movie> movies = movieRepository.findAll();
		List<TVShow> tvShows = tvShowRepository.findAll();

		double totalRating = movies.parallelStream()
			.mapToDouble(Movie::getRating)
			.sum();

		totalRating = totalRating + tvShows.parallelStream()
			.mapToDouble(TVShow::getRating)
			.sum();

		int totalCount = movies.size() + tvShows.size();

		return totalRating / totalCount;
	}

}