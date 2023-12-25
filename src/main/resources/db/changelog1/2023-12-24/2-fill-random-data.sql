--liquibase formatted sql
--changeset karol:2

# passwords hashed by bycrypt(default password = haslo123)
INSERT INTO User (username, password, email) VALUES
     ('admin', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'admin@example.com'),
     ('user2', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user2@example.com'),
     ('user3', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user3@example.com'),
     ('user4', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user4@example.com'),
     ('user5', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user5@example.com'),
     ('user6', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user6@example.com'),
     ('user7', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user7@example.com'),
     ('user8', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user8@example.com'),
     ('user9', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user9@example.com'),
     ('user10', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user10@example.com');

INSERT INTO Product (name, description, price, stock_quantity) VALUES
       ('Product1', 'Description1', 19.99, 100),
       ('Product2', 'Description2', 29.99, 75),
       ('Product3', 'Description3', 39.99, 50),
       ('Product4', 'Description4', 49.99, 25),
       ('Product5', 'Description5', 59.99, 10),
       ('Product6', 'Description6', 69.99, 5),
       ('Product7', 'Description7', 79.99, 20),
       ('Product8', 'Description8', 89.99, 30),
       ('Product9', 'Description9', 99.99, 15),
       ('Product10', 'Description10', 109.99, 8);

INSERT INTO Category (name, description) VALUES
    ('Category1', 'Category Description1'),
    ('Category2', 'Category Description2'),
    ('Category3', 'Category Description3'),
    ('Category4', 'Category Description4'),
    ('Category5', 'Category Description5');

INSERT INTO Cart (user_id, status) VALUES
    (1, 'ACTIVE'),
    (2, 'INACTIVE'),
    (3, 'ACTIVE'),
    (4, 'INACTIVE'),
    (5, 'ACTIVE'),
    (6, 'INACTIVE'),
    (7, 'ACTIVE'),
    (8, 'INACTIVE'),
    (9, 'ACTIVE'),
    (10, 'INACTIVE');

INSERT INTO Cart_item (cart_id, product_id, quantity) VALUES
     (1, 1, 2),
     (2, 3, 1),
     (3, 5, 3),
     (4, 7, 2),
     (5, 9, 1),
     (6, 2, 4),
     (7, 4, 1),
     (8, 6, 2),
     (9, 8, 3),
     (10, 10, 1);

INSERT INTO `Order` (user_id, total_price, status) VALUES
   (1, 79.98, 'PROCESSING'),
   (2, 49.99, 'SHIPPED'),
   (3, 209.97, 'DELIVERED'),
   (4, 99.98, 'PROCESSING'),
   (5, 59.99, 'SHIPPED'),
   (6, 279.96, 'DELIVERED'),
   (7, 79.99, 'PROCESSING'),
   (8, 179.97, 'SHIPPED'),
   (9, 299.95, 'DELIVERED'),
   (10, 109.99, 'PROCESSING');

INSERT INTO Order_item (order_id, product_id, quantity, unit_price) VALUES
       (1, 1, 2, 39.99),
       (2, 3, 1, 29.99),
       (3, 5, 3, 69.99),
       (4, 7, 2, 79.99),
       (5, 9, 1, 99.99),
       (6, 2, 4, 59.99),
       (7, 4, 1, 49.99),
       (8, 6, 2, 69.99),
       (9, 8, 3, 89.99),
       (10, 10, 1, 109.99);

INSERT INTO Role (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO User_role (user_id, role_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2);

INSERT INTO Address (user_id, street, city, zip_code, country) VALUES
       (1, 'Street1', 'City1', '12345', 'Country1'),
       (2, 'Street2', 'City2', '23456', 'Country2'),
       (3, 'Street3', 'City3', '34567', 'Country3'),
       (4, 'Street4', 'City4', '45678', 'Country4'),
       (5, 'Street5', 'City5', '56789', 'Country5'),
       (6, 'Street6', 'City6', '67890', 'Country6'),
       (7, 'Street7', 'City7', '78901', 'Country7'),
       (8, 'Street8', 'City8', '89012', 'Country8'),
       (9, 'Street9', 'City9', '90123', 'Country9'),
       (10, 'Street10', 'City10', '01234', 'Country10');
