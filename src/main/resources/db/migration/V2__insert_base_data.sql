INSERT INTO users (username, password, email)
VALUES ('alice_smith', 'pass123', 'alice@example.com'),
       ('bob_jones', 'securepass', 'bob@example.com'),
       ('charlie_williams', 'cwilliams2024', 'charlie@example.com'),
       ('david_clark', 'dave@123', 'david@example.com'),
       ('emily_brown', 'emily123', 'emily@example.com'),
       ('frank_thomas', 'frankie007', 'frank@example.com'),
       ('grace_taylor', 'grace123', 'grace@example.com'),
       ('hannah_anderson', 'hannah@123', 'hannah@example.com'),
       ('ian_jackson', 'ianjackson2024', 'ian@example.com'),
       ('jessica_white', 'jessica@123', 'jessica@example.com');

INSERT INTO product_type (name)
VALUES ('Smartphones'),
       ('Laptops'),
       ('T-shirts'),
       ('Shoes'),
       ('Books'),
       ('Appliances'),
       ('Gifts'),
       ('Food'),
       ('Furniture'),
       ('Cosmetics');

INSERT INTO product (name, cost, product_type_id, user_id)
VALUES ('iPhone 12', 1000, 1, 1),
       ('MacBook Pro', 1500, 2, 2),
       ('White T-shirt', 20, 3, 3),
       ('Nike Air Max', 80, 4, 4),
       ('The Great Gatsby', 15, 5, 5),
       ('Blender', 50, 6, 6),
       ('Gift Card', 25, 7, 7),
       ('Pizza', 10, 8, 8),
       ('Office Chair', 150, 9, 9),
       ('Lipstick', 20, 10, 10);
