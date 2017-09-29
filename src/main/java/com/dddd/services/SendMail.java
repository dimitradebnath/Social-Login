package com.dddd.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

import com.dddd.model.Otpgenerator;

public class SendMail {
	String s;
	
	public String Generate(String email) {
		ModelMap mode = null;
	
		String to = email;// change accordingly
		String from = "johannmehta98@gmail.com";// change accordingly
		// String host = "localhost";// or IP address

		// Get the session object
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props);

		try {
			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("johannmehta98@gmail.com", "DIMITRA DEB NATH");
				}
			});

			mailSession.setDebug(true); // Enable the debug mode

			Message msg = new MimeMessage(mailSession);

			// --[ Set the FROM, TO, DATE and SUBJECT fields
			msg.setFrom(new InternetAddress("johannmehta98@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSentDate(new Date());
			msg.setSubject("Password Reset");
			// --[ Create the body of the mail
			Otpgenerator obj = new Otpgenerator();
			 s = obj.OTP();
			msg.setText("Your OTP: " + s);
			mode = new ModelMap();
			mode.addAttribute("OTP", s);
			Transport.send(msg);
			return s;
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("Oops something has gone pearshaped!");
			System.out.println(e);

		}
		return s;
	}

}
