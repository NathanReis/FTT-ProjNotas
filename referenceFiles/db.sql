DROP DATABASE IF EXISTS `projnotas`;

CREATE DATABASE `projnotas`;

USE `projnotas`;

DROP USER IF EXISTS 'es2lp3n2'@'localhost';

CREATE USER 'es2lp3n2'@'localhost' IDENTIFIED BY 'nsvw@#220Z';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER ON `projnotas` . * TO 'es2lp3n2'@'localhost';
-- GRANT ALL PRIVILEGES ON `projnotas` . * TO 'es2lp3n2'@'localhost';
FLUSH PRIVILEGES;

