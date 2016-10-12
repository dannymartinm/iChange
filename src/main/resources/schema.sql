DROP TABLE if EXISTS user;
CREATE TABLE user
(
  idUser bigint auto_increment PRIMARY KEY,
  username VARCHAR (55),
  zone VARCHAR (40),
  password VARCHAR (15),
  mail VARCHAR (50),
  nickname VARCHAR (50) UNIQUE,
  rate double DEFAULT '-1',
  date_creation TIMESTAMP,
  date_edit TIMESTAMP
);

DROP TABLE if EXISTS exchange;
CREATE TABLE exchange(
  idExchange bigint auto_increment PRIMARY KEY,
  zoneEx VARCHAR (50),
  isDone BOOL NOT NULL DEFAULT '0',
  dateEx TIMESTAMP
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
  idExchange bigint DEFAULT 0,
  FOREIGN KEY (owner) REFERENCES user(idUser)
  ON DELETE CASCADE,
  FOREIGN KEY (idExchange) REFERENCES  exchange(idExchange)
  ON DELETE CASCADE

);

DROP TABLE if EXISTS user_exchange;
CREATE TABLE user_exchange(
  idExchange bigint,
  idUserEx bigint,
  FOREIGN KEY (idExchange) REFERENCES exchange(idExchange),
  FOREIGN KEY (idUserEx) REFERENCES  user(idUser)
);

DROP TABLE if EXISTS category;
CREATE TABLE category(
  idCategory bigint auto_increment PRIMARY KEY,
  nameCategory VARCHAR (30),
  description VARCHAR (140),
  date_creation TIMESTAMP,
  date_edit TIMESTAMP
);
DROP TABLE if EXISTS article_category;
CREATE TABLE article_category(
   idArt bigint,
   idCat bigint,
   FOREIGN KEY (idArt) REFERENCES article(idArticle),
   FOREIGN KEY (idCat) REFERENCES  category(idCategory)
);