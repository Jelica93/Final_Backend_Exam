package com.iktpreobuka.dnevnik.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.iktpreobuka.dnevnik.entities.RoleEntity;

public class UserSecondDTO {

	@Size(min = 5, max = 50, message = "Username must be between (min) and (max) characters long.")
	@NotNull
	private String username;
	
	@NotNull
	@Size(min = 2, max = 500, message = "Password must be between (min) and (max) characters long.")
	private String password;

	@NotNull
	@Size(min = 2, max = 500, message = "Confirm password must be between (min) and (max) characters long.")
	private String confirmPassword;
	
	@Size(min = 2, max = 50, message = "Name must be between {min} and {max} characters long.")
	@NotNull
	private String name;
	
	@Size(min = 2, max = 50, message = "Surname must be between {min} and {max} characters long.")
	@NotNull
	private String lastname;

	@NotBlank(message = "Email must be provided.")
	@Email(message = "Email is not valid.")
	private String email;

	//@NotNull
	private RoleEntity role;

	@Pattern(regexp = "^\\d{13}$")
	private String jmbg;

	public UserSecondDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
