CREATE TYPE status AS ENUM ('IN_PROGRESS', 'FINISHED', 'DELIVERED');

CREATE TABLE IF NOT EXISTS dish_order (
    id SERIAL PRIMARY KEY ,
    id_dish INT REFERENCES dish(id),
    quantity INT,
    order_status status
);