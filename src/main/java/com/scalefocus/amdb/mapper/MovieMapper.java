package com.scalefocus.amdb.mapper;

import org.mapstruct.Mapper;

import com.scalefocus.amdb.dto.MovieDto;
import com.scalefocus.amdb.model.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

	MovieDto movieToMovieDto(Movie movie);

	Movie movieDtoToMovie(MovieDto movieDto);

}