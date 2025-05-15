package com.hotelbookingsystem.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hotelbookingsystem.DAO.RoomDAO;
import com.hotelbookingsystem.model.Rooms;
import com.hotelbookingsystem.model.Rooms.BedType;
import com.hotelbookingsystem.model.Rooms.RoomType;

@WebServlet("/UpdateRoomController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UpdateRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract form data
        String roomIdStr = request.getParameter("roomId");
        String roomNumber = request.getParameter("roomNumber");
        String roomTypeParam = request.getParameter("roomType");
        String roomOfBedsStr = request.getParameter("noOfBeds");
        String bedTypeParam = request.getParameter("bedType");
        String roomAreaStr = request.getParameter("roomArea");
        String priceParam = request.getParameter("price");
        String floorNumberStr = request.getParameter("floorNumber");
        String maxOccupancyStr = request.getParameter("maxOccupancy");
        String description = request.getParameter("description");
        Part imagePart = request.getPart("roomImage");

        // Validate roomId
        if (roomIdStr == null || roomIdStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Room ID is missing.");
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdStr.trim());
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid room ID format.");
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        // Retrieve existing room
        Rooms existingRoom;
        try {
            RoomDAO dao = new RoomDAO();
            existingRoom = dao.getRoomById(roomId);
            if (existingRoom == null) {
                request.setAttribute("errorMessage", "Room not found with ID: " + roomId);
                request.setAttribute("roomTypes", RoomType.values());
                request.setAttribute("bedTypes", BedType.values());
                request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to retrieve room: " + e.getMessage());
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        // Validate other fields
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            request.setAttribute("roomNumberError", "Room number is required.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        RoomType roomType = null;
        try {
            roomType = RoomType.valueOf(roomTypeParam.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            request.setAttribute("roomTypeError", "Invalid or missing room type.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        int noOfBeds;
        try {
            noOfBeds = Integer.parseInt(roomOfBedsStr.trim());
            if (noOfBeds < 1 || noOfBeds > 4) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("noOfBedsError", "Number of beds must be between 1 and 4.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        BedType bedType;
        try {
            bedType = BedType.valueOf(bedTypeParam.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            request.setAttribute("bedTypeError", "Invalid or missing bed type.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        double roomArea;
        try {
            roomArea = Double.parseDouble(roomAreaStr.trim());
            if (roomArea < 1 || roomArea > 10000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("roomAreaError", "Room area must be between 1 and 10,000.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceParam.trim());
            if (price < 1 || price > 100000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("priceError", "Price must be between 1 and 100,000.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        int floorNumber;
        try {
            floorNumber = Integer.parseInt(floorNumberStr.trim());
            if (floorNumber < 1 || floorNumber > 11) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("floorNumberError", "Floor number must be between 1 and 11.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        int maxOccupancy;
        try {
            maxOccupancy = Integer.parseInt(maxOccupancyStr.trim());
            if (maxOccupancy < 1 || maxOccupancy > 6) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("maxOccupancyError", "Occupancy must be between 1 and 6.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        if (description == null || description.trim().isEmpty()) {
            request.setAttribute("descriptionError", "Description is required.");
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            return;
        }

        // Check room number uniqueness ONLY if the room number has changed
        if (!existingRoom.getRoomNumber().equals(roomNumber)) {
            try {
                RoomDAO dao = new RoomDAO();
                if (dao.roomNumberExists(roomNumber)) {
                    request.setAttribute("roomNumberExistsError", "This room number is already taken.");
                    request.setAttribute("room", existingRoom);
                    request.setAttribute("roomTypes", RoomType.values());
                    request.setAttribute("bedTypes", BedType.values());
                    request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Failed to check room number: " + e.getMessage());
                request.setAttribute("room", existingRoom);
                request.setAttribute("roomTypes", RoomType.values());
                request.setAttribute("bedTypes", BedType.values());
                request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
                return;
            }
        }

        // Image handling
        String fileName = existingRoom.getRoomImage(); // Use existing if none uploaded
        if (imagePart != null && imagePart.getSize() > 0) {
            fileName = imagePart.getSubmittedFileName();
            if (fileName != null && !fileName.trim().isEmpty()) {
                String appPath = request.getServletContext().getRealPath("");
                String savePath = appPath + File.separator + "photos";
                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                imagePart.write(savePath + File.separator + fileName);
            }
        }

        // Update RoomModel
        Rooms updatedRoom = new Rooms();
        updatedRoom.setRoomId(roomId);
        updatedRoom.setRoomNumber(roomNumber);
        updatedRoom.setRoomType(roomType);
        updatedRoom.setNoOfBeds(noOfBeds);
        updatedRoom.setBedType(bedType);
        updatedRoom.setRoomArea(roomArea);
        updatedRoom.setPricePerNight(price);
        updatedRoom.setFloorNumber(floorNumber);
        updatedRoom.setMaxOccupancy(maxOccupancy);
        updatedRoom.setDescription(description);
        updatedRoom.setRoomImage(fileName);
        updatedRoom.setAvailable(existingRoom.isAvailable()); // Preserve availability status

        // Update room in DB
        try {
            RoomDAO dao = new RoomDAO();
            boolean isUpdated = dao.updateRoom(updatedRoom);
            if (isUpdated) {
                request.getSession().setAttribute("successMessage", "Room updated successfully");
                response.sendRedirect(request.getContextPath() + "/GetRoomsServlet?page=viewRoomsForAdmin");
            } else {
                request.setAttribute("errorMessage", "Failed to update room. No changes were applied.");
                request.setAttribute("room", existingRoom);
                request.setAttribute("roomTypes", RoomType.values());
                request.setAttribute("bedTypes", BedType.values());
                request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error updating room: " + e.getMessage());
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Database driver not found: " + e.getMessage());
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error redirecting after update: " + e.getMessage());
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/pages/updateRooms.jsp").forward(request, response);
        }
    }
}