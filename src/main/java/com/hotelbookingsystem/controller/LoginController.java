package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;
import com.hotelbookingsystem.utility.EncryptDecrypt;

@WebServlet(asyncSupported = true, urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle POST request (Login)
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get form parameters (email & password)
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Get session
        HttpSession session = request.getSession();

        // Store email for form repopulation
        session.setAttribute("email", email);

        Connection conn = null;
        try {
            // Validation for empty email or password
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("emailError", "Email is required");
                request.getRequestDispatcher("access/login.jsp").forward(request, response);
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("passwordError", "Password is required");
                request.getRequestDispatcher("access/login.jsp").forward(request, response);
                return;
            }

            // Get database connection
            conn = DatabaseConnection.getConnection();
            UserDAO userDAO = new UserDAO();

            // Query user using encrypted password
            // for customer 
            Users users = userDAO.login(email, EncryptDecrypt.encrypt(password));
            
//            for admin
//            Users users = userDAO.login(email, password);


            if (users != null) {
                // Login successful - Set session attributes
                session.setAttribute("user_id", users.getUserId());
                session.setAttribute("username", users.getUsername()); // Use username, not email
                session.setAttribute("role", users.getRole());
                session.setAttribute("email", users.getEmail());
                session.setAttribute("loggedInCustomer", users); // Full user object stored in session

                session.setAttribute("currentUser", users);
                
                session.removeAttribute("email");

                // Redirect based on role
                String role = users.getRole();
                String contextPath = request.getContextPath();

                if ("admin".equalsIgnoreCase(role)) {
                    response.sendRedirect(contextPath + "/dashboard");
                } else { // customer or other roles
                    response.sendRedirect(contextPath + "/customer/home.jsp");
                }
            } else {
                // Invalid email or password
                request.setAttribute("passwordNotMatchError", "Invalid email or password");
                request.getRequestDispatcher("access/login.jsp").forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("access/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("access/login.jsp").forward(request, response);
        } finally {
            try {
                // Connection pooling management assumed by DatabaseConnection
                // If manual closing is needed, uncomment:
                // if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Handle GET request (Redirect to login page or home if already logged in)
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // If session exists and user is already logged in
        if (session != null && session.getAttribute("username") != null) {
            // Redirect based on user role
            String role = (String) session.getAttribute("role");
            if ("admin".equalsIgnoreCase(role)) {
                response.sendRedirect("/admin/dashboard.jsp");
            } else {
                response.sendRedirect("/customer/home.jsp");
            }
            return;
        }

        // If not logged in, show the login page
        request.getRequestDispatcher("access/login.jsp").forward(request, response);
    }
}