package com.hotelbookingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.RoomDAO;

@WebServlet("/DeleteRoomController")
public class DeleteRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Extract roomId from the request parameter
        String roomIdStr = request.getParameter("roomId");
        if (roomIdStr == null || roomIdStr.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Room ID is missing.");
            response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdStr.trim());
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Invalid room ID format.");
            response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
            return;
        }

        // Delete the room
        try {
            RoomDAO dao = new RoomDAO();
            boolean isDeleted = dao.deleteRoom(roomId);
            if (isDeleted) {
                request.getSession().setAttribute("successMessage", "Room deleted successfully.");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to delete room.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting room: " + e.getMessage());
        }

        // Redirect back to manageRooms.jsp
        response.sendRedirect(request.getContextPath() + "/ManageRoomsController?page=viewRoomsForAdmin");
    }
}