CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY, -- En PostgreSQL, usamos BIGSERIAL para auto incremento
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL, -- Puede ser más pequeño si es posible
    email VARCHAR(255) UNIQUE NOT NULL,
    dni CHAR(9) UNIQUE NOT NULL, -- Si el DNI es de longitud fija, usa CHAR
    password VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE, -- En PostgreSQL, DATE es adecuado para fechas sin hora
    date_of_registration DATE DEFAULT CURRENT_DATE, -- Asegura que la fecha de registro sea la fecha actual por defecto
    date_of_last_update DATE DEFAULT CURRENT_DATE -- Lo mismo para la última actualización
);