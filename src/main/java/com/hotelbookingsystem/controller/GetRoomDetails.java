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

/**
 * Servlet implementation class ManageRoomsController
 */
@WebServlet("/roomDetails")
public class GetRoomDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRoomDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String page = request.getParameter("page");
		try {
            RoomDAO dao = new RoomDAO();
            ArrayList<Rooms> rooms = dao.getAllRooms();
			 // Store categories in request/session to display in Dashboard
	        request.setAttribute("rooms", rooms);
	        
	        if ("viewRooms".equals(page)) {
	        	// Forward to rooms.jsp if the 'page' parameter is 'viewRoom'
                request.setAttribute("rooms", rooms);
                request.getRequestDispatcher("/customer/rooms.jsp").forward(request, response);
	        } else if ("manageRooms".equals(page)) {
                // Forward to manageRooms.jsp if the 'page' parameter is 'manageRooms'
                request.setAttribute("rooms", rooms);
                request.getRequestDispatcher("/admin/manageRooms.jsp").forward(request, response);
            } else if ("roomStatus".equals(page)) {
                // Forward to roomStatus.jsp if the 'page' parameter is 'roomStatus'
                request.setAttribute("rooms", rooms);
                request.getRequestDispatcher("/admin/roomStatus.jsp").forward(request, response);
            } else {
                // Default behavior or handle error
                response.getWriter().append("Invalid page parameter.");
            }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Could not get the Rooms");
			request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
		} 
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}