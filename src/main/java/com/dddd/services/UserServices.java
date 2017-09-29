package com.dddd.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dddd.dao.Userdao;
import com.dddd.dao.UserdaoImpl;
import com.dddd.model.Login;
import com.dddd.model.User;
import com.dddd.utility.MyBatisUtill;

@Service
public class UserServices {

	@Autowired
	private Userdao dao;

	@Transactional
	public void register(User user) {
		System.out.println("doa");
		dao.register(user);
	}
	@Transactional
	public void googleRegister(User user) {
		System.out.println("doa");
		dao.googleRegister(user);
	}
	
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		System.out.println("doa");
		System.out.println("Username: " + login.getUsername());
		System.out.println("Password: " + login.getPassword());
		return dao.validateUser(login);
	}

	public String validateUsername(String username) {

		return dao.validateUsername(username);

	}

	public void updatePassword(String password,String username) {
		System.out.println("Password and Username :"+password+" "+username);
		 dao.updatePassword(password,username);

	}
	
	public User getUserByEmailId(String emails) {
		return dao.getUserByEmailId(emails);
	}
	
	

}
