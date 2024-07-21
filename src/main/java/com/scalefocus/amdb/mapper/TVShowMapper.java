package com.scalefocus.amdb.mapper;

import org.mapstruct.Mapper;

import com.scalefocus.amdb.dto.TVShowDto;
import com.scalefocus.amdb.model.TVShow;

@Mapper(componentModel = "spring", uses = {
		TVSeasonMapper.class, TVEpisodeMapper.class
})
public interface TVShowMapper {

	TVShowDto tvShowToTVShowDto(TVShow tvShow);

	TVShow tvShowDtoToTVShow(TVShowDto tvShowDto);

}