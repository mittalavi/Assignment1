package assignment1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public void getbooks() {
		Connection conn = DBUtils.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("select * from books");
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next()) {
				System.out.println( "Book [bookId=" + resultSet.getString(1) + ", bookName=" + resultSet.getString(2) + ", authorName=" + resultSet.getString(3) + ", description="+ resultSet.getString(4) + "]");
			}		
		} catch (SQLException e) {
			System.out.println("Error in BookDao - getBooks()");
			e.printStackTrace();
		}
		
	}

	public void searchBook(String searchField) {
		
	
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement statement;
			statement = conn.prepareStatement("select * from books where bookName like ? OR authorName like ?");
			statement.setString(1, "%"+searchField+"%");
			statement.setString(2, "%"+searchField+"%");
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next()) {
				System.out.println( "Book [bookId=" + resultSet.getString(1) + ", bookName=" + resultSet.getString(2) + ", authorName=" + resultSet.getString(3) + ", description="+ resultSet.getString(4) + "]");
			}
		} catch (SQLException e) {
			System.out.println("Error in BookDao - searchBook");
			e.printStackTrace();
		}
		
		
	}

}
