package com.scalefocus.amdb.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scalefocus.amdb.client.SimulatedApiClient;
import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.mapper.MovieMapper;
import com.scalefocus.amdb.model.Movie;
import com.scalefocus.amdb.repository.MovieRepository;

@Service
public class MovieService implements MediaService<MovieDto> {

	private MovieRepository movieRepository;

	private MovieMapper movieMapper;

	private SimulatedApiClient simulatedApiClient;

	@Autowired
	public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
		this.movieRepository = movieRepository;
		this.movieMapper = movieMapper;
	}

	@Autowired
	public void setSimulatedApiClient(SimulatedApiClient simulatedApiClient) {
		this.simulatedApiClient = simulatedApiClient;
	}

	public Page<MovieDto> getAll(Pageable pageable) {
		Page<Movie> movies = movieRepository.findAll(pageable);
		return movies.map(movieMapper::movieToMovieDto);
	}

	@Override
	public Optional<MovieDto> get(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return movie.map(movieMapper::movieToMovieDto);
	}

	public Optional<MovieDto> get(String imdbId) {
		Optional<Movie> movie = movieRepository.findByImdbId(imdbId);
		return movie.map(movieMapper::movieToMovieDto);
	}

	@Override
	public MovieDto add(MovieDto movieDto) {
		Movie movie = movieMapper.movieDtoToMovie(movieDto);
		Movie savedMovie = movieRepository.save(movie);
		return movieMapper.movieToMovieDto(savedMovie);
	}

	@Override
	public Page<MovieDto> searchByTitle(String title, Pageable pageable) {
		Page<Movie> movies = movieRepository.findByTitleContaining(title, pageable);
		return movies.map(movieMapper::movieToMovieDto);
	}

	@Override
	public Optional<MovieDto> update(Long id, MovieDto movieDetails) {
		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			Movie existingMovie = movie.get();

			if (movieDetails.getTitle() != null) {
				existingMovie.setTitle(movieDetails.getTitle());
			}
			if (movieDetails.getDescription() != null) {
				existingMovie.setDescription(movieDetails.getDescription());
			}
			if (movieDetails.getRating() != null) {
				existingMovie.setRating(movieDetails.getRating());
			}
			if (movieDetails.getReleaseDate() != null) {
				existingMovie.setReleaseDate(movieDetails.getReleaseDate());
			}
			if (movieDetails.getDirector() != null) {
				existingMovie.setDirector(movieDetails.getDirector());
			}
			if (movieDetails.getWriter() != null) {
				existingMovie.setWriter(movieDetails.getWriter());
			}
			if (movieDetails.getStars() != null) {
				existingMovie.setStars(movieDetails.getStars());
			}
			if (movieDetails.getDuration() != null) {
				existingMovie.setDuration(movieDetails.getDuration());
			}
			if (movieDetails.getImdbId() != null) {
				existingMovie.setImdbId(movieDetails.getImdbId());
			}
			if (movieDetails.getYear() != null) {
				existingMovie.setYear(movieDetails.getYear());
			}

			Movie updatedMovie = movieRepository.save(existingMovie);

			MovieDto updatedMovieDto = movieMapper.movieToMovieDto(updatedMovie);

			return Optional.of(updatedMovieDto);
		}
		return Optional.empty();
	}

	@Override
	public void delete(MovieDto movieDto) {
		Movie movie = movieMapper.movieDtoToMovie(movieDto);
		movieRepository.delete(movie);
	}

	@Transactional
	public void delete(String imdbId) {
		movieRepository.deleteByImdbId(imdbId);
	}

	@Override
	public List<MovieDto> getTopRated(int limit) {
		return movieRepository.findAll()
			.stream()
			.sorted(Comparator.comparing(Movie::getRating).reversed())
			.limit(limit)
			.map(movieMapper::movieToMovieDto)
			.collect(Collectors.toList());
	}

	@Override
	public List<MovieDto> getByDirector(String director) {
		return movieRepository.findAll()
			.stream()
			.filter(movie -> director.equals(movie.getDirector()))
			.map(movieMapper::movieToMovieDto)
			.collect(Collectors.toList());
	}

	@Override
	public List<MovieDto> getByYearRange(int startYear, int endYear) {
		return movieRepository.findAll()
			.stream()
			.filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
			.map(movieMapper::movieToMovieDto)
			.collect(Collectors.toList());
	}

	public List<MovieDto> getByGenreAndTitleStartingWith(String genre, Optional<String> titleStartsWith) {

		BiFunction<Movie, Optional<String>, Boolean> filterByTitleStartingWith = (movie, firstLetter) -> firstLetter
			.map(
				letter -> movie.getTitle().startsWith(letter))
			.orElse(true);
		return movieRepository.findAll()
			.stream()
			.filter(movie -> movie.getGenres().stream().anyMatch(gnr -> gnr.getName().equals(genre))
					&& filterByTitleStartingWith.apply(movie, titleStartsWith))
			.map(movieMapper::movieToMovieDto)
			.collect(Collectors.toList());

	}

	@Async
	public void insertMoviesFromApiAsync() {
		simulatedApiClient.insertMoviesFromApi();
	}

}
