<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login | Hotel RockStar</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
    <link rel="stylesheet" href="../css/login.css" />
	
  </head>

  <body>
    <div class="login-container">
      <h2>Login Your Account</h2>
      <p>Welcome! Please enter your username and password</p>

      <form>

        <div class="row gx-5 justify-content-center">
          <div class="col-md-5 mt-4">
            <label for="username" class="form-label">Username:</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username">
          </div>
        </div>

        <div class="row gx-5 justify-content-center">
          <div class="col-md-5 mt-4 position-relative">
            <label for="password" class="form-label">Password:</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            <span class="material-symbols-rounded" onclick="togglePassword('password')">visibility</span>
          </div>
        </div>

        <button type="submit" class="btn btn-login">Login</button>

        <div class="footer-text">
          <span>Don't have an account?</span>
          <a href="./register.jsp">Register</a>
        </div>
      </form>
    </div>
  </body>
  
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
    </script>
  
</html>