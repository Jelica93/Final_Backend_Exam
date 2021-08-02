package com.iktpreobuka.dnevnik.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDTO {

//	@NotNull
//	@Size(min = 5, max = 50, message = "Username must be between {min} and {max} characters long.")
//	private String username;
//	@NotNull
//	@Size(min = 5, max = 100, message = "Password must be between {min} and {max} characters long.")
//   // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
//    private String password;
//
//	@NotNull
//	@Size(min = 5, max = 100, message = "Confirm password must be between {min} and {max} characters long.")
//    private String confirmPassword;
//	
//
//	@JsonProperty
//	@NotNull(message = "Firstname has to be provided.")
//	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
//	private String firstName;
//	
//	@JsonProperty
//	@NotNull(message = "Surname has to be provided.")
//	@Size(min = 2, max = 30, message = "Surname must be between {min} and {max} characters.")
//	private String surName;
//	
//	@NotNull(message = "Email has to be provided.")
//	@Email(message = "Email is not valid.")
//	@JsonProperty
//	private String email;
}
