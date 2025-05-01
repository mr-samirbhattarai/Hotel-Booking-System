	package com.hotelbookingsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private final static String databaseName = "try_coursework";
	private final static String userName = "root";
	private final static String password = "";
	private final static String jdbcURL = "jdbc:mysql://localhost:3306/" + databaseName;

	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcURL, userName, password);
		return conn;
	}
	
	
//	to check if the connection is successful
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			if(conn != null) {
				System.err.println("Connection Successful");
			}
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}