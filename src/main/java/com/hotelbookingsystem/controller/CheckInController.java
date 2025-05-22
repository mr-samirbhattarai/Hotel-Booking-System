//package com.hotelbookingsystem.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.hotelbookingsystem.DAO.BookingDAO;
//import com.hotelbookingsystem.model.Bookings;
//
//@WebServlet("/CheckInController")
//public class CheckInController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private BookingDAO bookingDAO;
//    private static final Logger LOGGER = Logger.getLogger(CheckInController.class.getName());
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            bookingDAO = new BookingDAO();
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, "Failed to initialize BookingDAO", e);
//            throw new ServletException("Failed to initialize BookingDAO", e);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Fetch pending bookings
//            List<Bookings> bookingList = bookingDAO.getBookingsByStatus("pending");
//            // Set bookingList attribute for checkIn.jsp
//            request.setAttribute("bookingList", bookingList);
//            // Forward to checkIn.jsp
//            request.getRequestDispatcher("/admin/checkIn.jsp").forward(request, response);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Error retrieving check-in requests", e);
//            request.setAttribute("errorMessage", "Error retrieving check-in requests: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Get bookingId from form
//            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
//            // Set actual check-in time to current time
//            LocalDateTime actualCheckIn = LocalDateTime.now();
//
//            // Update booking status to checked-in
//            boolean isCheckedIn = bookingDAO.checkInBooking(bookingId, actualCheckIn);
//            if (isCheckedIn) {
//                // Set success message
//                request.getSession().setAttribute("successMessage", "Customer checked in successfully!");
//            } else {
//                request.getSession().setAttribute("errorMessage", "Failed to check in customer. Booking may not exist or is not pending.");
//            }
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Error during check-in", e);
//            request.getSession().setAttribute("errorMessage", "Error during check-in: " + e.getMessage());
//        }
//        // Redirect to refresh the page
//        response.sendRedirect(request.getContextPath() + "/CheckInController");
//    }
//}