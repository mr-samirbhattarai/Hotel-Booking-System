package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.model.Users;
import com.hotelbookingsystem.utility.EncryptDecrypt;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            userDAO = new UserDAO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize UserDAO", e);
        }
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form parameters
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String phoneNo = request.getParameter("phoneNo");
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
        session.setAttribute("phoneNo", phoneNo);
        session.setAttribute("address", address);
        session.setAttribute("gender", gender);
        session.setAttribute("dob", dob);

        try {
            // Validation
            if (firstname == null || firstname.trim().isEmpty()) {
                request.setAttribute("errorMessage", "First name is required");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }
            if (lastname == null || lastname.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Last name is required");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }
            if (username == null || username.trim().isEmpty() || !username.matches("[a-zA-Z0-9]{3,50}")) {
                request.setAttribute("errorMessage", "Username must be 3-50 characters, letters and numbers only");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }
            if (email == null || email.trim().isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                request.setAttribute("errorMessage", "Valid email is required");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }
            if (password == null || password.length() < 6) {
                request.setAttribute("errorMessage", "Password must be at least 6 characters");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }
            if (!password.equals(retypePassword)) {
                request.setAttribute("errorMessage", "Passwords do not match");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }

            // Check for duplicate username or email
            if (userDAO.existsByUsernameOrEmail(username, email)) {
                request.setAttribute("errorMessage", "Username or email already exists");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
                return;
            }

            // Encrypt password
            String encryptedPassword = EncryptDecrypt.encrypt(password);

            // Create Users object
            Users user = new Users();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(encryptedPassword);
            user.setRole("customer");
            user.setPhoneNo(phoneNo != null && !phoneNo.isEmpty() ? phoneNo : null);
            user.setAddress(address != null && !address.isEmpty() ? address : null);
            user.setGender(gender != null && !gender.isEmpty() ? gender : null);
            if (dob != null && !dob.isEmpty()) {
                try {
                    user.setDob(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()));
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Invalid date format");
                    request.getRequestDispatcher("access/register.jsp").forward(request, response);
                    return;
                }
            }

            // Register user via UserDAO
            boolean registered = userDAO.register(user);
            if (registered) {
                // Clear session attributes on success
                session.removeAttribute("firstname");
                session.removeAttribute("lastname");
                session.removeAttribute("username");
                session.removeAttribute("email");
                session.removeAttribute("phoneNo");
                session.removeAttribute("address");
                session.removeAttribute("gender");
                session.removeAttribute("dob");
                response.sendRedirect("access/login.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("access/register.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            // Log detailed error (use a logger like SLF4J in production)
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred. Please try again later.");
            request.getRequestDispatcher("access/register.jsp").forward(request, response);
        } catch (Exception e) {
            // Log detailed error
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("access/register.jsp").forward(request, response);

        } finally {
            try {
                if (rs != null) {
					rs.close();
				}
                if (ps != null)
				 {
					ps.close();
					// Assume DatabaseConnection handles connection pooling
					// If manual closing is needed, uncomment: if (conn != null) conn.close();
				}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Clear session attributes on GET to start fresh
        HttpSession session = request.getSession();
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.removeAttribute("username");
        session.removeAttribute("email");
        session.removeAttribute("phoneNo");
        session.removeAttribute("address");
        session.removeAttribute("gender");
        session.removeAttribute("dob");
        response.sendRedirect("access/register.jsp");
    }
}