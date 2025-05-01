package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form parameters
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String terms = request.getParameter("terms");

        // Initialize flags for validation
        boolean hasError = false;

        // Validate inputs
        if (firstname == null || firstname.trim().isEmpty()) {
            request.setAttribute("firstnameError", "First name is required");
            hasError = true;
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            request.setAttribute("lastnameError", "Last name is required");
            hasError = true;
        }
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Username is required");
            hasError = true;
        }
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email is required");
            hasError = true;
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("emailError", "Invalid email format");
            hasError = true;
        }
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("passwordError", "Password is required");
            hasError = true;
        } else if (password.length() < 8) {
            request.setAttribute("passwordError", "Password must be at least 8 characters");
            hasError = true;
        }
        if (retypePassword == null || retypePassword.trim().isEmpty()) {
            request.setAttribute("retypePasswordError", "Please retype your password");
            hasError = true;
        }
        if (!password.equals(retypePassword)) {
            request.setAttribute("passwordMatchError", "Passwords do not match");
            hasError = true;
        }
        if (terms == null || !terms.equals("on")) {
            request.setAttribute("termsError", "You must agree to the terms and conditions");
            hasError = true;
        }

        // Check for existing username and email
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/hotelDB");
            conn = ds.getConnection();

            // Check if username exists
            pstmt = conn.prepareStatement("SELECT username FROM users WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                request.setAttribute("usernameExistsError", "Username already exists");
                hasError = true;
            }
            rs.close();
            pstmt.close();

            // Check if email exists
            pstmt = conn.prepareStatement("SELECT email FROM users WHERE email = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                request.setAttribute("emailExistsError", "Email already exists");
                hasError = true;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            hasError = true;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 

        // If there are errors, forward back to the registration page
        if (hasError) {
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        // Register the user
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/hotelDB");
            conn = ds.getConnection();

            String sql = "INSERT INTO users (firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, password); // In a real application, hash the password

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "Registration successful! Please login.");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Registration error: " + e.getMessage());
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}