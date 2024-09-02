package com.scalefocus.amdb.dto;

import java.time.LocalDate;
import java.util.Objects;

public class TVEpisodeDto {

	private Long id;

	private String title;

	private String description;

	private Double rating;

	private LocalDate releaseDate;

	private String director;

	private String writer;

	private String stars;

	private Integer duration;

	private String imdbId;

	private Integer year;

	private Integer number;

	public TVEpisodeDto() {
	}

	public TVEpisodeDto(TVEpisodeDtoBuilder builder) {
		this.id = builder.id;

		this.title = builder.title;

		this.description = builder.description;

		this.rating = builder.rating;

		this.releaseDate = builder.releaseDate;

		this.director = builder.director;

		this.writer = builder.writer;

		this.stars = builder.stars;

		this.duration = builder.duration;

		this.imdbId = builder.imdbId;

		this.year = builder.year;

		this.number = builder.number;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description,
			director,
			duration,
			id,
			imdbId,
			number,
			rating,
			releaseDate,
			stars,
			title,
			writer,
			year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TVEpisodeDto))
			return false;
		TVEpisodeDto other = (TVEpisodeDto) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(director, other.director)
				&& Objects.equals(duration, other.duration)
				&& Objects.equals(id, other.id)
				&& Objects.equals(imdbId, other.imdbId)
				&& Objects.equals(number, other.number)
				&& Objects.equals(rating, other.rating)
				&& Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(stars, other.stars)
				&& Objects.equals(title, other.title)
				&& Objects.equals(writer, other.writer)
				&& Objects.equals(year, other.year);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVEpisode [id=");
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
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}

	public static class TVEpisodeDtoBuilder {

		private Long id;
		private String title;
		private String description;
		private Double rating;
		private LocalDate releaseDate;
		private String director;
		private String writer;
		private String stars;
		private Integer duration;
		private String imdbId;
		private Integer year;
		private Integer number;

		public TVEpisodeDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public TVEpisodeDtoBuilder title(String title) {
			this.title = title;
			return this;
		}

		public TVEpisodeDtoBuilder description(String description) {
			this.description = description;
			return this;
		}

		public TVEpisodeDtoBuilder rating(Double rating) {
			this.rating = rating;
			return this;
		}

		public TVEpisodeDtoBuilder releaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
			return this;
		}

		public TVEpisodeDtoBuilder director(String director) {
			this.director = director;
			return this;
		}

		public TVEpisodeDtoBuilder writer(String writer) {
			this.writer = writer;
			return this;
		}

		public TVEpisodeDtoBuilder stars(String stars) {
			this.stars = stars;
			return this;
		}

		public TVEpisodeDtoBuilder duration(Integer duration) {
			this.duration = duration;
			return this;
		}

		public TVEpisodeDtoBuilder imdbId(String imdbId) {
			this.imdbId = imdbId;
			return this;
		}

		public TVEpisodeDtoBuilder year(Integer year) {
			this.year = year;
			return this;
		}

		public TVEpisodeDtoBuilder number(Integer number) {
			this.number = number;
			return this;
		}

		public TVEpisodeDto build() {
			return new TVEpisodeDto(this);
		}

	}

}