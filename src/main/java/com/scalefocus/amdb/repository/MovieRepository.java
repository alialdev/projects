package com.scalefocus.amdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.amdb.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Page<Movie> findByTitleContaining(String title, Pageable pageable);

	Optional<Movie> findByImdbId(String imdbId);

	List<Movie> deleteByImdbId(String imdbId);

}