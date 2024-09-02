package com.scalefocus.amdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.amdb.model.TVShow;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {

	Page<TVShow> findByTitleContaining(String title, Pageable pageable);

	Optional<TVShow> findByImdbId(String imdbId);

	List<TVShow> deleteByImdbId(String imdbId);

}
