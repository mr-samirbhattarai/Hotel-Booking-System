package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.UserModel;

public class RegisterDAO {

    // No need to specify URL, username, and password here as we're using DatabaseConnection
    public boolean registerUser(UserModel userModel) {
        String sql = "INSERT INTO users (firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Use the DatabaseConnection class
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, userModel.getFirstName());
            stmt.setString(2, userModel.getLastName());
            stmt.setString(3, userModel.getUsername());
            stmt.setString(4, userModel.getEmail());
            stmt.setString(5, userModel.getPassword());     
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;  // Return true if user was successfully registered
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;  // Return false if there was an error during registration
        }
    }
}