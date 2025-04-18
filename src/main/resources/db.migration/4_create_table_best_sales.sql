CREATE TABLE IF NOT EXISTS best_sales(
    id SERIAL PRIMARY KEY ,
    updated_at TIMESTAMP NOT NULL,
    sale_id INT REFERENCES sale(id)
);