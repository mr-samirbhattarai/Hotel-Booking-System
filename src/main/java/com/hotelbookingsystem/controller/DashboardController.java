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
import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.DAO.UserDAO;
import com.hotelbookingsystem.model.Bookings;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        try {
			userDAO = new UserDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			roomDAO = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bookingDAO = new BookingDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch metrics
        int totalUsers = userDAO.getTotalUsers();
        int availableRooms = roomDAO.getAvailableRooms();
        int unavailableRooms = roomDAO.getUnavailableRooms();
        int pendingBookings = bookingDAO.getPendingBookings();
        ArrayList<Bookings> pendingBookingsListDesc = bookingDAO.getPendingBookingsListDesc();


        // Set attributes
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("availableRooms", availableRooms);
        request.setAttribute("unavailableRooms", unavailableRooms);
        request.setAttribute("pendingBookings", pendingBookings);
        request.setAttribute("pendingBookingList", pendingBookingsListDesc); // for displaying table if needed


        // Forward to JSP
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}