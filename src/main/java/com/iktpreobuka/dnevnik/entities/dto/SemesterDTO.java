package com.iktpreobuka.dnevnik.entities.dto;

import javax.validation.constraints.NotNull;

public class SemesterDTO {

	@NotNull
	private String name;

	public SemesterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
