CREATE TABLE IF NOT EXISTS dish_order_status(
    id SERIAL,
    dish_order_id INT REFERENCES dish_order(id),
    PRIMARY KEY (id, dish_order_id),
    status status,
    updatedAt TIMESTAMP
);