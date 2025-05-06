package com.hotelbookingsystem.model;

/**
 * 
 */

import java.sql.Date;
import java.sql.Timestamp;

public class Users {

	private int userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role;
	private String email;
	private String phoneNo;
	private String address;
	private String gender;
	private Date dob;
	private String verificationId;
	private String profilePicture;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Users(int userId, String firstName, String lastName, String phoneNo, String email, String address, Date dob,
			String gender) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
	}

	public Users(int userId, String firstName, String lastName, String username, String password, String email,
			String gender, Date dob) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
	}

	public Users(int userId, String firstName, String lastName, String username, String password, String role,
			String email, String phoneNo, String address, String gender, Date dob, String verificationId,
			String profilePicture, boolean isActive, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
		this.verificationId = verificationId;
		this.profilePicture = profilePicture;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Constructor
	public Users() {

	}
	
//	public Users(String firstname2, String lastname2, String usersname, String password2, String phoneNo2,
//			String email2, String address2, Date dob2, String gender2) {
//		// TODO Auto-generated constructor stub
//	}
	
	public Users(String firstName, String lastName, String username, String password, 
            String phoneNo, String email, String address, Date dob, String gender) {
   this.firstName = firstName;
   this.lastName = lastName;
   this.username = username;
   this.password = password;
   this.phoneNo = phoneNo;
   this.email = email;
   this.address = address;
   this.dob = dob;
   this.gender = gender;
}

	
	

	public Users(int userId, String firstName, String lastName, String phoneNo, String email,
             String address, Date dob, String gender, String username, String password, String role) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNo = phoneNo;
    this.email = email;
    this.address = address;
    this.dob = dob;
    this.gender = gender;
    this.username = username;
    this.password = password;
    this.role = role;
}


	// Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp timestamp) {
		this.createdAt = timestamp;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp timestamp) {
		this.updatedAt = timestamp;
	}
}
