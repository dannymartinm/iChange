DROP TABLE if EXISTS user;
CREATE TABLE user
(
  idUser INT (6) auto_increment PRIMARY KEY,
  username VARCHAR (55),
  zone VARCHAR (40),
  password VARCHAR (15),
  mail VARCHAR (50),
  nickname VARCHAR (50) UNIQUE,
  rate double DEFAULT '-1',
  date_creation TIMESTAMP,
  date_edit TIMESTAMP
);

DROP TABLE if EXISTS article;
CREATE TABLE article (
  idArticle bigint auto_increment PRIMARY KEY,
  name VARCHAR (50),
  description VARCHAR (255),
  time int,
  yearMonth VARCHAR(1),
  quantity int,
  date_creation TIMESTAMP ,
  date_edit TIMESTAMP ,
  owner VARCHAR (55),
  FOREIGN KEY (owner)
  REFERENCES user(idUser)
  ON DELETE CASCADE
);

DROP TABLE if EXISTS exchange;
CREATE TABLE exchange(
  idExchange bigint auto_increment PRIMARY KEY,
  zoneEx VARCHAR (50),
  isDone BOOL NOT NULL DEFAULT '0',
  dateEx TIMESTAMP
);

DRoP TABLE if EXISTS catalog;
CREATE TABLE catalog(
  idCatalog bigint auto_increment PRIMARY KEY,
  descriptionCatalog VARCHAR (255)
);

