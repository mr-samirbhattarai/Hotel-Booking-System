package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelbookingsystem.DAO.RegisterDAO;
import com.hotelbookingsystem.model.UserModel;
import com.hotelbookingsystem.utility.EncryptDecrypt;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EncryptDecrypt encryptdecrypt = new EncryptDecrypt();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String terms = request.getParameter("terms");
        
        
     // Input validations
        if (firstName == null || firstName.trim().isEmpty()) {
            request.setAttribute("firstnameError", "First name is required!");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (lastName == null || lastName.trim().isEmpty()) {
            request.setAttribute("lastnameError", "Last name is required!");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Username is required!");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email is required!");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("emailError", "Invalid email format.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (password == null || password.isEmpty()) {
            request.setAttribute("passwordError", "Password cannot be empty.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
//        if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
//            request.setAttribute("passwordError", "Password must be at least 8 characters, including uppercase, lowercase, a number, and a special character.");
//            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//            return;
//        }
        
        if (retypePassword == null || retypePassword.isEmpty()) {
            request.setAttribute("retypePasswordError", "Please confirm your password.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (!password.equals(retypePassword)) {
            request.setAttribute("passwordMatchError", "Passwords do not match.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        if (terms == null || !terms.equals("on")) {
            request.setAttribute("termsError", "You must agree to the terms and conditions.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }
        
        
        
        try {
        	
            RegisterDAO dao = new RegisterDAO();
            if (dao.usernameExists(username)) {
                request.setAttribute("usernameExistsError", "This username is already taken.");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                return;
            }

            if (dao.emailExists(email)) {
                request.setAttribute("emailExistsError", "Email is already registered!");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                return;
            }
            
            // Encrypt password
            String encryptedPassword = encryptdecrypt.encrypt(password);
            if (encryptedPassword == null) {
                System.err.println("Password encryption failed for username: " + username);
                request.setAttribute("errorMessage", "Something went wrong while securing your password. Please try again.");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                return;
            }

            // Set user details
            UserModel userModel = new UserModel();
            userModel.setFirstName(firstName);
            userModel.setLastName(lastName);
            userModel.setUsername(username);
            userModel.setEmail(email);
            userModel.setPassword(encryptedPassword);

            // Register user
            boolean isRegistered = dao.registerUser(userModel);
            if (isRegistered) {
 //               request.setAttribute("status", "success");
                request.setAttribute("successMessage", "Registration successful! You can now log in.");
            } else {
  //              request.setAttribute("status", "error");
                request.setAttribute("errorMessage", "Registration failed. Please try again later.");
            }
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Something went wrong, please contact support");
		    request.getRequestDispatcher("/pages/register.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            request.setAttribute("errorMessage", "Server is busy or temporarily unavailable. Please try again later.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
		}
    }
}
