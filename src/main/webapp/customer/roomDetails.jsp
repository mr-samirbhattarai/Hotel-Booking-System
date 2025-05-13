<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>${room.roomType} Room Details | Hotel RockStar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roomDetails.css" />
</head>
<body>
    <c:set var="activePage" value="rooms" scope="request" />   
    <jsp:include page="/customer/header.jsp" />
    
    <div class="room-details-container">
        <div class="room-details-wrapper">
            <div class="room-gallery">
                <img src="${pageContext.request.contextPath}/photos/${room.roomImage}" 
                     alt="${room.roomType} Room" class="main-image">
            </div>
            
            <div class="room-info">
                <div class="room-header">
                    <h1>${room.roomType} Room</h1>
                    <p class="price">Rs. ${room.pricePerNight} <span>per night</span></p>
                </div>
                
                <div class="room-features">
                    <div class="feature">
                        <span class="feature-icon">🛏️</span>
                        <div class="feature-info">
                            <h3>Bed Configuration</h3>
                            <p>${room.noOfBeds} ${room.bedType} Bed(s)</p>
                        </div>
                    </div>
                    
                    <div class="feature">
                        <span class="feature-icon">📏</span>
                        <div class="feature-info">
                            <h3>Room Size</h3>
                            <p>${room.roomArea} sq ft</p>
                        </div>
                    </div>
                    
                    <div class="feature">
                        <span class="feature-icon">👥</span>
                        <div class="feature-info">
                            <h3>Max Occupancy</h3>
                            <p>${room.maxOccupancy} Guests</p>
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
                
                <div class="booking-section">
                    <form action="${pageContext.request.contextPath}/BookRoomServlet" method="post" class="booking-form">
                        <input type="hidden" name="roomId" value="${room.roomId}">
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="checkIn">Check In</label>
                                <input type="date" id="checkIn" name="checkIn" class="form-control" required
                                       min="${java.time.LocalDate.now()}">
                            </div>
                            
                            <div class="form-group">
                                <label for="checkOut">Check Out</label>
                                <input type="date" id="checkOut" name="checkOut" class="form-control" required
                                       min="${java.time.LocalDate.now().plusDays(1)}">
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="adults">Adults</label>
                                <select id="adults" name="adults" class="form-control" required>
                                    <c:forEach begin="1" end="${room.maxOccupancy}" var="i">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="children">Children</label>
                                <select id="children" name="children" class="form-control">
                                    <option value="0">0</option>
                                    <c:forEach begin="1" end="2" var="i">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        
                        <button type="submit" class="book-now-btn">Book Now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>    
    
    <script>
        // Ensure check-out date is after check-in date
        document.getElementById('checkIn').addEventListener('change', function() {
            const checkIn = new Date(this.value);
            const checkOutInput = document.getElementById('checkOut');
            const minCheckOut = new Date(checkIn);
            minCheckOut.setDate(minCheckOut.getDate() + 1);
            checkOutInput.min = minCheckOut.toISOString().split('T')[0];
            
            if (new Date(checkOutInput.value) <= checkIn) {
                checkOutInput.value = minCheckOut.toISOString().split('T')[0];
            }
        });
    </script>
</body>
</html>