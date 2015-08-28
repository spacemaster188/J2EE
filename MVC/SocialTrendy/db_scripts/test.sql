DROP SCHEMA IF EXISTS `test` ;

CREATE SCHEMA IF NOT EXISTS `test` character set utf8 collate utf8_general_ci;

USE `test` ;
-- -----------------------------------------------------

-- Table `test`.`Users`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`users` (

  `idUsers` INT AUTO_INCREMENT ,

  `firstname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `lastname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `email` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `gender` TINYINT(1)  NOT NULL DEFAULT true ,

  `country` VARCHAR(45) NOT NULL DEFAULT '' ,

  `permissions` TINYINT(1)  NOT NULL DEFAULT false ,

  `active` TINYINT(1)  NOT NULL DEFAULT true ,

  PRIMARY KEY (`idUsers`));



INSERT INTO users (email,password, firstname, lastname, country) VALUES('limitvadim@ya.ru','123', 'Vadzim','Salay','Norway');
INSERT INTO users (email,password, firstname, lastname, country) VALUES('mailsupportss@mail.ru','123','Vova','Mayorov','Belarus');
INSERT INTO users (email,password, firstname, lastname, country) VALUES('belhard@gmail.com','123', 'Vladzimir','Okunev','Jamaica');

-- -----------------------------------------------------

-- Table `test`.`News`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`news` (

  `id` INT AUTO_INCREMENT ,

  `usersId` INT NOT NULL ,

  `newsMessage` VARCHAR(255) character set utf8 collate utf8_general_ci NOT NULL ,

  `dt` DATETIME,
  PRIMARY KEY (`id`));


INSERT INTO news (usersId, newsMessage, dt) VALUES(1, 'The sun is shining well', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(1, 'The sky is blue !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(1, 'The weather is gooood !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(2, 'The sky is blue !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(2, 'The sun is shining well', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(2, 'The weather is gooood !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(3, 'The weather is gooood !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(3, 'The sky is blue !!!', NOW());
INSERT INTO news (usersId, newsMessage, dt) VALUES(3, 'The weather is gooood !!!', NOW());

-- -----------------------------------------------------

-- Table `test`.`Status`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`status` (

  `id` INT AUTO_INCREMENT ,

  `usersId` INT NOT NULL ,

  `status` VARCHAR(45) NOT NULL DEFAULT '' ,
  
  `lastseen` DATETIME,

  PRIMARY KEY (`id`));


INSERT INTO status (usersId, status, lastseen) VALUES(1, 'Vadzim s statusss', NOW());
INSERT INTO status (usersId, status, lastseen) VALUES(2, 'Vova s statusssss', NOW());
INSERT INTO status (usersId, status, lastseen) VALUES(3, 'Belhard s statuss', NOW());

-- -----------------------------------------------------

-- Table `test`.`Friends`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`friends` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `usersId` INT NOT NULL ,

  `friendsId` TEXT ,

  PRIMARY KEY (`id`) );


INSERT INTO friends (usersId, friendsId) VALUES(1, '2:3');
INSERT INTO friends (usersId, friendsId) VALUES(2, '1:3');
INSERT INTO friends (usersId, friendsId) VALUES(3, '1:2');

-- -----------------------------------------------------

-- Table `test`.`Messages`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`messages` (

  `id` INT AUTO_INCREMENT,

  `idFrom` INT NOT NULL ,

  `idTo` INT NOT NULL ,

  `dt` DATETIME NOT NULL,

  `message` TEXT ,

  PRIMARY KEY (`id`) );

INSERT INTO messages (idFrom, idTo, dt, message) VALUES(1, 2, NOW(), 'Hello, how re you?');
INSERT INTO messages (idFrom, idTo, dt, message) VALUES(1, 3, NOW(), 'Hi, Im will be here soon!');
INSERT INTO messages (idFrom, idTo, dt, message) VALUES(3, 1, NOW(), 'Waiting for u! Hope u gonna go to work tmrw');
INSERT INTO messages (idFrom, idTo, dt, message) VALUES(2, 1, NOW(), 'So Im wrote you a message tomorrow!');
-- -----------------------------------------------------

-- Table `test`.`Photos`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`photos` (

  `id` INT AUTO_INCREMENT ,

  `usersId` INT NOT NULL ,

  `photosStr` TEXT  ,

  PRIMARY KEY (`id`) );

  INSERT INTO photos (usersId, photosStr) VALUES(1,'default.png');
  INSERT INTO photos (usersId, photosStr) VALUES(2,'default.png');
  INSERT INTO photos (usersId, photosStr) VALUES(3,'default.png');
  -- -----------------------------------------------------

-- Table `test`.`Music`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `test`.`music` (

  `id` INT AUTO_INCREMENT ,

  `usersId` INT NOT NULL ,

  `musicStr` TEXT ,

  PRIMARY KEY (`id`) );
  
  <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbc/tmsDB" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8" username="root"/>