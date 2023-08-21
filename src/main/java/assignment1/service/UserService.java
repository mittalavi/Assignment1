package assignment1.service;

import java.util.Scanner;

import assignment1.dao.UserDAO;
import assignment1.to.User;

public class UserService {
	
	Scanner sc = new Scanner(System.in);
	
	UserDAO userDAO = new UserDAO();
	
	public void register() {
		
		System.out.println("Enter your Username!!");
		String username = sc.next();
		
		System.out.println("Enter your password!!");
		String password = sc.next();
		
		System.out.println("Enter your Name");
		String name = sc.next();
		
		System.out.println("Enter your Role!!");
		String role = sc.next();
		
		
		
		User user = new User(username, password, name, role);
		
		//userDAO.add(username, password, name, role);
		
		userDAO.add(user);
	}
	
	public int login() {
		
		int loginStatus = 0;
		
		System.out.println("Enter your Username!!");
		String username = sc.next();
		
		System.out.println("Enter your password!!");
		String password = sc.next();
		
		User user =  userDAO.getUser(username, password);
		
		if( user != null) {
			if(user.getRole().equals("user"))
				loginStatus = 1;
			else
				loginStatus = 2 ;
		}
		return loginStatus;
	}

}
