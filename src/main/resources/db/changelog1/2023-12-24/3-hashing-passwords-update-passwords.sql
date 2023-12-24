--liquibase formatted sql
--changeset karol:2
ALTER TABLE User
    ADD COLUMN salt VARCHAR(255) NOT NULL;

UPDATE User
SET salt = SHA2(UUID(), 256);

UPDATE User
SET password = SHA2(CONCAT(password, salt), 256);

ALTER TABLE User
    MODIFY COLUMN password VARCHAR(255) NOT NULL;