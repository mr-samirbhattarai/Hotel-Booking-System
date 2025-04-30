package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.UserModel;

public class LoginDAO {
    private Connection conn;
    private PreparedStatement ps;

    public LoginDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    // Method to retrieve all users (for admin purposes or if needed for registration check)
    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        String query = "SELECT * FROM users"; // Adjust query if you need to limit fields

        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ResultSet userSet = ps.executeQuery(); //stores all the user information retrieved by running query in database
                
                // Iterate through result set and populate User objects
                while (userSet.next()) {
                	UserModel userModel = new UserModel();
                	userModel.setFirstName(userSet.getString("firstname"));
                	userModel.setLastName(userSet.getString("lastname"));
                	userModel.setUsername(userSet.getString("username"));
                	userModel.setEmail(userSet.getString("email"));
                	userModel.setPassword(userSet.getString("password"));
                	userModel.setRole(userSet.getString("role")); // Optional if you need role
                    users.add(userModel);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // TODO Shows error if query fails
            }
        }
        return users; // Returns list of users (or empty list if no users found)
    }

    // Method to handle login based on email and password
    public UserModel loginUser(String email, String password) {
    	UserModel user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?"; // Adjust query if needed

        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, password); // TODO In production, always hash and compare passwords
                ResultSet userSet = ps.executeQuery();
                
                // If a user is found, populate the User object
                if (userSet.next()) {
                    user = new UserModel();
                    user.setFirstName(userSet.getString("firstname"));
                    user.setLastName(userSet.getString("lastname"));
                    user.setUsername(userSet.getString("username"));
                    user.setEmail(userSet.getString("email"));
                    user.setPassword(userSet.getString("password")); // Retrieve the encrypted password
                    user.setRole(userSet.getString("role"));
                }
            } catch (SQLException e) {
                e.printStackTrace(); // TODO Logs error if login fails
            }
        }
        return user; // Returns null if no match found and returns the user info if user the user was found in database
    }
}