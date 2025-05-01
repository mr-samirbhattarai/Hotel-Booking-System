package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.UserModel;

public class RegisterDAO {

    private Connection conn;

    public RegisterDAO() throws ClassNotFoundException, SQLException {
        conn = DatabaseConnection.getConnection(); // Establish a database connection
    }

    public boolean registerUser(UserModel userModel) {
        boolean isRowInserted = false;
        String sql = "INSERT INTO users (firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userModel.getFirstName());
                ps.setString(2, userModel.getLastName());
                ps.setString(3, userModel.getUsername());
                ps.setString(4, userModel.getEmail());
                ps.setString(5, userModel.getPassword());

                int rows = ps.executeUpdate();
                isRowInserted = (rows > 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRowInserted;
    }

    public boolean usernameExists(String username) {
        boolean isRowFound = false;
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        isRowFound = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRowFound;
    }

    public boolean emailExists(String email) {
        boolean isRowFound = false;
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        isRowFound = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRowFound;
    }
    

}
