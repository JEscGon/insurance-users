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
    date_of_registration DATE,
    date_of_last_update DATE
);

CREATE TABLE vehicles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    matricula VARCHAR(255) UNIQUE NOT NULL,
    km BIGINT NOT NULL,
    marca VARCHAR(255) NOT NULL,
    fecha_fabricacion DATE NOT NULL,
    date_of_registration DATE,
    date_of_last_update DATE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);