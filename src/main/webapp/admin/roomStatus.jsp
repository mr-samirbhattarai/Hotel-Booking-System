<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Room Status | Hotel RockStar</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roomStatus.css" />

</head>
<body>
  <c:set var="activePage" value="roomStatus" scope="request" />
  <jsp:include page="/admin/adminSidebar.jsp" />

  <div class="room-status">
    <div class="content">
      <div class="header">
        <div>
          <h2>Hotel Rooms</h2>
          <span class="subtitle">All hotels available rooms</span>
        </div>
        <div class="add-button">
          <a href="${pageContext.request.contextPath}/admin/addRooms.jsp" class="add-btn">View Booking</a>
        </div>
      </div>

      <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          ${successMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" 
                  onclick="<% session.removeAttribute("successMessage"); %>"></button>
        </div>
        <% session.removeAttribute("successMessage"); %> <!-- Clear session attribute -->
      </c:if>

      <table>
        <thead>
          <tr>
            <th>Room No.</th>
            <th>Room Image</th>
            <th>Room Type</th>
            <th>Max Occupancy</th>
            <th>Floor Number</th>
            <th>Price/Night</th>
            <th>Availability</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty rooms}">
              <c:forEach var="room" items="${rooms}">
                <tr>
                  <td>${room.roomNumber}</td>
                  <td>
                		<img src="${pageContext.request.contextPath}/photos/${room.roomImage}" class="room-image" alt="${room.roomType} image">
				  </td>
                  <td style="padding-left: 25px;">${room.roomType}</td>
				  <td style="padding-left: 60px;">${room.maxOccupancy}</td>
                  <td style="padding-left: 40px;">Floor ${room.floorNumber}</td>                  
                  <td style="padding-left: 25px;">Rs. ${room.pricePerNight}</td>
                  <td>
				    <% 
				        com.hotelbookingsystem.model.Rooms room = (com.hotelbookingsystem.model.Rooms) pageContext.getAttribute("room");
				        boolean available = room.isAvailable();
				        String statusClass = available ? "status-available" : "status-occupied";
				        String statusText = available ? "Available" : "Occupied";
				    %>
				    <span class="status-btn <%=statusClass%>">
				        <%=statusText%>
				    </span>
				</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="7">No rooms found.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>