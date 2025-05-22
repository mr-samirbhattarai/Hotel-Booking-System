<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Bookings</title>
    <style>
        /* Add some basic CSS for layout and table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
        }
        .success { color: green; }
        .error { color: red; }
    </style>
</head>
<body>

    <h2>Booking Management</h2>

    <!-- Success and Error Messages -->
    <c:if test="${not empty successMessage}">
        <p class="success">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>

    <!-- Filter by Status -->
    <form method="post" action="GetBookingByStatus">
        <input type="hidden" name="action" value="filter"/>
        <label>Status Filter:</label>
        <select name="status">
            <option value="all" ${selectedStatus == 'all' ? 'selected' : ''}>All</option>
            <option value="pending" ${selectedStatus == 'pending' ? 'selected' : ''}>Pending</option>
            <option value="checked_in" ${selectedStatus == 'checked_in' ? 'selected' : ''}>Checked In</option>
            <option value="checked_out" ${selectedStatus == 'checked_out' ? 'selected' : ''}>Checked Out</option>
        </select>
        <button type="submit">Filter</button>
    </form>

    <!-- Search by Customer Name -->
    <form method="post" action="GetBookingByStatus">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="customerName" placeholder="Search by name" value="${searchName}"/>
        <button type="submit">Search</button>
    </form>

    <!-- Add Booking Form -->
    <h3>Add Offline Booking</h3>
    <form method="post" action="GetBookingByStatus">
        <input type="hidden" name="action" value="addCustomer"/>
        <label>User:</label>
        <select name="userId">
            <c:forEach var="user" items="${users}">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>

        <label>Room:</label>
        <select name="roomId">
            <c:forEach var="room" items="${rooms}">
                <option value="${room.id}">${room.roomNumber}</option>
            </c:forEach>
        </select>

        <label>Check-In Date:</label>
        <input type="date" name="checkInDate" required/>

        <label>Check-Out Date:</label>
        <input type="date" name="checkOutDate" required/>

        <label>Contact Number:</label>
        <input type="text" name="contactNumber" required/>

        <label>Special Request:</label>
        <input type="text" name="specialRequest"/>

        <button type="submit">Add Booking</button>
    </form>

    <!-- Bookings Table -->
    <h3>All Bookings</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Room</th>
                <th>Check-In</th>
                <th>Check-Out</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookingList}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>${booking.customerName}</td>
                    <td>${booking.roomNumber}</td>
                    <td>${booking.checkInDate}</td>
                    <td>${booking.checkOutDate}</td>
                    <td>${booking.status}</td>
                    <td>
                        <c:if test="${booking.status == 'PENDING'}">
                            <form method="post" action="GetBookingByStatus" style="display:inline;">
                                <input type="hidden" name="action" value="checkIn"/>
                                <input type="hidden" name="bookingId" value="${booking.bookingId}"/>
                                <button type="submit">Check-In</button>
                            </form>
                        </c:if>
                        <c:if test="${booking.status == 'CHECKED_IN'}">
                            <form method="post" action="GetBookingByStatus" style="display:inline;">
                                <input type="hidden" name="action" value="checkOut"/>
                                <input type="hidden" name="bookingId" value="${booking.bookingId}"/>
                                <button type="submit">Check-Out</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
