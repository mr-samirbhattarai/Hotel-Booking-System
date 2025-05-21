//package com.hotelbookingsystem.controller;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.List;
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
//@WebServlet("/GetBookingByStatus")
//public class GetBookingByStatus extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private BookingDAO bookingDAO;
//   
//    @Override
//    public void init() throws ServletException {
//        try {
//            bookingDAO = new BookingDAO();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new ServletException("Failed to initialize BookingDAO", e);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            List<Bookings> bookingList = bookingDAO.getAllBookings();
//            request.setAttribute("bookingList", bookingList);
//            request.setAttribute("selectedStatus", "all");
////            request.setAttribute("users", bookingDAO.getAllUsers());
////            request.setAttribute("rooms", bookingDAO.getAllRooms());
//            request.getRequestDispatcher("/admin/bookings.jsp").forward(request, response);
//        } catch (Exception e) {
//            request.setAttribute("errorMessage", "Error retrieving bookings: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            String action = request.getParameter("action");
//            List<Bookings> bookingList;
//            String selectedStatus = "all";
//            String searchName = null;
//
//            if (action == null) {
//                action = "filter";
//            }
//
//            switch (action) {
//                case "filter":
//                    String status = request.getParameter("status");
//                    if (status == null || status.equals("all")) {
//                        bookingList = bookingDAO.getAllBookings();
//                        selectedStatus = "all";
//                    } else {
//                        if (!isValidStatus(status)) {
//                            request.setAttribute("errorMessage", "Invalid status: " + status);
//                            bookingList = bookingDAO.getAllBookings();
//                            selectedStatus = "all";
//                        } else {
//                            bookingList = bookingDAO.getBookingsByStatus(status);
//                            selectedStatus = status;
//                        }
//                    }
//                    break;
//
//                case "checkIn":
//                    int bookingIdCheckIn = Integer.parseInt(request.getParameter("bookingId"));
//                    Date checkInDate = null;
//				boolean isCheckedIn = bookingDAO.checkInBooking(bookingIdCheckIn, checkInDate);
//                    if (isCheckedIn) {
//                        request.setAttribute("successMessage", "Customer checked in successfully!");
//                    } else {
//                        request.setAttribute("errorMessage", "Failed to check in customer. Booking may not exist or is not pending.");
//                    }
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//                default:
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//            }
//
//            request.setAttribute("bookingList", bookingList);
//            request.setAttribute("selectedStatus", selectedStatus);
//            request.getRequestDispatcher("/admin/bookings.jsp").forward(request, response);
//        } catch (Exception e) {
//            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
//        }
//    }
//
//	private boolean isValidStatus(String status) {
//        try {
//            String string = new String();
//			string.valueOf(status.toUpperCase());
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
//}