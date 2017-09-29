package com.dddd.dao;

import com.dddd.model.Login;
import com.dddd.model.User;

public interface Userdao {


	void register(User user);
	User validateUser(Login login);
	String validateUsername(String username);
	void updatePassword(String password,String username);
	User getUserByEmailId(String emails);
	void googleRegister(User user);
}
