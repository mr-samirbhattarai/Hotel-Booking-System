<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Add Room | Hotel RockStar</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addRoom.css" />
</head>
<body>

<jsp:include page="/admin/adminSidebar.jsp" />
<div class="add-rooms">
	<div class="header">
    <h2>Manage Rooms</h2>
  		<a href="${pageContext.request.contextPath}/admin/manageRooms.jsp" class="back-btn">Back</a>
	</div>
	  <!-- For success message -->
	  <c:if test="${not empty successMessage}">
	    <div class="alert alert-success alert-dismissible fade show" role="alert">${successMessage}</div>
	  </c:if>
	  <!-- For error message -->
	  <c:if test="${not empty errorMessage}">
	    <div class="alert alert-danger alert-dismissible fade show" role="alert">${errorMessage}</div>
	  </c:if>

  <form action="${pageContext.request.contextPath}/addRoom" method="post" enctype="multipart/form-data">
    <div class="form-container">
      <div class="form-row">
        <div class="form-fields">
          <label for="roomNumber" class="label-for-form">Room Number:</label>
          <input type="text" class="form-control ${not empty roomNumberError ? 'is-invalid' : ''}" id="roomNumber" name="roomNumber" value="${param.roomNumber}" placeholder="${not empty roomNumberError ? roomNumberError : 'Enter room number'}">
          <c:if test="${not empty roomNumberExistsError}">
			        <small class="text-danger">${roomNumberExistsError}</small>
	      </c:if>
        </div>
        <div class="form-fields">
		  <label for="roomType" class="label-for-form">Room Type:</label>
		  <select class="form-control ${not empty roomTypeError ? 'is-invalid' : ''}" id="roomType" name="roomType">
		    <option value="">Select Room Type</option>
		    <option value="single" ${param.roomType == 'single' ? 'selected' : ''}>Single</option>
		    <option value="double" ${param.roomType == 'double' ? 'selected' : ''}>Double</option>
		    <option value="suite" ${param.roomType == 'suite' ? 'selected' : ''}>Suite</option>
		    <option value="deluxe" ${param.roomType == 'deluxe' ? 'selected' : ''}>Deluxe</option>
		  </select>
		  <c:if test="${not empty roomTypeError}">
			        <small class="text-danger">${roomTypeError}</small>
	      </c:if>
		</div>

      </div>

      <div class="form-row">
        <div class="form-fields">
          <label for="noOfBeds" class="label-for-form">Number of Beds:</label>
          <input type="text" class="form-control ${not empty noOfBedsEmptyError ? 'is-invalid' : ''}" id="noOfBeds" name="noOfBeds" value="${param.noOfBeds}" placeholder="${not empty noOfBedsEmptyError ? noOfBedsEmptyError : 'Enter number of beds'}">
          <c:if test="${not empty noOfBedsFormatError}">
			        <small class="text-danger">${noOfBedsFormatError}</small>
	      </c:if>
        </div>
        <div class="form-fields">
          <label for="bedType" class="label-for-form">Bed Type:</label>
          <select class="form-control ${not empty bedTypeError ? 'is-invalid' : ''}" id="bedType" name="bedType">
   		    <option value="">Select Bed Type</option>
            <option value="single" ${param.bedType == 'single' ? 'selected' : ''}>Single</option>
		    <option value="double" ${param.bedType == 'double' ? 'selected' : ''}>Double</option>
		    <option value="queen" ${param.bedType == 'queen' ? 'selected' : ''}>Queen</option>
		    <option value="king" ${param.bedType == 'king' ? 'selected' : ''}>King</option>
          </select>
          <c:if test="${not empty bedTypeError}">
		    <div class="invalid-feedback">${bedTypeError}</div>
		  </c:if>
        </div>
      </div>

      <div class="form-row">
        <div class="form-fields">
          <label for="price" class="label-for-form">Price Per Night (Rs.):</label>
          <input type="text" class="form-control ${not empty priceError ? 'is-invalid' : ''}" id="price" name="price" value="${param.price}" placeholder="${not empty priceError ? priceError : 'Enter room price per night'}">
          <c:if test="${not empty priceFormatError}">
			<small class="text-danger">${priceFormatError}</small>
	      </c:if>
        </div>
        <div class="form-fields">
          <label for="roomArea" class="label-for-form">Room Area:</label>
          <input type="text" class="form-control ${not empty roomAreaError ? 'is-invalid' : ''}" id="roomArea" name="roomArea" value="${param.roomArea}" placeholder="${not empty roomAreaError ? roomAreaError : 'Enter room area'}">
          <c:if test="${not empty roomAreaFormatError}">
			<small class="text-danger">${roomAreaFormatError}</small>
	      </c:if>
        </div>
      </div>

      <div class="form-row">
        <div class="form-fields">
          <label for="roomImage" class="label-for-form">Room Image:</label>
          <input type="file" id="roomImage" class="form-control" name="roomImage" accept="image/*">
          <c:if test="${not empty ImageError}">
			<small class="text-danger">${ImageError}</small>
	      </c:if>
        </div>

        <div class="form-fields">
          <label for="description" class="label-for-form">Description:</label>
          <textarea id="description" name="description" class="form-control desc ${not empty roomImageError ? 'is-invalid' : ''}" placeholder="Enter description">${param.description}</textarea>
          <c:if test="${not empty descriptionError}">
			<small class="text-danger">${descriptionError}</small>
	      </c:if>
        </div>
        
        
       
        
        
      </div>

      <button type="submit" class="btn-addRoom">Add Room</button>
    </div>
  </form>
</div>
</body>
</html>