package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.BookingDAO;
import com.hotelbookingsystem.model.Bookings;

/**
 * Servlet implementation class CheckInController
 */
@WebServlet("/CheckInController")
public class CheckInController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckInController() {
        super();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            BookingDAO bookingDAO = new BookingDAO();
//            ArrayList<Bookings> bookings = bookingDAO.getAllPendingBookings();
//            request.setAttribute("bookings", bookings);
//            request.getRequestDispatcher("/admin/checking.jsp").forward(request, response);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage", "Could not get the bookings.");
//            request.getRequestDispatcher("/admin/checking.jsp").forward(request, response);
//        }
//    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("searchName");

        try {
        	BookingDAO bookingDAO = new BookingDAO();
            ArrayList<Bookings> bookings;
            if (searchName != null && !searchName.trim().isEmpty()) {
                bookings = bookingDAO.searchPendingBookingsByCustomerName(searchName.trim());
            } else {
                bookings = bookingDAO.getAllPendingBookings();
            }

            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/admin/checking.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error retrieving bookings", e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        try {
            long bookingId = Long.parseLong(bookingIdStr);
            BookingDAO bookingDAO = new BookingDAO();
            bookingDAO.updateCheckInStatus(bookingId);
            request.getSession().setAttribute("successMessage", "Booking checked in successfully.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Invalid booking ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Failed to update check-in status.");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect(request.getContextPath() + "/CheckInController");
    }
}
