package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;

public class ViewCustomerDAO {
	private Connection conn;
	private PreparedStatement ps;

	public ViewCustomerDAO() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection(); // Establish a database connection when ViewCustomerDAO is created
	}

	// retrieving the users
	public ArrayList<Users> getAllUsers() throws SQLException {
		ArrayList<Users> users = new ArrayList<>();
		String sql = "SELECT * FROM users where role = 'customer' ";

		if (conn != null) {
			ps = conn.prepareStatement(sql);
			ResultSet customerSet = ps.executeQuery();
			while (customerSet.next()) {
				Users user = new Users(
					customerSet.getInt("user_id"),    
					customerSet.getString("firstname"), 
					customerSet.getString("lastname"), 
					customerSet.getString("username"),   
					customerSet.getString("password"),     
					customerSet.getString("email"),  
					customerSet.getString("gender"),    
					customerSet.getDate("dob"));
				users.add(user);
			}

			System.out.println("Number of users retrieved: " + users.size()); // Debugging line
		}

		return users;
	}
}