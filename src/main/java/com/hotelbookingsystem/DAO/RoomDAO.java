package com.hotelbookingsystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Bookings;
import com.hotelbookingsystem.model.Rooms;
import com.hotelbookingsystem.model.Rooms.BedType;
import com.hotelbookingsystem.model.Rooms.RoomType;

public class RoomDAO {
	private Connection conn;
	private PreparedStatement ps;

	// Constructor: Initializes the database connection when an object is created
	public RoomDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}

	public boolean isRoomValid(int roomId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM rooms WHERE room_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
	
	
	public boolean addNewRoom(Rooms roomModel) {
	    boolean isRowInserted = false;
	    String sql = "INSERT INTO rooms (room_number, room_type, price_per_night, no_of_beds, description, bed_type, room_area, is_available, floor_number, max_occupancy, room_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    if (conn != null) {
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, roomModel.getRoomNumber());
	            ps.setString(2, roomModel.getRoomType().name().toLowerCase());
	            ps.setDouble(3, roomModel.getPricePerNight());
	            ps.setInt(4, roomModel.getNoOfBeds());
	            ps.setString(5, roomModel.getDescription());
	            ps.setString(6, roomModel.getBedType().name().toLowerCase());
	            ps.setDouble(7, roomModel.getRoomArea());
	            ps.setBoolean(8, roomModel.isAvailable()); // default true if not provided
	            ps.setInt(9, roomModel.getFloorNumber());
	            ps.setInt(10, roomModel.getMaxOccupancy());
	            ps.setString(11, roomModel.getRoomImage());

	            int rows = ps.executeUpdate();
	            isRowInserted = (rows > 0);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return isRowInserted;
	}

	public ArrayList<Rooms> getAllRooms() {
		ArrayList<Rooms> rooms = new ArrayList<>();
		String query = "SELECT * FROM rooms";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(query);
				ResultSet roomSet = ps.executeQuery(); // stores all the room information retrieved by running query in
														// database

				// Iterate over result set and populate Room objects
				while (roomSet.next()) {
					Rooms room = new Rooms();
					room.setRoomId(roomSet.getInt("room_id"));
					room.setRoomType(RoomType.valueOf(roomSet.getString("room_type").toUpperCase()));
					room.setPricePerNight(roomSet.getDouble("price_per_night"));
					room.setNoOfBeds(roomSet.getInt("no_of_beds"));
					room.setDescription(roomSet.getString("description"));
					room.setBedType(BedType.valueOf(roomSet.getString("bed_type").toUpperCase()));
					room.setRoomArea(roomSet.getDouble("room_area"));
					room.setAvailable(roomSet.getBoolean("is_available"));
					System.out.println("Room " + room.getRoomNumber() + " isAvailable: " + room.isAvailable());
					room.setFloorNumber(roomSet.getInt("floor_number"));
					room.setMaxOccupancy(roomSet.getInt("max_occupancy"));
					room.setRoomImage(roomSet.getString("room_image"));
					room.setRoomNumber(roomSet.getString("room_number"));
					rooms.add(room);

				}
			} catch (SQLException e) {
				// TODO Shows error if query fails
				e.printStackTrace();
			}
		}
		return rooms;
	}

	// Get a room by its ID
	public Rooms getRoomById(long roomId) {
		Rooms room = null;
		String query = "SELECT * FROM rooms WHERE room_id = ?";
		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setLong(1, roomId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					room = new Rooms();
					room.setRoomId(rs.getLong("room_id"));
					room.setRoomType(RoomType.valueOf(rs.getString("room_type").toUpperCase()));
					room.setPricePerNight(rs.getDouble("price_per_night"));
					room.setNoOfBeds(rs.getInt("no_of_beds"));
					room.setDescription(rs.getString("description"));
					room.setBedType(BedType.valueOf(rs.getString("bed_type").toUpperCase()));
					room.setRoomArea(rs.getDouble("room_area"));
					room.setAvailable(rs.getBoolean("is_available"));
					room.setFloorNumber(rs.getInt("floor_number"));
					room.setMaxOccupancy(rs.getInt("max_occupancy"));
					room.setRoomImage(rs.getString("room_image"));
					room.setRoomNumber(rs.getString("room_number"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return room;
	}

	// Update an existing room in the database
	public boolean updateRoom(Rooms room) {
	    String query = "UPDATE rooms SET room_number = ?, room_type = ?, price_per_night = ?, no_of_beds = ?, description = ?, bed_type = ?, room_area = ?, is_available = ?, floor_number = ?, max_occupancy = ?, room_image = ? WHERE room_id = ?";
	    if (conn != null) {
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, room.getRoomNumber());
	            ps.setString(2, room.getRoomType().name().toLowerCase());
	            ps.setDouble(3, room.getPricePerNight());
	            ps.setInt(4, room.getNoOfBeds());
	            ps.setString(5, room.getDescription());
	            ps.setString(6, room.getBedType().name().toLowerCase());
	            ps.setDouble(7, room.getRoomArea());
	            ps.setBoolean(8, room.isAvailable());
	            ps.setInt(9, room.getFloorNumber());
	            ps.setInt(10, room.getMaxOccupancy());
	            ps.setString(11, room.getRoomImage());
	            ps.setLong(12, room.getRoomId());

	            int rows = ps.executeUpdate();
	            return rows > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}


	// Delete a room from the database
	public boolean deleteRoom(long roomId) {
		String query = "DELETE FROM rooms WHERE room_id = ?";
		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setLong(1, roomId);
				int rows = ps.executeUpdate();
				return rows > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean roomNumberExists(String roomNumber) {
		boolean isRowFound = false;
		String sql = "SELECT COUNT(*) FROM rooms WHERE room_number = ?";

		if (conn != null) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, roomNumber);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next() && rs.getInt(1) > 0) {
						isRowFound = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isRowFound;
	}
	

		public ArrayList<Rooms> getRoomsByType(String roomType) {
		    ArrayList<Rooms> rooms = new ArrayList<>();
		    String query = "SELECT * FROM rooms WHERE room_type = ?";
		    if (conn != null) {
		        try (PreparedStatement ps = conn.prepareStatement(query)) {
		            ps.setString(1, roomType.toLowerCase());
		            ResultSet roomSet = ps.executeQuery();
		            while (roomSet.next()) {
		                Rooms room = new Rooms();
		                room.setRoomId(roomSet.getLong("room_id"));
		                room.setRoomType(RoomType.valueOf(roomSet.getString("room_type").toUpperCase()));
		                room.setPricePerNight(roomSet.getDouble("price_per_night"));
		                room.setNoOfBeds(roomSet.getInt("no_of_beds"));
		                room.setDescription(roomSet.getString("description"));
		                room.setBedType(BedType.valueOf(roomSet.getString("bed_type").toUpperCase()));
		                room.setRoomArea(roomSet.getDouble("room_area"));
		                room.setAvailable(roomSet.getBoolean("is_available"));
		                room.setFloorNumber(roomSet.getInt("floor_number"));
		                room.setMaxOccupancy(roomSet.getInt("max_occupancy"));
		                room.setRoomImage(roomSet.getString("room_image"));
		                room.setRoomNumber(roomSet.getString("room_number"));
		                rooms.add(room);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return rooms;
		}
		
		
		
		public int getAvailableRooms() {
	        int availableRooms = 0;
	        String query = "SELECT COUNT(*) FROM rooms WHERE is_available = 1";
	        if (conn != null) {
	            try {
	                PreparedStatement ps = conn.prepareStatement(query);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    availableRooms = rs.getInt(1);
	                }
	                rs.close();
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return availableRooms;
	    }

	    public int getUnavailableRooms() {
	        int unavailableRooms = 0;
	        String query = "SELECT COUNT(*) FROM rooms WHERE is_available = 0";
	        if (conn != null) {
	            try {
	                PreparedStatement ps = conn.prepareStatement(query);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    unavailableRooms = rs.getInt(1);
	                }
	                rs.close();
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return unavailableRooms;
	    }
	    
	    
	    public ArrayList<Bookings> getPendingBookingsListDesc() {
	        ArrayList<Bookings> bookings = new ArrayList<>();
	        String sql = "SELECT * FROM bookings WHERE status = 'pending' ORDER BY created_at DESC";

	        if (conn != null) {
	            try {
	                PreparedStatement ps = conn.prepareStatement(sql);
	                ResultSet rs = ps.executeQuery();

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
	        }

	        return bookings;
	    }


			public ArrayList<Rooms> getRoomsByType(String roomType) {
			    ArrayList<Rooms> rooms = new ArrayList<>();
			    String query = "SELECT * FROM rooms WHERE room_type = ?";
			    if (conn != null) {
			        try (PreparedStatement ps = conn.prepareStatement(query)) {
			            ps.setString(1, roomType.toLowerCase());
			            ResultSet roomSet = ps.executeQuery();
			            while (roomSet.next()) {
			                Rooms room = new Rooms();
			                room.setRoomId(roomSet.getLong("room_id"));
			                room.setRoomType(RoomType.valueOf(roomSet.getString("room_type").toUpperCase()));
			                room.setPricePerNight(roomSet.getDouble("price_per_night"));
			                room.setNoOfBeds(roomSet.getInt("no_of_beds"));
			                room.setDescription(roomSet.getString("description"));
			                room.setBedType(BedType.valueOf(roomSet.getString("bed_type").toUpperCase()));
			                room.setRoomArea(roomSet.getDouble("room_area"));
			                room.setAvailable(roomSet.getBoolean("is_available"));
			                room.setFloorNumber(roomSet.getInt("floor_number"));
			                room.setMaxOccupancy(roomSet.getInt("max_occupancy"));
			                room.setRoomImage(roomSet.getString("room_image"));
			                room.setRoomNumber(roomSet.getString("room_number"));
			                rooms.add(room);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			    }
			    return rooms;
			}

}