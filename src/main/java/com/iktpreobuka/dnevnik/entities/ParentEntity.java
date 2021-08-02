package com.iktpreobuka.dnevnik.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "parents")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class ParentEntity extends UserEntity{

	
	@OneToMany(mappedBy = "parents", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<StudentEntity> students;

	public ParentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	
	
}
