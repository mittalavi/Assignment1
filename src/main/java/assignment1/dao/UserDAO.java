package assignment1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import assignment1.to.User;
import assignment1.utils.DBUtils;

public class UserDAO {


	public void add(User user) {

		try {

			Connection conn = DBUtils.getConnection();

			PreparedStatement statement = conn.prepareStatement("insert into user values(?, ?, ?, ?)");

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getRole());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of add() of UserDAO...");
		}
	}

	public User getUser(String username, String password) {
		User user = null;

		try {

			Connection conn = DBUtils.getConnection();

			PreparedStatement statement = conn.prepareStatement("select * from user where username = ? and password = ?");

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();
			
			if( resultSet.next()) {
				
				String uname = resultSet.getString(1);
				String pwd = resultSet.getString(2);
				String name = resultSet.getString(3);
				String role = resultSet.getString(4);
				
				user = new User(uname, pwd, name, role);
			}
			
		} catch (Exception e) {
			System.out.println("inside catch of add() of UserDAO...");
		}
		return user;
	}
}
