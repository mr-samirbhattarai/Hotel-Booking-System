package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.utility.EncryptDecrypt;

@WebServlet("/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || 
            !"customer".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/access/login.jsp");
            return;
        }

        request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || 
            !"customer".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/access/login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Validation
            if (currentPassword == null || newPassword == null || confirmPassword == null ||
                currentPassword.trim().isEmpty() || newPassword.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "New password and confirm password do not match.");
                request.getRequestDispatcher("/customer/changePassword.jsp").forward(request, response);
                return;
            }

            if (newPassword.length() < 6) {
                request.setAttribute("errorMessage", "New password must be at least 6 characters.");
                request.getRequestDispatcher("/customer/changePassword.jsp").forward(request, response);
                return;
            }

            // Get database connection
            conn = DatabaseConnection.getConnection();

            // Verify current password
            String sql = "SELECT password FROM Users WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String decryptedStoredPassword = EncryptDecrypt.decrypt(storedPassword);
                if (!currentPassword.equals(decryptedStoredPassword)) {
                    request.setAttribute("errorMessage", "Current password is incorrect.");
                    request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("errorMessage", "User not found.");
                request.getRequestDispatcher("/customer/changePassword.jsp").forward(request, response);
                return;
            }

            // Encrypt new password
            String encryptedNewPassword = EncryptDecrypt.encrypt(newPassword);

            // Update password
            String updateSql = "UPDATE Users SET password = ? WHERE username = ?";
            ps = conn.prepareStatement(updateSql);
            ps.setString(1, encryptedNewPassword);
            ps.setString(2, username);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "Password changed successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update password.");
            }

            request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("customer/changePassword.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                // Assume DatabaseConnection handles connection pooling
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}