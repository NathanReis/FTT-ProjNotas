DROP DATABASE IF EXISTS `projnotas`;
CREATE DATABASE `projnotas`;
USE `projnotas`;

DROP USER IF EXISTS 'es2lp3n2'@'localhost';
CREATE USER 'es2lp3n2'@'localhost' IDENTIFIED BY 'nsvw@#220Z';
GRANT SELECT, INSERT, UPDATE, DELETE ON `projnotas` . * TO 'es2lp3n2'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE `tbTeachingInstitutions`
(
  `id` SMALLINT UNSIGNED AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE(`name`)
);

CREATE TABLE `tbUsers`
(
  `id` INT UNSIGNED AUTO_INCREMENT,
  `userName` VARCHAR(20) NOT NULL,
  `password` VARBINARY(255) NOT NULL,
  `idTeachingInstitution` SMALLINT UNSIGNED,
  `type` CHAR(1) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`idTeachingInstitution`) REFERENCES `tbTeachingInstitutions`(`id`),
  UNIQUE(`userName`)
);

CREATE TABLE `tbSubjects`
(
  `id` SMALLINT UNSIGNED AUTO_INCREMENT,
  `description` VARCHAR(30) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE(`description`)
);

CREATE TABLE `tbSubjectsXUsers`
(tbsubjects
  `id` INT UNSIGNED AUTO_INCREMENT,
  `idSubject` SMALLINT UNSIGNED NOT NULL,
  `idTeachingInstitution` SMALLINT UNSIGNED NOT NULL,
  `idUser` INT UNSIGNED NOT NULL,
  `grade` DECIMAL(3, 1) NOT NULL,
  `semester` TINYINT UNSIGNED NOT NULL,
  `year` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`idSubject`) REFERENCES `tbSubjects`(`id`),
  FOREIGN KEY(`idTeachingInstitution`) REFERENCES `tbTeachingInstitutions`(`id`),
  FOREIGN KEY(`idUser`) REFERENCES `tbUsers`(`id`)
);

CREATE TABLE `tbSubjectsXTeachingInstitutions`
(
  `id` INT UNSIGNED AUTO_INCREMENT,
  `idSubject` SMALLINT UNSIGNED NOT NULL,
  `idTeachingInstitution` SMALLINT UNSIGNED NOT NULL,
  `active` CHAR(1) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`idSubject`) REFERENCES `tbSubjects`(`id`),
  FOREIGN KEY(`idTeachingInstitution`) REFERENCES `tbTeachingInstitutions`(`id`)
);
