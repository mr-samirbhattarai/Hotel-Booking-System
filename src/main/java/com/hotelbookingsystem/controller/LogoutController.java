package com.hotelbookingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidate the session to remove all attributes and make it expire
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Explicitly delete JSESSIONID cookie
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("JSESSIONID".equals(cookie.getName())) {
//                    cookie.setMaxAge(0);       // Expire the cookie immediately
//                    cookie.setPath("/");       // Path must match the original cookie path
//                    response.addCookie(cookie);
//                }
//            }
//        }

        // Redirect to login page
        response.sendRedirect("access/login.jsp");
    }
}