package com.dddd.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.dddd.model.User;

public class UserValidator implements org.springframework.validation.Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		
		
		
		System.out.println("Checking...");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required","User Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required","Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Email","Email.required","Email Id is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.required","First Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.required","Last Name is required");
	}
}
