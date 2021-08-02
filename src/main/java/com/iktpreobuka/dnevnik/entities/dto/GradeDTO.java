package com.iktpreobuka.dnevnik.entities.dto;



import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.dnevnik.entities.Semester;
import com.iktpreobuka.dnevnik.entities.StudentEntity;
import com.iktpreobuka.dnevnik.entities.Subject;
import com.iktpreobuka.dnevnik.entities.TeacherEntity;
import com.iktpreobuka.dnevnik.entities.enums.EGradeType;

public class GradeDTO {


	@JsonProperty(value = "value")
	@Min(1)
	@Max(5)
	private Integer value;
	
	@JsonProperty(value = "gradeType")
	@Enumerated(EnumType.STRING)
	private EGradeType gradeType;

	//@JsonProperty
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm")
	//private LocalDate date;
	
//	@JsonProperty(value = "semester")
//	private Integer semesterId;
//
//	@JsonProperty(value = "student")
//	private Integer studentId;
//	
//	@JsonProperty(value = "teacher")
//	private Integer teacherId;
//	
//	@JsonProperty(value = "subject")
//	private Integer subjectId;

	public GradeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public EGradeType getGradeType() {
		return gradeType;
	}

	public void setGradeType(EGradeType gradeType) {
		this.gradeType = gradeType;
	}

//	public LocalDate getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}

//	public Integer getSemesterId() {
//		return semesterId;
//	}
//
//	public void setSemesterId(Integer semesterId) {
//		this.semesterId = semesterId;
//	}
//
//	public Integer getStudentId() {
//		return studentId;
//	}
//
//	public void setStudentId(Integer studentId) {
//		this.studentId = studentId;
//	}
//
//	public Integer getTeacherId() {
//		return teacherId;
//	}
//
//	public void setTeacherId(Integer teacherId) {
//		this.teacherId = teacherId;
//	}
//
//	public Integer getSubjectId() {
//		return subjectId;
//	}
//
//	public void setSubjectId(Integer subjectId) {
//		this.subjectId = subjectId;
//	}
//


	
}
