package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.BookingDAO;
import com.hotelbookingsystem.model.Bookings;

@WebServlet("/CancelBookingController")
public class CancelBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        try {
            bookingDAO = new BookingDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize BookingDAO", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("/access/login.jsp");
            return;
        }

        String bookingIdStr = request.getParameter("bookingId");
        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            request.setAttribute("error", "Invalid booking ID.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
            return;
        }

        long bookingId;
        try {
            bookingId = Long.parseLong(bookingIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid booking ID format.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
            return;
        }

        Bookings booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            request.setAttribute("error", "Booking not found.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
            return;
        }

        String status = booking.getStatus();
        if ("completed".equalsIgnoreCase(status)) {
            request.setAttribute("error", "Cannot cancel a completed booking.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
            return;
        }

        if (!"pending".equalsIgnoreCase(status)) {
            request.setAttribute("error", "Only pending bookings can be cancelled.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
            return;
        }

        boolean updated = false;
		try {
			updated = bookingDAO.updateBookingStatus(bookingId, "cancelled");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (updated) {
            response.sendRedirect("BookingHistory?message=Booking cancelled successfully");
        } else {
            request.setAttribute("error", "Failed to cancel booking.");
            request.getRequestDispatcher("/customer/viewBookingHistory.jsp").forward(request, response);
        }
    }
}
