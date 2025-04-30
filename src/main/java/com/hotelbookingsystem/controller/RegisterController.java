//package com.hotelbookingsystem.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.hotelbookingsystem.DAO.RegisterDAO;
//import com.hotelbookingsystem.model.UserModel;
//import com.hotelbookingsystem.utility.EncryptDecrypt;
//
//@WebServlet("/RegisterController")
//public class RegisterController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private EncryptDecrypt encryptdecrypt = new EncryptDecrypt();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Retrieve form parameters
//        String firstName = request.getParameter("firstname");
//        String lastName = request.getParameter("lastname");
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String retypePassword = request.getParameter("retypePassword");
//        String terms = request.getParameter("terms");
//
//        boolean hasError = false;
//
//        try {
//            RegisterDAO dao = new RegisterDAO();
//
//            // Validate inputs
//            if (firstName == null || firstName.trim().isEmpty()) {
//                request.setAttribute("firstnameError", "First name is required!");
//                hasError = true;
//            }
//
//            else if (lastName == null || lastName.trim().isEmpty()) {
//                request.setAttribute("lastnameError", "Last name is required!");
//                hasError = true;
//            }
//
//            else if (username == null || username.trim().isEmpty()) {
//                request.setAttribute("usernameError", "Username is required!");
//                hasError = true;
//            } 
//            
//            else if (dao.usernameExists(username)) {
//                request.setAttribute("usernameError", "Username already exists!");
//                hasError = true;
//            }
//
//            else if (email == null || email.trim().isEmpty()) {
//                request.setAttribute("emailError", "Email is required!");
//                hasError = true;
//            } 
//            
//            else if (dao.emailExists(email)) {
//                request.setAttribute("emailError", "Email is already registered!");
//                hasError = true;
//            }
//
//            else if (password == null || password.isEmpty()) {
//                request.setAttribute("passwordError", "Password is required!");
//                hasError = true;
//            }
//
//            else if (retypePassword == null || retypePassword.isEmpty()) {
//                request.setAttribute("retypePasswordError", "Please confirm your password!");
//                hasError = true;
//            }
//
//            else if (password != null && retypePassword != null && !password.equals(retypePassword)) {
//                request.setAttribute("passwordMatchError", "Passwords do not match!");
//                hasError = true;
//            }
//
//            else if (terms == null || !terms.equals("on")) {
//                request.setAttribute("termsError", "You must accept the terms and conditions!");
//                hasError = true;
//            }
//
//            // If there are validation errors, forward back to the registration page
//            if (hasError) {
//                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//                return;
//            }
//
//            // Encrypt password
//            String encryptedPassword = encryptdecrypt.encrypt(password);
//            if (encryptedPassword == null) {
//                request.setAttribute("errorMessage", "An error occurred while processing your password.");
//                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//                return;
//            }
//
//            // Create user model and register user
//            UserModel userModel = new UserModel();
//            userModel.setFirstName(firstName);
//            userModel.setLastName(lastName);
//            userModel.setUsername(username);
//            userModel.setEmail(email);
//            userModel.setPassword(encryptedPassword);
//
//            boolean isRegistered = dao.registerUser(userModel);
//            if (isRegistered) {
//                request.setAttribute("status", "success");
//            } else {
//                request.setAttribute("status", "error");
//            }
//
//            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("status", "error");
//            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//        }
//    }
//}






//package com.hotelbookingsystem.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.hotelbookingsystem.DAO.RegisterDAO;
//import com.hotelbookingsystem.model.UserModel;
//import com.hotelbookingsystem.utility.EncryptDecrypt;
//
//@WebServlet("/RegisterController")
//public class RegisterController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private EncryptDecrypt encryptdecrypt = new EncryptDecrypt();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Retrieve form parameters
//        String firstName = request.getParameter("firstname");
//        String lastName = request.getParameter("lastname");
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String retypePassword = request.getParameter("retypePassword");
//        String terms = request.getParameter("terms");
//
//        boolean hasError = false;
//
//        try {
//            RegisterDAO dao = new RegisterDAO();
//
//            // Validate inputs
//            if (firstName == null || firstName.trim().isEmpty()) {
//                request.setAttribute("firstnameError", "First name is required!");
//                hasError = true;
//            }
//
//            else if (lastName == null || lastName.trim().isEmpty()) {
//                request.setAttribute("lastnameError", "Last name is required!");
//                hasError = true;
//            }
//
//            else if (username == null || username.trim().isEmpty()) {
//                request.setAttribute("usernameError", "Username is required!");
//                hasError = true;
//            } 
//            
//            else if (dao.usernameExists(username)) {
//                request.setAttribute("usernameError", "Username already exists!");
//                hasError = true;
//            }
//
//            else if (email == null || email.trim().isEmpty()) {
//                request.setAttribute("emailError", "Email is required!");
//                hasError = true;
//            } 
//            
//            else if (dao.emailExists(email)) {
//                request.setAttribute("emailError", "Email is already registered!");
//                hasError = true;
//            }
//
//            else if (password == null || password.isEmpty()) {
//                request.setAttribute("passwordError", "Password is required!");
//                hasError = true;
//            }
//
//            else if (retypePassword == null || retypePassword.isEmpty()) {
//                request.setAttribute("retypePasswordError", "Please confirm your password!");
//                hasError = true;
//            }
//
//            else if (password != null && retypePassword != null && !password.equals(retypePassword)) {
//                request.setAttribute("passwordMatchError", "Passwords do not match!");
//                hasError = true;
//            }
//
//            else if (terms == null || !terms.equals("on")) {
//                request.setAttribute("termsError", "You must accept the terms and conditions!");
//                hasError = true;
//            }
//
//            // If there are validation errors, forward back to the registration page
//            if (hasError) {
//                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//                return;
//            }
//
//            // Encrypt password
//            String encryptedPassword = encryptdecrypt.encrypt(password);
//            if (encryptedPassword == null) {
//                request.setAttribute("errorMessage", "An error occurred while processing your password.");
//                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//                return;
//            }
//
//            // Create user model and register user
//            UserModel userModel = new UserModel();
//            userModel.setFirstName(firstName);
//            userModel.setLastName(lastName);
//            userModel.setUsername(username);
//            userModel.setEmail(email);
//            userModel.setPassword(encryptedPassword);
//
//            boolean isRegistered = dao.registerUser(userModel);
//            if (isRegistered) {
//                request.setAttribute("status", "success");
//            } else {
//                request.setAttribute("status", "error");
//            }
//
//            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("status", "error");
//            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
//        }
//    }
//}






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
