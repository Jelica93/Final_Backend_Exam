package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teachers")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class TeacherEntity extends UserEntity {
	
//	@JsonBackReference
//	@OneToMany(mappedBy = "teachers", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
//	private List<TeacherSubject> teacherSubject = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "teachers", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<ClassEntity> classes = new ArrayList<>();
	
	public TeacherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public List<TeacherSubject> getTeacherSubject() {
//		return teacherSubject;
//	}
//	public void setTeacherSubject(List<TeacherSubject> teacherSubject) {
//		this.teacherSubject = teacherSubject;
//	}
	public List<ClassEntity> getClasses() {
		return classes;
	}
	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}
	
	
}
