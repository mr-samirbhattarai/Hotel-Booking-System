-- Seed data for Users table
INSERT INTO Users (firstname, lastname, username, password, role, email, phoneNo, address, gender, DOB, is_active) VALUES
('John', 'Doe', 'johndoe', 'hashed_password1', 'customer', 'john.doe@email.com', '1234567890', '123 Main St', 'male', '1990-05-15', TRUE),
('Jane', 'Smith', 'janesmith', 'hashed_password2', 'admin', 'jane.smith@email.com', '2345678901', '456 Oak Ave', 'female', '1985-08-22', TRUE),
('Mike', 'Johnson', 'mikej', 'hashed_password3', 'staff', 'mike.j@email.com', '3456789012', '789 Pine Rd', 'male', '1992-03-10', TRUE),
('Emily', 'Brown', 'emilyb', 'hashed_password4', 'customer', 'emily.brown@email.com', '4567890123', '101 Maple Ln', 'female', '1995-11-30', TRUE),
('David', 'Wilson', 'davidw', 'hashed_password5', 'customer', 'david.w@email.com', '5678901234', '202 Birch St', 'male', '1988-07-14', TRUE),
('Sarah', 'Taylor', 'saraht', 'hashed_password6', 'customer', 'sarah.t@email.com', '6789012345', '303 Cedar Dr', 'female', '1993-09-25', TRUE),
('Chris', 'Moore', 'chrism', 'hashed_password7', 'staff', 'chris.m@email.com', '7890123456', '404 Elm St', 'male', '1990-12-05', TRUE),
('Lisa', 'Anderson', 'lisaa', 'hashed_password8', 'customer', 'lisa.a@email.com', '8901234567', '505 Spruce Ave', 'female', '1987-04-18', TRUE),
('Tom', 'Clark', 'tomc', 'hashed_password9', 'customer', 'tom.c@email.com', '9012345678', '606 Willow Rd', 'male', '1994-06-20', TRUE),
('Anna', 'Lewis', 'annal', 'hashed_password10', 'customer', 'anna.l@email.com', '0123456789', '707 Ash St', 'female', '1991-02-28', TRUE),
('Mark', 'Walker', 'markw', 'hashed_password11', 'staff', 'mark.w@email.com', '1234509876', '808 Chestnut Ln', 'male', '1989-10-12', TRUE),
('Laura', 'Hall', 'laurah', 'hashed_password12', 'customer', 'laura.h@email.com', '2345609876', '909 Poplar Dr', 'female', '1996-01-07', TRUE),
('Steve', 'Allen', 'stevea', 'hashed_password13', 'customer', 'steve.a@email.com', '3456709876', '1010 Sycamore St', 'male', '1986-03-19', TRUE),
('Megan', 'Young', 'megany', 'hashed_password14', 'customer', 'megan.y@email.com', '4567809876', '1111 Magnolia Ave', 'female', '1997-08-03', TRUE),
('Paul', 'King', 'paulk', 'hashed_password15', 'customer', 'paul.k@email.com', '5678909876', '1212 Laurel Rd', 'male', '1990-11-11', TRUE);

-- Seed data for Rooms table with room_number and room_image included
INSERT INTO Rooms (
    room_number, room_type, price_per_night, no_of_beds, description,
    bed_type, room_area, is_available, floor_number, max_occupancy, room_image
) VALUES
('101A', 'single', 100.00, 1, 'Cozy single room', 'single', 20.50, TRUE, 1, 1, 'img/room101a.jpg'),
('102B', 'double', 150.00, 2, 'Spacious double room', 'double', 30.00, TRUE, 2, 2, 'img/room102b.jpg'),
('201C', 'suite', 250.00, 2, 'Luxurious suite with view', 'king', 50.00, TRUE, 3, 4, 'img/room201c.jpg'),
('301D', 'deluxe', 200.00, 2, 'Deluxe room with balcony', 'queen', 40.00, TRUE, 4, 3, 'img/room301d.jpg'),
('103A', 'single', 110.00, 1, 'Single room with city view', 'single', 22.00, TRUE, 2, 1, 'img/room103a.jpg'),
('104B', 'double', 160.00, 2, 'Double room with garden view', 'double', 32.00, TRUE, 1, 2, 'img/room104b.jpg'),
('202C', 'suite', 270.00, 2, 'Executive suite', 'king', 55.00, FALSE, 5, 4, 'img/room202c.jpg'),
('302D', 'deluxe', 210.00, 2, 'Deluxe with ocean view', 'queen', 42.00, TRUE, 3, 3, 'img/room302d.jpg'),
('105A', 'single', 105.00, 1, 'Compact single room', 'single', 18.00, TRUE, 2, 1, 'img/room105a.jpg'),
('106B', 'double', 155.00, 2, 'Double room with amenities', 'double', 31.00, TRUE, 4, 2, 'img/room106b.jpg'),
('203C', 'suite', 260.00, 2, 'Premium suite', 'king', 52.00, TRUE, 6, 4, 'img/room203c.jpg'),
('303D', 'deluxe', 205.00, 2, 'Deluxe with city view', 'queen', 41.00, TRUE, 2, 3, 'img/room303d.jpg'),
('107A', 'single', 115.00, 1, 'Single room with balcony', 'single', 21.00, TRUE, 3, 1, 'img/room107a.jpg'),
('108B', 'double', 165.00, 2, 'Double room with pool access', 'double', 33.00, TRUE, 1, 2, 'img/room108b.jpg'),
('204C', 'suite', 280.00, 2, 'Grand suite with terrace', 'king', 60.00, TRUE, 7, 4, 'img/room204c.jpg');

-- Seed data for Bookings table
INSERT INTO Bookings (status, user_id, room_id, check_in_date, check_out_date, number_of_guests, created_at) VALUES
('confirmed', 1, 1, '2025-05-01', '2025-05-03', 1, '2025-04-01 10:00:00'),
('pending', 2, 2, '2025-06-10', '2025-06-15', 2, '2025-04-02 12:00:00'),
('cancelled', 3, 3, '2025-07-01', '2025-07-05', 3, '2025-04-03 14:00:00'),
('completed', 4, 4, '2025-03-01', '2025-03-03', 2, '2025-02-01 09:00:00'),
('confirmed', 5, 5, '2025-08-15', '2025-08-20', 1, '2025-04-04 11:00:00'),
('pending', 6, 6, '2025-09-01', '2025-09-04', 2, '2025-04-05 13:00:00'),
('confirmed', 7, 7, '2025-10-10', '2025-10-12', 4, '2025-04-06 15:00:00'),
('completed', 8, 8, '2025-02-15', '2025-02-20', 3, '2025-01-15 08:00:00'),
('cancelled', 9, 9, '2025-11-01', '2025-11-03', 1, '2025-04-07 16:00:00'),
('confirmed', 10, 10, '2025-12-01', '2025-12-05', 2, '2025-04-08 17:00:00'),
('pending', 11, 11, '2025-05-20', '2025-05-25', 4, '2025-04-09 18:00:00'),
('completed', 12, 12, '2025-01-10', '2025-01-15', 3, '2024-12-10 07:00:00'),
('confirmed', 13, 13, '2025-06-01', '2025-06-03', 1, '2025-04-10 19:00:00'),
('pending', 14, 14, '2025-07-15', '2025-07-20', 2, '2025-04-11 20:00:00'),
('confirmed', 15, 15, '2025-08-01', '2025-08-05', 4, '2025-04-12 21:00:00');

-- Seed data for CheckIns table
INSERT INTO CheckIns (booking_id, check_in_date, check_in_time, check_out_date, check_out_time, total_price) VALUES
(1, '2025-05-01', '14:00:00', '2025-05-03', '12:00:00', 200.00),
(2, '2025-06-10', '15:00:00', '2025-06-15', '11:00:00', 750.00),
(3, '2025-07-01', '13:00:00', '2025-07-05', '10:00:00', 1000.00),
(4, '2025-03-01', '14:30:00', '2025-03-03', '11:30:00', 400.00),
(5, '2025-08-15', '12:00:00', '2025-08-20', '12:00:00', 550.00),
(6, '2025-09-01', '16:00:00', '2025-09-04', '10:30:00', 480.00),
(7, '2025-10-10', '14:00:00', '2025-10-12', '11:00:00', 540.00),
(8, '2025-02-15', '15:30:00', '2025-02-20', '12:00:00', 1050.00),
(9, '2025-11-01', '13:30:00', '2025-11-03', '10:00:00', 210.00),
(10, '2025-12-01', '14:00:00', '2025-12-05', '11:00:00', 620.00),
(11, '2025-05-20', '15:00:00', '2025-05-25', '12:00:00', 1300.00),
(12, '2025-01-10', '12:30:00', '2025-01-15', '11:30:00', 1025.00),
(13, '2025-06-01', '14:00:00', '2025-06-03', '10:00:00', 230.00),
(14, '2025-07-15', '15:00:00', '2025-07-20', '12:00:00', 825.00),
(15, '2025-08-01', '13:00:00', '2025-08-05', '11:00:00', 1120.00);

-- Seed data for Payments table
INSERT INTO Payments (booking_id, amount, payment_date, payment_method, payment_status) VALUES
(1, 200.00, '2025-04-01 10:30:00', 'credit_card', 'completed'),
(2, 750.00, '2025-04-02 12:30:00', 'online', 'pending'),
(3, 1000.00, '2025-04-03 14:30:00', 'cash', 'failed'),
(4, 400.00, '2025-02-01 09:30:00', 'debit_card', 'completed'),
(5, 550.00, '2025-04-04 11:30:00', 'credit_card', 'completed'),
(6, 480.00, '2025-04-05 13:30:00', 'online', 'pending'),
(7, 540.00, '2025-04-06 15:30:00', 'credit_card', 'completed'),
(8, 1050.00, '2025-01-15 08:30:00', 'debit_card', 'completed'),
(9, 210.00, '2025-04-07 16:30:00', 'cash', 'failed'),
(10, 620.00, '2025-04-08 17:30:00', 'credit_card', 'completed'),
(11, 1300.00, '2025-04-09 18:30:00', 'online', 'pending'),
(12, 1025.00, '2024-12-10 07:30:00', 'debit_card', 'completed'),
(13, 230.00, '2025-04-10 19:30:00', 'credit_card', 'completed'),
(14, 825.00, '2025-04-11 20:30:00', 'online', 'pending'),
(15, 1120.00, '2025-04-12 21:30:00', 'credit_card', 'completed');

-- Seed data for Reviews table
INSERT INTO Reviews (user_id, room_id, booking_id, rating, comment, review_date_time) VALUES
(1, 1, 1, 4.5, 'Great room, very comfortable', '2025-05-04 09:00:00'),
(2, 2, 2, 4.0, 'Nice view, but a bit noisy', '2025-06-16 10:00:00'),
(3, 3, 3, 3.5, 'Good suite, needs better cleaning', '2025-07-06 11:00:00'),
(4, 4, 4, 5.0, 'Amazing experience!', '2025-03-04 08:00:00'),
(5, 5, 5, 4.2, 'Cozy and clean', '2025-08-21 12:00:00'),
(6, 6, 6, 4.8, 'Loved the garden view', '2025-09-05 13:00:00'),
(7, 7, 7, 4.0, 'Spacious but pricey', '2025-10-13 14:00:00'),
(8, 8, 8, 4.7, 'Fantastic ocean view', '2025-02-21 07:00:00'),
(9, 9, 9, 3.8, 'Room was okay, small size', '2025-11-04 15:00:00'),
(10, 10, 10, 4.3, 'Comfortable stay', '2025-12-06 16:00:00'),
(11, 11, 11, 4.9, 'Best suite ever!', '2025-05-26 17:00:00'),
(12, 12, 12, 4.1, 'Good room, great service', '2025-01-16 06:00:00'),
(13, 13, 13, 4.4, 'Nice balcony', '2025-06-04 18:00:00'),
(14, 14, 14, 4.6, 'Pool access was great', '2025-07-21 19:00:00'),
(15, 15, 15, 5.0, 'Perfect stay, highly recommend', '2025-08-06 20:00:00');