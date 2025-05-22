package com.hotelbookingsystem.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.hotelbookingsystem.database.DatabaseConnection;
import com.hotelbookingsystem.model.Users;


public class UserDAO {
    private Connection conn;
    private PreparedStatement ps;

    // Constructor: Initializes the database connection
    public UserDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    // Registers a new user in the database
    public boolean register(Users users) {
        boolean isUserRegistered = false;
        String query = "INSERT INTO users (firstName, lastName, username, email, password, role, is_active, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, users.getFirstName());
                ps.setString(2, users.getLastName());
                ps.setString(3, users.getUsername());
                ps.setString(4, users.getEmail());
                ps.setString(5, users.getPassword());
                ps.setString(6, "customer"); // Default role
                ps.setBoolean(7, true); // Default active status
                ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                if (ps.executeUpdate() > 0) {
                    isUserRegistered = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUserRegistered;
    }


 // Gets all users from the database
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet userSet = ps.executeQuery();
                while (userSet.next()) {
                    Users user = new Users();
                    user.setUserId(userSet.getInt("user_id"));
                    user.setRole(userSet.getString("role"));
                    user.setPhoneNo(userSet.getString("phoneNo"));
                    user.setAddress(userSet.getString("address"));
                    user.setGender(userSet.getString("gender"));
                    user.setDob(userSet.getDate("dob"));
                    user.setVerificationId(userSet.getString("verification_id"));
                    user.setProfilePicture(userSet.getString("profile_picture"));
                    user.setActive(userSet.getBoolean("is_active"));
                    user.setCreatedAt(userSet.getTimestamp("created_at"));
                    user.setUpdatedAt(userSet.getTimestamp("updated_at"));
                    users.add(user);
                }
                userSet.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();

    
    
    // Checks if a user exists by username or email
    public boolean existsByUsernameOrEmail(String username, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }

            }
        }
        return false;
    }

    public int getTotalUsers() {
        int totalUsers = 0;
        String query = "SELECT COUNT(*) FROM users";
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    totalUsers = rs.getInt(1);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalUsers;
    }
    
    
    // Gets all users from the database
    public ArrayList<Users> getAllUsers() {
    	ArrayList<Users> users = new ArrayList<>();
		String sql = "SELECT * FROM users where role = 'customer'";

		if (conn != null) {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet customerSet = null;
			try {
				customerSet = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (customerSet.next()) {
					Users user = new Users(
						customerSet.getInt("user_Id"),    
						customerSet.getString("firstname"), 
						customerSet.getString("lastname"), 
						customerSet.getString("username"),   
						customerSet.getString("password"),     
						customerSet.getString("email"),  
						customerSet.getString("gender"),    
						customerSet.getDate("dob"));
						users.add(user);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				System.out.println("Number of users retrieved: " + users.size()); // Debugging line
			}

			return users;
		}
    
    public ArrayList<Users> getAllCustomers() throws SQLException{
		ArrayList<Users> users = new ArrayList<>(); 
		String sql = "Select * from users where username =?";
		if (conn != null) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet customerSet = ps.executeQuery();
			while (customerSet.next()) {
				Users user = new Users(
					customerSet.getInt("user_Id"),    
					customerSet.getString("firstname"), 
					customerSet.getString("lastname"), 
					customerSet.getString("username"),   
					customerSet.getString("password"),     
					customerSet.getString("email"),  
					customerSet.getString("gender"),    
					customerSet.getDate("dob"));
				users.add(user);
			}

			System.out.println("Number of users retrieved: " + users.size()); // Debugging line
		}

		return users;
	}

    // Authenticates user by checking email and password
    public Users login(String emailToCheck, String encryptedPassword) {
        Users users = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND is_active = true";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, emailToCheck);
                ps.setString(2, encryptedPassword);
                ResultSet userSet = ps.executeQuery();
                if (userSet.next()) {
                    users = new Users();
                    users.setFirstName(userSet.getString("firstName"));
                    users.setLastName(userSet.getString("lastName"));
                    users.setUsername(userSet.getString("username"));
                    users.setEmail(userSet.getString("email"));
                    users.setPassword(userSet.getString("password"));
                    users.setUserId(userSet.getInt("user_id"));
                    users.setRole(userSet.getString("role"));
                    users.setPhoneNo(userSet.getString("phoneNo"));
                    users.setAddress(userSet.getString("address"));
                    users.setGender(userSet.getString("gender"));
                    users.setDob(userSet.getDate("dob"));
                    users.setActive(userSet.getBoolean("is_active"));
                    users.setCreatedAt(userSet.getTimestamp("created_at"));
                    users.setUpdatedAt(userSet.getTimestamp("updated_at"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    // Retrieve user by username
    public Users getUserByUsername(String username) throws SQLException {
        Users user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Users(
                        rs.getInt("user_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("phoneNo"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        }
        return user;
    }
    
    public Users getUserById(int userId) {
        Users user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new Users();
                        user.setUserId(rs.getInt("user_id"));
                        user.setFirstName(rs.getString("firstname"));
                        user.setLastName(rs.getString("lastname"));
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setPhoneNo(rs.getString("phoneNo"));
                        user.setAddress(rs.getString("address"));
                        user.setGender(rs.getString("gender"));
                        user.setDob(rs.getDate("dob"));
                        user.setRole(rs.getString("role"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

 // Update user profile
    public boolean updateUserProfile(Users user) throws SQLException {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, phoneNo = ?, email = ?, address = ?, dob = ?, gender = ? WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNo());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress());
            ps.setDate(6, user.getDob() != null ? new java.sql.Date(user.getDob().getTime()) : null);
            ps.setString(7, user.getGender());
            ps.setString(8, user.getUsername());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}