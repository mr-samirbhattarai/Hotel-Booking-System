<%@ page import="com.hotelbookingsystem.model.Users" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Users user = (Users) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("access/login.jsp");
        return;
    }
%>

<div class="container mt-5">
    <h2 class="text-center">Edit Profile</h2>
    <form action="UpdateProfileController" method="post">
        <div class="form-group">
            <label>First Name</label>
            <input type="text" name="firstName" class="form-control" value="<%= user.getFirstName() %>" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" name="lastName" class="form-control" value="<%= user.getLastName() %>" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control" value="<%= user.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label>Phone No</label>
            <input type="text" name="phoneNo" class="form-control" value="<%= user.getPhoneNo() %>" required>
        </div>
        <div class="form-group">
            <label>Address</label>
            <input type="text" name="address" class="form-control" value="<%= user.getAddress() %>" required>
        </div>
        <div class="form-group">
            <label>DOB</label>
            <input type="date" name="dob" class="form-control" value="<%= user.getDob() %>">
        </div>
        <div class="form-group">
            <label>Gender</label>
            <select name="gender" class="form-control">
                <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
                <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
                <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Save Changes</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
