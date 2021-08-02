package com.iktpreobuka.dnevnik.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "subjects")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Subject {
	
	@Column(nullable = false)
	private String name;

	
	@OneToMany(mappedBy = "subjects", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<ClassEntity> classEntity = new HashSet<>();	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId")
	private TeacherEntity teachers;
	
	@Column(name = "nedeljni_fond")
	private Integer weeklyFond;
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "teacherSubject")
//	private TeacherSubject teacherSubject;
//	
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<ClassEntity> getClassEntity() {
		return classEntity;
	}


	public void setClassEntity(Set<ClassEntity> classEntity) {
		this.classEntity = classEntity;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public TeacherEntity getTeachers() {
		return teachers;
	}


	public void setTeachers(TeacherEntity teachers) {
		this.teachers = teachers;
	}


	public Integer getWeeklyFond() {
		return weeklyFond;
	}


	public void setWeeklyFond(Integer weeklyFond) {
		this.weeklyFond = weeklyFond;
	}


//	public TeacherSubject getTeacherSubject() {
//		return teacherSubject;
//	}
//
//
//	public void setTeacherSubject(TeacherSubject teacherSubject) {
//		this.teacherSubject = teacherSubject;
//	}
	
	
}
