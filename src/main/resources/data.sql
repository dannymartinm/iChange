INSERT INTO user VALUES (1, 'Josep', 'barceloneta','123456', 'roure@tecnocampus.cat', 'josi',0, '2016-09-20' ,'2016-09-20');
INSERT INTO user VALUES (2, 'Jordi', 'gótico', '123456', 'pi@tecnocampus.cat', 'jordinho',3, '2016-09-20' ,'2016-09-20');
INSERT INTO user VALUES (3, 'Mercè', 'gracia', '123456', 'proure@gmail.com', 'merc',3, '2016-09-20' ,'2016-09-20');
INSERT INTO user VALUES (4, 'Maria', 'barceloneta', '123456', 'espinac@gmail.com','mery',1, '2016-09-20' ,'2016-09-20');
INSERT INTO user(iduser, username, zone, password, mail, nickname, date_creation, date_edit)
VALUES (5, 'Josefina', 'Mataro', '123456', 'koki@gmail.com','Jota','2016-09-20' ,'2016-09-20');

INSERT INTO exchange(idExchange, zoneEx, isDone, dateEx) VALUES (1,'mataro',1,'2016-09-20');

INSERT INTO article (idArticle, name, description, time, yearMonth, quantity, date_creation, date_edit, owner, idExchange) VALUES (1,'vestido vintage','color rojo', 2, 'Y', 2,'2016-09-20' ,'2016-09-20', 2,1);
INSERT INTO article (idArticle, name, description, time, yearMonth, quantity, date_creation, date_edit, owner, idExchange) VALUES (2,'libro harry potter','Cádiz del FUego', 5, 'M', 7, '2016-09-20','2016-09-20', 1,1);
INSERT INTO article (idArticle, name, description, time, yearMonth, quantity, date_creation, date_edit, owner, idExchange) VALUES (4,'reloj de pulsera','azul y de flores', 4, 'M', 5, '2016-09-20','2016-09-20', 4,1);
INSERT INTO article (idArticle, name, description, time, yearMonth, quantity, date_creation, date_edit, owner, idExchange) VALUES (3,'móvil','última generación', 2, 'Y', 1,'2016-09-20' ,'2016-09-20', 3,1);
INSERT INTO article (idArticle, name, description, time, yearMonth, quantity, date_creation, date_edit, owner, idExchange) VALUES (5,'comida perros','de carne', 5, 'M', 1, '2016-09-20','2016-09-20', 4,1);


INSERT INTO category (idCategory, nameCategory, description, date_creation, date_edit) VALUES  (1, 'Books', 'Libros','2016-09-20' ,'2016-09-20' );
INSERT INTO category (idCategory, nameCategory, description, date_creation, date_edit) VALUES  (2, 'Movies', 'Películas','2016-09-20' ,'2016-09-20' );