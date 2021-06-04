package com.dextra.harrypotter.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.dextra.harrypotter.domain.model.House;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HousesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("houses")
	private List<House> houses;

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
}
