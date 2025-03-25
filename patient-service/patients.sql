SHOW DATABASES;
CREATE DATABASE IF NOT EXISTS patientdb;

USE patientdb;
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(20) NOT NULL,
    address VARCHAR(255) DEFAULT NULL,
    phone_number VARCHAR(20) DEFAULT NULL
);

USE patientdb;
SELECT * FROM patients;

INSERT INTO patients (first_name, last_name, birth_date, gender, address, phone_number)
VALUES
    ('TestNone', 'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
    ('TestBorderline', 'Test', '1945-06-24', 'M', '2 High St', '200-333-4444'),
    ('TestInDanger', 'Test', '2004-06-18', 'M', '3 Club Road', '300-444-5555'),
    ('TestEarlyOnset', 'Test', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');
