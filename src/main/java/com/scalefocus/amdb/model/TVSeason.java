package com.scalefocus.amdb.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TVSeason {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer number;

	@OneToMany(mappedBy = "tvSeason", cascade = CascadeType.ALL)
	private List<TVEpisode> episodes;

	@ManyToOne
	@JoinColumn(name = "tv_show_id")
	@JsonBackReference
	private TVShow tvShow;

	// Getters and Setters
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

	public List<TVEpisode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<TVEpisode> episodes) {
		this.episodes = episodes;
	}

	public TVShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TVShow tvShow) {
		this.tvShow = tvShow;
	}

	@Override
	public int hashCode() {
		return Objects.hash(episodes, id, number, tvShow);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TVSeason))
			return false;
		TVSeason other = (TVSeason) obj;
		return Objects.equals(episodes, other.episodes)
				&& Objects.equals(id, other.id)
				&& Objects.equals(number, other.number)
				&& Objects.equals(tvShow, other.tvShow);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TVSeason [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", episodes=");
		builder.append(episodes);
		builder.append(", tvShow=");
		builder.append(tvShow);
		builder.append("]");
		return builder.toString();
	}

}