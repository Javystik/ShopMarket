CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50)        NOT NULL,
    email    VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE product_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE product
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(60) UNIQUE NOT NULL,
    cost            INT                NOT NULL,
    product_type_id INTEGER            NOT NULL,
    user_id         INTEGER            NOT NULL,
    FOREIGN KEY (product_type_id) REFERENCES product_type (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);