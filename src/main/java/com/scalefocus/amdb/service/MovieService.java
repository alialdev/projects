package com.scalefocus.amdb.service;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

    public String getAllMovies() {
        return "Service: List of all movies";
    }

    public String addMovie() {
        return "Service: New movie is added";
    }
    
    public String deleteMovie(Long id) {
		return "Service: Movie with id: " + id + " is deleted";
	}
}