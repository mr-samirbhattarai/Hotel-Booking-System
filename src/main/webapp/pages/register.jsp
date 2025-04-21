<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .registration-form {
            width: 400px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .registration-form h2 {
            text-align: center;
            margin-bottom: 25px;
        }
        .registration-form label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .registration-form input, .registration-form select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 4px;
        }
        .registration-form button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
        }
        .registration-form button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="registration-form">
        <h2>Register</h2>
        <!-- Error Message Display -->
        <c:if test="${not empty passwordError}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${passwordError} <!-- Display the error message set in RegisterController -->
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/RegisterController" method="post">

            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname">

            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username">

            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword">

            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
