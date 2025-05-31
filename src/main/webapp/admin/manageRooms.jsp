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
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
		<div class="content">
			<div class="header">
				<div>
					<h2>Manage Rooms</h2>
					<span class="subtitle">Available Rooms</span>
				</div>
				<div class="add-button">
					<a
						href="${pageContext.request.contextPath}/admin/addRooms.jsp"
						class="add-btn">Add Room</a>
				</div>
			</div>

			<c:if test="${not empty successMessage}">
				<div
					class="alert alert-success alert-dismissible fade show"
					role="alert">
					${successMessage}
					<button
						type="button"
						class="btn-close"
						data-bs-dismiss="alert"
						aria-label="Close"
						onclick="<%session.removeAttribute("successMessage");%>"></button>
				</div>
				<%
				session.removeAttribute("successMessage");
				%>
				<!-- Clear session attribute -->
			</c:if>

			<table>
				<thead>
					<tr>
						<th>Room No.</th>
						<th>Room Type</th>
						<th>Max Occupancy</th>
						<th>Beds</th>
						<th>Price/Night</th>
						<th>Floor Number</th>
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
									<td>Floor ${room.floorNumber}</td>
									<td>
										<div class="button-container">
											<a
												href="${pageContext.request.contextPath}/GetRoomById?page=updateRooms&roomId=${room.roomId}"
												class="action-btn update-btn">Update</a> <a
												href="${pageContext.request.contextPath}/DeleteRoomController?roomId=${room.roomId}"
												class="action-btn delete-btn"
												onclick="return confirm('Are you sure you want to delete this room?');">Delete</a>
										</div>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>