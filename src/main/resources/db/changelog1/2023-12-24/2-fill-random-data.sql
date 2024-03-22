--liquibase formatted sql
--changeset karol:2

# passwords hashed by bycrypt(default password = haslo123)
INSERT INTO User (username, password, email, activated, activation_token) VALUES
      ('admin', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'admin@example.com', true, NULL),
      ('user2', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user2@example.com', true, 'unique_token_for_user2'),
      ('user3', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user3@example.com', true, 'unique_token_for_user3'),
      ('user4', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user4@example.com', true, 'unique_token_for_user4'),
      ('user5', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user5@example.com', false, 'unique_token_for_user5'),
      ('user6', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user6@example.com', false, 'unique_token_for_user6'),
      ('user7', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user7@example.com', false, 'unique_token_for_user7'),
      ('user8', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user8@example.com', false, 'unique_token_for_user8'),
      ('user9', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user9@example.com', false, 'unique_token_for_user9'),
      ('user10', '$2a$10$93kp3hxxkXegHiqeYg2R9ObDtSKFy7or4YwgQhC7ywm64EuycrBPG', 'user10@example.com', false, 'unique_token_for_user10');

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

INSERT INTO Code (code_id, code, discount, is_active) VALUES
      (1, 'PROMO10', 0.1, true),
      (2, 'PROMO15', 0.15, true),
      (3, 'PROMO18', 0.18, false),
      (4, 'PROMO20', 0.2, true),
      (5, 'PROMO25', 0.25, true);
