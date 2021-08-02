package com.iktpreobuka.dnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectDTO {
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "weeklyFond")
	private Integer weeklyFond;

	public SubjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeeklyFond() {
		return weeklyFond;
	}

	public void setWeeklyFond(Integer weeklyFond) {
		this.weeklyFond = weeklyFond;
	}
	
	
}
