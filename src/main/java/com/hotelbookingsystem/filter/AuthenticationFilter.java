package com.hotelbookingsystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/access/login.jsp", "/access/register.jsp" })
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("role") != null;

        if (loggedIn) {
            String role = (String) session.getAttribute("role");
            String contextPath = req.getContextPath();
            if ("admin".equalsIgnoreCase(role)) {
                res.sendRedirect(contextPath + "/admin/dashboard.jsp");
            } else if ("customer".equalsIgnoreCase(role)) {
                res.sendRedirect(contextPath + "/RoomsController");
            } else {
                res.sendRedirect(contextPath + "/error.jsp"); // Handle unknown roles
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}

