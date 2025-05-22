//package com.hotelbookingsystem.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
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
//                    LocalDateTime actualCheckIn = LocalDateTime.now();
//                    boolean isCheckedIn = bookingDAO.checkInBooking(bookingIdCheckIn, actualCheckIn);
//                    if (isCheckedIn) {
//                        request.setAttribute("successMessage", "Customer checked in successfully!");
//                    } else {
//                        request.setAttribute("errorMessage", "Failed to check in customer. Booking may not exist or is not pending.");
//                    }
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//
//                case "checkOut":
//                    int bookingIdCheckOut = Integer.parseInt(request.getParameter("bookingId"));
//                    LocalDateTime actualCheckOut = LocalDateTime.now();
//                    boolean isCheckedOut = bookingDAO.checkOutBooking(bookingIdCheckOut, actualCheckOut);
//                    if (isCheckedOut) {
//                        request.setAttribute("successMessage", "Customer checked out successfully!");
//                    } else {
//                        request.setAttribute("errorMessage", "Failed to check out customer. Booking may not exist or is not checked-in.");
//                    }
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//
//                case "addCustomer":
//                    int userId = Integer.parseInt(request.getParameter("userId"));
//                    int roomId = Integer.parseInt(request.getParameter("roomId"));
//                    LocalDate checkInDate = LocalDate.parse(request.getParameter("checkInDate"));
//                    LocalDate checkOutDate = LocalDate.parse(request.getParameter("checkOutDate"));
//                    String contactNumber = request.getParameter("contactNumber");
//                    String specialRequest = request.getParameter("specialRequest");
//                    boolean isAdded = bookingDAO.addBooking(userId, roomId, checkInDate, checkOutDate, contactNumber, specialRequest);
//                    if (isAdded) {
//                        request.setAttribute("successMessage", "Offline booking added successfully!");
//                    } else {
//                        request.setAttribute("errorMessage", "Failed to add offline booking.");
//                    }
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//
//                case "search":
//                    searchName = request.getParameter("customerName");
//                    if (searchName == null || searchName.trim().isEmpty()) {
//                        bookingList = bookingDAO.getAllBookings();
//                    } else {
//                        bookingList = bookingDAO.searchBookingsByCustomerName(searchName);
//                    }
//                    request.setAttribute("searchName", searchName);
//                    break;
//
//                default:
//                    bookingList = bookingDAO.getAllBookings();
//                    break;
//            }
//
//            request.setAttribute("bookingList", bookingList);
//            request.setAttribute("selectedStatus", selectedStatus);
//            request.setAttribute("users", bookingDAO.getAllUsers());
//            request.setAttribute("rooms", bookingDAO.getAllRooms());
//            request.getRequestDispatcher("/admin/bookings.jsp").forward(request, response);
//        } catch (Exception e) {
//            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
//        }
//    }
//
//    private boolean isValidStatus(String status) {
//        try {
//            Bookings.BookingStatus.valueOf(status.toUpperCase());
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
//}