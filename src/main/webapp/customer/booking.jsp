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
            <h2>User Information</h2>
            <div class="form-group">
                <label for="userId">User ID (Read-only):</label>
                <input type="text" id="userId" name="userId" value="${user.userId}" readonly>
            </div>
            <div class="form-group">
                <label for="firstName">First Name (Read-only):</label>
                <input type="text" id="firstName" name="firstName" value="${user.firstName}" readonly>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name (Read-only):</label>
                <input type="text" id="lastName" name="lastName" value="${user.lastName}" readonly>
            </div>
            <div class="form-group">
                <label for="username">Username (Read-only):</label>
                <input type="text" id="username" name="username" value="${user.username}" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email (Read-only):</label>
                <input type="email" id="email" name="email" value="${user.email}" readonly>
            </div>
            <div class="form-group">
                <label for="phoneNo">Phone Number:</label>
                <input type="text" id="phoneNo" name="phoneNo" value="${user.phoneNo}" placeholder="Enter phone number">
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${user.address}" placeholder="Enter address">
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="" ${user.gender == null ? 'selected' : ''}>Select Gender</option>
                    <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                    <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                    <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="${user.dob}">
            </div>

            <h2>Booking Details</h2>
            <div class="form-group">
                <label for="checkInDate">Check-In Date:</label>
                <input type="date" id="checkInDate" name="checkInDate" required>
            </div>
            <div class="form-group">
                <label for="checkOutDate">Check-Out Date:</label>
                <input type="date" id="checkOutDate" name="checkOutDate" required>
            </div>
            <div class="form-group">
                <label for="numberOfGuests">Number of Guests:</label>
                <input type="number" id="numberOfGuests" name="numberOfGuests" min="1" required>
            </div>

            <div class="form-group">
                <button type="submit">Submit Booking</button>
            </div>
        </form>
        <a href="RoomsController" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>