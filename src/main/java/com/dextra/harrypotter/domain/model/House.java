package com.dextra.harrypotter.domain.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "House", timeToLive = 120l)
public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String headOfHouse;
	private List<String> values;
	private List<String> colors;
	private String school;
	private String mascot;
	private String houseGhost;
	private String founder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadOfHouse() {
		return headOfHouse;
	}

	public void setHeadOfHouse(String headOfHouse) {
		this.headOfHouse = headOfHouse;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMascot() {
		return mascot;
	}

	public void setMascot(String mascot) {
		this.mascot = mascot;
	}

	public String getHouseGhost() {
		return houseGhost;
	}

	public void setHouseGhost(String houseGhost) {
		this.houseGhost = houseGhost;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
