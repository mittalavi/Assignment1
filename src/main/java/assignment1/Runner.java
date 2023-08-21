package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import assignment1.dao.UserDAO;
import assignment1.service.BookService;
import assignment1.service.UserService;
import assignment1.to.User;
import assignment1.utils.DBUtils;

public class Runner {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		mainMenu();
	}

	public static void mainMenu() {
		UserService userService = new UserService();
		BookService bookService = new BookService();

		boolean mainMenuFlag = true;

		while (mainMenuFlag) {

			System.out.println("Enter 1 for Registration, 2 for Login and 0 for Exit");
			int mainMenuInput = sc.nextInt();

			switch (mainMenuInput) {

			case 1:
				userService.register();
				break;

			case 2:
				boolean loginFlag = true;
				while (loginFlag) {
					int loginStatus = userService.login();
					if (loginStatus != 0 ) {
						System.out.println("Welcome To Osttra Book Store");
						int terminateInput = 0;
						if(loginStatus == 1) {
							System.out.println("Press 1 to view all book details, Press 2 to search book from Book or Author name and Press 0 to terminate");
							terminateInput = sc.nextInt();
							if(terminateInput == 1)
								bookService.getBooks();	
							else if(terminateInput==2)
								bookService.searchBook();
						}
						else {
							System.out.println("Press 1 to add book and 0 to terminate");
							terminateInput = sc.nextInt();
							if(terminateInput == 1)
								bookService.addBook();
							
						}
							loginFlag = false;
							System.out.println("Thank You, You have been successfully logged out");
					} else {
						System.out.println("credentials are wrong...");
					}
				}
				break;

			case 0:
				mainMenuFlag = false;
				break;
			}
		}

	}
}