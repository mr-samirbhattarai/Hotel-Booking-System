<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register | Hotel RockStar</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
        <link rel="stylesheet" href="../css/register.css" />
    

    
  </head>

  <body>
    <div class="register-container">
      <h2>Create Your Account</h2>
      <p>Welcome! Please enter your details</p>

      <!-- Display error message if present -->
      <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
      </c:if>

      <form action="${pageContext.request.contextPath}/RegisterController" method="post" onsubmit="return validateForm()">
        <div class="row gx-5 justify-content-center">
          <div class="col-md-5 mt-4">
            <label for="firstName" class="form-label">First Name:</label>
            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name" value="${param.firstName}">
          </div>
          <div class="col-md-5 mt-4">
            <label for="lastName" class="form-label">Last Name:</label>
            <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name" value="${param.lastName}">
          </div>
        </div>

        <div class="row gx-5 justify-content-center">
          <div class="col-md-5 mt-4">
            <label for="username" class="form-label">Username:</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" value="${param.username}">
          </div>
          <div class="col-md-5 mt-4">
            <label for="email" class="form-label">Email:</label>
            <input type="email" id="email" name="email" class="form-control" placeholder="Email Address" value="${param.email}">
          </div>
        </div>

        <div class="row gx-5 justify-content-center">
          <div class="col-md-5 mt-4 position-relative">
            <label for="password" class="form-label">Password:</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            <span class="material-symbols-rounded" onclick="togglePassword('password')">visibility</span>
          </div>
          <div class="col-md-5 mt-4 position-relative">
            <label for="retypePassword" class="form-label">Retype Password:</label>
            <input type="password" id="retypePassword" name="retypePassword" class="form-control" placeholder="Confirm Password">
            <span class="material-symbols-rounded" onclick="togglePassword('retypePassword')">visibility</span>
          </div>
        </div>

        <div class="form-check mt-4">
          <input class="form-check-input" type="checkbox" id="terms" name="terms" required />
          <label class="form-check-label" for="terms">I accept all terms & conditions</label>
        </div>

        <button type="submit" class="btn btn-register">Register</button>

        <div class="footer-text">
          <span>Already have an account?</span>
          <a href="./login.jsp">Log in</a>
        </div>
      </form>
    </div>

    <script>
      function togglePassword(id) {
        const input = document.getElementById(id);
        const icon = input.nextElementSibling;
        if (input.type === "password") {
          input.type = "text";
          icon.innerText = "visibility_off";
        } else {
          input.type = "password";
          icon.innerText = "visibility";
        }
      }

      function validateForm() {
        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const username = document.getElementById('username').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        const retypePassword = document.getElementById('retypePassword').value;
        const terms = document.getElementById('terms').checked;

        if (!firstName || !lastName || !username || !email || !password || !retypePassword) {
          alert('Please fill in all required fields.');
          return false;
        }

        if (!terms) {
          alert('You must accept the terms and conditions.');
          return false;
        }

        if (password !== retypePassword) {
          alert('Passwords do not match!');
          return false;
        }

        return true;
      }
    </script>
  </body>
</html>