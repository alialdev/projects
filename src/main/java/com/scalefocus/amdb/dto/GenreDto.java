package com.scalefocus.amdb.dto;

import java.util.Objects;

public class GenreDto {

	private Long id;

	private String name;

	public GenreDto() {
	}

	private GenreDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GenreDto))
			return false;
		GenreDto other = (GenreDto) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Genre [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Long id;

		private String name;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public GenreDto build() {
			return new GenreDto(id, name);
		}

	}

}