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

    <style>
      @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap");

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
      }

      body {
        background: linear-gradient(#f1f3ff, #cbd4ff);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
      }

      .login-container {
        background-color: #fff;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        max-width: 900px;
        width: 100%;
      }

      h2 {
        font-size: 28px;
        font-weight: 600;
        text-align: center;
        color: #151a2d;
        margin-bottom: 10px;
      }

      p {
        text-align: center;
        color: #666;
        font-size: 16px;
        margin-bottom: 10px;
      }

      .form-label {
        font-size: 15px;
        font-weight: 500;
        color: #151a2d;
        margin-bottom: 8px;
      }

      .form-control {
        border-radius: 8px;
        border: 1px solid #ced4da;
        padding: 12px;
        font-size: 16px;
        transition: border-color 0.3s ease, box-shadow 0.3s ease;
      }

      .form-control:focus {
        border-color: #19211c;
        box-shadow: 0 0 8px rgba(27, 38, 32, 0.3);
        outline: none;
      }

      .position-relative {
        position: relative;
      }

      .material-symbols-rounded {
        position: absolute;
        right: 34px;
        top: 68%;
        transform: translateY(-50%);
        cursor: pointer;
        color: #888;
        font-size: 22px;
        transition: color 0.3s ease;
      }

      .material-symbols-rounded:hover {
        color: #000;
      }

      .btn-login {
        background-color: #000;
        color: #fff;
        width: 160px;
        padding: 10px 20px;
        border-radius: 8px;
        border: 2px solid transparent;
        transition: all 0.3s ease;
        display: block;
        margin: 20px auto 0;
        font-size: 16px;
        margin-top: 30px;
      }

      .btn-login:hover {
        background-color: #fff;
        color: #000;
        border: 2px solid #000;
      }

      .footer-text {
        text-align: center;
        margin-top: 20px;
        font-size: 16px;
        color: #666;
      }

      .footer-text span {
        display: inline;
      }

      .footer-text a {
        color: #000;
        text-decoration: none;
        font-weight: 500;
        margin-left: 5px;
      }

      .footer-text a:hover {
        text-decoration: underline;
        color: #000;
      }

    </style>
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