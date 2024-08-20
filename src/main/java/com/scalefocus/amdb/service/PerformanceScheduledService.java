package com.scalefocus.amdb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.MovieRepository;
import com.scalefocus.amdb.repository.TVShowRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceScheduledService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TVShowRepository tvShowRepository;

    @Scheduled(fixedRate = 30 * 1000) // Every 30 seconds
    public void printPerformanceDifference() {
        Instant start = Instant.now();
        double sequentialAverageRating = averageRatingSequentially();
        Instant endSequential = Instant.now();
        Duration durationSequential = Duration.between(start, endSequential);

        Instant startParallel = Instant.now();
        double parallelAverageRating = averageRatingInParallel();
        Instant endParallel = Instant.now();
        Duration durationParallel = Duration.between(startParallel, endParallel);

        System.out.println("Sequential Average Rating: " + sequentialAverageRating);
        System.out.println("Sequential Processing Time: " + durationSequential.toMillis() + " milliseconds");

        System.out.println("Parallel Average Rating: " + parallelAverageRating);
        System.out.println("Parallel Processing Time: " + durationParallel.toMillis() + " milliseconds");
        
        System.out.println("The Difference: " + (durationSequential.toMillis() - durationParallel.toMillis() + " milliseconds"));
        
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

        totalRating =  totalRating + tvShows.parallelStream()
            .mapToDouble(TVShow::getRating)
            .sum();

        int totalCount = movies.size() + tvShows.size();

        return totalRating / totalCount;
    }
}