package com.iktpreobuka.dnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

	@NotBlank(message = "Email must be provided.")
	@Email(message = "Email is not valid.")
	@Column(name = "email", unique = true)
	private String email;
	
	@NotBlank(message = "User name has to be provided.")
	@Size(min = 5, max = 50, message = "Username must be between (min) and (max) characters long.")
	@Column(name = "username", unique = true)
	private String username;
	
	@NotBlank(message = "Password has to be provided.")
	@Column(name = "password")
	@JsonIgnore
	@Size(min = 2, max = 500, message = "Password must be between (min) and (max) characters long.")
	private String password;
	
	@Size(min = 2, max = 500, message = "Confirm password must be between (min) and (max) characters long.")
	@Column(name = "confirmPassword")
	private String confirmPassword;
	
	@NotBlank(message = "Name must be provided.")
	@Size(min = 2, max = 50, message = "Name must be between {min} and {max} characters long.")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "Lastame must be provided.")
	@Size(min = 2, max = 50, message = "Surname must be between {min} and {max} characters long.")
	@Column(name = "last_name")
	private String lastname;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(unique = true, name = "jmbg")
	@Pattern(regexp = "^\\d{13}$")
	private String jmbg;

	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	public RoleEntity role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public UserEntity() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
}
