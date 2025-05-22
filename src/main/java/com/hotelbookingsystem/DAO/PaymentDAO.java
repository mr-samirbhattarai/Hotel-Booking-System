package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Payment;

public class PaymentDAO {

	private Connection conn;
	public PaymentDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}
	public boolean insertPayment(Payment payment) throws SQLException {
		String querry = "INSERT INTO payment (booking_id, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(querry)) {
				ps.setLong(1, payment.getBookingId());
				ps.setDouble(2, payment.getAmount());
				ps.setString(3, payment.getPaymentMethod());
				ps.setString(4, payment.getPaymentStatus());

				return ps.executeUpdate() > 0;
			}
		}
		return false;
	}

	public List<Payment> getPaymentsByBookingId(long bookingId) throws SQLException {
		List<Payment> payments = new ArrayList<>();
		String querry = "SELECT * FROM payment WHERE booking_id = ?";
		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(querry)) {

				ps.setLong(1, bookingId);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getLong("payment_id"));
					payment.setBookingId(rs.getLong("booking_id"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setPaymentDate(rs.getTimestamp("payment_date"));
					payment.setPaymentMethod(rs.getString("payment_method"));
					payment.setPaymentStatus(rs.getString("payment_status"));
					payments.add(payment);
				}
			}
			return payments;
		}
		return payments;
	}
}
