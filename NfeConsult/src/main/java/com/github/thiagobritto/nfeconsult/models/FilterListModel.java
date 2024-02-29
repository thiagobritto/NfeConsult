package com.github.thiagobritto.nfeconsult.models;

import java.util.Objects;

public class FilterListModel {

	private Integer id;
	private String description;

	public FilterListModel() {
		super();
	}
	
	public FilterListModel(Integer id) {
		super();
		this.id = id;
	}
	
	public FilterListModel(String description) {
		super();
		this.description = description;
	}
	
	public FilterListModel(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilterListModel other = (FilterListModel) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FilterListModel [id=" + id + ", description=" + description + "]";
	}

}
