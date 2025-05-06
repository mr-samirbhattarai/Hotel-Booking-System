<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>


<%@page import="com.hotelbookingsystem.DAO.RoomsDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hotelbookingsystem.model.Rooms"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.lang.ClassNotFoundException"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0" />
<title>Manage Rooms | Hotel RockStar</title>
<link
	rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/manageRooms.css" />



<style>
table {
	width: 80%;
	border-collapse: collapse;
	margin-top: 20px;
	margin-left: 300px;
}

th, td {
	padding: 10px;
	border: 1px solid #999;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

h2 {
	text-align: center;
}
</style>


</head>
<body>
	<c:set
		var="activePage"
		value="rooms"
		scope="request" />

	<jsp:include page="/admin/adminSidebar.jsp" />
	<%
	if (request.getAttribute("rooms") == null) {
		try {
			RoomsDAO dao = new RoomsDAO();
			ArrayList<Rooms> rooms = dao.getAllRooms();
			request.setAttribute("rooms", rooms);
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("errorMessage", "Could not load rooms: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	ArrayList<Rooms> rooms = (ArrayList<Rooms>) request.getAttribute("rooms");
	String error = (String) request.getAttribute("errorMessage");
	%>
	<h2>List of All Rooms</h2>

	<%
	if (error != null) {
	%>
	<p style="color: red;"><%=error%></p>
	<%
	}
	%>

	<%
	if (rooms != null && !rooms.isEmpty()) {
	%>

	<table>
		<tr>
			<th>Room No.</th>
			<th>Room Type</th>
			<th>Max Occupancy</th>
			<th>Bed Type</th>
			<th>Price/Night</th>
			<th>No of Beds</th>
			<th>Description</th>
		</tr>

		<%
		for (Rooms room : rooms) {
		%>
		<tr>
			<td><%=room.getRoomNumber()%></td>
			<td><%=room.getRoomType()%></td>
			<td><%=room.getMaxOccupancy()%></td>
			<td><%=room.getBedType()%></td>
			<td><%=room.getPricePerNight()%></td>
			<td><%=room.getNoOfBeds()%></td>
			<td><%=room.getDescription()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else if (error == null) {
	%>
	<p>No users found.</p>
	<%
	}
	%>

</body>
</html>






