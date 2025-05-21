<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Form</title>
    <link rel="stylesheet" href="css/booking.css">
</head>
<body>

<c:set var="activePage" value="home" scope="request" />   
   	<jsp:include page="/customer/header.jsp" />
        



    <div class="container">
        <h1>Book a Room</h1>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/BookingController" method="post">

            <h2>Booking Details</h2>
            <div class="form-group">
                <label for="checkInDate">Check-In Date:</label>
                <input type="date" id="checkInDate" name="checkInDate" >
            </div>
            <div class="form-group">
                <label for="checkOutDate">Check-Out Date:</label>
                <input type="date" id="checkOutDate" name="checkOutDate" >
            </div>
            <div class="form-group">
                <label for="numberOfGuests">Number of Guests:</label>
                <input type="number" id="numberOfGuests" name="numberOfGuests" min="1" >
            </div>

            <div class="form-group">
                <button type="submit">Submit Booking</button>
            </div>
        </form>
        <a href="RoomsController" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>