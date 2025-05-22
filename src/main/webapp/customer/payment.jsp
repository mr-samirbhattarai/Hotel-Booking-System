<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Payment - Hotel RockStar</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/changePassword.css">
	<style>
		h2 {
			text-align: center;
			margin-bottom: 20px;
			color: #000;
			font-weight: 600;
		}
	</style>
</head>
<body>
	<jsp:include page="/customer/header.jsp" />

	<div class="container">
		<h2>Payment Information</h2>

		<c:if test="${not empty errorMessage}">
			<p class="error">${errorMessage}</p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p class="success">${successMessage}</p>
		</c:if>

		<form action="${pageContext.request.contextPath}/PaymentController" method="post">

			<input type="hidden" name="bookingId" value="${bookingId}">
			<input type="hidden" name="amount" value="${amount}">

			<div class="form-group">
				<label for="paymentMethod">Payment Method:</label>
				<select id="paymentMethod" name="paymentMethod">
					<option value="">-- Select Method --</option>
					<option value="credit_card" ${paymentMethod == 'credit_card' ? 'selected' : ''}>Credit Card</option>
					<option value="debit_card" ${paymentMethod == 'debit_card' ? 'selected' : ''}>Debit Card</option>
					<option value="cash" ${paymentMethod == 'cash' ? 'selected' : ''}>Cash</option>
					<option value="online" ${paymentMethod == 'online' ? 'selected' : ''}>Online</option>
				</select>
			</div>

			<div class="form-group">
				<label for="cardHolder">Card Holder Name:</label>
				<input type="text" id="cardHolder" name="cardHolder" placeholder="Name on Card" value="${cardHolder}">
			</div>

			<div class="form-group">
				<label for="cardNumber">Card Number:</label>
				<input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456" value="${cardNumber}">
			</div>

			<div class="form-group">
				<label for="expiry">Expiry Date:</label>
				<input type="month" id="expiry" name="expiry" value="${expiry}">
			</div>

			<div class="form-group">
				<label for="cvv">CVV:</label>
				<input type="password" id="cvv" name="cvv" placeholder="CVV" value="${cvv}">
			</div>

			<button type="submit" class="submit-btn">Pay Now</button>
		</form>
	</div>
</body>
</html>
