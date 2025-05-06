<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Staff Detail</title>
    <style>
        body {
            font-family: "Poppins", sans-serif;
            margin: 0;
            padding: 0;
        }

        .form-container {
            background-color: #ffffff;
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .button-group {
            text-align: center;
        }

        .button {
            background-color: black;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
        }

        .button:hover {
            background-color: #333;
        }
    </style>
    <link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

<div class="form-container">
    <h2>Add New Staff</h2>
    <form action="${pageContext.request.contextPath}/AddStaffController" method="post">

        <label for="firstname">First Name</label>
        <input type="text" name="firstname" required>

        <label for="lastname">Last Name</label>
        <input type="text" name="lastname" required>

        <label for="username">Username</label>
        <input type="text" name="username" required>

        <label for="password">Password</label>
        <input type="password" name="password" required>

        <label for="phoneNo">Phone</label>
        <input type="text" name="phoneNo">

        <label for="email">Email</label>
        <input type="email" name="email" required>

        <label for="address">Address</label>
        <input type="text" name="address">

        <label for="dob">Date of Birth</label>
        <input type="date" name="dob">

        <label for="gender">Gender</label>
        <select name="gender" required>
            <option value="" disabled selected>Select gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>

        <div class="button-group">
            <button type="submit" class="button">Add Staff</button>
            <button type="reset" class="button">Reset</button>
        </div>

    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
