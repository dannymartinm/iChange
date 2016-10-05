DROP TABLE if EXISTS user;
CREATE TABLE user
(
  id INT (6) auto_increment PRIMARY KEY,
  username VARCHAR (55),
  zone VARCHAR (40),
  password VARCHAR (15),
  mail VARCHAR (50),
  nickname VARCHAR (50),
  rate double,
  date_creation TIMESTAMP,
  date_edit TIMESTAMP
);

DROP TABLE if EXISTS article;
CREATE TABLE article (
  id bigint auto_increment PRIMARY KEY,
  name VARCHAR (50),
  description VARCHAR (255),
  time int,
  yearMonth VARCHAR(1),
  quantity int,
  date_creation TIMESTAMP ,
  date_edit TIMESTAMP ,
  owner VARCHAR (55),
  FOREIGN KEY (owner)
  REFERENCES user(id)
);

