package com.scalefocus.amdb.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TVShowScheduledService {

	private static final Logger logger = LoggerFactory.getLogger(TVShowScheduledService.class);

	private final TVShowRepository tvShowRepository;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Autowired
	public TVShowScheduledService(TVShowRepository tvShowRepository) {
		this.tvShowRepository = tvShowRepository;
	}

	@PostConstruct
	public void startScheduledTasks() {
		scheduler.scheduleAtFixedRate(this::printTVShowInfo, 0, 2, TimeUnit.MINUTES);
	}

	private void printTVShowInfo() {
		List<TVShow> tvShows = tvShowRepository.findAll();
		logger.info("Number of TV shows: {}", tvShows.size());
		tvShows.forEach(tvShow -> logger.info("Title: {}", tvShow.getTitle()));
	}

}