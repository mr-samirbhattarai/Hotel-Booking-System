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
        
        // Get parameters from the form
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
        
        boolean hasErrors = false;
        
        // Validate roomId
        long roomId = 0;
        if (roomIdStr == null || roomIdStr.trim().isEmpty()) {
            request.setAttribute("roomIdError", "Room ID is required.");
            hasErrors = true;
        } else {
            try {
                roomId = Long.parseLong(roomIdStr.trim());
                if (roomId <= 0) {
                    request.setAttribute("roomIdError", "Invalid room ID.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("roomIdError", "Please enter a valid room ID.");
                hasErrors = true;
            }
        }

        // Validate roomNumber
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            request.setAttribute("roomNumberError", "Room number is required.");
            hasErrors = true;
        }

        // Validate roomType
        RoomType roomType = null;
        if (roomTypeParam == null || roomTypeParam.trim().isEmpty()) {
            request.setAttribute("roomTypeError", "Room type is required.");
            hasErrors = true;
        } else {
            try {
                roomType = RoomType.valueOf(roomTypeParam.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                request.setAttribute("roomTypeError", "Invalid room type selected.");
                hasErrors = true;
            }
        }
        
        // Validate number of beds
        int roomOfBeds = 0;
        if (roomOfBedsStr == null || roomOfBedsStr.trim().isEmpty()) {
            request.setAttribute("noOfBedsEmptyError", "Number of beds is required.");
            hasErrors = true;
        } else {
            try {
                roomOfBeds = Integer.parseInt(roomOfBedsStr.trim());
                if (roomOfBeds < 1 || roomOfBeds > 4) {
                    request.setAttribute("noOfBedsFormatError", "Number of beds must be between 1 and 4.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("noOfBedsFormatError", "Please enter a valid number of beds.");
                hasErrors = true;
            }
        }

        // Validate bedType
        BedType bedType = null;
        if (bedTypeParam == null || bedTypeParam.trim().isEmpty()) {
            request.setAttribute("bedTypeError", "Bed type is required.");
            hasErrors = true;
        } else {
            try {
                bedType = BedType.valueOf(bedTypeParam.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                request.setAttribute("bedTypeError", "Invalid bed type selected.");
                hasErrors = true;
            }
        }
        
        // Validate price
        double price = 0;
        if (priceParam == null || priceParam.trim().isEmpty()) {
            request.setAttribute("priceError", "Price is required.");
            hasErrors = true;
        } else {
            try {
                price = Double.parseDouble(priceParam.trim());
                if (price < 1 || price > 100000) {
                    request.setAttribute("priceFormatError", "Price must be between 1 and 100,000.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("priceFormatError", "Please enter a valid price.");
                hasErrors = true;
            }
        }
        
        // Validate roomArea
        double roomArea = 0;
        if (roomAreaStr == null || roomAreaStr.trim().isEmpty()) {
            request.setAttribute("roomAreaError", "Room area is required.");
            hasErrors = true;
        } else {
            try {
                roomArea = Double.parseDouble(roomAreaStr.trim());
                if (roomArea < 1 || roomArea > 10000) {
                    request.setAttribute("roomAreaFormatError", "Area must be between 1 and 10,000.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("roomAreaFormatError", "Please enter a valid area.");
                hasErrors = true;
            }
        }
        
        // Validate floorNumber
        int floorNumber = 0;
        if (floorNumberStr == null || floorNumberStr.trim().isEmpty()) {
            request.setAttribute("floorNumberError", "Floor number is required.");
            hasErrors = true;
        } else {
            try {
                floorNumber = Integer.parseInt(floorNumberStr.trim());
                if (floorNumber < 1 || floorNumber > 11) {
                    request.setAttribute("floorNumberFormatError", "Floor number must be between 1 and 11.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("floorNumberFormatError", "Please enter a valid floor number (e.g., 1, 2, 3).");
                hasErrors = true;
            }
        }
        
        // Validate maxOccupancy
        int maxOccupancy = 0;
        if (maxOccupancyStr == null || maxOccupancyStr.trim().isEmpty()) {
            request.setAttribute("maxOccupancyError", "Maximum occupancy is required.");
            hasErrors = true;
        } else {
            try {
                maxOccupancy = Integer.parseInt(maxOccupancyStr.trim());
                if (maxOccupancy < 1 || maxOccupancy > 6) {
                    request.setAttribute("maxOccupancyFormatError", "Maximum occupancy must be between 1 and 6.");
                    hasErrors = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("maxOccupancyFormatError", "Please enter a valid number of guests (e.g., 1, 2, 3).");
                hasErrors = true;
            }
        }
        
        // Validate description
        if (description == null || description.trim().isEmpty()) {
            request.setAttribute("descriptionError", "Description is required.");
            hasErrors = true;
        }
        
        // Fetch existing room for error case
        Rooms existingRoom = null;
        try {
            RoomDAO dao = new RoomDAO();
            existingRoom = dao.getRoomById(roomId);
            if (existingRoom == null) {
                request.setAttribute("errorMessage", "Room not found with ID: " + roomId);
                hasErrors = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve room data: " + e.getMessage());
            hasErrors = true;
        }

        // If there are validation errors, return to form
        if (hasErrors) {
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
            return;
        }
        
        // Process the update
        try {
            RoomDAO dao = new RoomDAO();
            
            // Check if the room number is changed and already exists
            if (!existingRoom.getRoomNumber().equals(roomNumber) && dao.roomNumberExists(roomNumber)) {
                request.setAttribute("roomNumberExistsError", "This room number is already taken.");
                request.setAttribute("room", existingRoom);
                request.setAttribute("roomTypes", RoomType.values());
                request.setAttribute("bedTypes", BedType.values());
                request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
                return;
            }
            
            // Handle image upload if a new image is provided
            String fileName = existingRoom.getRoomImage(); // Default to existing image
            
            if (imagePart != null && imagePart.getSize() > 0) {
                fileName = imagePart.getSubmittedFileName();
                
                if (fileName != null && !fileName.trim().isEmpty()) {
                    String storePath = request.getServletContext().getRealPath("");
                    String filePath = "photos" + File.separator + fileName;
                    
                    File photoDir = new File(storePath + File.separator + "photos");
                    if (!photoDir.exists()) {
                        photoDir.mkdirs();
                    }
                    
                    imagePart.write(storePath + File.separator + filePath);
                }
            }
            
            // Update room model
            Rooms room = new Rooms();
            room.setRoomId(roomId);
            room.setRoomNumber(roomNumber);
            room.setRoomType(roomType);
            room.setNoOfBeds(roomOfBeds);
            room.setBedType(bedType);
            room.setPricePerNight(price);
            room.setRoomArea(roomArea);
            room.setFloorNumber(floorNumber);
            room.setMaxOccupancy(maxOccupancy);
            room.setRoomImage(fileName);
            room.setDescription(description);
            room.setAvailable(existingRoom.isAvailable());
            
            // Update room in database
            boolean isUpdated = dao.updateRoom(room);
            
            if (isUpdated) {
                request.getSession().setAttribute("successMessage", "Room updated successfully!");
                response.sendRedirect(request.getContextPath() + "/ManageRoomsController");
            } else {
                request.setAttribute("errorMessage", "Failed to update room. No changes were applied.");
                request.setAttribute("room", existingRoom);
                request.setAttribute("roomTypes", RoomType.values());
                request.setAttribute("bedTypes", BedType.values());
                request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Server error occurred: " + e.getMessage());
            request.setAttribute("room", existingRoom);
            request.setAttribute("roomTypes", RoomType.values());
            request.setAttribute("bedTypes", BedType.values());
            request.getRequestDispatcher("/admin/updateRoom.jsp").forward(request, response);
        }
    }
}