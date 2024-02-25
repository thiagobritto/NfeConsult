package com.github.thiagobritto.nfeconsult.models;

import java.util.Objects;

public class ItemModel extends DefaultModel {
	private String description;

	public ItemModel() {
		super();
	}

	public ItemModel(Integer id) {
		super(id);
	}

	public ItemModel(String description) {
		super();
		this.description = description;
	}
	
	public ItemModel(Integer id, String description) {
		super(id);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemModel other = (ItemModel) obj;
		return Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "ItemModel [description=" + description + "]";
	}
	
	
}
