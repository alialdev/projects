package com.scalefocus.amdb.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class TVShow extends Media {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tvShow", cascade = CascadeType.ALL)
	private List<TVSeason> seasons;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tv_show_genres",
			joinColumns = @JoinColumn(name = "tv_show_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;

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
		if (!(obj instanceof TVShow))
			return false;
		TVShow other = (TVShow) obj;
		return Objects.equals(genres, other.genres) && Objects.equals(seasons, other.seasons);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVShow [id=");
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
		builder.append(", seasons=");
		builder.append(seasons);
		builder.append("]");
		return builder.toString();
	}

}