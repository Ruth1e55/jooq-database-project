CREATE DATABASE `library`;

USE `library`;

CREATE TABLE `author` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `first_name` varchar(255) DEFAULT NULL,
                          `last_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
);

insert into author (id, first_name, last_name) values (1, 'Joanne', 'Rowling');
insert into author (id, first_name, last_name) values (2, 'Herman', 'Melville');
insert into author (id, first_name, last_name) values (3, 'Anne', 'Rice');
insert into author (id, first_name, last_name) values (4, 'Stephen', 'King');
insert into author (id, first_name, last_name) values (5, 'Isaac', 'Asimov');
insert into author (id, first_name, last_name) values (6, 'Frank', 'Herbert');
insert into author (id, first_name, last_name) values (7, 'J.R.R.', 'Tolkien');
insert into author (id, first_name, last_name) values (8, 'George', 'Orwell');
insert into author (id, first_name, last_name) values (9, 'J.K.', 'Rowling');
insert into author (id, first_name, last_name) values (10, 'William', 'Shakespeare');
