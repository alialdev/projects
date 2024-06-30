package com.scalefocus.amdb.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class TVShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TVSeason> seasons;

	@ManyToMany
	@JoinTable(
			name = "tv_show_genres",
			joinColumns = @JoinColumn(name = "tv_show_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;

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

	public List<TVSeason> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<TVSeason> seasons) {
		this.seasons = seasons;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description,
			director,
			duration,
			genres,
			id,
			imdbId,
			rating,
			releaseDate,
			seasons,
			stars,
			title,
			writer,
			year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TVShow))
			return false;
		TVShow other = (TVShow) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(director, other.director)
				&& Objects.equals(duration, other.duration)
				&& Objects.equals(genres, other.genres)
				&& Objects.equals(id, other.id)
				&& Objects.equals(imdbId, other.imdbId)
				&& Objects.equals(rating, other.rating)
				&& Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(seasons, other.seasons)
				&& Objects.equals(stars, other.stars)
				&& Objects.equals(title, other.title)
				&& Objects.equals(writer, other.writer)
				&& Objects.equals(year, other.year);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVShow [id=");
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

}