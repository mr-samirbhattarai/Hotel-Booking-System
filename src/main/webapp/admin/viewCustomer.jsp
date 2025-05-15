<%@page import="com.hotelbookingsystem.DAO.ViewCustomerDAO"%>
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hotelbookingsystem.model.Users"%>
<%@ page import="com.hotelbookingsystem.DAO.ViewStaffDAO"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.lang.ClassNotFoundException"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>View Customers</title>
<style>
table {
	width: 80%;
	border-collapse: collapse;
	margin-top: 20px;
	margin-left: 300px;
}

th, td {
	padding: 10px;
	border: 1px solid #999;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

h2 {
	text-align: center;
}
</style>
</head>
<body>
	<c:set
		var="activePage"
		value="viewCustomer"
		scope="request" />
	<jsp:include page="/admin/adminSidebar.jsp" />
	<%
	if (request.getAttribute("users") == null) {
		try {
			ViewCustomerDAO dao = new ViewCustomerDAO();
			ArrayList<Users> users = dao.getAllUsers();
			request.setAttribute("users", users);
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("errorMessage", "Could not load users: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	ArrayList<Users> users = (ArrayList<Users>) request.getAttribute("users");
	String error = (String) request.getAttribute("errorMessage");
	%>

	<h2>List of All Users</h2>

	<%
	if (error != null) {
	%>
	<p style="color: red;"><%=error%></p>
	<%
	}
	%>

	<%
	if (users != null && !users.isEmpty()) {
	%>
	<table>
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>User Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>DOB</th>
		</tr>
		<%
		for (Users user : users) {
		%>
		<tr>
			<td><%=user.getUserId()%></td>
			<td><%=user.getFirstName()%></td>
			<td><%=user.getLastName()%></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getEmail()%></td>
			<td><%=user.getGender()%></td>
			<td><%=user.getDob()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else if (error == null) {
	%>
	<p>No users found.</p>
	<%
	}
	%>

</body>
</html>