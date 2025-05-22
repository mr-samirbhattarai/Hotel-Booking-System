<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Bookings | Hotel RockStar</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageRooms.css" />
</head>
<body>
  <c:set var="activePage" value="bookings" scope="request" />
  <jsp:include page="/admin/adminSidebar.jsp" />

  <div class="bookings">
    <div class="content">
      <div class="header">
        <h2>Booking Management</h2>
        <span class="subtitle">View, manage, and add bookings</span>
      </div>

      <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          ${successMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"
                  onclick="<% session.removeAttribute("successMessage"); %>"></button>
        </div>
        <% session.removeAttribute("successMessage"); %>
      </c:if>

      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
          ${errorMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"
                  onclick="<% session.removeAttribute("errorMessage"); %>"></button>
        </div>
        <% session.removeAttribute("errorMessage"); %>
      </c:if>

      <!-- Search by Customer Name -->
      <form method="post" action="${pageContext.request.contextPath}/GetBookingByStatus" class="mb-3">
        <input type="hidden" name="action" value="search">
        <div class="row g-2 align-items-center">
          <div class="col-auto">
            <label for="customerName" class="form-label">Search by Customer Name:</label>
          </div>
          <div class="col-auto">
            <input type="text" name="customerName" id="customerName" class="form-control" value="${searchName}" placeholder="Enter name">
          </div>
          <div class="col-auto">
            <button type="submit" class="btn btn-primary">Search</button>
            <a href="${pageContext.request.contextPath}/GetBookingByStatus" class="btn btn-secondary">Clear</a>
          </div>
        </div>
      </form>

      <!-- Status Filter Form -->
      <form method="post" action="${pageContext.request.contextPath}/GetBookingByStatus" class="mb-3">
        <input type="hidden" name="action" value="filter">
        <div class="row g-2 align-items-center">
          <div class="col-auto">
            <label for="status" class="form-label">Filter by Status:</label>
          </div>
          <div class="col-auto">
            <select name="status" id="status" class="form-select">
              <option value="all" ${selectedStatus == 'all' ? 'selected' : ''}>All</option>
              <option value="pending" ${selectedStatus == 'pending' ? 'selected' : ''}>Pending</option>
              <option value="checked-in" ${selectedStatus == 'checked-in' ? 'selected' : ''}>Checked-In</option>
              <option value="completed" ${selectedStatus == 'completed' ? 'selected' : ''}>Completed</option>
              <option value="cancelled" ${selectedStatus == 'cancelled' ? 'selected' : ''}>Cancelled</option>
            </select>
          </div>
          <div class="col-auto">
            <button type="submit" class="btn btn-primary">Filter</button>
          </div>
        </div>
      </form>

      <!-- Add Customer (Offline Booking) Form -->
      <div class="card mb-3">
        <div class="card-header">
          <h5>Add Offline Booking</h5>
        </div>
        <div class="card-body">
          <form method="post" action="${pageContext.request.contextPath}/GetBookingByStatus">
            <input type="hidden" name="action" value="addCustomer">
            <div class="row g-2">
              <div class="col-md-4">
                <label for="userId" class="form-label">Customer:</label>
                <select name="userId" id="userId" class="form-select" required>
                  <option value="">Select Customer</option>
                  <c:forEach var="user" items="${users}">
                    <option value="${user.userId}">${user.firstname} ${user.lastname}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-md-4">
                <label for="roomId" class="form-label">Room:</label>
                <select name="roomId" id="roomId" class="form-select" required>
                  <option value="">Select Room</option>
                  <c:forEach var="room" items="${rooms}">
                    <option value="${room.roomId}">${room.roomNumber} (${room.roomType})</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-md-4">
                <label for="checkInDate" class="form-label">Check-In Date:</label>
                <input type="date" name="checkInDate" id="checkInDate" class="form-control" required>
              </div>
              <div class="col-md-4">
                <label for="checkOutDate" class="form-label">Check-Out Date:</label>
                <input type="date" name="checkOutDate" id="checkOutDate" class="form-control" required>
              </div>
              <div class="col-md-4">
                <label for="contactNumber" class="form-label">Contact Number:</label>
                <input type="text" name="contactNumber" id="contactNumber" class="form-control" required>
              </div>
              <div class="col-md-4">
                <label for="specialRequest" class="form-label">Special Request:</label>
                <input type="text" name="specialRequest" id="specialRequest" class="form-control">
              </div>
              <div class="col-md-12">
                <button type="submit" class="btn btn-primary mt-2">Add Booking</button>
              </div>
            </div>
          </form>
        </div>
      </div>

      <!-- Bookings Table -->
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Customer Name</th>
            <th>Contact Number</th>
            <th>Room Number</th>
            <th>Room Type</th>
            <th>Check-In Date</th>
            <th>Check-Out Date</th>
            <th>Actual Check-In</th>
            <th>Actual Check-Out</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty bookingList}">
              <c:forEach var="b" items="${bookingList}">
                <tr>
                  <td>${b.user.firstname} ${b.user.lastname}</td>
                  <td>${b.contactNumber}</td>
                  <td>${b.room.roomNumber}</td>
                  <td>${b.room.roomType}</td>
                  <td>${b.checkIn}</td>
                  <td>${b.checkOut}</td>
                  <td>${b.actualCheckIn}</td>
                  <td>${b.actualCheckOut}</td>
                  <td>${b.status}</td>
                  <td>
                    <c:choose>
                      <c:when test="${b.status == 'PENDING'}">
                        <form method="post" action="${pageContext.request.contextPath}/GetBookingByStatus" style="display:inline;">
                          <input type="hidden" name="action" value="checkIn">
                          <input type="hidden" name="bookingId" value="${b.bookingId}" />
                          <button type="submit" class="btn btn-success btn-sm"
                                  onclick="return confirm('Check in this customer?');">
                            Check In
                          </button>
                        </form>
                      </c:when>
                      <c:when test="${b.status == 'CHECKED_IN'}">
                        <form method="post" action="${pageContext.request.contextPath}/GetBookingByStatus" style="display:inline;">
                          <input type="hidden" name="action" value="checkOut">
                          <input type="hidden" name="bookingId" value="${b.bookingId}" />
                          <button type="submit" class="btn btn-warning btn-sm"
                                  onclick="return confirm('Check out this customer?');">
                            Check Out
                          </button>
                        </form>
                      </c:when>
                    </c:choose>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="10" class="text-center">
                  No bookings found for status: ${selectedStatus == 'all' ? 'All' : selectedStatus}
                  <c:if test="${not empty searchName}"> matching name: ${searchName}</c:if>
                </td>
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