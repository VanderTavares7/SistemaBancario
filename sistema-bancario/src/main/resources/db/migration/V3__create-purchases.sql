CREATE TABLE purchases (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    product_id BIGINT REFERENCES product(id),
    quantity INTEGER NOT NULL,
    total_price NUMERIC(15,2) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);