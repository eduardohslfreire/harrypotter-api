package com.dextra.harrypotter.domain.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class CharacterInput {

	@ApiModelProperty(example = "Harry Potter")
	@NotBlank
	private String name;

	@ApiModelProperty(example = "student")
	@NotBlank
	private String role;

	@ApiModelProperty(example = "Hogwarts School of Witchcraft and Wizardry")
	@NotBlank
	private String school;

	@ApiModelProperty(example = "1760529f-6d51-4cb1-bcb1-25087fce5bde")
	@NotBlank
	private String house;

	@ApiModelProperty(example = "stag")
	@NotBlank
	private String patronus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getPatronus() {
		return patronus;
	}

	public void setPatronus(String patronus) {
		this.patronus = patronus;
	}
}
