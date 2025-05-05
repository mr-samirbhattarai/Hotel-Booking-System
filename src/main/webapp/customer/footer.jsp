<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer>
    <style>
        .footer {
            background-color: #4CAF50;
            padding: 15px;
            text-align: center;
            color: white;
            position: relative;
            bottom: 0;
            width: 100%;
            box-shadow: 0 -2px 5px rgba(0,0,0,0.1);
        }
        .footer-links a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
        }
        .footer-links a:hover {
            text-decoration: underline;
        }
        .footer p {
            margin: 10px 0 0;
            font-size: 14px;
        }
    </style>
    <div class="footer">
        <div class="footer-links">
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
            <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
            <a href="${pageContext.request.contextPath}/contact.jsp">Contact</a>
        </div>
        <p>&copy; 2025 My Web App. All rights reserved.</p>
    </div>
</footer>