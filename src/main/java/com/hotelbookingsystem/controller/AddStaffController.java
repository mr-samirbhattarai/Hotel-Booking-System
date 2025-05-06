package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.AddStaffDAO;
import com.hotelbookingsystem.model.Users;


@WebServlet("/AddStaffController")
public class AddStaffController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");

        Users Users = new Users(firstname, lastname, username, password, phoneNo, email, address, dob, gender);
        AddStaffDAO addStaffDAO = new AddStaffDAO();

        if (addStaffDAO.addUser(Users)) {
            response.sendRedirect("admin/viewStaff.jsp");
        } else {
        	System.out.println("First Name: " + firstname);
            response.sendRedirect("error.jsp");
        }
        

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin/addStaff.jsp");
    }

}