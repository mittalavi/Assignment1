package assignment1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import assignment1.dao.BookDao;
import assignment1.to.Book;
import assignment1.to.User;
import assignment1.utils.DBUtils;

public class BookService {
	Scanner sc = new Scanner(System.in);
	BookDao bookDao = new BookDao();

	public void getBooks() {
		bookDao.getbooks();

		
	}

	public void addBook() {
		
		System.out.println("Enter Book Id!!");
		int bookId = sc.nextInt();
		
		System.out.println("Enter Book Name!!");
		String bookName = sc.next();
		
		System.out.println("Enter Author Name ");
		String authorName = sc.next();
		
		System.out.println("Enter Description!!");
		String description = sc.next();
		
		
		Book book = new Book(bookId, bookName, authorName, description);
		
		
		bookDao.add(book);
		
	}

	public void searchBook() {
		System.out.println("Enter either Book name or Author Name");
		String searchField = sc.next();
		bookDao.searchBook(searchField);
		
	}
}