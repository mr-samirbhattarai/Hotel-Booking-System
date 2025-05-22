package com.hotelbookingsystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelbookingsystem.DAO.PaymentDAO;
import com.hotelbookingsystem.model.Payment;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDAO paymentDAO;

    public void init() {
        try {
			paymentDAO = new PaymentDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || !"customer".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/access/login.jsp");
            return;
        }

        String bookingIdStr = request.getParameter("bookingId");
        String amountStr = request.getParameter("amount");
        String paymentMethod = request.getParameter("paymentMethod");

        if (bookingIdStr == null || amountStr == null || paymentMethod == null ||
            bookingIdStr.trim().isEmpty() || amountStr.trim().isEmpty() || paymentMethod.trim().isEmpty()) {
            
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("customer/paymentForm.jsp").forward(request, response);
            return;
        }

        try {
            long bookingId = Long.parseLong(bookingIdStr);
            double amount = Double.parseDouble(amountStr);

            Payment payment = new Payment();
            payment.setBookingId(bookingId);
            payment.setAmount(amount);
            payment.setPaymentMethod(paymentMethod);
            payment.setPaymentStatus("pending");

            boolean success = paymentDAO.insertPayment(payment);

            if (success) {
                request.setAttribute("successMessage", "Payment initiated successfully.");
            } else {
                request.setAttribute("errorMessage", "Payment failed. Try again.");
            }

            request.getRequestDispatcher("customer/paymentStatus.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid booking ID or amount format.");
            request.getRequestDispatcher("customer/paymentForm.jsp").forward(request, response);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("customer/payment.jsp").forward(request, response);
    }

    
}
