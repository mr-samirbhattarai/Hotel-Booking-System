package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.utility.EncryptDecrypt;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form parameters
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String phoneNo = request.getParameter("phone_no");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");

        // Get session
        HttpSession session = request.getSession();

        // Store form data in session for repopulation
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname", lastname);
        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("phone_no", phoneNo);
        session.setAttribute("address", address);
        session.setAttribute("gender", gender);
        session.setAttribute("dob", dob);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Validation
            if (firstname == null || firstname.trim().isEmpty()) {
                request.setAttribute("errorMessage", "First name is required");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }
            if (lastname == null || lastname.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Last name is required");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }
            if (username == null || username.trim().isEmpty() || !username.matches("[a-zA-Z0-9]{3,50}")) {
                request.setAttribute("errorMessage", "Username must be 3-50 characters, letters and numbers only");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }
            if (email == null || email.trim().isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                request.setAttribute("errorMessage", "Valid email is required");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }
            if (password == null || password.length() < 6) {
                request.setAttribute("errorMessage", "Password must be at least 6 characters");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }
            if (!password.equals(retypePassword)) {
                request.setAttribute("errorMessage", "Passwords do not match");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }

            // Get database connection
            conn = DatabaseConnection.getConnection();

            // Check for duplicate username or email
            String checkSql = "SELECT COUNT(*) FROM Users WHERE username = ? OR email = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                request.setAttribute("errorMessage", "Username or email already exists");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
                return;
            }

            // Encrypt password
            String encryptedPassword = EncryptDecrypt.encrypt(password); // Adjust method name as per your Encrypt class

            // Insert user (role defaults to 'customer')
            String insertSql = "INSERT INTO Users (firstname, lastname, username, password, role, email, phone_no, address, gender, DOB, is_active) VALUES (?, ?, ?, ?, 'customer', ?, ?, ?, ?, ?, TRUE)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, username);
            stmt.setString(4, encryptedPassword);
            stmt.setString(5, email);
            stmt.setString(6, phoneNo != null && !phoneNo.isEmpty() ? phoneNo : null);
            stmt.setString(7, address != null && !address.isEmpty() ? address : null);
            stmt.setString(8, gender != null && !gender.isEmpty() ? gender : null);
            if (dob != null && !dob.isEmpty()) {
                stmt.setDate(9, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()));
            } else {
                stmt.setNull(9, java.sql.Types.DATE);
            }

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Clear session attributes on success
                session.removeAttribute("firstname");
                session.removeAttribute("lastname");
                session.removeAttribute("username");
                session.removeAttribute("email");
                session.removeAttribute("phone_no");
                session.removeAttribute("address");
                session.removeAttribute("gender");
                session.removeAttribute("dob");
                // Redirect to login
                response.sendRedirect("pages/login.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Assume DatabaseConnection handles connection pooling
                // If manual closing is needed, uncomment: if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Clear session attributes on GET to start fresh
        HttpSession session = request.getSession();
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.removeAttribute("username");
        session.removeAttribute("email");
        session.removeAttribute("phone_no");
        session.removeAttribute("address");
        session.removeAttribute("gender");
        session.removeAttribute("dob");
        // Redirect to registration page
        response.sendRedirect("pages/register.jsp");
    }
}