package com.scalefocus.amdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.amdb.service.TvShowService;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

	private final TvShowService tvShowService;

	@Autowired
	public TvShowController(TvShowService tvShowService) {
		this.tvShowService = tvShowService;
	}

	@GetMapping("/list")
	public String getAllTvShows() {
		return tvShowService.getAllTvShows();
	}

	@PostMapping("/add")
	public String addTvShow() {
		return tvShowService.addTvShow();
	}

	@DeleteMapping("/delete/{id}")
	public String  deleteTvShow(@PathVariable Long id) {
		return tvShowService.deleteTvShow(id);
	}
}