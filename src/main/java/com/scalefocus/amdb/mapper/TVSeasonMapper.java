package com.scalefocus.amdb.mapper;

import org.mapstruct.Mapper;

import com.scalefocus.amdb.dto.TVSeasonDto;
import com.scalefocus.amdb.model.TVSeason;

@Mapper(componentModel = "spring", uses = TVEpisodeMapper.class)
public interface TVSeasonMapper {

	TVSeasonDto tvSeasonToTVSeasonDto(TVSeason tvSeason);

	TVSeason tvSeasonDtoToTvSeason(TVSeasonDto tvSeasonDto);

}