<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History</title>
    <link rel="stylesheet" href="css/viewBookingHistory.css">
</head>
<body>
    <div class="container">
        <h1>Your Booking History</h1>
        <a href="RoomsController" class="back-link">Back</a>
        
        <c:if test="${not empty bookings}">
            <table class="booking-table">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Room ID</th>
                        <th>Check-In Date</th>
                        <th>Check-Out Date</th>
                        <th>Guests</th>
                        <th>Status</th>
                        <th>Booked On</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.bookingId}</td>
                            <td>${booking.roomId}</td>
                            <td>${booking.checkInDate}</td>
                            <td>${booking.checkOutDate}</td>
                            <td>${booking.numberOfGuests}</td>
                            <td>${booking.status}</td>
                            <td>${booking.createdAt}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${booking.status == 'pending'}">
                                        <form action="CancelBookingController" method="post" style="margin:0;">
                                            <input type="hidden" name="bookingId" value="${booking.bookingId}" />
                                            <input class="cancel-btn" type="submit" value="Cancel" 
                                                onclick="return confirm('Are you sure you want to cancel this booking?');" />
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color:gray;">Not cancellable</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty bookings}">
            <p>No bookings found.</p>
        </c:if>
    </div>
</body>
</html>
