package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.model.Rooms;
import com.hotelbookingsystem.model.Rooms.BedType;
import com.hotelbookingsystem.model.Rooms.RoomType;

@WebServlet("/GetRoomById")
public class GetRoomById extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String roomIdParam = request.getParameter("roomId");
        
        if (roomIdParam == null || roomIdParam.trim().isEmpty()) {
            request.setAttribute("errorMessage", "No room ID specified.");
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
            return;
        }
        
        try {
            int roomId = Integer.parseInt(roomIdParam);
            RoomDAO dao = new RoomDAO();
            Rooms room = dao.getRoomById(roomId);
            
            if (room == null) {
                request.setAttribute("errorMessage", "Room not found.");
                request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
                return;
            }
            
            // Set room data and enum values for form dropdowns
            request.setAttribute("room", room);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid room ID format.");
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
        }
    }
}