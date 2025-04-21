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
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.RegisterDAO;
import com.hotelbookingsystem.model.UserModel;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RegisterDAO dao = new RegisterDAO();
    private EncryptDecrypt encryptdecrypt = new EncryptDecrypt();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // 1. Check for empty fields
        if (firstName == null || firstName.isEmpty() ||
            lastName == null || lastName.isEmpty() ||
            username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty()) {

            request.setAttribute("errorMessage", "Please fill in all the fields.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            return;
        }

        // 2. Check if passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            return;
        }

        // 3. Check if username or email already exists
        if (dao.usernameExists(username)) {
            request.setAttribute("errorMessage", "Username already exists. Try another one.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            return;
        }

        if (dao.emailExists(email)) {
            request.setAttribute("errorMessage", "Email already registered. Try logging in.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            return;
        }

        // 4. Register user
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUsername(username);
        userModel.setEmail(email);
        userModel.setPassword(encryptdecrypt.encrypt(password));

        boolean isRegistered = dao.registerUser(userModel);

        if (isRegistered) {
            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Registered successfully! Please log in.");
            response.sendRedirect("pages/register.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
        }
    }
}


class EncryptDecrypt {
    private static final String SECRET_KEY = "HotelRockstar";
    private static final String SALT = "GrandTheftAutoVI";

    public String encrypt(String password) {
        try {
            byte[] iv = new byte[16]; // all 0s
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
