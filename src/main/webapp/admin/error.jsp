<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Error</h2>
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
        <a href="${pageContext.request.contextPath}/GetBookingByStatus" class="btn btn-primary">Back to Bookings</a>
    </div>
</body>
</html>