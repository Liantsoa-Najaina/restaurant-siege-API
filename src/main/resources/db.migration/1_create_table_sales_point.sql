CREATE TABLE IF NOT EXISTS sales_point(
    id SERIAL PRIMARY KEY,
    "name" VARCHAR NOT NULL,
    url TEXT NOT NULL,
    api_key TEXT
);