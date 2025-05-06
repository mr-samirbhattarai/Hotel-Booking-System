package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;

public class ManageStaffDAO {
    private Connection conn;

    public ManageStaffDAO() throws ClassNotFoundException, SQLException {
        conn = DatabaseConnection.getConnection();
    }

    public void updateUser(Users user) throws SQLException {
        String sql = 
          "UPDATE users " +
          "SET firstname = ?, lastname = ?, phoneNo = ?, email = ?, address = ?, dob = ?, gender = ? " +
          "WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNo());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress());
            if (user.getDob() != null) {
                ps.setDate(6, new java.sql.Date(user.getDob().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            ps.setString(7, user.getGender());
            ps.setInt   (8, user.getUserId());
            ps.execute();
        }
    }

    public void deleteUser(int userId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE user_id = ?")) {
            ps.setInt(1, userId);
            ps.execute();
        }
    }

}