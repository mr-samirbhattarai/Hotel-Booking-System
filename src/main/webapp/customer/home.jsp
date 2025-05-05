<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hotelbookingsystem.model.Users" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Hotel RockStar</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            background-color: #fff;
        }

        header {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            padding: 14px 20px;
            border-bottom: 1px solid #e0e0e0;
            background-color: #fff;
            z-index: 1000;
        }

        .header-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .hotel-info {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .hotel-logo img {
            width: 40px;
            height: 40px;
            object-fit: contain;
        }

        .hotel-name {
            font-size: 20px;
            font-weight: 600;
            color: #000;
        }

        nav ul {
            display: flex;
            list-style: none;
            gap: 30px;
            font-weight: 500;
        }

        nav ul li a {
            text-decoration: none;
            color: #333;
            font-size: 16px;
        }

        nav ul li a:hover {
            color: #000;
        }

        nav ul li a.active {
            color: #000;
            font-weight: 600;
        }

        .login-btn {
            padding: 10px 20px;
            background-color: #000;
            color: #fff;
            border: 2px solid transparent;
            font-size: 16px;
            border-radius: 5px;
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .login-btn:hover {
            background-color: transparent;
            color: #000;
            border: 2px solid #000;
        }

        .user-icon {
            display: flex;
            align-items: center;
            gap: 8px;
            font-size: 16px;
            color: #000;
            text-decoration: none;
        }

        .user-icon:hover {
            color: #333;
        }

        .main-section {
            max-width: 1200px;
            margin: 0 auto;
            padding: 120px 20px 20px;
            display: flex;
            justify-content: space-between;
            gap: 40px;
        }

        .content-left {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .content-right {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .hotel-slogan .slogan-top {
            font-size: 18px;
            color: #666;
            margin-bottom: 10px;
            text-transform: uppercase;
        }

        .hotel-slogan h1 {
            font-size: 48px;
            line-height: 1.2;
            margin-bottom: 20px;
            font-weight: 700;
        }

        .hotel-slogan .description {
            font-size: 16px;
            color: #666;
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .booking-form {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            background-color: #fff;
            padding: 15px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        }

        .booking-form .form-group {
            flex: 1;
            min-width: 150px;
        }

        .booking-form label {
            display: block;
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
        }

        .booking-form input,
        .booking-form select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }

        .find-room-btn {
            padding: 10px 20px;
            background-color: #000;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            flex: 1;
            min-width: 150px;
        }

        .hotel_main-image img {
            width: 100%;
            height: auto;
            max-height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }

        .hotel_images {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .hotel_images img {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 10px;
        }

        .view-room-btn {
            padding: 10px 26px;
            background-color: #000;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
            margin: 10px auto;
            display: block;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            .main-section {
                flex-direction: column;
                padding: 120px 20px;
            }

            .content-left, .content-right {
                width: 100%;
            }

            .hotel-slogan h1 {
                font-size: 36px;
            }

            .hotel-slogan .slogan-top {
                font-size: 16px;
            }

            .hotel_images {
                flex-direction: row;
                justify-content: center;
                gap: 10px;
                margin: 20px 0;
            }

            .hotel_images img {
                width: 100px;
                height: 100px;
            }

            .view-room-btn {
                width: 100%;
                max-width: 200px;
            }

            .booking-form {
                flex-direction: column;
            }

            .booking-form .form-group {
                width: 100%;
            }

            .booking-form .checkin-group,
            .booking-form .checkout-group {
                flex: 1;
                min-width: 0;
            }

            .checkin-checkout {
                display: flex;
                gap: 10px;
                width: 100%;
            }

            .find-room-btn {
                width: 100%;
            }

            .header-container {
                flex-direction: column;
                gap: 15px;
                align-items: center;
            }

            nav ul {
                flex-direction: column;
                gap: 15px;
                text-align: center;
            }

            .login-btn, .user-icon {
                width: 100%;
                text-align: center;
                max-width: 200px;
            }
        }

        @media (max-width: 576px) {
            .hotel-slogan h1 {
                font-size: 28px;
            }

            .hotel-slogan .slogan-top {
                font-size: 14px;
            }

            .hotel_images img {
                width: 80px;
                height: 80px;
            }

            .hotel-slogan .description {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <header>
        <div class="header-container">
            <div class="hotel-info">
                <div class="hotel-logo">
                    <img src="${pageContext.request.contextPath}/images/RockStar-logo.jpg" alt="RockStar Hotel Logo">
                </div>
                <div class="hotel-name">Hotel RockStar</div>
            </div>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/pages/home.jsp" class="active">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/rooms.jsp">Rooms</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/facilities.jsp">Facilities</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/deals.jsp">Deals</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/about.jsp">About Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact Us</a></li>
                </ul>
            </nav>
            <%
                Users users = (Users) session.getAttribute("user");
                if (users != null) {
            %>
                <a href="${pageContext.request.contextPath}/pages/profile.jsp" class="user-icon">
                    <i class="fa fa-user-circle"></i>
                    <span><%= users.getFirstName() %></span>
                </a>
            <%
                } else {
            %>
                <a href="${pageContext.request.contextPath}/pages/login.jsp" class="login-btn">Login</a>
            <%
                }
            %>
        </div>
    </header>

    <section class="main-section">
        <div class="content-left">
            <div class="hotel-slogan">
                <p class="slogan-top">Discover Comfort & Luxury</p>
                <h1>Book Your Dream Stay Today!</h1>
                <p class="description">Explore our curated selection of premium rooms and exclusive deals, tailored for your perfect getaway, whether for leisure or business.</p>
            </div>
            <form class="booking-form" action="${pageContext.request.contextPath}/RoomSearchController" method="post">
                <div class="form-group">
                    <label for="guests">Number of Guests</label>
                    <select id="guests" name="guests" required>
                        <option disabled selected>Number of Guests</option>
                        <option value="1">1 Guest</option>
                        <option value="2">2 Guests</option>
                        <option value="3">3 Guests</option>
                        <option value="4">4+ Guests</option>
                    </select>
                </div>
                <div class="checkin-checkout">
                    <div class="form-group checkin-group">
                        <label for="checkin">Check-in Date</label>
                        <input type="date" id="checkin" name="checkin" required>
                    </div>
                    <div class="form-group checkout-group">
                        <label for="checkout">Check-out Date</label>
                        <input type="date" id="checkout" name="checkout" required>
                    </div>
                </div>
                <button type="submit" class="find-room-btn">Find Rooms</button>
            </form>
        </div>
        <div class="content-right">
            <div class="hotel_main-image">
                <img src="${pageContext.request.contextPath}/images/Deluxe-room.jpg" alt="Main Room Image">
            </div>
            <div class="hotel_images">
                <img src="${pageContext.request.contextPath}/images/dinning-space.jpeg" alt="Dining Space" />
                <img src="${pageContext.request.contextPath}/images/Swimming_Pool_Seen.jpg" alt="Swimming Pool" />
                <img src="${pageContext.request.contextPath}/images/Room-view.jpg" alt="Room View" />
            </div>
            <button class="view-room-btn">View More</button>
        </div>
    </section>
</body>
</html>