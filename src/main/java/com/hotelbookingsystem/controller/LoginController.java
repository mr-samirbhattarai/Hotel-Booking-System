package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(asyncSupported = true, urlPatterns = { "/LogInController" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	
    	// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
    	
    	
    	
    	
    	
    	// Retrieve form parameters
    	
    	

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Initialize flags for validation
        boolean hasError = false;

        // Validate inputs
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("emailError", "Email is required");
            hasError = true;
        }
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("passwordError", "Password is required");
            hasError = true;
        }

        // If there are validation errors, forward back to the login page
        if (hasError) {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/hotelDB");
            conn = ds.getConnection();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // In a real application, compare hashed passwords

            rs = pstmt.executeQuery();
            if (rs.next()) {
                // Successful login
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("userId", rs.getInt("id")); // Assuming user table has an id column
                response.sendRedirect(request.getContextPath() + "/pages/home.jsp"); // Redirect to home page
            } else {
                // Invalid credentials
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Login error: " + e.getMessage());
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}