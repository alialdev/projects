package com.scalefocus.amdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.amdb.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>  {

}
