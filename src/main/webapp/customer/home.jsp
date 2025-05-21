<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home | Hotel RockStar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" />
    
</head>
<body>
    <c:set var="activePage" value="home" scope="request" />   
   	<jsp:include page="/customer/header.jsp" />
        
    <section class="main-section">
        <div class="content">

            <div class="slogan-and-images">
                <div class="hotel-slogan">
                    <p style="font-size: 21px; color: #444;">Feel Like Home, Stay with Comfort</p>
                    <h1>Book Your Perfect Room Today!</h1>
                    <p style="line-height: 1.6;">We have clean, peaceful, and beautiful rooms for your stay. Whether you are coming for a holiday or work, we will take care of your comfort and needs.</p>
                </div>
    
                <div class="hotel_images">
                    <img src="${pageContext.request.contextPath}/images/dinning-space.jpeg" alt="Dining Space" />
                    <img src="${pageContext.request.contextPath}/images/Swimming_Pool_Seen.jpg" alt="Swimming Pool" />
                    <img src="${pageContext.request.contextPath}/images/Room-view.jpg" alt="Room View" />
    
                    <a href="RoomController?roomType=SINGLE" class="view-room-btn">View More</a>
                </div>
            </div>
			
			<form method="get" action="${pageContext.request.contextPath}/RoomsController">
			    <label for="roomType">Choose Your Perfect Room</label>			    
				    <select name="roomType" id="roomType">
				        <option value="">All</option>
				        <option value="SINGLE" ${param.roomType == 'SINGLE' ? 'selected' : ''}>Single</option>
				        <option value="DOUBLE" ${param.roomType == 'DOUBLE' ? 'selected' : ''}>Double</option>
				        <option value="SUITE" ${param.roomType == 'SUITE' ? 'selected' : ''}>Suite</option>
				        <option value="DELUXE" ${param.roomType == 'DELUXE' ? 'selected' : ''}>Deluxe</option>
				    </select>
			    <button class="find-room-btn" type="submit" >Find Rooms</button>
			</form>
			
			
			
			
        </div>
        <div class="hotel_main-image">
            <img src="${pageContext.request.contextPath}/images/Deluxe-room.jpg" alt="Main Room Image">
        </div>
    </section>
</body>
</html>