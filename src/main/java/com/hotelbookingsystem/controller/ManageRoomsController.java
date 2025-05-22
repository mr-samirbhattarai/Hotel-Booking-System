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
@WebServlet("/ManageRoomsController")
public class ManageRoomsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageRoomsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
            RoomDAO dao = new RoomDAO();
            ArrayList<Rooms> rooms = dao.getAllRooms();
            if (rooms != null && !rooms.isEmpty()) {
                request.setAttribute("rooms", rooms);
            } else {
                request.setAttribute("errorMessage", "No room found.");
            }

            request.getRequestDispatcher("/admin/manageRooms.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to load room list.");
            request.getRequestDispatcher("/admin/manageRooms.jsp").forward(request, response);
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