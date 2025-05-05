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

@WebFilter({"/access/login.jsp", "/access/register.jsp"})  // Only apply to login and register pages
public class LoginRegisterFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            String role = (String) session.getAttribute("role");

            // Redirect to a page that is NOT filtered here
            if ("admin".equalsIgnoreCase(role)) {
                res.sendRedirect(req.getContextPath() + "/adminDashboard.jsp");
            } else if ("staff".equalsIgnoreCase(role)) {
                res.sendRedirect(req.getContextPath() + "/staffPortal.jsp");
            } else {
                res.sendRedirect(req.getContextPath() + "/RoomsController");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}
}
