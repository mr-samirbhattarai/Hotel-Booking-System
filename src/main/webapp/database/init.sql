-- Create Tables with Primary and Foreign Key Constraints

CREATE TABLE Users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'customer', 'staff') NOT NULL DEFAULT 'customer',
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNo VARCHAR(15),
    address TEXT,
    gender ENUM('male', 'female', 'other'),
    DOB DATE,
    verification_id VARCHAR(100),
    profile_picture VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Rooms (
    room_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_type ENUM('single', 'double', 'suite', 'deluxe') NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    no_of_beds INT NOT NULL,
    description TEXT,
    bed_type ENUM('single', 'double', 'queen', 'king') NOT NULL,
    room_area DECIMAL(6, 2) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    floor_number INT,
    max_occupancy INT NOT NULL,
    room_image VARCHAR(255),
    room_number VARCHAR(255)
);

CREATE TABLE Bookings (
    booking_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('pending', 'confirmed', 'cancelled', 'completed') NOT NULL,
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_of_guests INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE RESTRICT
);

CREATE TABLE CheckIns (
    check_in_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL,
    check_in_date DATE NOT NULL,
    check_in_time TIME NOT NULL,
    check_out_date DATE NOT NULL,
    check_out_time TIME NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE
);

CREATE TABLE Payments (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method ENUM('credit_card', 'debit_card', 'cash', 'online') NOT NULL,
    payment_status ENUM('pending', 'completed', 'failed') NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE
);

CREATE TABLE Reviews (
    review_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    booking_id BIGINT NOT NULL,
    rating DECIMAL(3, 1) NOT NULL,
    comment TEXT,
    review_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE RESTRICT,
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE
);

