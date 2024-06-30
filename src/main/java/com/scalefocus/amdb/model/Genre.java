package com.scalefocus.amdb.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "genres")
	private Set<Movie> movies;

	@ManyToMany(mappedBy = "genres")
	private Set<TVShow> tvShows;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Set<TVShow> getTvShows() {
		return tvShows;
	}

	public void setTvShows(Set<TVShow> tvShows) {
		this.tvShows = tvShows;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, movies, name, tvShows);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Genre))
			return false;
		Genre other = (Genre) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(movies, other.movies)
				&& Objects.equals(name, other.name)
				&& Objects.equals(tvShows, other.tvShows);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Genre [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", movies=");
		builder.append(movies);
		builder.append(", tvShows=");
		builder.append(tvShows);
		builder.append("]");
		return builder.toString();
	}

}