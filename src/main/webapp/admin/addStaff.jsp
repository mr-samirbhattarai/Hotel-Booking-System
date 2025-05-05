<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee Detail</title>
    <style>
        body {
            background-color: #a3ffbc;
            font-family: sans-serif;
        }
        .form-container {
            width: 800px;
            margin: auto;
        }
        label {
            display: inline-block;
            width: 150px;
            font-weight: bold;
            margin: 10px 0;
        }
        input, select {
            width: 200px;
            padding: 5px;
            background-color: #b1fcc5;
        }
        .button {
            background-color: black;
            color: white;
            padding: 10px 20px;
            margin: 20px 10px;
            border: none;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Add Employee</h2>
   	<form action="${pageContext.request.contextPath}/AddEmployeeController" method="post">
        <label>First Name:</label><input type="text" name="firstname"><br>
<label>Last Name:</label><input type="text" name="lastname"><br>
<label>Username:</label><input type="text" name="username"><br>
 <label>Password:</label><input type="password" name="password" required><br>
        <label>Phone:</label><input type="text" name="phoneNo"><br>
        <label>Email:</label><input type="text" name="email"><br>
        <label>Address:</label><input type="text" name="address"><br>
        <label>Date of Birth:</label><input type="date" name="dob"><br>
        <label>Gender:</label><input type="text" name="gender"><br>
        
        
        
        <input class="button" type="submit">

    </form>
</div>
</body>
</html>