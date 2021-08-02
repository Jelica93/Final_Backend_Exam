package com.iktpreobuka.dnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

@Entity
@Table(name = "students")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class StudentEntity extends UserEntity {


	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "isInClass")
	private ClassEntity classEntity;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parents")
	private ParentEntity parents;

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	public ParentEntity getParents() {
		return parents;
	}

	public void setParents(ParentEntity parents) {
		this.parents = parents;
	}

	
	
}
