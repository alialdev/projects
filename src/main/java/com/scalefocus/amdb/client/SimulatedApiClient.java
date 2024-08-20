package com.scalefocus.amdb.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TVShowRepository tvShowRepository;

	@Autowired
	private MovieMapper movieMapper;

	@Autowired
	private TVShowMapper tvShowMapper;

	// Method to simulate insertion of movies from API
	public void insertMoviesFromApi() {
		List<MovieDto> movies = getMoviesFromApi();

		for (MovieDto movieDto : movies) {
			try {
				// Thread.sleep(2000);
				TimeUnit.SECONDS.sleep(2); // More readable and flexible
			} catch (InterruptedException e) {
				// It’s recommended to call Thread.currentThread().interrupt() to allow higher-level methods 
				//or thread management systems to detect and handle the
				// interrupt properly.
				Thread.currentThread().interrupt();
				throw new RuntimeException("Sleep interrupted", e);
			}

			Movie movie = movieMapper.movieDtoToMovie(movieDto);
			Optional<Movie> existingMovie = movieRepository.findByImdbId(movie.getImdbId());
			existingMovie.ifPresent(m -> movieRepository.deleteById(m.getId()));

			// Save the movie
			movieRepository.save(movie);

			System.out.println("Inserted Movie: " + movie.getTitle());
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

			if (tvShowRepository.findByImdbId(tvShow.getImdbId()) != null) {
				tvShowRepository.deleteByImdbId(tvShow.getImdbId());
			}

			tvShowRepository.save(tvShow);
			System.out.println("Inserted TV Show: " + tvShow.getTitle());
		}
	}

	public List<MovieDto> getMoviesFromApi() {

		List<MovieDto> movies = new ArrayList<>();
		movies.add(createMovieDto(7L,
			"Inception",
			"Dream within a dream within a dream within a dreadm.",
			8.8,
			LocalDate.of(2010, 7, 16),
			"Christopher Nolan",
			"Christopher Nolan",
			"Leonardo D",
			148,
			"tt1375666",
			2010,
			Set.of(createGenreDto(1L, "Action"))));
		
		movies.add(createMovieDto(8L,
			"The Matrix",
			"Blue Pill or Red Pill",
			8.7,
			LocalDate.of(1999, 3, 31),
			"Wachowski's",
			"Wachowski's",
			"John Wick, Morpheus",
			136,
			"tt0133093",
			1999,
			Set.of(createGenreDto(2L, "Sci-Fi"))));
		
		movies.add(createMovieDto(9L,
			"Interstellar",
			"Good Movie, Lame Ending",
			8.6,
			LocalDate.of(2014, 11, 7),
			"Christopher Nolan",
			"Nolan Brothers",
			"That guy, that women",
			169,
			"tt0816692",
			2014,
			Set.of(createGenreDto(3L, "Drama"))));

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
		
		TVSeasonDto season1 = createTVSeasonDto(1L,
			1,
			List.of(createTVEpisodeDto(1L,
				"Pilot",
				"Sherlock Holmes.",
				9.0,
				LocalDate.of(2010, 7, 25),
				"Some British Dude",
				"Other British Dude",
				"Benedict Cucumber",
				88,
				"tt1877051",
				2010,
				1)));
		
		TVSeasonDto season2 = createTVSeasonDto(2L,
			2,
			List.of(createTVEpisodeDto(2L,
				"The Pilot",
				"Welcome to post-apocalypse.",
				8.8,
				LocalDate.of(2016, 7, 2),
				"N.C",
				"N.C",
				"S.P",
				60,
				"tt5435294",
				2016,
				2)));
		
		tvShows.add(createTVShowDto(7L,
			"Sherlock",
			"Sherlock Holmes stories.",
			9.1,
			LocalDate.of(2010, 7, 25),
			"Some British Dude",
			"Other British Dude",
			"Benedict Cucumber",
			60,
			"tt2076687",
			2010,
			List.of(season1),
			Set.of(createGenreDto(1L, "Drama"))));
		
		tvShows.add(createTVShowDto(8L,
			"Westworld",
			"A.I gone mad.",
			8.6,
			LocalDate.of(2016, 10, 2),
			"JN",
			"JN",
			"ERW",
			60,
			"tt0475784",
			2016,
			List.of(season2),
			Set.of(createGenreDto(2L, "Sci-Fi"))));
		
		tvShows.add(createTVShowDto(9L,
			"The Crown",
			"Queen Elizabeth alives",
			8.7,
			LocalDate.of(2016, 11, 4),
			"Someone",
			"Someone",
			"Someone",
			58,
			"tt5555260",
			2016,
			List.of(),
			Set.of(createGenreDto(3L, "History"))));

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Sleep interrupted", e);
		}

		return tvShows;
	}

	private GenreDto createGenreDto(Long id, String name) {
		GenreDto genre = new GenreDto();
		genre.setId(id);
		genre.setName(name);
		return genre;
	}

	// Helper method to create MovieDto
	private MovieDto createMovieDto(Long id,
			String title,
			String description,
			Double rating,
			LocalDate releaseDate,
			String director,
			String writer,
			String stars,
			Integer duration,
			String imdbId,
			Integer year,
			Set<GenreDto> genres) {
		MovieDto movieDto = new MovieDto();
		movieDto.setId(id);
		movieDto.setTitle(title);
		movieDto.setDescription(description);
		movieDto.setRating(rating);
		movieDto.setReleaseDate(releaseDate);
		movieDto.setDirector(director);
		movieDto.setWriter(writer);
		movieDto.setStars(stars);
		movieDto.setDuration(duration);
		movieDto.setImdbId(imdbId);
		movieDto.setYear(year);
		movieDto.setGenres(genres);
		return movieDto;
	}

	private TVShowDto createTVShowDto(Long id,
			String title,
			String description,
			Double rating,
			LocalDate releaseDate,
			String director,
			String writer,
			String stars,
			Integer duration,
			String imdbId,
			Integer year,
			List<TVSeasonDto> seasons,
			Set<GenreDto> genres) {
		TVShowDto show = new TVShowDto();
		show.setId(id);
		show.setTitle(title);
		show.setDescription(description);
		show.setRating(rating);
		show.setReleaseDate(releaseDate);
		show.setDirector(director);
		show.setWriter(writer);
		show.setStars(stars);
		show.setDuration(duration);
		show.setImdbId(imdbId);
		show.setYear(year);
		show.setSeasons(seasons);
		show.setGenres(genres);
		return show;
	}

	private TVSeasonDto createTVSeasonDto(Long id, Integer number, List<TVEpisodeDto> episodes) {
		TVSeasonDto season = new TVSeasonDto();
		season.setId(id);
		season.setNumber(number);
		season.setEpisodes(episodes);
		return season;
	}

	private TVEpisodeDto createTVEpisodeDto(Long id,
			String title,
			String description,
			Double rating,
			LocalDate releaseDate,
			String director,
			String writer,
			String stars,
			Integer duration,
			String imdbId,
			Integer year,
			Integer number) {
		TVEpisodeDto episode = new TVEpisodeDto();
		episode.setId(id);
		episode.setTitle(title);
		episode.setDescription(description);
		episode.setRating(rating);
		episode.setReleaseDate(releaseDate);
		episode.setDirector(director);
		episode.setWriter(writer);
		episode.setStars(stars);
		episode.setDuration(duration);
		episode.setImdbId(imdbId);
		episode.setYear(year);
		episode.setNumber(number);
		return episode;
	}

}