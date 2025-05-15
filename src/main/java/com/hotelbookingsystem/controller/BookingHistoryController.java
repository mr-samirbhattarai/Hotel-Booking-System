package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.BookingDAO;
import com.hotelbookingsystem.model.Bookings;

@WebServlet("/BookingHistory")
public class BookingHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        try {
            bookingDAO = new BookingDAO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize BookingDAO", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("/access/login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("user_id");
        ArrayList<Bookings> bookings = bookingDAO.getBookingsByUserId(userId.longValue());
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}