	<%@page import="com.hotelbookingsystem.DAO.ViewStaffDAO"%>
	<%@page import="com.hotelbookingsystem.model.Users"%>
	
	<%@ page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.sql.SQLException"%>
	<%@ page import="java.lang.ClassNotFoundException"%>
	<%@ taglib
		uri="http://java.sun.com/jsp/jstl/core"
		prefix="c"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>View Staff</title>
	<style>
	table {
		width: 70%;
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
	<link
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		rel="stylesheet">
	</head>
	<body>
		<c:set
			var="activePage"
			value="viewStaff"
			scope="request" />
		<jsp:include page="/admin/adminSidebar.jsp" />
		<%
		if (request.getAttribute("users") == null) {
			try {
				ViewStaffDAO dao = new ViewStaffDAO();
				ArrayList<Users> users = dao.getAllStaffs();
				request.setAttribute("users", users);
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("errorMessage", "Could not load employees: " + e.getMessage());
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<Users> users = (ArrayList<Users>) request.getAttribute("users");
		String error = (String) request.getAttribute("errorMessage");
		%>
	
		<h2>List of All Staffs</h2>
	
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
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone No</th>
					<th>Email</th>
					<th>Address</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Users user : users) {
				%>
				<tr>
					<td><%=user.getUserId()%></td>
					<td><%=user.getFirstName()%></td>
					<td><%=user.getLastName()%></td>
					<td><%=user.getPhoneNo()%></td>
					<td><%=user.getEmail()%></td>
					<td><%=user.getAddress()%></td>
					<td><%=user.getDob()%></td>
					<td><%=user.getGender()%></td>
					<td>
						<!-- Edit Button triggers modal -->
						<button
							class="btn btn-warning btn-sm"
							data-toggle="modal"
							data-target="#editModal<%=user.getUserId()%>">Edit</button> <!-- Delete as GET -->
						<a
						href="${pageContext.request.contextPath}/ManageStaffController?action=delete&user_id=<%= user.getUserId() %>"
						class="btn btn-danger btn-sm"
						onclick="return confirm('Delete <%= user.getFirstName() %> <%= user.getLastName() %>?');">
							Delete </a>
					</td>
				</tr>
	
				<!-- Edit Modal -->
				<div
					class="modal fade"
					id="editModal<%=user.getUserId()%>"
					tabindex="-1"
					role="dialog"
					aria-labelledby="editModalLabel"
					aria-hidden="true">
					<div
						class="modal-dialog"
						role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5
									class="modal-title"
									id="editModalLabel">
									Edit Employee:
									<%=user.getFirstName()%>
									<%=user.getLastName()%></h5>
								<button
									type="button"
									class="close"
									data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form
								action="${pageContext.request.contextPath}/ManageStaffController"
								method="post">
								<div class="modal-body">
									<!-- Tell servlet this is an update -->
									<input
										type="hidden"
										name="action"
										value="update">
									<!-- Hidden user_id -->
									<input
										type="hidden"
										name="user_id"
										value="<%=user.getUserId()%>">
	
									<div class="form-group">
										<label for="firstName">First Name</label> <input
											type="text"
											class="form-control"
											name="firstName"
											value="<%=user.getFirstName()%>"
											required>
									</div>
									<div class="form-group">
										<label for="lastName">Last Name</label> <input
											type="text"
											class="form-control"
											name="lastName"
											value="<%=user.getLastName()%>"
											required>
									</div>
									<div class="form-group">
										<label for="phoneNo">Phone No</label> <input
											type="text"
											class="form-control"
											name="phoneNo"
											value="<%=user.getPhoneNo()%>"
											required>
									</div>
									<div class="form-group">
										<label for="email">Email</label> <input
											type="email"
											class="form-control"
											name="email"
											value="<%=user.getEmail()%>"
											required>
									</div>
									<div class="form-group">
										<label for="address">Address</label> <input
											type="text"
											class="form-control"
											name="address"
											value="<%=user.getAddress()%>"
											required>
									</div>
									<div class="form-group">
										<label for="dob">DOB</label> <input
											type="date"
											class="form-control"
											name="dob"
											value="<%=user.getDob()%>">
									</div>
									<div class="form-group">
										<label for="gender">Gender</label> <select
											class="form-control"
											name="gender"
											required>
											<option
												value="Male"
												<%="Male".equals(user.getGender()) ? "selected" : ""%>>Male</option>
											<option
												value="Female"
												<%="Female".equals(user.getGender()) ? "selected" : ""%>>Female</option>
											<option
												value="Other"
												<%="Other".equals(user.getGender()) ? "selected" : ""%>>Other</option>
										</select>
									</div>
								</div>
								<div class="modal-footer">
									<button
										type="button"
										class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button
										type="submit"
										class="btn btn-primary">Save changes</button>
								</div>
							</form>
						</div>
					</div>
				</div>
	
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else if (error == null) {
		%>
		<p>No employees found.</p>
		<%
		}
		%>
	
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	</body>
	</html>