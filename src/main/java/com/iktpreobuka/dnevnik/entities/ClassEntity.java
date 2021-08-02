package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "classes")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "classNumber", nullable = false)
	@NotNull
	@Min(value = 1, message = "Class number must be equal or bigger than {value}.")
	@Max(value = 8, message = "Class number must be equal or smaller than {value}.")
	private Integer classNumber;
	
	@Version
	private Integer version;

	@JsonBackReference
	@OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<StudentEntity> student = new ArrayList<>();
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	private TeacherSubject teacherSubject;
	
	@OneToMany(mappedBy = "classes")
	private List<Semester> semester = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Subject subjects;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private TeacherEntity teachers;
	
	public ClassEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(List<StudentEntity> student) {
		this.student = student;
	}
//
//	public TeacherSubject getTeacherSubject() {
//		return teacherSubject;
//	}
//
//	public void setTeacherSubject(TeacherSubject teacherSubject) {
//		this.teacherSubject = teacherSubject;
//	}

	public List<Semester> getSemester() {
		return semester;
	}

	public void setSemester(List<Semester> semester) {
		this.semester = semester;
	}

	public Subject getSubjects() {
		return subjects;
	}

	public void setSubjects(Subject subjects) {
		this.subjects = subjects;
	}

	public TeacherEntity getTeachers() {
		return teachers;
	}

	public void setTeachers(TeacherEntity teachers) {
		this.teachers = teachers;
	}	
	
}
