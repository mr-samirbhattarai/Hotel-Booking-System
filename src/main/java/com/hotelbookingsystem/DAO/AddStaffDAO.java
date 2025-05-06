package com.hotelbookingsystem.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;

public class AddStaffDAO {
    public boolean addUser(Users user) {
        String sql = "INSERT INTO users (firstname, lastname, username, password, phoneNo, email, address, dob, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            System.out.println("First Name: " + user.getFirstName());

            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNo());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getAddress());
            ps.setDate(8, user.getDob());
            ps.setString(9, user.getGender());
            ps.setString(10, "Staff");
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}