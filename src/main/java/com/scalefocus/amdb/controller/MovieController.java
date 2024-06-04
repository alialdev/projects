package com.scalefocus.amdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.amdb.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    // @Autowired - Field Injection
    // private MovieService movieService;

    // @Autowired - Setter Injection
    // public void setMovieService(MovieService movieService) {
    //     this.movieService = movieService;
    // }
    
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @GetMapping("/list")  
    public String getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/add")  
    public String addMovie() {
        return movieService.addMovie();
    }
    
	@DeleteMapping("/delete/{id}")
	public String deleteMovie(@PathVariable Long id) {
		return movieService.deleteMovie(id);
	}

    
}