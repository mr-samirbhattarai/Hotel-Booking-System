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
<meta charset="UTF-8">
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Customer Profile | Hotel RockStar</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
.profile-container {
	max-width: 600px;
	margin: auto;
	margin-top: 110px;
	margin-bottom: 10px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
}

.form-label {
	font-weight: bold;
}

.error-message {
	color: red;
	font-size: 0.9em;
}

.update-btn {
	padding: 10px 20px;
	background-color: #000;
	color: #fff;
	border: 2px solid transparent;
	cursor: pointer;
	font-size: 16px;
	border-radius: 5px;
	transition: all 0.3s ease;
	text-decoration: none;
}

.update-btn:hover {
	background-color: transparent;
	color: #000;
	border: 2px solid #000;
}

.back-btn {
	padding: 10px 50px;
	background-color: #fff;
	margin: 20px;
	color: #000;
	border: 2px solid #000;
	cursor: pointer;
	font-size: 16px;
	border-radius: 5px;
	transition: all 0.3s ease;
	text-decoration: none;
	color: #000;
}

.back-btn:hover {
	background-color: #000;
	color: #fff;
	border: 2px solid #000;
}
</style>
</head>
<body>

	<jsp:include page="/customer/header.jsp" />
	<div class="profile-container">

		<h2>Your Profile</h2>
		<p>View or update your personal information</p>
		<c:if test="${not empty successMessage}">
			<div
				class="alert alert-success alert-dismissible fade show"
				role="alert">
				${successMessage}
				<button
					type="button"
					class="btn-close"
					data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div
				class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${errorMessage}
				<button
					type="button"
					class="btn-close"
					data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>


		<form
			method="post"
			action="${pageContext.request.contextPath}/ProfileController">
			<div class="mb-3">
				<label
					for="firstname"
					class="form-label">First Name</label> <input
					type="text"
					class="form-control"
					id="firstname"
					name="firstname"
					value="${user.firstName}"
					required>
			</div>
			<div class="mb-3">
				<label
					for="lastname"
					class="form-label">Last Name</label> <input
					type="text"
					class="form-control"
					id="lastname"
					name="lastname"
					value="${user.lastName}"
					required>
			</div>
			<div class="mb-3">
				<label
					for="email"
					class="form-label">Email</label> <input
					type="email"
					class="form-control"
					id="email"
					name="email"
					value="${user.email}"
					required>
			</div>
			<div class="mb-3">
				<label
					for="phoneNo"
					class="form-label">Phone Number</label> <input
					type="text"
					class="form-control"
					id="phoneNo"
					name="phoneNo"
					value="${user.phoneNo}"
					pattern="\d{10}"
					required>
			</div>
			<div class="mb-3">
				<label
					for="address"
					class="form-label">Address</label> <input
					type="text"
					class="form-control"
					id="address"
					name="address"
					value="${user.address}">
			</div>
			<div class="mb-3">
				<label
					for="dob"
					class="form-label">Date of Birth</label> <input
					type="date"
					class="form-control"
					id="dob"
					name="dob"
					value="${user.dob != null ? user.dob : ''}">
			</div>
			<%@ taglib
				uri="http://java.sun.com/jsp/jstl/core"
				prefix="c"%>

			<div class="mb-3">
				<label
					for="gender"
					class="form-label">Gender</label> <select
					class="form-control"
					id="gender"
					name="gender">
					<option
						value=""
						<c:if test="${empty user.gender}">selected</c:if>>Select</option>
					<option
						value="Male"
						<c:if test="${user.gender == 'male'}">selected</c:if>>Male</option>
					<option
						value="Female"
						<c:if test="${user.gender == 'female'}">selected</c:if>>Female</option>
					<option
						value="Other"
						<c:if test="${user.gender == 'other'}">selected</c:if>>Other</option>
				</select>
			</div>


			<button
				type="submit"
				class="update-btn">Update Profile</button>

			<a
				href="${pageContext.request.contextPath}/customer/home.jsp"
				class="back-btn">Back</a>

		</form>
	</div>

	<jsp:include page="/customer/footer.jsp" />
</body>
</html>