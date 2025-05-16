package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
        List<Rooms> rooms = roomDAO.getAllRooms();
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
        String phoneNo = request.getParameter("phoneNo");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        int numberOfGuests;
        try {
            numberOfGuests = Integer.parseInt(request.getParameter("numberOfGuests"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number of guests");
            request.getRequestDispatcher("customer/booking.jsp").forward(request, response);
            return;
        }

        try {
            // Fetch an available room
            Rooms room = roomDAO.getAvailableRoom();
            if (room == null) {
                request.setAttribute("error", "No rooms available at the moment");
                request.getRequestDispatcher("customer/booking.jsp").forward(request, response);
                return;
            }

            // Update user profile
            Users user = userDAO.getUserById(userId);
            if (phoneNo != null && !phoneNo.isEmpty()) user.setPhoneNo(phoneNo);
            if (address != null && !address.isEmpty()) user.setAddress(address);
            if (gender != null && !gender.isEmpty()) user.setGender(gender);
            if (dob != null && !dob.isEmpty()) user.setDob(Date.valueOf(dob));
            userDAO.updateUserProfile(user);

            // Create booking
            Bookings booking = new Bookings();
            booking.setStatus("pending");
            booking.setUserId(userId.longValue());
            booking.setRoomId(room.getRoomId()); // Set the roomId from the available room
            booking.setCheckInDate(Date.valueOf(checkInDate));
            booking.setCheckOutDate(Date.valueOf(checkOutDate));
            booking.setNumberOfGuests(numberOfGuests);
            booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            if (bookingDAO.addNewBooking(booking)) {
                response.sendRedirect("BookingHistory");
            } else {
                request.setAttribute("error", "Failed to create booking");
                request.getRequestDispatcher("customer/booking.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}