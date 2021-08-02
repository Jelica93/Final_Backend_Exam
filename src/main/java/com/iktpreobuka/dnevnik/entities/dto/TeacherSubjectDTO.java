package com.iktpreobuka.dnevnik.entities.dto;


import javax.validation.constraints.NotNull;

public class TeacherSubjectDTO {

	@NotNull
	private String subjectName;
	
	@NotNull
	private Integer weeklyFond;

	public TeacherSubjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getWeeklyFond() {
		return weeklyFond;
	}

	public void setWeeklyFond(Integer weeklyFond) {
		this.weeklyFond = weeklyFond;
	}
	
	
}
