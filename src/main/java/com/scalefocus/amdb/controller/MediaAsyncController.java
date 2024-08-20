package com.scalefocus.amdb.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.amdb.client.SimulatedApiClient;
import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.dto.TVShowDto;

@RestController
@RequestMapping("/media")
public class MediaAsyncController {

	private final SimulatedApiClient simulatedApiClient;

	public MediaAsyncController(SimulatedApiClient simulatedApiClient) {
		this.simulatedApiClient = simulatedApiClient;
	}

	@GetMapping("/titles")
	public List<String> getAllTitles() {
		CompletableFuture<List<String>> tvShowsFuture = CompletableFuture.supplyAsync(() -> {
			return simulatedApiClient.getTVShowsFromApi()
				.stream()
				.map(TVShowDto::getTitle)
				.collect(Collectors.toList());
		})
			.completeOnTimeout(Collections.emptyList(), 4, TimeUnit.SECONDS)
			.exceptionally(ex -> {
				System.err.println("Error fetching TV shows: " + ex.getMessage());
				return Collections.emptyList();
			});

		CompletableFuture<List<String>> moviesFuture = CompletableFuture.supplyAsync(() -> {
			return simulatedApiClient.getMoviesFromApi()
				.stream()
				.map(MovieDto::getTitle)
				.collect(Collectors.toList());
		})
			.completeOnTimeout(Collections.emptyList(), 4, TimeUnit.SECONDS)
			.exceptionally(ex -> {				
				System.err.println("Error fetching movies: " + ex.getMessage());
				return Collections.emptyList();
			});

		return Stream.concat(
			tvShowsFuture.join().stream(),
			moviesFuture.join().stream()).collect(Collectors.toList());
	}

}