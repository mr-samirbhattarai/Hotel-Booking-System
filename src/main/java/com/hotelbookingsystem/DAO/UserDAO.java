package com.hotelbookingsystem.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;


public class UserDAO {
    private Connection conn;
    private PreparedStatement ps;

    // Constructor: Initializes the database connection
    public UserDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    // Registers a new user in the database
    public boolean register(Users users) {
        boolean isUserRegistered = false;
        String query = "INSERT INTO users (firstName, lastName, username, email, password, role, is_active, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, users.getFirstName());
                ps.setString(2, users.getLastName());
                ps.setString(3, users.getUsername());
                ps.setString(4, users.getEmail());
                ps.setString(5, users.getPassword()); // Should be encrypted in practice
                ps.setString(6, "USER"); // Default role
                ps.setBoolean(7, true); // Default active status
                ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                if (ps.executeUpdate() > 0) {
                    isUserRegistered = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUserRegistered;
    }

    // Gets all users from the database
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ResultSet userSet = ps.executeQuery();
                while (userSet.next()) {
                    Users user = new Users();
                    user.setUserId(userSet.getLong("user_id"));
                    user.setRole(userSet.getString("role"));
                    user.setPhoneNo(userSet.getString("phone_no"));
                    user.setAddress(userSet.getString("address"));
                    user.setGender(userSet.getString("gender"));
                    user.setDob(userSet.getDate("dob"));
                    user.setVerificationId(userSet.getString("verification_id"));
                    user.setProfilePicture(userSet.getString("profile_picture"));
                    user.setActive(userSet.getBoolean("is_active"));
                    user.setCreatedAt(userSet.getTimestamp("created_at"));
                    user.setUpdatedAt(userSet.getTimestamp("updated_at"));
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    // Authenticates user by checking email and password
    public Users login(String emailToCheck, String encryptedPassword) {
        Users users = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND is_active = true";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, emailToCheck);
                ps.setString(2, encryptedPassword);
                ResultSet userSet = ps.executeQuery();
                if (userSet.next()) {
                    users = new Users();
                    users.setFirstName(userSet.getString("firstName"));
                    users.setLastName(userSet.getString("lastName"));
                    users.setUsername(userSet.getString("username"));
                    users.setEmail(userSet.getString("email"));
                    users.setPassword(userSet.getString("password"));
                    users.setUserId(userSet.getLong("user_id"));
                    users.setRole(userSet.getString("role"));
                    users.setPhoneNo(userSet.getString("phone_no"));
                    users.setAddress(userSet.getString("address"));
                    users.setGender(userSet.getString("gender"));
                    users.setDob(userSet.getDate("dob"));
                    users.setVerificationId(userSet.getString("verification_id"));
                    users.setProfilePicture(userSet.getString("profile_picture"));
                    users.setActive(userSet.getBoolean("is_active"));
                    users.setCreatedAt(userSet.getTimestamp("created_at"));
                    users.setUpdatedAt(userSet.getTimestamp("updated_at"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}