--liquibase formatted sql
--changeset karol:1
CREATE TABLE IF NOT EXISTS User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    activated BOOLEAN NOT NULL DEFAULT FALSE,
    activation_token VARCHAR(255),
    UNIQUE KEY unique_email (email)
);

CREATE TABLE IF NOT EXISTS Product (
   product_id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS Cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
    );

CREATE TABLE IF NOT EXISTS Cart_item (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
    );

CREATE TABLE IF NOT EXISTS `Order` (
     order_id INT AUTO_INCREMENT PRIMARY KEY,
     user_id INT,
     order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
    );

CREATE TABLE IF NOT EXISTS Order_item (
     order_item_id INT AUTO_INCREMENT PRIMARY KEY,
     order_id INT,
     product_id INT,
     quantity INT NOT NULL,
     unit_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
    );

CREATE TABLE IF NOT EXISTS Role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS User_role (
    user_role_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
    );


CREATE TABLE IF NOT EXISTS Address (
   address_id INT AUTO_INCREMENT PRIMARY KEY,
   user_id INT,
   street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
    );

