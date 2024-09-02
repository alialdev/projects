package com.scalefocus.amdb.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scalefocus.amdb.dto.GenreDto;
import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.dto.TVEpisodeDto;
import com.scalefocus.amdb.dto.TVSeasonDto;
import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.mapper.MovieMapper;
import com.scalefocus.amdb.mapper.TVShowMapper;
import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.model.TVShow;
import com.scalefocus.amdb.repository.MovieRepository;
import com.scalefocus.amdb.repository.TVShowRepository;

@Component
public class SimulatedApiClient {

	private static final Logger logger = LoggerFactory.getLogger(SimulatedApiClient.class);

	private final MovieRepository movieRepository;

	private final TVShowRepository tvShowRepository;

	private final MovieMapper movieMapper;

	private final TVShowMapper tvShowMapper;

	// Constructor for dependency injection
	@Autowired
	public SimulatedApiClient(MovieRepository movieRepository,
			TVShowRepository tvShowRepository,
			MovieMapper movieMapper,
			TVShowMapper tvShowMapper) {
		this.movieRepository = movieRepository;
		this.tvShowRepository = tvShowRepository;
		this.movieMapper = movieMapper;
		this.tvShowMapper = tvShowMapper;
	}

	// Method to simulate insertion of movies from API
	public void insertMoviesFromApi() {
		List<MovieDto> movies = getMoviesFromApi();

		for (MovieDto movieDto : movies) {
			try {
				// Thread.sleep(2000);
				TimeUnit.SECONDS.sleep(2); // More readable and flexible
			} catch (InterruptedException e) {
				// It’s recommended to call Thread.currentThread().interrupt() to allow higher-level methods
				// or thread management systems to detect and handle the
				// interrupt properly.
				Thread.currentThread().interrupt();
				throw new RuntimeException("Sleep interrupted", e);
			}

			Movie movie = movieMapper.movieDtoToMovie(movieDto);
			Optional<Movie> existingMovie = movieRepository.findByImdbId(movie.getImdbId());
			existingMovie.ifPresent(m -> movieRepository.deleteById(m.getId()));

			// Save the movie
			movieRepository.save(movie);
			logger.info("Inserted Movie: {}", movie.getTitle());
		}
	}

	public void insertTVShowsFromApi() {
		List<TVShowDto> tvShows = getTVShowsFromApi();

		for (TVShowDto tvShowDto : tvShows) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException("Sleep interrupted", e);
			}

			TVShow tvShow = tvShowMapper.tvShowDtoToTVShow(tvShowDto);

			Optional<TVShow> existingTVShow = tvShowRepository.findByImdbId(tvShow.getImdbId());
			existingTVShow.ifPresent(m -> tvShowRepository.deleteByImdbId(m.getImdbId()));

			tvShowRepository.save(tvShow);
			logger.info("Inserted TV Show: {}", tvShow.getTitle());
		}
	}

	public List<MovieDto> getMoviesFromApi() {

		List<MovieDto> movies = new ArrayList<>();
		movies.add(new MovieDto.MovieDtoBuilder()
			.id(7L)
			.title("Inception")
			.description("Dream within a dream within a dream within a dreadm.")
			.rating(8.8)
			.releaseDate(LocalDate.of(2010, 7, 16))
			.director("Christopher Nolan")
			.writer("Christopher Nolan")
			.stars("Leonardo D")
			.duration(148)
			.imdbId("tt1375666")
			.year(2010)
			.genres(Set.of(new GenreDto.Builder().id(1L).name("Action").build()))
			.build());

		movies.add(new MovieDto.MovieDtoBuilder()
			.id(8L)
			.title("The Matrix")
			.description("Blue Pill or Red Pill")
			.rating(8.7)
			.releaseDate(LocalDate.of(1999, 3, 31))
			.director("Wachowski's")
			.writer("Wachowski's")
			.stars("John Wick, Morpheus")
			.duration(136)
			.imdbId("tt0133093")
			.year(1999)
			.genres(Set.of(new GenreDto.Builder().id(2L).name("Sci-Fi").build()))
			.build());

		movies.add(new MovieDto.MovieDtoBuilder()
			.id(9L)
			.title("Interstellar")
			.description("Good Movie, Lame Ending")
			.rating(8.6)
			.releaseDate(LocalDate.of(2014, 11, 7))
			.director("Christopher Nolan")
			.writer("Nolan Brothers")
			.stars("That guy, that women")
			.duration(169)
			.imdbId("tt0816692")
			.year(2014)
			.genres(Set.of(new GenreDto.Builder().id(3L).name("Drama").build()))
			.build());

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Sleep interrupted", e);
		}

		return movies;

	}

	public List<TVShowDto> getTVShowsFromApi() {

		List<TVShowDto> tvShows = new ArrayList<>();

		TVSeasonDto season1 = new TVSeasonDto.TVSeasonDtoBuilder()
			.id(1L)
			.number(1)
			.episodes(List.of(new TVEpisodeDto.TVEpisodeDtoBuilder()
				.id(1L)
				.title("Pilot")
				.description("Sherlock Holmes.")
				.rating(9.0)
				.releaseDate(LocalDate.of(2010, 7, 25))
				.director("Some British Dude")
				.writer("Other British Dude")
				.stars("Benedict Cucumber")
				.duration(88)
				.imdbId("tt1877051")
				.year(2010)
				.number(1)
				.build()))
			.build();

		TVSeasonDto season2 = new TVSeasonDto.TVSeasonDtoBuilder()
			.id(2L)
			.number(2)
			.episodes(List.of(new TVEpisodeDto.TVEpisodeDtoBuilder()
				.id(2L)
				.title("The Pilot")
				.description("Welcome to post-apocalypse.")
				.rating(8.8)
				.releaseDate(LocalDate.of(2016, 7, 2))
				.director("N.C")
				.writer("N.C")
				.stars("S.P")
				.duration(60)
				.imdbId("tt1234567")
				.year(2016)
				.number(2)
				.build()))
			.build();

		tvShows.add(new TVShowDto.TVShowDtoBuilder()
			.id(7L)
			.title("Sherlock")
			.description("Modern-day Sherlock Holmes.")
			.rating(9.1)
			.releaseDate(LocalDate.of(2010, 7, 25))
			.director("Some British Dude")
			.writer("Other British Dude")
			.stars("Benedict Cucumber")
			.duration(60)
			.imdbId("tt2076687")
			.year(2010)
			.seasons(List.of(season1, season2))
			.genres(Set.of(new GenreDto.Builder().id(3L).name("Drama").build()))
			.build());

		tvShows.add(new TVShowDto.TVShowDtoBuilder()
			.id(8L)
			.title("Westworld")
			.description("A.I gone mad.")
			.rating(8.6)
			.releaseDate(LocalDate.of(2016, 10, 2))
			.director("JN")
			.writer("JN")
			.stars("ERW")
			.duration(60)
			.imdbId("tt047578")
			.year(2016)
			.seasons(List.of(season2))
			.genres(Set.of(new GenreDto.Builder().id(2L).name("Sci-Fi").build()))
			.build());

		tvShows.add(new TVShowDto.TVShowDtoBuilder()
			.id(9L)
			.title("The Crown")
			.description("Queen Elizabeth alives")
			.rating(8.7)
			.releaseDate(LocalDate.of(2016, 11, 4))
			.director("Someone")
			.writer("Someone")
			.stars("Someone")
			.duration(58)
			.imdbId("tt5555260")
			.year(2016)
			.seasons(List.of(season1))
			.genres(Set.of(new GenreDto.Builder().id(3L).name("Drama").build()))
			.build());

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Sleep interrupted", e);
		}

		return tvShows;
	}

}