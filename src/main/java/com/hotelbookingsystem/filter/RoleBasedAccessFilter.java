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

@WebFilter(urlPatterns = { "/customer/*", "/admin/*" })
public class RoleBasedAccessFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("role") != null;

		// Allow access to login/register pages and controllers
		if (uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.contains("/LoginController") || uri.contains("/RegisterController")) {
			chain.doFilter(request, response);
			return;
		}

		if (!loggedIn) {
			res.sendRedirect(req.getContextPath() + "/access/login.jsp");
			return;
		}

		String role = (String) session.getAttribute("role");
		// Role-based access control
		if (uri.contains("/admin/") && !"admin".equalsIgnoreCase(role)) {
			res.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}
		if (uri.contains("/customer/") && !"customer".equalsIgnoreCase(role)) {
			res.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}

		chain.doFilter(request, response);
	}
}
