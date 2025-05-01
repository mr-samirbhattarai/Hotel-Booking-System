package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.User;

public class RegisterDAO {

    // No need to specify URL, username, and password here as we're using DatabaseConnection
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Use the DatabaseConnection class
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());     
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;  // Return true if user was successfully registered
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Registration failed: " + e.getMessage());
            return false;  // Return false if there was an error during registration
        }
    }
}
