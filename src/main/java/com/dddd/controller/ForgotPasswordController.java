package com.dddd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dddd.model.Login;
import com.dddd.model.User;
import com.dddd.services.SendMail;
import com.dddd.services.UserServices;

@Controller
public class ForgotPasswordController {

	@Autowired
	SendMail sm;
	@Autowired
	UserServices us;

	@RequestMapping(value = "uname", method = RequestMethod.GET)
	public ModelAndView forgotPass(HttpServletRequest req, HttpServletResponse resp) {

		System.out.println("Fetching the username ");
		ModelAndView mav = new ModelAndView("uname");
		mav.addObject("command", "");

		return mav;
	}

	@RequestMapping(value = "/Otp", method = RequestMethod.GET)
	public ModelAndView genotp(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		ModelAndView mav = null;
		System.out.println("checking the username");
		String u = req.getParameter("username");

		session.setAttribute("username", u);
		String email = us.validateUsername(u);
		if (email == null) {
			mav = new ModelAndView("uname");
			mav.addObject("message", "Uname is not a valid uname");
			return mav;
		} else {
			mav = new ModelAndView("Otp");

			String otp1 = sm.Generate(email);
			session.setAttribute("otp1", otp1);

			return mav;
		}

	}

	@RequestMapping(value = "/Password", method = RequestMethod.POST)
	public ModelAndView resetPass(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam Map<String, String> reqparam, HttpSession session) {
		ModelAndView mav = null;
		String otp2 = reqparam.get("otp");
		System.out.println(reqparam.get("otp"));
		req.getSession();
		String opt1 = (String) session.getAttribute("otp1");
		System.out.println("opt1:--- "+opt1);
		
		if (opt1.equals(otp2)) {
			System.out.println("correct entry of otp");
			mav = new ModelAndView("Password");
			return mav;
		}
		else {
			System.out.println("Incorrect entry of otp");
			mav = new ModelAndView("Otp");
			mav.addObject("message", "Valid otp please");
			return mav;
		}
		

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView showLogin(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam Map<String, String> reqparam, HttpSession session) {
		ModelAndView mav = null;
		String resetpassword = reqparam.get("password");
		req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(session.getAttribute("username"));
		System.out.println(reqparam.get("password"));

		us.updatePassword(resetpassword, username);
		System.out.println("inside the slash of the login controller");
		mav = new ModelAndView("login");
		Login newLogin = new Login();
		mav.addObject("login", newLogin);
		return mav;
	}

}
