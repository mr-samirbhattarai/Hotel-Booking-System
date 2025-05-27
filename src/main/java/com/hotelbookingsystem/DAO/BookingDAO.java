package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Bookings;
import com.hotelbookingsystem.model.Rooms;
import com.hotelbookingsystem.model.Users;

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
			try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
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

	public boolean updateBookingStatus(long bookingId, String newStatus) throws SQLException {
		String query = "UPDATE bookings SET status = ? WHERE booking_id = ?";
		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, newStatus);
				ps.setLong(2, bookingId);
				int rows = ps.executeUpdate();
				return rows > 0;
			}
		}
		return false;
	}

	public boolean isRoomAvailable(long roomId, Date checkInDate, Date checkOutDate) {
		String query = "SELECT booking_id FROM bookings " + "WHERE room_id = ? AND status != 'cancelled' AND "
				+ "(? < check_out_date AND ? > check_in_date)";

		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(query)) {

				ps.setLong(1, roomId);
				ps.setDate(2, checkInDate);
				ps.setDate(3, checkOutDate);

				ResultSet rs = ps.executeQuery();
				return !rs.next(); // If no results, room is available
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	
	public int getPendingBookings() {
        int pendingBookings = 0;
        String query = "SELECT COUNT(*) FROM bookings WHERE status = 'pending'";
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    pendingBookings = rs.getInt(1);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pendingBookings;
    }
    
    public ArrayList<Bookings> getPendingBookingsListDesc() {
        ArrayList<Bookings> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status = 'pending' ORDER BY created_at DESC LIMIT 5";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bookings b = new Bookings();
                b.setBookingId(rs.getLong("booking_id"));
                b.setStatus(rs.getString("status"));
                b.setUserId(rs.getLong("user_id"));
                b.setRoomId(rs.getLong("room_id"));
                b.setCheckInDate(rs.getDate("check_in_date"));
                b.setCheckOutDate(rs.getDate("check_out_date"));
                b.setNumberOfGuests(rs.getInt("number_of_guests"));
                b.setCreatedAt(rs.getTimestamp("created_at"));
                bookings.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
    
    public ArrayList<Bookings> getAllPendingBookings() throws SQLException {
        ArrayList<Bookings> list = new ArrayList<>();

        String sql = "SELECT b.booking_id, b.status, b.check_in_date, b.check_out_date, " +
                     "b.number_of_guests, b.created_at, " +
                     "u.user_id, u.firstname, u.lastname, u.phoneNo, " +
                     "r.room_id, r.room_number, r.room_type " +
                     "FROM bookings b " +
                     "JOIN users u ON b.user_id = u.user_id " +
                     "JOIN rooms r ON b.room_id = r.room_id " +
                     "WHERE b.status = 'pending'";

        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Bookings booking = new Bookings();

                    booking.setBookingId(rs.getInt("booking_id"));
                    booking.setStatus(rs.getString("status"));
                    booking.setCheckInDate(rs.getDate("check_in_date"));
                    booking.setCheckOutDate(rs.getDate("check_out_date"));
                    booking.setNumberOfGuests(rs.getInt("number_of_guests"));
                    booking.setCreatedAt(rs.getTimestamp("created_at"));

                    // Set userId directly
                    booking.setUserId(rs.getInt("user_id"));

                    // Create and set Users object
                    Users user = new Users();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    user.setPhoneNo(rs.getString("phoneNo"));
                    booking.setUser(user);

                    // Set roomId directly
                    booking.setRoomId(rs.getInt("room_id"));

                    // Create and set Rooms object
                    Rooms room = new Rooms();
                    room.setRoomId(rs.getInt("room_id"));
                    room.setRoomNumber(rs.getString("room_number"));
                    room.setRoomType(Rooms.RoomType.valueOf(rs.getString("room_type").toUpperCase()));
                    booking.setRoom(room);

                    list.add(booking);
                }

            } catch (SQLException e) {
                throw e;
            }
        }

        return list;
    }
    
    public void updateCheckInStatus(long bookingId) throws SQLException {
        String updateBookingSQL = "UPDATE bookings SET status = 'check_in' WHERE booking_id = ?";
        String updateRoomSQL = "UPDATE rooms SET is_available = 0 WHERE room_id = " +
                              "(SELECT room_id FROM bookings WHERE booking_id = ?)";

        if (conn != null) {
            try {
                conn.setAutoCommit(false); // Start transaction
                // Update booking status
                try (PreparedStatement psBooking = conn.prepareStatement(updateBookingSQL)) {
                    psBooking.setLong(1, bookingId);
                    psBooking.executeUpdate();
                }
                // Update room availability
                try (PreparedStatement psRoom = conn.prepareStatement(updateRoomSQL)) {
                    psRoom.setLong(1, bookingId);
                    psRoom.executeUpdate();
                }
                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                conn.rollback(); // Rollback on error
                e.printStackTrace();
                throw new SQLException("Failed to update check-in status", e);
            } finally {
                conn.setAutoCommit(true); // Reset auto-commit
            }
        } else {
            throw new SQLException("Database connection is null");
        }
    }

        
    public ArrayList<Bookings> searchPendingBookingsByCustomerName(String searchName) throws SQLException {
        ArrayList<Bookings> list = new ArrayList<>();

        String sql = "SELECT b.booking_id, b.status, b.check_in_date, b.check_out_date, " +
                "b.number_of_guests, b.created_at, " +
                "u.user_id, u.firstname, u.lastname, u.phoneNo, " +
                "r.room_id, r.room_number, r.room_type " +
                "FROM bookings b " +
                "JOIN users u ON b.user_id = u.user_id " +
                "JOIN rooms r ON b.room_id = r.room_id " +
                "WHERE b.status = 'pending' AND " +
                "(LOWER(u.firstname) LIKE ? OR LOWER(u.lastname) LIKE ?)";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                String searchPattern = "%" + searchName.toLowerCase() + "%";
                ps.setString(1, searchPattern);
                ps.setString(2, searchPattern);

                try (ResultSet rs = ps.executeQuery()) {
                	while (rs.next()) {
                        Bookings booking = new Bookings();

                        booking.setBookingId(rs.getInt("booking_id"));
                        booking.setStatus(rs.getString("status"));
                        booking.setCheckInDate(rs.getDate("check_in_date"));
                        booking.setCheckOutDate(rs.getDate("check_out_date"));
                        booking.setNumberOfGuests(rs.getInt("number_of_guests"));
                        booking.setCreatedAt(rs.getTimestamp("created_at"));

                        // Set userId directly
                        booking.setUserId(rs.getInt("user_id"));

                        // Create and set Users object
                        Users user = new Users();
                        user.setUserId(rs.getInt("user_id"));
                        user.setFirstName(rs.getString("firstname"));
                        user.setLastName(rs.getString("lastname"));
                        user.setPhoneNo(rs.getString("phoneNo"));
                        booking.setUser(user);

                        // Set roomId directly
                        booking.setRoomId(rs.getInt("room_id"));

                        // Create and set Rooms object
                        Rooms room = new Rooms();
                        room.setRoomId(rs.getInt("room_id"));
                        room.setRoomNumber(rs.getString("room_number"));
                        room.setRoomType(Rooms.RoomType.valueOf(rs.getString("room_type").toUpperCase()));
                        booking.setRoom(room);

                        list.add(booking);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to search pending bookings", e);
            }
        } else {
            throw new SQLException("Database connection is null");
        }

        return list;
    }


    
}