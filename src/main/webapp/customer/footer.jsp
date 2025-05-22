<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer>
    <style>
        .footer {
        	position: relative;
            background-color: #333;
            padding: 15px;
            text-align: center;
            color: white;
            position: relative;
            bottom: 0;
            width: 100%;
            box-shadow: 0 -2px 5px rgba(0,0,0,0.1);
        }
        
        .footer-links a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
        }
        .footer-links a:hover {
            text-decoration: underline;
        }
        .footer p {
            margin: 10px 0 0;
            font-size: 14px;
        }
    </style>
    <div class="footer">
        <div class="footer-links">
            <a href="${pageContext.request.contextPath}/customer/home.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/RoomsController">Rooms</a>
            <a href="${pageContext.request.contextPath}/customer/aboutUs.jsp">About Us</a>
            <a href="${pageContext.request.contextPath}/contactUs.jsp">Contact Us</a>
        </div>
        <p>&copy; 2025 Hotel Booking System | RockStars. All rights reserved.</p>
    </div>
</footer>