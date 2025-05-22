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
<title>Change Password - Hotel RockStar</title>
<link
	rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap">
<link
	rel="stylesheet"
	href="${pageContext.request.contextPath}/css/changePassword.css">
</head>
<body>
	<jsp:include page="/customer/header.jsp" />
	<div class="container">
		<c:if test="${not empty errorMessage}">
			<p class="error">${errorMessage}</p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p class="success">${successMessage}</p>
		</c:if>
		<form
			action="${pageContext.request.contextPath}/ChangePasswordController"
			method="post">
			<div class="form-group password-wrapper">
				<label for="currentPassword">Current Password:</label> <input
					type="password"
					id="currentPassword"
					name="currentPassword"
					 value="${currentPassword}"
					placeholder="Enter your current password"> <span
					class="material-symbols-rounded"
					onclick="togglePassword('currentPassword', this)">visibility</span>
			</div>

			<div class="form-group password-wrapper">
				<label for="newPassword">New Password:</label> <input
					type="password"
					id="newPassword"
					name="newPassword"
					 value="${newPassword}"
					placeholder="Enter your new password"> <span
					class="material-symbols-rounded"
					onclick="togglePassword('newPassword', this)">visibility</span>
			</div>

			<div class="form-group password-wrapper">
				<label for="confirmPassword">Confirm New Password:</label> <input
					type="password"
					id="confirmPassword"
					name="confirmPassword"
					 value="${confirmPassword}"
					placeholder="Re-enter your new password"> <span
					class="material-symbols-rounded"
					onclick="togglePassword('confirmPassword', this)">visibility</span>
			</div>
			<button
				type="submit"
				class="submit-btn">Change Password</button>
		<a
				href="${pageContext.request.contextPath}/customer/home.jsp"
				class="back-btn">Back</a>
		</form>
	</div>


	<script>
    function togglePassword(inputId, iconElement) {
        const input = document.getElementById(inputId);
        if (input.type === "password") {
            input.type = "text";
            iconElement.textContent = "visibility_off";
        } else {
            input.type = "password";
            iconElement.textContent = "visibility";
        }
    }
</script>
</body>
</html>