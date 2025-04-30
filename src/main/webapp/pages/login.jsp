<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login | Hotel RockStar</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
  </head>

  <body>
  <div class="main-container">
    <div class="image-section">
      <img src="${pageContext.request.contextPath}/images/Room-view.jpg" alt="Hotel Image">
      <div class="overlay">
        <h2>Welcome to Hotel RockStar</h2>
        <p>Book your stay with us and enjoy a luxurious experience.</p>
        <div class="register">
          <span>Don't have an account?</span><br/>
          <a href="${pageContext.request.contextPath}/pages/register.jsp">Register</a>
        </div>
      </div>
    </div>

    <div class="login-container">
      <h2>Login Your Account</h2>
      <p>Please, enter your username and password</p>
      <form action="${pageContext.request.contextPath}/LoginController" method="post">
        <div class="form-container">
	          <div class="form-row">
	            <div class="form-fields">
	              <label for="username" class="label-for-form">Username:</label>
	              <input type="text" class="form-control ${not empty usernameError ? 'is-invalid' : ''}" 
	                     id="username" name="username" value="${param.username}" 
	                     placeholder="${not empty usernameError ? usernameError : 'Enter your username'}">
	            </div>
	          </div>

	          <div class="form-row">
				  <div class="form-fields">
				    <div class="password-wrapper">
				      <label for="password" class="label-for-form">Password:</label>
				      <input type="password" class="form-control ${not empty passwordError || not empty passwordMatchError ? 'is-invalid' : ''}" id="password" name="password" value="${param.password}" 
				             placeholder="${not empty passwordError ? passwordError : (not empty passwordMatchError ? passwordMatchError : 'Enter your password')}">
				      <span class="material-symbols-rounded" onclick="togglePassword('password')">visibility</span>
				    </div>
				  </div>
			</div>
			<div class="forget-password-section">
	          <a href="#">Forgot Password?</a>
	        </div>
        </div>
        <button type="submit" class="btn-login">Login</button>
      </form>
    </div>
  </div>

  <script>
    function togglePassword(inputId) {
      const input = document.getElementById(inputId);
      const icon = input.nextElementSibling;
      if (input.type === "password") {
        input.type = "text";
        icon.textContent = "visibility_off";
      } else {
        input.type = "password";
        icon.textContent = "visibility";
      }
    }
  </script>
</body>
</html>