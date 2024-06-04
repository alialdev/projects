package com.scalefocus.amdb.service;

import org.springframework.stereotype.Service;

@Service
public class TvShowService {

	public String getAllTvShows() {
		return "Service: List of all TV shows";
	}

	public String addTvShow() {
		return "Service: New TV Show is added";
	}

	public String deleteTvShow(Long id) {
		return "Service: TV show with id: " + id + " is deleted";
	}
}