-- Seed Data for Testing

INSERT INTO Users (firstname, lastname, username, password, role, email, phone_no, address, gender, DOB, verification_id, profile_picture, is_active) VALUES
('John', 'Doe', 'johndoe', 'hashed_password_123', 'customer', 'john.doe@example.com', '1234567890', '123 Main St, City', 'male', '1990-05-15', 'ID123456', 'profile1.jpg', TRUE),
('Jane', 'Smith', 'janesmith', 'hashed_password_456', 'admin', 'jane.smith@example.com', '0987654321', '456 Elm St, City', 'female', '1985-08-22', 'ID789012', 'profile2.jpg', TRUE),
('Mike', 'Johnson', 'mikej', 'hashed_password_789', 'staff', 'mike.j@example.com', '5551234567', '789 Oak St, City', 'male', '1992-03-10', 'ID345678', NULL, TRUE);

INSERT INTO Rooms (room_type, price_per_night, no_of_beds, description, bed_type, room_area, is_available, floor_number, max_occupancy, has_wifi, has_tv, has_minibar, has_jacuzzi) VALUES
('single', 100.00, 1, 'Cozy single room with city view', 'single', 20.50, TRUE, 2, 1, TRUE, TRUE, FALSE, FALSE),
('double', 180.00, 2, 'Spacious double room with balcony', 'queen', 35.75, TRUE, 3, 2, TRUE, TRUE, TRUE, FALSE),
('suite', 300.00, 2, 'Luxurious suite with jacuzzi', 'king', 50.00, FALSE, 5, 4, TRUE, TRUE, TRUE, TRUE);

INSERT INTO Bookings (status, total_price, user_id, room_id, check_in_date, check_out_date, number_of_guests, created_at) VALUES
('confirmed', 200.00, 1, 1, '2025-05-01', '2025-05-03', 1, '2025-04-10 10:00:00'),
('pending', 360.00, 2, 2, '2025-06-01', '2025-06-03', 2, '2025-04-15 14:30:00'),
('cancelled', 100.00, 3, 1, '2025-04-20', '2025-04-21', 1, '2025-04-12 09:15:00');

INSERT INTO CheckIns (booking_id, check_in_date, check_in_time, check_out_date, check_out_time, additional_charges) VALUES
(1, '2025-05-01', '14:00:00', '2025-05-03', '11:00:00', 20.00),
(2, '2025-06-01', '15:00:00', '2025-06-03', '12:00:00', 0.00);

INSERT INTO Payments (booking_id, amount, payment_date, payment_method, payment_status) VALUES
(1, 220.00, '2025-04-10 10:30:00', 'credit_card', 'completed'),
(2, 360.00, '2025-04-15 14:45:00', 'online', 'pending');

INSERT INTO Reviews (user_id, room_id, booking_id, overall_rating, cleanliness_rating, service_rating, value_rating, comment, review_date_time) VALUES
(1, 1, 1, 4.5, 4.0, 4.8, 4.2, 'Great room, very clean and comfortable!', '2025-05-03 12:30:00'),
(2, 2, 2, 4.8, 4.5, 4.9, 4.7, 'Loved the balcony view and amenities', '2025-06-03 13:00:00');