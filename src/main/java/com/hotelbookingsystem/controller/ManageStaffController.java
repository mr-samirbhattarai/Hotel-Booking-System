package com.hotelbookingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.ManageStaffDAO;
import com.hotelbookingsystem.DAO.ViewStaffDAO;
import com.hotelbookingsystem.model.Users;

@WebServlet("/ManageStaffController")
public class ManageStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET handles deletions
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
			int userId = Integer.parseInt(request.getParameter("user_id"));
			try {
				new ManageStaffDAO().deleteUser(userId);
				request.setAttribute("users", new ViewStaffDAO().getAllStaffs());
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Delete failed: " + e.getMessage());
			}
			request.getRequestDispatcher("admin/viewStaff.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown GET action");
		}
	}

	// POST handles updates
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("update".equals(action)) {
			int userId = Integer.parseInt(request.getParameter("user_id"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phoneNo = request.getParameter("phoneNo");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String dobStr = request.getParameter("dob");
			String gender = request.getParameter("gender");

			java.sql.Date dob = null;
			if (dobStr != null && !dobStr.isEmpty()) {
				dob = java.sql.Date.valueOf(dobStr);
			}

			Users user = new Users(userId, firstName, lastName, phoneNo, email, address, dob, gender);

			try {
				new ManageStaffDAO().updateUser(user);
				request.setAttribute("users", new ViewStaffDAO().getAllStaffs());
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Update failed: " + e.getMessage());
			}
			request.getRequestDispatcher("admin/viewStaff.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown POST action");
		}
	}
}
