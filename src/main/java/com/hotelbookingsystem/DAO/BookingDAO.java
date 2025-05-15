package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Bookings;

public class BookingDAO {
    private Connection conn;

    public BookingDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public boolean addNewBooking(Bookings booking) {
        String sql = "INSERT INTO bookings (status, user_id, room_id, check_in_date, check_out_date, number_of_guests, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, booking.getStatus());
                ps.setLong(2, booking.getUserId());
                ps.setLong(3, booking.getRoomId());
                ps.setDate(4, booking.getCheckInDate());
                ps.setDate(5, booking.getCheckOutDate());
                ps.setInt(6, booking.getNumberOfGuests());
                ps.setTimestamp(7, booking.getCreatedAt());
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Bookings> getAllBookings() {
        ArrayList<Bookings> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bookings booking = new Bookings();
                    booking.setBookingId(rs.getLong("booking_id"));
                    booking.setStatus(rs.getString("status"));
                    booking.setUserId(rs.getLong("user_id"));
                    booking.setRoomId(rs.getLong("room_id"));
                    booking.setCheckInDate(rs.getDate("check_in_date"));
                    booking.setCheckOutDate(rs.getDate("check_out_date"));
                    booking.setNumberOfGuests(rs.getInt("number_of_guests"));
                    booking.setCreatedAt(rs.getTimestamp("created_at"));
                    bookings.add(booking);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookings;
    }

    public ArrayList<Bookings> getBookingsByUserId(long userId) {
        ArrayList<Bookings> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ? ORDER BY created_at DESC";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Bookings booking = new Bookings();
                        booking.setBookingId(rs.getLong("booking_id"));
                        booking.setStatus(rs.getString("status"));
                        booking.setUserId(rs.getLong("user_id"));
                        booking.setRoomId(rs.getLong("room_id"));
                        booking.setCheckInDate(rs.getDate("check_in_date"));
                        booking.setCheckOutDate(rs.getDate("check_out_date"));
                        booking.setNumberOfGuests(rs.getInt("number_of_guests"));
                        booking.setCreatedAt(rs.getTimestamp("created_at"));
                        bookings.add(booking);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookings;
    }

    public Bookings getBookingById(long bookingId) {
        Bookings booking = null;
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, bookingId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        booking = new Bookings();
                        booking.setBookingId(rs.getLong("booking_id"));
                        booking.setStatus(rs.getString("status"));
                        booking.setUserId(rs.getLong("user_id"));
                        booking.setRoomId(rs.getLong("room_id"));
                        booking.setCheckInDate(rs.getDate("check_in_date"));
                        booking.setCheckOutDate(rs.getDate("check_out_date"));
                        booking.setNumberOfGuests(rs.getInt("number_of_guests"));
                        booking.setCreatedAt(rs.getTimestamp("created_at"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return booking;
    }

    public boolean updateBooking(Bookings booking) {
        String query = "UPDATE bookings SET status = ?, user_id = ?, room_id = ?, check_in_date = ?, check_out_date = ?, number_of_guests = ?, created_at = ? WHERE booking_id = ?";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, booking.getStatus());
                ps.setLong(2, booking.getUserId());
                ps.setLong(3, booking.getRoomId());
                ps.setDate(4, booking.getCheckInDate());
                ps.setDate(5, booking.getCheckOutDate());
                ps.setInt(6, booking.getNumberOfGuests());
                ps.setTimestamp(7, booking.getCreatedAt());
                ps.setLong(8, booking.getBookingId());
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteBooking(long bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, bookingId);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean bookingExists(long bookingId) {
        String query = "SELECT COUNT(*) FROM bookings WHERE booking_id = ?";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, bookingId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}