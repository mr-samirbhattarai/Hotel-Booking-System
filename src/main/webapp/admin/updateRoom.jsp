<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Update Room | Hotel RockStar</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateRoom.css" />

</head>
<body>
	<c:set var="activePage" value="manageRooms" scope="request" />
	<jsp:include page="/admin/adminSidebar.jsp" />

	<div class="update-rooms">
	
	<div class="content">

	<div class="header">
        <div>
          <h2>Update Rooms</h2>
          <span class="subtitle">You can update existing rooms</span>
        </div>
        <div class="back-button">
			<a href="${pageContext.request.contextPath}/ManageRoomsController?page=viewRoomsForAdmin"
				class="back-btn">Back</a>
        </div>
      </div>

		<c:if test="${not empty room}">
			<form action="${pageContext.request.contextPath}/UpdateRoomController"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="roomId" value="${room.roomId}" />
				<div class="form-container">
					<div class="form-row">
						<div class="form-fields">
							<label for="roomNumber" class="label-for-form">Room
								Number:</label> <input type="text"
								class="form-control ${not empty roomNumberError or not empty roomNumberExistsError ? 'is-invalid' : ''}"
								id="roomNumber" name="roomNumber" value="${room.roomNumber}" />
							<c:if test="${not empty roomNumberError}">
								<div class="invalid-feedback">${roomNumberError}</div>
							</c:if>
							<c:if test="${not empty roomNumberExistsError}">
								<div class="invalid-feedback">${roomNumberExistsError}</div>
							</c:if>
						</div>
						<div class="form-fields">
							<label for="roomType" class="label-for-form">Room Type:</label> <select
								class="form-control ${not empty roomTypeError ? 'is-invalid' : ''}"
								id="roomType" name="roomType">
								<option value="">Select Room Type</option>
								<c:forEach items="${roomTypes}" var="type">
									<option value="${type}"
										${room.roomType == type ? 'selected' : ''}>${type}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty roomTypeError}">
								<div class="invalid-feedback">${roomTypeError}</div>
							</c:if>
						</div>
					</div>

					<div class="form-row">
						<div class="form-fields">
							<label for="noOfBeds" class="label-for-form">Number of
								Beds:</label> <input type="text"
								class="form-control ${not empty noOfBedsEmptyError or not empty noOfBedsFormatError ? 'is-invalid' : ''}"
								id="noOfBeds" name="noOfBeds" value="${room.noOfBeds}" />
							<c:if test="${not empty noOfBedsEmptyError}">
								<div class="invalid-feedback">${noOfBedsEmptyError}</div>
							</c:if>
							<c:if test="${not empty noOfBedsFormatError}">
								<div class="invalid-feedback">${noOfBedsFormatError}</div>
							</c:if>
						</div>
						<div class="form-fields">
							<label for="bedType" class="label-for-form">Bed Type:</label> <select
								class="form-control ${not empty bedTypeError ? 'is-invalid' : ''}"
								id="bedType" name="bedType">
								<option value="">Select Bed Type</option>
								<c:forEach items="${bedTypes}" var="bed">
									<option value="${bed}" ${room.bedType == bed ? 'selected' : ''}>${bed}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty bedTypeError}">
								<div class="invalid-feedback">${bedTypeError}</div>
							</c:if>
						</div>
					</div>

					<div class="form-row">
						<div class="form-fields">
							<label for="price" class="label-for-form">Price Per Night
								(Rs.):</label> <input type="text"
								class="form-control ${not empty priceError or not empty priceFormatError ? 'is-invalid' : ''}"
								id="price" name="price" value="${room.pricePerNight}" />
							<c:if test="${not empty priceError}">
								<div class="invalid-feedback">${priceError}</div>
							</c:if>
							<c:if test="${not empty priceFormatError}">
								<div class="invalid-feedback">${priceFormatError}</div>
							</c:if>
						</div>
						<div class="form-fields">
							<label for="roomArea" class="label-for-form">Room Area
								(sq ft):</label> <input type="text" 
								class="form-control ${not empty roomAreaError or not empty roomAreaFormatError ? 'is-invalid' : ''}"
								id="roomArea" name="roomArea" value="${room.roomArea}" />
							<c:if test="${not empty roomAreaError}">
								<div class="invalid-feedback">${roomAreaError}</div>
							</c:if>
							<c:if test="${not empty roomAreaFormatError}">
								<div class="invalid-feedback">${roomAreaFormatError}</div>
							</c:if>
						</div>
					</div>

					<div class="form-row">
						<div class="form-fields">
							<label for="floorNumber" class="label-for-form">Floor
								Number:</label> <input type="text"
								class="form-control ${not empty floorNumberError or not empty floorNumberFormatError ? 'is-invalid' : ''}"
								id="floorNumber" name="floorNumber" value="${room.floorNumber}" />
							<c:if test="${not empty floorNumberError}">
								<div class="invalid-feedback">${floorNumberError}</div>
							</c:if>
							<c:if test="${not empty floorNumberFormatError}">
								<div class="invalid-feedback">${floorNumberFormatError}</div>
							</c:if>
						</div>
						<div class="form-fields">
							<label for="maxOccupancy" class="label-for-form">Max
								Occupancy:</label> <input type="text"
								class="form-control ${not empty maxOccupancyError or not empty maxOccupancyFormatError ? 'is-invalid' : ''}"
								id="maxOccupancy" name="maxOccupancy"
								value="${room.maxOccupancy}" />
							<c:if test="${not empty maxOccupancyError}">
								<div class="invalid-feedback">${maxOccupancyError}</div>
							</c:if>
							<c:if test="${not empty maxOccupancyFormatError}">
								<div class="invalid-feedback">${maxOccupancyFormatError}</div>
							</c:if>
						</div>
					</div>

					<div class="form-row">
						<div class="form-fields">
							<label for="roomImage" class="label-for-form">Room Image:</label>

							<div>
								<div class="current-image">
									<c:choose>
										<c:when test="${not empty room.roomImage}">
											<p>Current image: ${room.roomImage}</p>
											<img
												src="${pageContext.request.contextPath}/photos/${room.roomImage}"
												onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/imgNotFound.png';"
												alt="Room image" class="img-thumbnail"
												style="max-width: 200px;">
										</c:when>

										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/images/imgNotFound.png"
												alt="Default room image" class="img-thumbnail"
												style="max-width: 200px;">
										</c:otherwise>
									</c:choose>
								</div>
							</div>



							<input type="file"
								class="form-control ${not empty ImageError ? 'is-invalid' : ''}"
								id="roomImage" name="roomImage" accept="image/*" /> <small
								class="form-text text-muted">Upload a new image only if
								you want to replace the current one.</small>
							<c:if test="${not empty ImageError}">
								<div class="invalid-feedback">${ImageError}</div>
							</c:if>
						</div>
						<div class="form-fields">
							<label for="description" class="label-for-form">Description:</label>
							<textarea id="description" name="description"
								class="form-control desc ${not empty descriptionError ? 'is-invalid' : ''}">${room.description}</textarea>
							<c:if test="${not empty descriptionError}">
								<div class="invalid-feedback">${descriptionError}</div>
							</c:if>
						</div>
					</div>

					<button type="submit" class="btn-updateRoom">Update Room</button>
				</div>
			</form>
		</c:if>
		<c:if test="${empty room}">
			<div class="alert alert-warning">Room information not found.
				Please go back and select a room to update.</div>
		</c:if>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>