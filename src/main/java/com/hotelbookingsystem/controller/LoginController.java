package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.LoginDAO;
import com.hotelbookingsystem.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {
	 Encrypt encryptt = new Encrypt();
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			LoginDAO dao = new LoginDAO();
			UserModel userModel = dao.loginUser(email, encryptt.encrypt(password));
//creating a session 
			if (userModel != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loggedInUser", userModel);
				//setting the interval for the user
				session.setMaxInactiveInterval(120);
				response.sendRedirect("success.jsp");
			} else {
				request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response); // ✅ Corrected path
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "A system error occurred. Please try again later.");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response); // ✅ Corrected path
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}


 class Encrypt {
	private static final String SECRET_KEY = "HotelRockstar";
	private static final String SALT = "GrandTheftAutoVI";

	public String encrypt(String password) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			String encryptedPassword =  Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
			return encryptedPassword;
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
}