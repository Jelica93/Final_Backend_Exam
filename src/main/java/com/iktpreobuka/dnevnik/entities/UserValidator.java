package com.iktpreobuka.dnevnik.entities;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.dnevnik.entities.dto.UserSecondDTO;



@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserSecondDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserSecondDTO teacher = (UserSecondDTO) target;
		
		if(!teacher.getPassword().equals(teacher.getConfirmPassword())) {
			errors.reject("400", "Passwords must match.");
		}
		
	}

}
