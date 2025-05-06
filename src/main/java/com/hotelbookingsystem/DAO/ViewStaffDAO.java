package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;


public class ViewStaffDAO {
	private Connection conn;
	private PreparedStatement ps;
	public ViewStaffDAO() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
	}
		
		//retrieving the users
		
		public ArrayList<Users> getAllStaffs() throws SQLException{
			ArrayList<Users> users = new ArrayList<>(); 
			String sql = "Select * from users where role ='staff'";
			if(conn!=null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet userSet = ps.executeQuery();
				while (userSet.next()) {
				    Users user = new Users(
				        userSet.getInt("user_id"),    
				        userSet.getString("firstname"), 
				        userSet.getString("lastname"),  
				        userSet.getString("phoneNo"), 
				        userSet.getString("email"),     
				        userSet.getString("address"), 
				        userSet.getDate("dob"),       
				        userSet.getString("gender"));
				    users.add(user);
				}
			}
			return users;
		}
	}