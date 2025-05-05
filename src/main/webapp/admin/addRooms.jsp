<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Room</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group input[type="file"] {
            padding: 3px;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
        .success-message {
            color: green;
            font-weight: bold;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Add New Room</h2>

        <%-- Show success message if redirected after successful insert --%>
        <% if ("true".equals(request.getParameter("success"))) { %>
            <p class="success-message">Room added successfully!</p>
        <% } %>

        <%-- Show specific error message from servlet --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>

        <% if (request.getAttribute("roomNumberExistsError") != null) { %>
            <p class="error-message"><%= request.getAttribute("roomNumberExistsError") %></p>
        <% } %>

        <form action="${pageContext.request.contextPath}/addRoom" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="roomNumber">Room Number:</label>
                <input type="text" id="roomNumber" name="roomNumber" required>
            </div>

            <div class="form-group">
                <label for="roomType">Room Type:</label>
                <select id="roomType" name="roomType" required>
                    <option value="">Select Room Type</option>
                    <option value="SINGLE">Single</option>
                    <option value="DOUBLE">Double</option>
                    <option value="SUITE">Suite</option>
                    <option value="DELUXE">Deluxe</option>
                </select>
            </div>

            <div class="form-group">
                <label for="price">Price Per Night (Rs.):</label>
                <input type="number" step="0.01" id="price" name="price" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="roomImage">Room Image:</label>
                <input type="file" id="roomImage" name="roomImage" accept="image/*" required>
            </div>

            <button type="submit" class="submit-btn">Add Room</button>
        </form>
        <br>
        <a href="rooms.jsp">View All Rooms</a>
    </div>
</body>
</html>