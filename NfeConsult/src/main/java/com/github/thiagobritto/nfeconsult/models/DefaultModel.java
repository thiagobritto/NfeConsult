package com.github.thiagobritto.nfeconsult.models;

import java.util.Objects;

abstract class DefaultModel {
	private Integer id;

	public DefaultModel() {
		super();
	}

	public DefaultModel(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultModel other = (DefaultModel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DefaultModel [id=" + id + "]";
	}

}
