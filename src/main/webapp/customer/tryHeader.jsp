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
						href="${pageContext.request.contextPath}/RoomsController?page=viewRooms">Rooms</a>

					</li>
					<li><a href="#">About Us</a></li>
					<li><a href="#">Contact</a></li>
					<li><button class="mobile-login">Login</button></li>
				</ul>
				<span
					id="menuIcon"
					class="material-symbols-rounded menu-icon"
					onclick="toggleMenu()">menu</span>
			</nav>
			<!-- Sidebar -->
			<div
				id="sidebar"
				class="sidebar">
				<a
					href="javascript:void(0)"
					class="closebtn"
					onclick="toggleSidebar()">&times;</a> <a
					href="${pageContext.request.contextPath}/ProfileController">Profile</a>
				<a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
			</div>

		</div>
	</header>
	<script>
		function toggleMenu() {
			var navLinks = document.getElementById("navLinks");
			var menuIcon = document.getElementById("menuIcon");

			navLinks.classList.toggle("show");

			if (navLinks.classList.contains("show")) {
				menuIcon.innerText = "close";
			} else {
				menuIcon.innerText = "menu";
			}
		}
	</script>
</body>
</html>