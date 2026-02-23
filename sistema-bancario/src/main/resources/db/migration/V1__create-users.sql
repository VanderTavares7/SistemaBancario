CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    birth_date DATE NOT NULL,
    balance NUMERIC(15,2) NOT NULL,
    credit_limit NUMERIC(15,2) NOT NULL,
    email VARCHAR(100) UNIQUE,
    role TEXT NOT NULL
);