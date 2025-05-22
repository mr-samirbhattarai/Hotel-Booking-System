<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0" />
	<title>About Us | Hotel RockStar</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutUs.css" />

</head>
<body>
	<c:set var="activePage" value="aboutUs" scope="request" />
	<jsp:include page="/customer/header.jsp" />

	<div class="about-container">
		<h1 class="about-title">About Hotel RockStar</h1>
		<p class="about-subtitle">Welcome to Hotel RockStar – Where Luxury
			Meets Comfort.</p>

		<div class="about-section">
			<div class="about-image">
				<img
					src="${pageContext.request.contextPath}/images/suit 5.jpg"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/imgNotFound.jpg';"
					alt="Hotel View" />
			</div>
			<div class="about-text">
				<p>At Hotel RockStar, we redefine the meaning of luxury stays.
					With elegant interiors, modern amenities, and exceptional service,
					we promise an experience that blends relaxation and sophistication.
					Whether you're traveling for business or leisure, our hotel offers
					the perfect escape.</p>
				<p>Our system showcases a curated selection of rooms with
					detailed descriptions, pricing, and real-time availability. Each
					listing includes high-quality images, room features, and amenities
					to help you make the best choice for your journey.</p>
				<div class="mission-box">
					<h4>Our Mission</h4>
					<p>To simplify travel by providing a seamless, secure, and
						user-friendly hotel booking experience—connecting guests with
						comfort, convenience, and exceptional service at every stay. Based
						in the scenic city of Pokhara, Matepani, we strive to bring the
						warmth of local hospitality to every traveler’s journey.</p>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/customer/footer.jsp" />
</body>
</html>