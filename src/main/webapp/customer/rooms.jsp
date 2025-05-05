<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Rooms | Hotel RockStar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rooms.css" />
    
</head>
<body>
    <c:set var="activePage" value="rooms" scope="request" />   
   	<jsp:include page="/customer/header.jsp" />
        
    <div class="room-container">
    	<h2>Choose Your Room</h2>
      	<p>Feel at home, wherever you are.</p>
        <div class="row">
           <c:forEach var="room" items="${rooms}">
        
            <div class="roombox">
                <div class="room">
                    <div class="room-image">
                		<img src="${pageContext.request.contextPath}/photos/${room.roomImage}" class="room-img-top" alt="${room.roomType} image">
                    </div>
                    <div class="room-body">
                        <h5 class="room-type room-head">${room.roomType} Type Room</h5>
                        <p class="room-desc">${room.description}</p>
                        <p class="room-price">
                            Rs. ${room.pricePerNight}
                        </p>
                        <div class="button-wrapper">
                            <button class="view-details-btn">View Details</button>
                        </div>
                    </div>
                </div>
            </div>
          </c:forEach>
        </div>
      </div>
</body>
</html>