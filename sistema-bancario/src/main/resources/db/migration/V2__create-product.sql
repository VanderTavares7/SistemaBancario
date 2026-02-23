CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    productname VARCHAR(100) NOT NULL,
    productPrice NUMERIC(15,2) NOT NULL,
    productQuantity INTEGER NOT NULL
)