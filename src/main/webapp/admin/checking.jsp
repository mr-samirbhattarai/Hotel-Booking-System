<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Check In | Hotel RockStar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checking.css" />
</head>
<body>
<c:set var="activePage" value="checkings" scope="request" />
<jsp:include page="/admin/adminSidebar.jsp" />

<div class="check-in">
    <div class="content">
    <div class="header">
        <div>
          <h2>Hotel pending bookings</h2>
          <span class="subtitle">All customer's pending bookings request</span>
        </div>
        <div class="search-bar">
        	<form action="${pageContext.request.contextPath}/CheckInController" method="get" class="search-form">
			    <div class="search-container">
			        <input type="text" name="searchName" class="search-input" placeholder="Search by customer name" value="${param.searchName}">
			        <button type="submit" class="search-button">🔍 Search</button>
			    </div>
			</form>
        </div>
      </div>
        <div class="all-bookings">			      
            <table class="custom-table">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Customer Name</th>
                        <th>Phone No</th>
                        <th>Room Number</th>
                        <th>Room Type</th>
                        <th>Check-In</th>
                        <th>Check-Out</th>
                        <th>Guests</th>
                        <th>Created At</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.bookingId}</td>
                            <td>${booking.user.firstName} ${booking.user.lastName}</td>
                            <td>${empty booking.user.phoneNo ? 'N/A' : booking.user.phoneNo}</td>
                            <td>${booking.room.roomNumber}</td>
                            <td>${booking.room.roomType}</td>
                            <td>${booking.checkInDate}</td>
                            <td>${booking.checkOutDate}</td>
                            <td>${booking.numberOfGuests}</td>
                            <td>${booking.createdAt}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${booking.status == 'pending'}">
										<form action="${pageContext.request.contextPath}/CheckInController" method="post">
                                            <input type="hidden" name="bookingId" value="${booking.bookingId}"/>
                                            <button type="submit" class="action-btn checkin-btn" style="border: none;">Check In</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-badge ${booking.status.toLowerCase()}">${booking.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty bookings}">
                        <tr>
                            <td colspan="10" class="text-center">No recent pending bookings found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
