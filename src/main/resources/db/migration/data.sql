USE `insurance-db-users`;

INSERT INTO `users` (name, surname, phone, email, password, city, country, address, date_of_birth, date_of_registration, date_of_last_update, dni) VALUES
('Jane', 'Smith', '234567890', 'jane.smith@example.com', 'password456', 'City 2', 'Country 2', 'Address 2', '1991-02-02', '2025-02-24', NULL, '23456789B'),
('Alice', 'Johnson', '345678901', 'alice.johnson@example.com', 'password789', 'City 3', 'Country 3', 'Address 3', '1992-03-03', '2025-02-24', NULL, '34567890C'),
('Bob', 'Brown', '456789012', 'bob.brown@example.com', 'password012', 'City 4', 'Country 4', 'Address 4', '1993-04-04', '2025-02-24', NULL, '45678901D'),
('Charlie', 'Davis', '567890123', 'charlie.davis@example.com', 'password345', 'City 5', 'Country 5', 'Address 5', '1994-05-05', '2025-02-24', NULL, '56789012E');