package com.scalefocus.amdb.dto;

import java.util.List;
import java.util.Objects;

public class TVSeasonDto {

	private Long id;

	private Integer number;

	private List<TVEpisodeDto> episodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<TVEpisodeDto> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<TVEpisodeDto> episodes) {
		this.episodes = episodes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(episodes, id, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TVSeasonDto))
			return false;
		TVSeasonDto other = (TVSeasonDto) obj;
		return Objects.equals(episodes, other.episodes)
				&& Objects.equals(id, other.id)
				&& Objects.equals(number, other.number);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVSeasonDto [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", episodes=");
		builder.append(episodes);
		builder.append("]");
		return builder.toString();
	}

}