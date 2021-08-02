package com.iktpreobuka.dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "admins")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class AdminEntity extends UserEntity {

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
