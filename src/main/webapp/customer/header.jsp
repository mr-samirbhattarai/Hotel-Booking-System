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
<link
	rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css" />
</head>

<body>
	<header>
		<div class="header-container">
			<div class="hotel-info">
				<div class="hotel-logo">
					<img
						src="${pageContext.request.contextPath}/images/RockStar-logo.jpg"
						alt="RockStar Hotel Logo" />
				</div>
				<div class="hotel-name">Hotel RockStar</div>
			</div>

			<nav>
				<ul id="navLinks">
					<li><a
						class="${activePage == 'home' ? 'active' : ''}"
						href="${pageContext.request.contextPath}/customer/home.jsp">Home</a></li>
					<li><a
						class="${activePage == 'rooms' ? 'active' : ''}"
						href="${pageContext.request.contextPath}/roomDetails?page=viewRooms">Rooms</a>
					</li>
					<li><a class="${activePage == 'aboutUs' ? 'active' : ''}" href="${pageContext.request.contextPath}/customer/aboutUs.jsp">About Us</a></li>
					<li><a class="${activePage == 'contactUs' ? 'active' : ''}" href="${pageContext.request.contextPath}/customer/contactUs.jsp">Contact</a></li>

					<li><button class="mobile-login">Login</button></li>
				</ul>
				<span
					id="menuIcon"
					class="material-symbols-rounded menu-icon"
					onclick="toggleMenu()">menu</span>
			</nav>
			<div id="authLinks" class="auth-links">
										<div class="profile-wrapper">
						<a
							class="login-btn"
							href="javascript:void(0)"
							onclick="toggleProfileMenu()">Profile</a>
						<div id="profileMenu" class="profile-menu">
							<a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
							<a href="${pageContext.request.contextPath}/ProfileController">Update Profile</a>
							<a href="${pageContext.request.contextPath}/ChangePasswordController">Change Password</a>
							<a href="${pageContext.request.contextPath}/BookingHistory">View Booking History</a>
							<a href="${pageContext.request.contextPath}/PaymentController">Payment</a>
						</div>
					</div>
			</div>
		</div>
	</header>
	<script>
	function toggleMenu() {
		var navLinks = document.getElementById("navLinks");
		var authLinks = document.getElementById("authLinks");
		var menuIcon = document.getElementById("menuIcon");

		navLinks.classList.toggle("show");
		authLinks.classList.toggle("show");

		if (navLinks.classList.contains("show")) {
			menuIcon.innerText = "close";
		} else {
			menuIcon.innerText = "menu";
		}
	}

	function toggleProfileMenu() {
		var profileMenu = document.getElementById("profileMenu");
		profileMenu.classList.toggle("show");
	}

	// Close profile menu when clicking outside
	document.addEventListener("click", function(event) {
		var profileMenu = document.getElementById("profileMenu");
		var profileLink = document.querySelector(".profile-wrapper .login-btn");
		if (!profileMenu.contains(event.target) && !profileLink.contains(event.target)) {
			profileMenu.classList.remove("show");
		}
	});
	</script>
</body>
</html>