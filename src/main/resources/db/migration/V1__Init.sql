CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    dni CHAR(9) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    date_of_registration DATE DEFAULT CURRENT_DATE,
    date_of_last_update DATE DEFAULT CURRENT_DATE
);

CREATE TABLE vehicles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    matricula VARCHAR(255) UNIQUE NOT NULL,
    km BIGINT NOT NULL,
    marca VARCHAR(255) NOT NULL,
    color VARCHAR(255),
    fecha_fabricacion DATE NOT NULL,
    date_of_registration DATE DEFAULT CURRENT_DATE,
    date_of_last_update DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE third_users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(255),
    dni CHAR(9) UNIQUE,
    city VARCHAR(255),
    country VARCHAR(255),
    address VARCHAR(255),
    date_of_birth DATE,
    date_of_registration DATE DEFAULT CURRENT_DATE,
    date_of_last_update DATE DEFAULT CURRENT_DATE
);

CREATE TABLE thirds_vehicles (
    id BIGSERIAL PRIMARY KEY,
    user_third_id BIGINT,
    matricula VARCHAR(255) UNIQUE,
    km BIGINT,
    marca VARCHAR(255),
    color VARCHAR(255),
    aseguradora VARCHAR(255),
    fecha_fabricacion DATE,
    date_of_registration DATE DEFAULT CURRENT_DATE,
    date_of_last_update DATE DEFAULT CURRENT_DATE
);