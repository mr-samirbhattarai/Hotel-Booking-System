<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Login</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f2f2f2; }
    .login-form {
      width: 360px; margin: 80px auto; padding: 30px;
      background: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .login-form h2 { text-align: center; margin-bottom: 20px; }
    .login-form label { display: block; margin-bottom: 5px; font-weight: bold; }
    .login-form input {
      width: 100%; padding: 8px; margin-bottom: 15px;
      border: 1px solid #ccc; border-radius: 4px;
    }
    .login-form button {
      width: 100%; padding: 10px; background: #4CAF50;
      color: #fff; border: none; border-radius: 4px; font-size: 16px;
    }
    .login-form button:hover { background: #45a049; }
    .error { color: red; text-align: center; margin-bottom: 15px; }
  </style>
</head>
<body>
	<div class="login-form">
			<h2>Login</h2>
			<!-- Error Message Display -->
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					${errorMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/LoginController" method="post">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
  
	
      <button type="submit">Login</button>
    </form>
		</div>
</body>
</html>



