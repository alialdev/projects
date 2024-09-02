package com.scalefocus.amdb.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TVShowDto extends MediaDto {

	private List<TVSeasonDto> seasons;

	private Set<GenreDto> genres;

	public TVShowDto() {
	}

	public TVShowDto(TVShowDtoBuilder builder) {
		super(builder);
		this.seasons = builder.seasons;
		this.genres = builder.genres;
	}

	public List<TVSeasonDto> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<TVSeasonDto> seasons) {
		this.seasons = seasons;
	}

	public Set<GenreDto> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreDto> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(genres, seasons);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TVShowDto))
			return false;
		TVShowDto other = (TVShowDto) obj;
		return Objects.equals(genres, other.genres) && Objects.equals(seasons, other.seasons);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVShowDto [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append(", director=");
		builder.append(director);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", stars=");
		builder.append(stars);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", imdbId=");
		builder.append(imdbId);
		builder.append(", year=");
		builder.append(year);
		builder.append(", seasons=");
		builder.append(seasons);
		builder.append(", genres=");
		builder.append(genres);
		builder.append("]");
		return builder.toString();
	}

	public static class TVShowDtoBuilder extends MediaDtoBuilder<TVShowDtoBuilder> {

		private List<TVSeasonDto> seasons;
		private Set<GenreDto> genres;

		public TVShowDtoBuilder seasons(List<TVSeasonDto> seasons) {
			this.seasons = seasons;
			return this;
		}

		public TVShowDtoBuilder genres(Set<GenreDto> genres) {
			this.genres = genres;
			return this;
		}

		public TVShowDto build() {
			return new TVShowDto(this);
		}

	}

}