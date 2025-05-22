
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Bookings Rooms | Hotel RockStar</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookings.css" />
</head>
<body>
  <c:set var="activePage" value="bookings" scope="request" />
  <jsp:include page="/admin/adminSidebar.jsp" />
  
  <div class="bookings">
  <!-- Booking Table -->
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>User Name</th>
                <th>Room</th>
                <th>Status</th>
                <th>Check In</th>
                <th>Check Out</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="b" items="${bookingList}">
            <tr>
                <td>${b.bookingId}</td>
                <td>${b.user.firstName} ${b.user.lastName}</td>
                <td>${b.room.roomNumber} (${b.room.roomType})</td>
                <td>${b.status}</td>
                <td>${b.checkIn}</td>
                <td>${b.checkOut}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
 </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>