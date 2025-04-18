CREATE TABLE IF NOT EXISTS sale (
    id SERIAL PRIMARY KEY ,
    sales_point_id INT REFERENCES sales_point(id) NOT NULL ,
    dish_id INT REFERENCES dish(id) NOT NULL ,
    quantity_sold INT NOT NULL ,
    total_amount NUMERIC NOT NULL
);