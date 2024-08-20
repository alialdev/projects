package com.scalefocus.amdb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.TVShowRepository;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TVShowScheduledService {

    @Autowired
    private TVShowRepository tvShowRepository;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void startScheduledTasks() {
        scheduler.scheduleAtFixedRate(this::printTVShowInfo, 0, 2, TimeUnit.MINUTES);
    }

    private void printTVShowInfo() {
        List<TVShow> tvShows = tvShowRepository.findAll();
        System.out.println("Number of TV shows: " + tvShows.size());
        tvShows.forEach(tvShow -> System.out.println("Title: " + tvShow.getTitle()));
    }
}