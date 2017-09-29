package com.dddd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dddd.model.User;
import com.dddd.services.UserServices;
import com.dddd.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserServices userService;
	@Autowired
	UserValidator userValidator;

	@RequestMapping(value = "/registration")
	public ModelAndView showRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mav = new ModelAndView("registration");
		User user = new User(); /* creating an empty user model */
		mav.addObject("registration", user);
		System.out.println("reg control");
		return mav;
	}

	@RequestMapping(value = "/registrationProcess", method = RequestMethod.POST)
	public String addUser(HttpServletRequest req,
			HttpServletRequest request, @ModelAttribute("registration") User user,
			BindingResult result) throws IOException {
		System.out.println(user);

		userValidator.validate(user, result);
		System.out.println(result);
		if (result.hasErrors()) {			
			return "registration";
		}


		else {
			userService.register(user);
			System.out.println(req.getParameter("username"));
			return "redirect:/";
			
		}
	}
}
