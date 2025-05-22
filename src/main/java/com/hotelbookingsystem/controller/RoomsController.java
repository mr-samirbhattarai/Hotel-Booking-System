package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.model.Rooms;

@WebServlet("/RoomsController")
public class RoomsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoomsController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RoomDAO roomDao = new RoomDAO();
			String roomType = request.getParameter("roomType");
			ArrayList<Rooms> rooms;

			if (roomType != null && !roomType.trim().isEmpty()) {
				rooms = roomDao.getRoomsByType(roomType);
			} else {
				rooms = roomDao.getAllRooms();
			}

			request.setAttribute("rooms", rooms);
			request.getRequestDispatcher("/customer/rooms.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Could not get the Rooms! Error: " + e.getMessage());
			request.getRequestDispatcher("/customer/rooms.jsp").forward(request, response);
		}
	}
}