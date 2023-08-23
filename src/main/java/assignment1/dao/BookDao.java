package assignment1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment1.to.Book;
import assignment1.utils.DBUtils;

public class BookDao {

	public void add(Book book) {
		try {

			Connection conn = DBUtils.getConnection();

			PreparedStatement statement = conn.prepareStatement("insert into books values(?, ?, ?, ?)");

			statement.setInt(1, book.getBookId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getAuthorName());
			statement.setString(4, book.getDescription());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of add() of BookDao...");
		}
		
	}

	public List<Book> getbooks() {
		
		Book bookDetails = null;
		List<Book> getBookList = new ArrayList<>();
	
		Connection conn = DBUtils.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("select * from books");
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next()) {
				int tID = resultSet.getInt(1);
				String tBookName = resultSet.getString(2);
				String tAuthorName = resultSet.getString(3);
				String tDescription = resultSet.getString(4);
				bookDetails = new Book(tID,tBookName,tAuthorName,tDescription);
				
				getBookList.add(bookDetails);
			}		
		} catch (SQLException e) {
			System.out.println("Error in BookDao - getBooks()");
			e.printStackTrace();
		}
		return getBookList;
		
	}

	public List<Book> searchBook(String searchField) {
		Book bookDetails = null;
		List<Book> getBookList = new ArrayList<>();
	
		try {
			
			Connection conn = DBUtils.getConnection();
			PreparedStatement statement;
			statement = conn.prepareStatement("select * from books where bookName like ? OR authorName like ?");
			statement.setString(1, "%"+searchField+"%");
			statement.setString(2, "%"+searchField+"%");
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next()) {
				int tID = resultSet.getInt(1);
				String tBookName = resultSet.getString(2);
				String tAuthorName = resultSet.getString(3);
				String tDescription = resultSet.getString(4);
				bookDetails = new Book(tID,tBookName,tAuthorName,tDescription);
				
				getBookList.add(bookDetails);
			}
		} catch (SQLException e) {
			System.out.println("Error in BookDao - searchBook");
			e.printStackTrace();
		}
		return getBookList;
		
	}

}
