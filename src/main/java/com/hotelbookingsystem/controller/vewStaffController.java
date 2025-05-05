package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.ViewStaffDAO;
import com.hotelbookingsystem.model.Users;

/**
 * Servlet implementation class GetCategoriesServlet
 */
@WebServlet("/ViewEmployeeController")
public class vewStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public vewStaffController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ViewStaffDAO dao = new ViewStaffDAO();
			ArrayList<Users> users = dao.getAllEmployees();
//            System.out.println("Employees: " + employees);  // Check the list contents

			if (users != null) {
				request.setAttribute("users", users);
			} else {
				request.setAttribute("errorMessage", "No employees found.");
			}
			request.setAttribute("users", users);
			request.getRequestDispatcher("/admin/viewStaff.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Unable to load employee list.");

			request.getRequestDispatcher("/admin/viewEmployees.jsp").forward(request, response);
		}
	}
}