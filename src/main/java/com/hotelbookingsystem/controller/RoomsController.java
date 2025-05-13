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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Initialize RoomDAO to get room data
            RoomDAO roomDao = new RoomDAO();
            ArrayList<Rooms> rooms = roomDao.getAllRooms();

            // Store rooms in request attribute to display in view
            request.setAttribute("rooms", rooms);

            // Forward to rooms.jsp to display the list of rooms
            request.getRequestDispatcher("/customer/rooms.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception and forward the error message to rooms.jsp
            e.printStackTrace();
            request.setAttribute("errorMessage", "Could not get the Rooms! Error: " + e.getMessage());
            request.getRequestDispatcher("/customer/rooms.jsp").forward(request, response);
        }
    }
}