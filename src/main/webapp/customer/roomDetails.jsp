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
<title>${room.roomType}RoomDetails|HotelRockStar</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/roomDetails.css" />
</head>
<body>
	<c:set
		var="activePage"
		value="rooms"
		scope="request" />
	<jsp:include page="/customer/header.jsp" />

	<div class="room-details-container">
		<div class="room-details-wrapper">
			<div class="room-gallery">
				<img
					src="${pageContext.request.contextPath}/photos/${room.roomImage}"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/imgNotFound.png';"
					alt="${room.roomType} Room"
					class="main-image">
			</div>

			<div class="room-info">
				<div class="room-header">
					<h1>${room.roomType}Room</h1>
					<p class="price">
						Rs. ${room.pricePerNight} <span>per night</span>
					</p>
				</div>

				<div class="room-features">
					<div class="feature">
						<span class="feature-icon">🛏️</span>
						<div class="feature-info">
							<h3>Bed Configuration</h3>
							<p>${room.noOfBeds}${room.bedType}Bed(s)</p>
						</div>
					</div>

					<div class="feature">
						<span class="feature-icon">📏</span>
						<div class="feature-info">
							<h3>Room Size</h3>
							<p>${room.roomArea}sqft</p>
						</div>
					</div>

					<div class="feature">
						<span class="feature-icon">👥</span>
						<div class="feature-info">
							<h3>Max Occupancy</h3>
							<p>${room.maxOccupancy}Guests</p>
						</div>
					</div>

					<div class="feature">
						<span class="feature-icon">🏢</span>
						<div class="feature-info">
							<h3>Floor</h3>
							<p>${room.floorNumber}</p>
						</div>
					</div>
				</div>

				<div class="room-description">
					<h2>Room Description</h2>
					<p>${room.description}</p>
				</div>
				<a
					href="${pageContext.request.contextPath}/BookingController?roomId=${room.roomId}"
					class="book-now-btn">Book Now</a>

			</div>
		</div>
	</div>
</body>
</html>