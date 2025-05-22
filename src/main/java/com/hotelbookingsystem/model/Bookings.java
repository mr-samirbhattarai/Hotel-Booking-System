package com.hotelbookingsystem.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Bookings {
    private long bookingId;
    private String status;
    private long userId;
    private long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfGuests;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public long getBookingId() { return bookingId; }
    public void setBookingId(long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getRoomId() { return roomId; }
    public void setRoomId(long roomId) { this.roomId = roomId; }
    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }
    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

}