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

@WebServlet("/addRoom")	//url pattern for the servlet
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddRoomController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String roomNumber = request.getParameter("roomNumber");
        String roomTypeParam = request.getParameter("roomType");
        String roomOfBedsStr = request.getParameter("noOfBeds");
	     String bedTypeParam = request.getParameter("bedType");
        String priceParam = request.getParameter("price");
        String roomAreaStr = request.getParameter("roomArea");
        String roomFloorStr = request.getParameter("roomFloorNumber");
        String maxOccupancyStr = request.getParameter("maxOccupancy");
        Part imagePart = request.getPart("roomImage");
        String description = request.getParameter("description");

        // Validate inputs    
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            request.setAttribute("roomNumberError", "Room number is required.");
            request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
            return;
        }

        if (roomTypeParam == null || roomTypeParam.trim().isEmpty()) {
            request.setAttribute("roomTypeError", "Room type is required.");
            request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
            return;
        }
        
        RoomType roomType = null;
        try {
            roomType = RoomType.valueOf(roomTypeParam.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid room type selected.");
            request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
            return;
        }
        
	     // Validate if the user entered a number of beds
	     if (roomOfBedsStr == null || roomOfBedsStr.trim().isEmpty()) {
	         request.setAttribute("noOfBedsEmptyError", "Number of beds in room is required.");
	         request.getRequestDispatcher("/adimn/addRooms.jsp").forward(request, response);
	         return;
	     }
	
	     int roomOfBeds;
	     try {
	         roomOfBeds = Integer.parseInt(roomOfBedsStr.trim());
	
	         // Check if the roomOfBeds value is within the valid range (1 to 4)
	         if (roomOfBeds < 1 || roomOfBeds > 4) {
	             request.setAttribute("noOfBedsFormatError", "Number of beds must be between 1 and 4.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	             return;
	         }
	     } catch (NumberFormatException e) {
	         // Handle case where the input is not a number
	         request.setAttribute("noOfBedsFormatError", "Please enter a valid number.");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }
	     

	     if (bedTypeParam == null || bedTypeParam.trim().isEmpty()) {
	         request.setAttribute("bedTypeError", "Bed type is required.");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }

	     BedType bedType = null;
	     try {
	         bedType = BedType.valueOf(bedTypeParam.trim().toUpperCase());
	     } catch (IllegalArgumentException e) {
	         request.setAttribute("errorMessage", "Invalid bed type selected.");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }
	     
	     if (priceParam == null || priceParam.trim().isEmpty()) {
			request.setAttribute("priceError", "Price is required.");
			request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
			return;
	     }
	     
	     
// 	for price validation price 1 to 1 lakhs samma matra hune	     
	     double price;
	     try {
	         price = Double.parseDouble(priceParam.trim());

	         if (price < 1 || price > 100000) {
	             request.setAttribute("priceFormatError", "Price must be between 1 and 100,000.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	             return;
	         }

	     } catch (NumberFormatException e) {
	         request.setAttribute("priceFormatError", "Please enter a valid price.");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }
	       
	     
		 // Validate if the user entered a area of room
		 if (roomAreaStr == null || roomAreaStr.trim().isEmpty()) {
		     request.setAttribute("roomAreaError", "Area of room is required.");
		     request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
		     return;
		 }
		
		 int roomArea;
		 try {
			 roomArea = Integer.parseInt(roomAreaStr.trim());
		
		     // Check if the roomOfBeds value is within the valid range (1 to 4)
		     if (roomArea < 1 || roomArea > 10000) {
		         request.setAttribute("roomAreaFormatError", "Area of room only must be between 1 and 10000.");
		         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
		         return;
		     }
		 } catch (NumberFormatException e) {
		     // Handle case where the input is not a number
		     request.setAttribute("roomAreaFormatError", "Please enter a valid area.");
		     request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
		     return;
		 }
		 
		// Validate the room floor number
		 if (roomFloorStr == null || roomFloorStr.trim().isEmpty()) {
		     request.setAttribute("roomFloorError", "Room Floor number is required.");
		     request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
		     return;
		 }
		
		 int roomFloor;
	     try {
	    	 roomFloor = Integer.parseInt(roomFloorStr.trim());
	
	         // Check if the room floor value is within the valid range 1st to 11th
	         if (roomFloor < 1) {
	             request.setAttribute("roomFloorFormatError", "The lowest available floor is 1.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	             return;
	         } else if (roomFloor > 11) {
	        	 request.setAttribute("roomFloorFormatError", "The highest available floor is 11.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         }
	         
	     } catch (NumberFormatException e) {
	         // Handle case where the input is not a number
	    	 request.setAttribute("roomFloorFormatError", "Please enter a valid floor number (e.g., 0, 1, 2).");
		     request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
		     return;
	     }
	     
	     
	  // Validate the maximum occupancy
	     if (maxOccupancyStr == null || maxOccupancyStr.trim().isEmpty()) {
	         request.setAttribute("maxOccupancyError", "Room maximum occupancy is required.");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }

	     int maxOccupancy;
	     try {
	         maxOccupancy = Integer.parseInt(maxOccupancyStr.trim());

	         // Check if the maximum occupancy is within the valid range (1 to 6)
	         if (maxOccupancy < 1) {
	             request.setAttribute("maxOccupancyFormatError", "The minimum occupancy is 1 guest.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	             return;
	         } else if (maxOccupancy > 6) {
	             request.setAttribute("maxOccupancyFormatError", "The maximum occupancy is 6 guests.");
	             request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	             return;
	         }
	     } catch (NumberFormatException e) {
	         request.setAttribute("maxOccupancyFormatError", "Please enter a valid number of guests (e.g., 1, 2, 3).");
	         request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
	         return;
	     }
		 
        
        if (description == null || description.trim().isEmpty()) {
            request.setAttribute("descriptionError", "Description is required.");
            request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
            return;
        }
        

        try {
            RoomDAO dao = new RoomDAO();
            if (dao.roomNumberExists(roomNumber)) {
                request.setAttribute("roomNumberExistsError", "This room number is already taken.");
                request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
                return;
            }

            
            String fileName = imagePart.getSubmittedFileName(); //get the filename from the uploaded file itself

            if (fileName != null && !fileName.trim().isEmpty()) {
                // Only proceed if the file name is not empty (i.e., a file was uploaded)
    	        // File upload handling
    			System.out.println(fileName);
    	
    			String storePath = request.getServletContext().getRealPath(""); //get path of deployment folder
    			String filePath = "photos"+File.separator+fileName;   //prepared file path like photos\fileName.jpg
    			System.out.println(storePath);  
    			System.out.println(filePath);
    			
    			File photoDir = new File(storePath + File.separator + "photos");
    			if (!photoDir.exists()) {
    			    photoDir.mkdirs(); // create directory if not exists
    			}
    	
    		    imagePart.write(storePath+File.separator +filePath); //upload file to selected path
    			System.out.println("File uploaded");
    	        
    	        
    	        String displayPath = request.getContextPath()+File.separator+filePath; //to use in jsp we can just add context to filePath from above
    			System.out.println(displayPath);
                System.out.println("****Boom::: Image uploaded successfull!****");

    			
            } else {
                System.out.println("No image uploaded");
                request.setAttribute("ImageError", "Please upload a room image.");
                request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
                return;
            }
            
            
            // Set room details
            Rooms room = new Rooms();
            room.setRoomNumber(roomNumber);
            room.setRoomType(roomType);
            room.setNoOfBeds(roomOfBeds);
            room.setBedType(bedType);
            room.setPricePerNight(price);
            room.setRoomArea(roomArea);
            room.setFloorNumber(roomFloor);
            room.setMaxOccupancy(maxOccupancy);
            room.setRoomImage(fileName); // Save file name only
            room.setDescription(description);
            

            // Add room to database
            boolean isAdded = dao.addNewRoom(room);
            if (isAdded) {
                // Redirect to avoid form resubmission
            	System.out.println("xxxxxxxxxxx Added Room Sucessfully! xxxxxxxxxxx");
                request.setAttribute("successMessage", "Added Room Successfully.");

                response.sendRedirect("admin/addRooms.jsp?success=true");
            } else {
                request.setAttribute("errorMessage", "Failed to add room.");
                request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Server error occurred. Please contact support.");
            request.getRequestDispatcher("/admin/addRooms.jsp").forward(request, response);
        }
    }
}