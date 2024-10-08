package com.scalefocus.amdb.dto;

import java.time.LocalDate;
import java.util.Objects;

public abstract class MediaDto {

	protected Long id;

	protected String title;

	protected String description;

	protected Double rating;

	protected LocalDate releaseDate;

	protected String director;

	protected String writer;

	protected String stars;

	protected Integer duration;

	protected String imdbId;

	protected Integer year;

	protected MediaDto() {
	}

	protected MediaDto(MediaDtoBuilder<?> builder) {

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

	@Override
	public int hashCode() {
		return Objects.hash(description,
			director,
			duration,
			id,
			imdbId,
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
		if (!(obj instanceof MediaDto))
			return false;
		MediaDto other = (MediaDto) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(director, other.director)
				&& Objects.equals(duration, other.duration)
				&& Objects.equals(id, other.id)
				&& Objects.equals(imdbId, other.imdbId)
				&& Objects.equals(rating, other.rating)
				&& Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(stars, other.stars)
				&& Objects.equals(title, other.title)
				&& Objects.equals(writer, other.writer)
				&& Objects.equals(year, other.year);
	}

	@SuppressWarnings("unchecked")
	public static class MediaDtoBuilder<T extends MediaDtoBuilder<T>> {

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

		public T id(Long id) {
			this.id = id;
			return (T) this;
		}

		public T title(String title) {
			this.title = title;
			return (T) this;
		}

		public T description(String description) {
			this.description = description;
			return (T) this;
		}

		public T rating(Double rating) {
			this.rating = rating;
			return (T) this;
		}

		public T releaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
			return (T) this;
		}

		public T director(String director) {
			this.director = director;
			return (T) this;
		}

		public T writer(String writer) {
			this.writer = writer;
			return (T) this;
		}

		public T stars(String stars) {
			this.stars = stars;
			return (T) this;
		}

		public T duration(Integer duration) {
			this.duration = duration;
			return (T) this;
		}

		public T imdbId(String imdbId) {
			this.imdbId = imdbId;
			return (T) this;
		}

		public T year(Integer year) {
			this.year = year;
			return (T) this;
		}

	}

}