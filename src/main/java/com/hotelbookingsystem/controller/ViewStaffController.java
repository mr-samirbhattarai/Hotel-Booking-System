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

@WebServlet("/ViewStaffController")
public class ViewStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewStaffController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ViewStaffDAO dao = new ViewStaffDAO();
            ArrayList<Users> users = dao.getAllStaffs();

            if (users.isEmpty()) {
                request.setAttribute("errorMessage", "No Staff found.");
            } else {
                request.setAttribute("users", users);
            }

            // Always forward to the same JSP
            request.getRequestDispatcher("/admin/viewStaff.jsp")
                   .forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to load Staff list: " + e.getMessage());
            request.getRequestDispatcher("/admin/viewStaff.jsp")
                   .forward(request, response);
        }
    }
}
