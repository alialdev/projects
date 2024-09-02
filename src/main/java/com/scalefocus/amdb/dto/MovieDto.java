package com.scalefocus.amdb.dto;

import java.util.Objects;
import java.util.Set;

public class MovieDto extends MediaDto {

	private Set<GenreDto> genres;

	public MovieDto() {
	}

	public MovieDto(MovieDtoBuilder builder) {
		super(builder);
		this.genres = builder.genres;
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
		result = prime * result + Objects.hash(genres);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof MovieDto))
			return false;
		MovieDto other = (MovieDto) obj;
		return Objects.equals(genres, other.genres);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieDto [id=");
		builder.append(id);
		builder.append(", genres=");
		builder.append(genres);
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
		builder.append("]");
		return builder.toString();
	}

	public static class MovieDtoBuilder extends MediaDtoBuilder<MovieDtoBuilder> {

		private Set<GenreDto> genres;

		public MovieDtoBuilder genres(Set<GenreDto> genres) {
			this.genres = genres;
			return this;
		}

		public MovieDto build() {
			return new MovieDto(this);
		}

	}

}
