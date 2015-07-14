DROP SCHEMA IF EXISTS `training_db` ;

CREATE SCHEMA IF NOT EXISTS `training_db` character set utf8 collate utf8_general_ci;

USE `training_db` ;



-- -----------------------------------------------------

-- Table `training_db`.`Users`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`Users` (

  `idUsers` INT NOT NULL AUTO_INCREMENT ,

  `firstname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `lastname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `email` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `gender` TINYINT(1)  NOT NULL DEFAULT true ,

  `country` VARCHAR(45) NOT NULL DEFAULT '' ,

  `permissions` TINYINT(1)  NOT NULL DEFAULT false ,

  `active` TINYINT(1)  NOT NULL DEFAULT true ,

  PRIMARY KEY (`idUsers`) );



INSERT INTO Users (email,password, firstname, lastname, country) VALUES('limitvadim@ya.ru','123', 'Vadzim','Salay','Norway');
INSERT INTO Users (email,password,permissions, firstname, lastname, country) VALUES('mailsupportss@mail.ru','123', true, 'Vova','Mayorov','Belarus');
INSERT INTO Users (email,password,firstname, lastname, country) VALUES('belhard@gmail.com','123', 'Vladzimir','Okunev','Jamaica');

-- -----------------------------------------------------

-- Table `training_db`.`News`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`News` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `fk_id_users` INT NOT NULL ,

  `news_message` VARCHAR(255) NOT NULL ,

  `date` VARCHAR(45) NOT NULL ,

  INDEX `fk_id_users_idx` USING HASH (`fk_id_users` ASC) ,

  PRIMARY KEY (`id`) ,

  CONSTRAINT `fk_id_users`

    FOREIGN KEY (`fk_id_users` )

    REFERENCES `training_db`.`Users` (`idUsers` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci;



INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(1, 'The sun is shining well', '15.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(1, 'The sky is blue !!!', '16.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(1, 'The weather is gooood !!!', '17.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(2, 'The sky is blue !!!', '15.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(2, 'The sun is shining well', '16.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(2, 'The weather is gooood !!!', '17.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(3, 'The weather is gooood !!!', '15.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(3, 'The sky is blue !!!', '16.09.2014');
INSERT INTO NEWS (fk_id_users, news_message, date) VALUES(3, 'The weather is gooood !!!', '17.09.2014');

-- -----------------------------------------------------

-- Table `training_db`.`Status`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`Status` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `fk_id_users` INT NOT NULL ,

  `status` VARCHAR(45) NOT NULL DEFAULT '' ,

  INDEX `fk_id_users_idx` (`fk_id_users` ASC) ,

  PRIMARY KEY (`id`) ,

  CONSTRAINT `  fk_id_users`

    FOREIGN KEY (`fk_id_users` )

    REFERENCES `training_db`.`Users` (`idUsers` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);



INSERT INTO Status (fk_id_users, status) VALUES(1, 'Vadzim s statusss');
INSERT INTO Status (fk_id_users, status) VALUES(2, 'Vova s statusssss');
INSERT INTO Status (fk_id_users, status) VALUES(2, 'Belhard s statuss');

-- -----------------------------------------------------

-- Table `training_db`.`Friends`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`Friends` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `fk_users` INT NOT NULL ,

  `friends_id` VARCHAR(255) NOT NULL DEFAULT '' ,

  PRIMARY KEY (`id`) );



INSERT INTO FRIENDS (fk_users, friends_id) VALUES(1, '2:3');
INSERT INTO FRIENDS (fk_users, friends_id) VALUES(2, '1:3');
INSERT INTO FRIENDS (fk_users, friends_id) VALUES(3, '1:2');

-- -----------------------------------------------------

-- Table `training_db`.`Messages`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`Messages` (

  `id` INT NOT NULL AUTO_INCREMENT,

  `id_from` INT NOT NULL ,

  `id_to` INT NOT NULL ,

  `message_date` VARCHAR(45) NOT NULL ,

  `message` VARCHAR(255) NOT NULL ,

  PRIMARY KEY (`id`) );

INSERT INTO MESSAGES (ID_FROM, ID_TO, MESSAGE_DATE, MESSAGE) VALUES(1, 2, '19.09.2014', 'Hello, how re you?');
INSERT INTO MESSAGES (ID_FROM, ID_TO, MESSAGE_DATE, MESSAGE) VALUES(1, 3, '19.09.2014', 'Hi, Im will be here soon!');
INSERT INTO MESSAGES (ID_FROM, ID_TO, MESSAGE_DATE, MESSAGE) VALUES(3, 1, '19.09.2014', 'Waiting for u! Hope u gonna go to work tmrw');
INSERT INTO MESSAGES (ID_FROM, ID_TO, MESSAGE_DATE, MESSAGE) VALUES(2, 1, '19.09.2014', 'So Im wrote you a message tomorrow!');
-- -----------------------------------------------------

-- Table `training_db`.`Photos`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `training_db`.`Photos` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `fk_users` INT NOT NULL ,

  `photos_str` VARCHAR(255) NOT NULL DEFAULT 'default.png' ,

  PRIMARY KEY (`id`) );







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

