package com.scalefocus.amdb.dto;

import java.time.LocalDate;
import java.util.Objects;

public class MediaDto {

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MediaDto [id=");
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
		builder.append("]");
		return builder.toString();
	}

}
