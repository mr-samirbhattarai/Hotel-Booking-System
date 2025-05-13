<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>

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
</head>
<body>
	<c:set
		var="activePage"
		value="manageRooms"
		scope="request" />
	<jsp:include page="/admin/adminSidebar.jsp" />

	<div class="manage-rooms">
		<div class="header">
			<h2>Manage Rooms</h2>
			<a
				href="${pageContext.request.contextPath}/admin/addRooms.jsp"
				class="add-btn">Add Room</a>
		</div>

		<form>
			<div class="table-wrapper">
				<table>
					<thead>
						<tr>
							<th>Room No.</th>
							<th>Room Type</th>
							<th>Max Occupancy</th>
							<th>Beds</th>
							<th>Price/Night</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty rooms}">
								<c:forEach
									var="room"
									items="${rooms}">
									<tr>
										<td>${room.roomNumber}</td>
										<td>${room.roomType}</td>
										<td>${room.maxOccupancy}</td>
										<td>${room.noOfBeds}</td>
										<td>Rs. ${room.pricePerNight}</td>
										<td>
											<div class="button_container">
												<a
													href="${pageContext.request.contextPath}/GetRoomById?page=updateRoom&roomId=${room.roomId}"
													class="action-btn update-btn">Update</a> <a
													href="DeleteRoomServlet?roomNumber=${room.roomId}"
													class="action-btn delete-btn"
													onclick="return confirm('Are you sure you want to delete this room?');">Delete</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">No rooms found.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</form>
	</div>

</body>
</html>