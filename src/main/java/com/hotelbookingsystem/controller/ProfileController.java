package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.model.Users;

@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		try {
			userDAO = new UserDAO();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServletException("Database connection error", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null
				|| !"customer".equalsIgnoreCase((String) session.getAttribute("role"))) {
			response.sendRedirect(request.getContextPath() + "/access/login.jsp");
			return;
		}

		String username = (String) session.getAttribute("username");
		try {
			Users user = userDAO.getUserByUsername(username);
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("/customer/profile.jsp").forward(request, response);
			} else {
				// Handle case where user is not found
				request.setAttribute("errorMessage", "User profile not found. Please log in again.");
				response.sendRedirect(request.getContextPath() + "/access/login.jsp");
			}
		} catch (SQLException e) {
			request.setAttribute("errorMessage", "Database error occurred. Please try again later.");
			response.sendRedirect(request.getContextPath() + "/access/login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null
				|| !"customer".equalsIgnoreCase((String) session.getAttribute("role"))) {
			response.sendRedirect(request.getContextPath() + "/access/login.jsp");
			return;
		}

		String username = (String) session.getAttribute("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phoneNo = request.getParameter("phoneNo");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String dobStr = request.getParameter("dob");
		String gender = request.getParameter("gender");

		java.sql.Date dob = null;
		try {
			if (dobStr != null && !dobStr.trim().isEmpty()) {
				dob = java.sql.Date.valueOf(dobStr); // Format: yyyy-MM-dd
			}
		} catch (IllegalArgumentException e) {
			request.setAttribute("errorMessage", "Invalid date format.");
		}

		try {
			Users existingUser = userDAO.getUserByUsername(username);
			if (existingUser == null) {
				response.sendRedirect(request.getContextPath() + "/access/login.jsp");
				return;
			}

			// Update user fields
			existingUser.setFirstName(firstname);
			existingUser.setLastName(lastname);
			existingUser.setEmail(email);
			existingUser.setPhoneNo(phoneNo);
			existingUser.setAddress(address);
			existingUser.setDob(dob);
			existingUser.setGender(gender);

			boolean updated = userDAO.updateUserProfile(existingUser);
			if (updated) {
				request.setAttribute("successMessage", "Profile updated successfully.");
			} else {
				request.setAttribute("errorMessage", "Failed to update profile.");
			}

			request.setAttribute("user", existingUser);
			request.getRequestDispatcher("/customer/profile.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database error occurred.");
			request.getRequestDispatcher("/customer/profile.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}