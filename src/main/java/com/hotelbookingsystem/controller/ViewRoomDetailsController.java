package com.hotelbookingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.model.Rooms;

@WebServlet("/ViewRoomDetailsController")
public class ViewRoomDetailsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String roomId = request.getParameter("roomId");
        
        if (roomId == null || roomId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
            return;
        }
        
        try {
            RoomDAO roomDAO = new RoomDAO();
            Rooms room = roomDAO.getRoomById(Long.parseLong(roomId));
            
            if (room == null) {
                response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
                return;
            }
            
            request.setAttribute("room", room);
            request.getRequestDispatcher("/customer/roomDetails.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
        }
    }
}