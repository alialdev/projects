package com.scalefocus.amdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.repository.MovieRepository;


@Service
public class MovieScheduledService {

    @Autowired
    private MovieRepository movieRepository;

    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void printMovieInfo() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Number of movies: " + movies.size());
        movies.forEach(movie -> System.out.println("Title: " + movie.getTitle()));
    }
}