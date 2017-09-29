package com.dddd.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dddd.model.GmailProfile;
import com.dddd.model.Login;
import com.dddd.model.User;
import com.dddd.services.UserServices;
import com.dddd.social.Gmail;
import com.dddd.validation.UserValidator;

@Controller
public class LoginController {

	@Autowired
	UserServices userService;
	@Autowired
	UserValidator userValidator;
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest req, HttpServletResponse resp) {

		System.out.println("inside the slash of the login controller");
		ModelAndView mav = new ModelAndView("login");
		Login newLogin = new Login();
		mav.addObject("login", newLogin);
		return mav;
	}

	@RequestMapping(value = "/loginprocess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("login") Login newLogin, BindingResult result, HttpSession session) throws IOException {
		System.out.println(newLogin);
		ModelAndView mav = null;
		User user = userService.validateUser(newLogin);

		if (user != null) {
			mav = new ModelAndView("Welcome");

			String u = req.getParameter("username");

			session.setAttribute("username", u);
		}

		else {

			mav = new ModelAndView("login");

			mav.addObject("message", "Please LOG IN Again");
		}
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/loginWithGoogle")
	public void socialLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String lsr = request.getRequestURL().toString();
		String appURL = lsr.substring(0, lsr.lastIndexOf('/'));
		String stateCode = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", stateCode);
		String gmailUrl = Gmail.getGmailURl(appURL, stateCode);

		System.out.println("inside google login");
		response.sendRedirect(gmailUrl);

		return;
	}

	@RequestMapping(value = "/postGoogle")
	public String afterRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sessionState = (String) request.getSession().getAttribute("STATE");
		logger.warn("Inside post Google");
		String stateFromGmail = request.getParameter("state");

		if (sessionState == null || !sessionState.equals(stateFromGmail)) {

			response.sendRedirect("loginWithGoogle");
		}
		String error = request.getParameter("error");
		/* System.out.println(error+"This is error inside the login controller"); */

		if (error != null && !error.trim().isEmpty()) {

			response.sendRedirect("/");
		}

		String lsr = request.getRequestURL().toString();
		String appURL = lsr.substring(0, lsr.lastIndexOf('/'));

		String authCode = request.getParameter("code");

		GmailProfile profile = Gmail.authUser(authCode, appURL);
		System.out.println(profile);

		User user = userService.getUserByEmailId(profile.getEmails().get(0).getValue());
		if (user == null) {
			user = new User();

			user.setEmail(profile.getEmails().get(0).getValue());
			userService.googleRegister(user);
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "home";

	}

}
