<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Page Not Found | Hotel RockStar</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: 'Poppins', sans-serif;
    }

    body {
      background-color: #1a1a1a;
      color: #fff;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      text-align: center;
      padding: 20px;
    }

    .error-container {
      max-width: 600px;
    }

    .error-code {
      font-size: 100px;
      font-weight: 600;
      color: #e74c3c;
    }

    .error-message {
      font-size: 24px;
      margin-top: 10px;
      margin-bottom: 20px;
    }

    .back-btn {
      padding: 10px 20px;
      background-color: transparent;
      color: white;
      border: 1px solid white;
      border-radius: 5px;
      font-size: 16px;
      text-decoration: none;
      transition: all 0.3s ease;
    }

    .back-btn:hover {
      background-color: white;
      color: #1a1a1a;
    }
  </style>
</head>
<body>
  <div class="error-container">
    <div class="error-code">404</div>
    <div class="error-message">Oops! The page you're looking for doesn't exist or has been moved.</div>
    <a href="${pageContext.request.contextPath}/customer/home.jsp" class="back-btn">Go Back Home</a>
  </div>
</body>
</html>
