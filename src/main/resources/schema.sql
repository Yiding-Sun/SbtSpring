CREATE TABLE mytable(
       `id` INT AUTO_INCREMENT,
       `username` CHAR(20) NOT NULL,
       `pass` CHAR(20) NOT NULL,
       `enable` BOOLEAN,
       `birthday` DATE,
       PRIMARY KEY(`id`)
);
INSERT INTO mytable (username,pass,birthday)
       VALUES
       ('user','pass','2001-02-01'),
       ('user2','pass2','2002-03-02'),
       ('user3','pass3','2003-04-03');
