
-- Inserts para usuarios
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Juan', 'Pérez', '600111222', 'juan@example.com', '12345678A', 'pass123', 'Madrid', 'España', 'Calle Sol 1', '1985-05-10');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('María', 'González', '600111223', 'maria@example.com', '12345678B', 'pass124', 'Barcelona', 'España', 'Calle Luna 2', '1988-07-15');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Carlos', 'Rodríguez', '600111224', 'carlos@example.com', '12345678C', 'pass125', 'Valencia', 'España', 'Calle Estrella 3', '1979-03-20');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Laura', 'Fernández', '600111225', 'laura@example.com', '12345678D', 'pass126', 'Sevilla', 'España', 'Calle Mar 4', '1992-11-05');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Miguel', 'López', '600111226', 'miguel@example.com', '12345678E', 'pass127', 'Zaragoza', 'España', 'Calle Río 5', '1980-09-22');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Ana', 'Martínez', '600111227', 'ana@example.com', '12345678F', 'pass128', 'Málaga', 'España', 'Calle Monte 6', '1995-01-30');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Javier', 'Sánchez', '600111228', 'javier@example.com', '12345678G', 'pass129', 'Bilbao', 'España', 'Calle Valle 7', '1983-06-12');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Carmen', 'Díaz', '600111229', 'carmen@example.com', '12345678H', 'pass130', 'Alicante', 'España', 'Calle Playa 8', '1990-02-28');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Roberto', 'García', '600111230', 'roberto@example.com', '12345678I', 'pass131', 'Córdoba', 'España', 'Calle Parque 9', '1977-08-18');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Sofía', 'Ruiz', '600111231', 'sofia@example.com', '12345678J', 'pass132', 'Granada', 'España', 'Calle Bosque 10', '1993-04-25');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('David', 'Hernández', '600111232', 'david@example.com', '12345678K', 'pass133', 'Murcia', 'España', 'Calle Fuente 11', '1986-12-07');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Patricia', 'Torres', '600111233', 'patricia@example.com', '12345678L', 'pass134', 'Palma', 'España', 'Calle Jardín 12', '1991-10-14');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Alejandro', 'Moreno', '600111234', 'alejandro@example.com', '12345678M', 'pass135', 'Valladolid', 'España', 'Calle Puente 13', '1982-07-29');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Cristina', 'Ortega', '600111235', 'cristina@example.com', '12345678N', 'pass136', 'Vigo', 'España', 'Calle Torre 14', '1997-05-04');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Pablo', 'Rubio', '600111236', 'pablo@example.com', '12345678O', 'pass137', 'Gijón', 'España', 'Calle Castillo 15', '1978-02-17');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Lucía', 'Molina', '600111237', 'lucia@example.com', '12345678P', 'pass138', 'Almería', 'España', 'Calle Farol 16', '1994-11-21');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Fernando', 'Serrano', '600111238', 'fernando@example.com', '12345678Q', 'pass139', 'Santander', 'España', 'Calle Portal 17', '1984-09-08');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Elena', 'Romero', '600111239', 'elena@example.com', '12345678R', 'pass140', 'Burgos', 'España', 'Calle Mirador 18', '1989-03-15');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Alberto', 'Castro', '600111240', 'alberto@example.com', '12345678S', 'pass141', 'Salamanca', 'España', 'Calle Ventana 19', '1976-06-27');
INSERT INTO users (name, surname, phone, email, dni, password, city, country, address, date_of_birth) VALUES ('Marta', 'Sáez', '600111241', 'marta@example.com', '12345678T', 'pass142', 'Logroño', 'España', 'Calle Esquina 20', '1996-01-03');

-- Inserts para vehicles
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (1, '1234ABC', 50000, 'Toyota', 'Rojo', '2018-01-15');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (2, '5678DEF', 30000, 'Seat', 'Azul', '2019-05-20');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (3, '9012GHI', 70000, 'Renault', 'Negro', '2016-11-10');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (4, '3456JKL', 25000, 'Ford', 'Blanco', '2020-03-05');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (5, '7890MNO', 60000, 'Volkswagen', 'Gris', '2017-07-22');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (6, '1357PQR', 15000, 'Audi', 'Negro', '2021-01-30');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (7, '2468STU', 45000, 'BMW', 'Azul', '2018-09-12');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (8, '3579VWX', 35000, 'Mercedes', 'Plata', '2019-12-05');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (9, '8642YZA', 80000, 'Opel', 'Verde', '2015-04-18');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (10, '9753BCD', 20000, 'Peugeot', 'Rojo', '2020-08-27');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (11, '1598EFG', 55000, 'Citroen', 'Gris', '2017-11-14');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (12, '2684HIJ', 40000, 'Fiat', 'Blanco', '2018-06-03');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (13, '3720KLM', 65000, 'Honda', 'Negro', '2016-02-19');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (14, '4936NOP', 10000, 'Hyundai', 'Azul', '2021-05-07');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (15, '5142QRS', 75000, 'Kia', 'Rojo', '2015-10-25');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (16, '6257TUV', 30000, 'Mazda', 'Gris', '2019-09-11');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (17, '7361WXY', 50000, 'Nissan', 'Blanco', '2017-12-30');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (18, '8476ZAB', 20000, 'Subaru', 'Negro', '2020-04-22');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (19, '9582CDE', 60000, 'Suzuki', 'Azul', '2016-08-09');
INSERT INTO vehicles (user_id, matricula, km, marca, color, fecha_fabricacion) VALUES (20, '0693FGH', 25000, 'Volvo', 'Plata', '2019-03-17');

-- Inserts para third_users
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Pedro', 'Alonso', '600222222', 'pedro@example.com', '87654321A', 'Madrid', 'España', 'Av. Principal 1', '1980-12-10');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Luisa', 'Gómez', '600222223', 'luisa@example.com', '87654321B', 'Barcelona', 'España', 'Av. Secundaria 2', '1985-05-15');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Antonio', 'Martín', '600222224', 'antonio@example.com', '87654321C', 'Valencia', 'España', 'Av. Central 3', '1975-07-20');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Sara', 'López', '600222225', 'sara@example.com', '87654321D', 'Sevilla', 'España', 'Av. Norte 4', '1990-02-25');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Marcos', 'Ruiz', '600222226', 'marcos@example.com', '87654321E', 'Zaragoza', 'España', 'Av. Sur 5', '1978-09-30');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Isabel', 'Sánchez', '600222227', 'isabel@example.com', '87654321F', 'Málaga', 'España', 'Av. Este 6', '1992-04-05');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Raúl', 'García', '600222228', 'raul@example.com', '87654321G', 'Bilbao', 'España', 'Av. Oeste 7', '1983-11-10');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Mónica', 'Fernández', '600222229', 'monica@example.com', '87654321H', 'Alicante', 'España', 'Av. Primera 8', '1987-06-15');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Jorge', 'Díaz', '600222230', 'jorge@example.com', '87654321I', 'Córdoba', 'España', 'Av. Segunda 9', '1976-03-20');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Natalia', 'Pérez', '600222231', 'natalia@example.com', '87654321J', 'Granada', 'España', 'Av. Tercera 10', '1991-10-25');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Francisco', 'Moreno', '600222232', 'francisco@example.com', '87654321K', 'Murcia', 'España', 'Av. Cuarta 11', '1982-05-30');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Silvia', 'Torres', '600222233', 'silvia@example.com', '87654321L', 'Palma', 'España', 'Av. Quinta 12', '1989-12-05');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Daniel', 'Ortega', '600222234', 'daniel@example.com', '87654321M', 'Valladolid', 'España', 'Av. Sexta 13', '1979-07-10');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Rosa', 'Rubio', '600222235', 'rosa@example.com', '87654321N', 'Vigo', 'España', 'Av. Séptima 14', '1993-02-15');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('José', 'Molina', '600222236', 'jose@example.com', '87654321O', 'Gijón', 'España', 'Av. Octava 15', '1977-09-20');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Marina', 'Serrano', '600222237', 'marina@example.com', '87654321P', 'Almería', 'España', 'Av. Novena 16', '1995-04-25');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Hugo', 'Romero', '600222238', 'hugo@example.com', '87654321Q', 'Santander', 'España', 'Av. Décima 17', '1984-11-30');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Diana', 'Castro', '600222239', 'diana@example.com', '87654321R', 'Burgos', 'España', 'Av. Undécima 18', '1988-06-05');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Guillermo', 'Sáez', '600222240', 'guillermo@example.com', '87654321S', 'Salamanca', 'España', 'Av. Duodécima 19', '1974-01-10');
INSERT INTO third_users (name, surname, phone, email, dni, city, country, address, date_of_birth) VALUES ('Alicia', 'Giménez', '600222241', 'alicia@example.com', '87654321T', 'Logroño', 'España', 'Av. Decimotercera 20', '1996-08-15');

-- Inserts para thirds_vehicles
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (1, '1111AAA', 45000, 'Toyota', 'Rojo', 'Mapfre', '2017-03-15');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (2, '2222BBB', 35000, 'Seat', 'Azul', 'Allianz', '2018-06-20');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (3, '3333CCC', 65000, 'Renault', 'Negro', 'AXA', '2016-09-10');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (4, '4444DDD', 20000, 'Ford', 'Blanco', 'Generali', '2019-12-05');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (5, '5555EEE', 55000, 'Volkswagen', 'Gris', 'Liberty', '2017-03-22');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (6, '6666FFF', 15000, 'Audi', 'Negro', 'Pelayo', '2020-06-30');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (7, '7777GGG', 40000, 'BMW', 'Azul', 'Mutua Madrileña', '2018-09-12');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (8, '8888HHH', 30000, 'Mercedes', 'Plata', 'Zurich', '2019-12-05');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (9, '9999III', 75000, 'Opel', 'Verde', 'RACC', '2015-03-18');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (10, '1010JJJ', 18000, 'Peugeot', 'Rojo', 'Catalana Occidente', '2020-06-27');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (11, '1212KKK', 50000, 'Citroen', 'Gris', 'Línea Directa', '2017-09-14');
INSERT INTO thirds_vehicles (user_third_id, matricula, km, marca, color, aseguradora, fecha_fabricacion) VALUES (12, '1313LLL', 35000, 'Fiat', 'Blanco', 'Reale', '2018-12-03');
