package com.scalefocus.amdb.mapper;

import org.mapstruct.Mapper;

import com.scalefocus.amdb.dto.TVEpisodeDto;
import com.scalefocus.amdb.model.TVEpisode;

@Mapper(componentModel = "spring")
public interface TVEpisodeMapper {

	TVEpisodeDto tvEpisodeToTVEpisodeDto(TVEpisode tvEpisode);

	TVEpisode tvEpisodeDtoToTvEpisode(TVEpisodeDto tvEpisodeDto);

}