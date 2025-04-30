<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Register | Hotel RockStar</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
  
</head>

<body>
  <div class="main-container">
    <div class="register-container">
      <h2>Create Your Account</h2>
      <p>Please enter your details</p>
   		<!-- For success message -->
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success alert-dismissible fade show" role="alert"> ${successMessage} </div>
		</c:if>
		<!-- For error message -->
		<c:if test="${not empty errorMessage}">
   			<div class="alert alert-danger alert-dismissible fade show" role="alert"> ${errorMessage}</div>
		</c:if>

      <form method="post" action="${pageContext.request.contextPath}/RegisterController">
        <div class="form-container">
          <div class="form-row">
            <div class="form-fields">
              <label for="firstname" class="label-for-form">First Name:</label>
              <input type="text" class="form-control ${not empty firstnameError ? 'is-invalid' : ''}" 
                     id="firstname" name="firstname" value="${param.firstname}" 
                     placeholder="${not empty firstnameError ? firstnameError : 'Enter your first name'}">
            </div>
            <div class="form-fields">
              <label for="lastname" class="label-for-form">Last Name:</label>
              <input type="text" class="form-control ${not empty lastnameError ? 'is-invalid' : ''}" 
                     id="lastname" name="lastname" value="${param.lastname}" 
                     placeholder="${not empty lastnameError ? lastnameError : 'Enter your last name'}">
            </div>
          </div>

          <div class="form-row">
            <div class="form-fields">
              <label for="username" class="label-for-form">Username:</label>
              <input type="text" class="form-control ${not empty usernameError ? 'is-invalid' : ''}" 
                     id="username" name="username" value="${param.username}" 
                     placeholder="${not empty usernameError ? usernameError : 'Enter your username'}">
                     <c:if test="${not empty usernameExistsError}">
					   <small class="text-danger error-message">${usernameExistsError}</small>
					 </c:if> 
            </div>
            <div class="form-fields">
              <label for="email" class="label-for-form">Email:</label>
              <input type="email" class="form-control ${not empty emailError ? 'is-invalid' : ''}" 
                     id="email" name="email" value="${param.email}" 
                     placeholder="${not empty emailError ? emailError : 'Enter your email'}">
                     <c:if test="${not empty emailExistsError}">
					   <small class="text-danger">${emailExistsError}</small>
					 </c:if> 
            </div>
          </div>

          <div class="form-row">
			  <div class="form-fields">
			    <div class="password-wrapper">
			      <label for="password" class="label-for-form">Password:</label>
			      <input type="password" class="form-control ${not empty passwordError || not empty passwordMatchError ? 'is-invalid' : ''}" id="password" name="password" value="${param.password}" 
			             placeholder="${not empty passwordError ? passwordError : (not empty passwordMatchError ? passwordMatchError : 'Enter your password')}">
			      <span class="material-symbols-rounded" onclick="togglePassword('password')">visibility</span>
			      <c:if test="${not empty passwordMatchError}">
			        <small class="text-danger">${passwordMatchError}</small>
			      </c:if>
			    </div>
			  </div>

			  <div class="form-fields">
			    <div class="password-wrapper">
			      <label for="retypePassword" class="label-for-form">Retype Password:</label>
			      <input type="password" class="form-control ${not empty retypePasswordError || not empty passwordMatchError ? 'is-invalid' : ''}" id="retypePassword" name="retypePassword" value="${param.retypePassword}" 
			             placeholder="${not empty retypePasswordError ? retypePasswordError : (not empty passwordMatchError ? passwordMatchError : 'Retype your password')}">
			      <span class="material-symbols-rounded" onclick="togglePassword('retypePassword')">visibility</span>
			      <c:if test="${not empty passwordMatchError}">
			        <small class="text-danger">${passwordMatchError}</small>
			      </c:if>
			    </div>
			  </div>
		</div>
          <div class="form-check">
            <input class="form-check-input ${not empty termsError ? 'is-invalid' : ''}" type="checkbox" id="terms" name="terms" ${param.terms == 'on' ? 'checked' : ''}>
            <label class ="form-check-label" for="terms">I agree to the terms and conditions</label>
        </div>
        </div>
                <button type="submit" class="btn-register">Register</button>
      </form>
    </div>

    <div class="image-section">
      <img src="${pageContext.request.contextPath}/images/Room-view.jpg" alt="Hotel Image">
      <div class="overlay">
        <h2>Welcome to Hotel RockStar</h2>
        <p>Book your stay with us and enjoy a luxurious experience.</p>
        <div class="login">
          <span>Already have an account?</span><br/>
          <a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
        </div>
      </div>
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
