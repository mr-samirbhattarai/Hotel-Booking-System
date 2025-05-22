package com.hotelbookingsystem.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.model.Users;

/**
 * Servlet implementation class GetCategoriesServlet
 */
@WebServlet("/ViewCustomerController")
public class ViewCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UserDAO dao= new UserDAO();
            ArrayList<Users> users = dao.getAllUsers();
            if (users != null && !users.isEmpty()) {
                request.setAttribute("users", users);
            } else {
                request.setAttribute("errorMessage", "No users found.");
            }

            request.getRequestDispatcher("/admin/viewCustomer.jsp").forward(request, response);
                 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to load user list.");
            request.getRequestDispatcher("/admin/viewCustomer.jsp").forward(request, response);
        }
    }
}