package com.hotelbookingsystem.controller;

import java.io.IOException;
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

import com.hotelbookingsystem.DAO.RegisterDAO;
import com.hotelbookingsystem.model.User;

@WebServlet("/RegisterController")  // Add this annotation for servlet mapping
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       EncryptDecrypt encryptdecrypt = new EncryptDecrypt();
    	
       String firstName = request.getParameter("firstname");
       String lastName = request.getParameter("lastname");
       String username = request.getParameter("username");
       String email = request.getParameter("email");
       String password = request.getParameter("password");
      String confirmPassword = request.getParameter("confirmPassword");
      
      //checking if the passwords are similar
      if (password.equals(confirmPassword)) {
          
          // Creating user object and setting values
          User user = new User();
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setUsername(username);
          user.setEmail(email);
          user.setPassword(encryptdecrypt.encrypt(password));

          //registering the user
          RegisterDAO dao = new RegisterDAO();
          boolean isRegistered = dao.registerUser(user);

          // Redirect
          if (isRegistered) {
              response.sendRedirect("success.jsp");
          } else {
              response.sendRedirect("error.jsp");
          }
      } else {
          // Passwords do not match, send back an error message
          request.setAttribute("passwordError", "Passwords do not match.");
          request.getRequestDispatcher("pages/register.jsp").forward(request, response);
      }
  }
}

class EncryptDecrypt {
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
			return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
}
