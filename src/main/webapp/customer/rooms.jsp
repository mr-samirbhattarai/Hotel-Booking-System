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
<title>Rooms | Hotel RockStar</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rooms.css" />
<style>
</style>
</head>
<body>

    <c:set var="activePage" value="rooms" scope="request" />   
   	<jsp:include page="/customer/header.jsp" />
        
    <div class="room-container">
    	<div class="header" style="justify-content: space-between;">
	    	<div class="title">
	    		<h2>Choose Your Room</h2>
	      		<p>Feel at home, wherever you are.</p>
	    	</div>
		<form
			method="get"
			action="${pageContext.request.contextPath}/RoomsController">

			<nav>
				<ul id="roomTypeNav">
					<li><a
						href="?roomType="
						class="${param.roomType == null || param.roomType == '' ? 'active' : ''}">All</a>
					</li>
					<li><a
						href="?roomType=SINGLE"
						class="${param.roomType == 'SINGLE' ? 'active' : ''}">Single</a></li>
					<li><a
						href="?roomType=DOUBLE"
						class="${param.roomType == 'DOUBLE' ? 'active' : ''}">Double</a></li>
					<li><a
						href="?roomType=SUITE"
						class="${param.roomType == 'SUITE' ? 'active' : ''}">Suite</a></li>
					<li><a
						href="?roomType=DELUXE"
						class="${param.roomType == 'DELUXE' ? 'active' : ''}">Deluxe</a></li>
				</ul>

			</nav>

		</form>
		<div class="row">
			<c:forEach
				var="room"
				items="${rooms}">



				<div class="roombox">
					<div class="room">
						<div class="room-image">
							<img
								src="${pageContext.request.contextPath}/photos/${room.roomImage}"
								class="room-img-top"
								onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/imgNotFound.png';"
								alt="Image not available">
						</div>
						<div class="room-body">
							<h5 class="room-type room-head">${room.roomType}TypeRoom</h5>
							<p class="room-desc">${room.description}</p>
							<p class="room-price">Rs. ${room.pricePerNight}</p>
							<div class="button-wrapper">
								<a
									href="${pageContext.request.contextPath}/ViewRoomDetailsController?roomId=${room.roomId}"
									class="view-details-btn">View Details</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="/customer/footer.jsp" />
</body>
</html>