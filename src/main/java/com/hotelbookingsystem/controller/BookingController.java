package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.BookingDAO;
import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.model.Bookings;
import com.hotelbookingsystem.model.Rooms;
import com.hotelbookingsystem.model.Users;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        try {
            userDAO = new UserDAO();
            roomDAO = new RoomDAO();
            bookingDAO = new BookingDAO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize DAOs", e);
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
        Users user = userDAO.getUserById(userId);
        ArrayList<Rooms> rooms = roomDAO.getAllRooms();
        request.setAttribute("user", user);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("/access/login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("user_id");

        // Fetch user info and validate profile
        Users user = userDAO.getUserById(userId);
        if (user.getPhoneNo() == null || user.getPhoneNo().isEmpty() ||
            user.getGender() == null || user.getGender().isEmpty() ||
            user.getAddress() == null || user.getAddress().isEmpty() ||
            user.getDob() == null) {
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/customer/profile.jsp").forward(request, response);
        	return;
        }

        // Retrieve form data
        String checkInDateStr = request.getParameter("checkInDate");
        String checkOutDateStr = request.getParameter("checkOutDate");

        java.sql.Date checkInDate;
        java.sql.Date checkOutDate;
        try {
            checkInDate = Date.valueOf(checkInDateStr);
            checkOutDate = Date.valueOf(checkOutDateStr);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        // Validate date logic
        Date today = new Date(System.currentTimeMillis());
        if (checkInDate.before(today)) {
            request.setAttribute("error", "Check-in date cannot be in the past.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        long difference = checkOutDate.getTime() - checkInDate.getTime();
        if (difference < 24 * 60 * 60 * 1000) { // less than 1 day
            request.setAttribute("error", "Checkout date must be at least 1 day after check-in.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        // Validate guest count
        int numberOfGuests;
        try {
            numberOfGuests = Integer.parseInt(request.getParameter("numberOfGuests"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number of guests.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        // Room validation
        String roomIdStr = request.getParameter("roomId");
        if (roomIdStr == null || roomIdStr.isEmpty()) {
            request.setAttribute("error", "Room ID is missing in the request.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid room ID.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        Rooms room = roomDAO.getRoomById(roomId);
        if (room == null) {
            request.setAttribute("error", "Selected room does not exist.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }
        
        boolean isAvailable = bookingDAO.isRoomAvailable(roomId, checkInDate, checkOutDate);
        if (!isAvailable) {
            request.setAttribute("error", "The selected room is not available for the chosen dates.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
            return;
        }

        // Create booking
        Bookings booking = new Bookings();
        booking.setStatus("pending");
        booking.setUserId(userId.longValue());
        booking.setRoomId(roomId);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setNumberOfGuests(numberOfGuests);
        booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        if (bookingDAO.addNewBooking(booking)) {
            response.sendRedirect("BookingHistory");
        } else {
            request.setAttribute("error", "Failed to create booking.");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
        }
    }

}
