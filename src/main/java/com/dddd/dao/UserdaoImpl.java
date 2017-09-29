package com.dddd.dao;

import org.apache.ibatis.session.SqlSession;

import com.dddd.model.Login;
import com.dddd.model.User;
import com.dddd.utility.MyBatisUtill;


public class UserdaoImpl implements Userdao {

	public void register(User user) {
		System.out.println("inside register function of Userdao IMPL");
		SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
		try {
			Userdao userdao = sqlSession.getMapper(Userdao.class);
			userdao.register(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	public void googleRegister(User user) {
		System.out.println("inside googleRegister function of Userdao IMPL");
		SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
		try {
			Userdao userdao = sqlSession.getMapper(Userdao.class);
			userdao.googleRegister(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public User validateUser(Login login) {
		System.out.println("hello check validate");
		SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
		try {
			System.out.println("inside validate");
			
			
			Userdao userdao = sqlSession.getMapper(Userdao.class);
			System.out.println("Username: " + login.getUsername());
			System.out.println("Password: " + login.getPassword());
			return userdao.validateUser(login);
		} finally {
			sqlSession.close();
		}
	}	
		
		public String validateUsername(String username) {
			System.out.println("hello check validate  of Username");
			SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
			try {
				System.out.println("inside validateof Username");
				Userdao userdao = sqlSession.getMapper(Userdao.class);
				System.out.println("class");
				return userdao.validateUsername(username);
			}
			finally {
				sqlSession.close();
			}
			
		
	}
		
		public void updatePassword(String password,String username) {
			System.out.println("hello check validate of updatePassword");
			SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
			try {
				System.out.println("inside validate  of updatePassword");
				Userdao userdao = sqlSession.getMapper(Userdao.class);
				userdao.updatePassword(password,username);
				sqlSession.commit();
			}
			finally {
				sqlSession.close();
			}
			
		}
		

		public User getUserByEmailId(String emails) {
			System.out.println("hello check MAIL");
			SqlSession sqlSession = MyBatisUtill.getSqlSessionFactory().openSession();
			try {
				System.out.println("inside check mailid of user");
				Userdao userdao = sqlSession.getMapper(Userdao.class);
				userdao.getUserByEmailId(emails);
				return userdao.getUserByEmailId(emails);
				
			}
			finally {
				sqlSession.close();
			}
			
		}
}





