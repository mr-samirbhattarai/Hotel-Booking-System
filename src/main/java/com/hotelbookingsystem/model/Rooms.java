package com.hotelbookingsystem.model;


public class Rooms {

    public enum RoomType {
        SINGLE, DOUBLE, SUITE, DELUXE
    }

    public enum BedType {
        SINGLE, DOUBLE, QUEEN, KING
    }

    private long roomId;
    private RoomType roomType;
    private double pricePerNight;
    private int noOfBeds;
    private String description;
    private BedType bedType;
    private double roomArea;
    private boolean isAvailable;
    private int floorNumber;
    private int maxOccupancy;
    private String roomImage;
    private String roomNumber;

    // Constructors
    public Rooms() {}

    public Rooms(long roomId, RoomType roomType, double pricePerNight, int noOfBeds,
                     String description, BedType bedType, double roomArea, boolean isAvailable,
                     int floorNumber, int maxOccupancy, String roomImage, String roomNumber) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.noOfBeds = noOfBeds;
        this.description = description;
        this.bedType = bedType;
        this.roomArea = roomArea;
        this.isAvailable = isAvailable;
        this.floorNumber = floorNumber;
        this.maxOccupancy = maxOccupancy;
        this.roomImage = roomImage;
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(double roomArea) {
        this.roomArea = roomArea;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}