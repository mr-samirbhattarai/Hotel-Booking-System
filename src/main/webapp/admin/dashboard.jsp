<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dashboard | Hotel RockStar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css" />
     
</head>
<body>
<c:set var="activePage" value="dashboard" scope="request" />
    <jsp:include page="/admin/adminSidebar.jsp" />
   
    <div class="dashboard">
        <div class="dashboard-main">
            <div class="heading-div">
                <h1>Today's Overview</h1>
                <div class="subtitle">Hotel Summary</div>
                <div class="metrics">
                    <div class="metric-card">
                        <span class="material-symbols-rounded">groups</span>
                        <div class="value">${totalUsers}</div>
                        <div class="label">Total Users</div>
                        <div class="change">+3% from yesterday</div>
                    </div>
                    <div class="metric-card">
                        <span class="material-symbols-rounded">hotel</span>
                        <div class="value">${availableRooms}</div>
                        <div class="label">Available Rooms</div>
                        <div class="change">+2% from yesterday</div>
                    </div>
                    <div class="metric-card">
                        <span class="material-symbols-rounded">hotel</span>
                        <div class="value">${unavailableRooms}</div>
                        <div class="label">Unavailable Rooms</div>
                        <div class="change">+5% from yesterday</div>
                    </div>
                    <div class="metric-card">
                        <span class="material-symbols-rounded">book</span>
                        <div class="value">${pendingBookings}</div>
                        <div class="label">Pending Bookings</div>
                        <div class="change">+4% from yesterday</div>
                    </div>
                </div>
            </div>
            
            <div class="recent-bookings">
			    <h2>Recent Pending Bookings</h2>
			    <table class="custom-table">
			        <thead>
			            <tr>
			                <th>#</th>
			                <th>Booking ID</th>
			                <th>User ID</th>
			                <th>Room ID</th>
			                <th>Check-In</th>
			                <th>Check-Out</th>
			                <th>Guests</th>
			                <th>Created At</th>
			                <th>Status</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="booking" items="${pendingBookingList}" varStatus="status">
			                <tr>
			                    <td>${status.index + 1}</td>
			                    <td>${booking.bookingId}</td>
			                    <td>${booking.userId}</td>
			                    <td>${booking.roomId}</td>
			                    <td>${booking.checkInDate}</td>
			                    <td>${booking.checkOutDate}</td>
			                    <td>${booking.numberOfGuests}</td>
			                    <td>${booking.createdAt}</td>
			                    <td><span class="status-badge">${booking.status}</span></td>
			                </tr>
			            </c:forEach>
			            <c:if test="${empty pendingBookingList}">
			                <tr>
			                    <td colspan="9" class="no-bookings">No recent pending bookings found.</td>
			                </tr>
			            </c:if>
			        </tbody>
			    </table>
			</div>
			            
        </div>
    </div>
</body>
</html>