DROP SCHEMA IF EXISTS `chat` ;

CREATE SCHEMA IF NOT EXISTS `chat` character set utf8 collate utf8_general_ci;

USE `chat` ;
-- -----------------------------------------------------
-- Table `test`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chat`.`users` (

  `idUsers` INT PRIMARY KEY AUTO_INCREMENT ,

  `login` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `firstname` VARCHAR(45) DEFAULT '' ,

  `lastname` VARCHAR(45) DEFAULT '' ,

  `email` VARCHAR(45));
  -- -----------------------------------------------------
-- Table `test`.`Messages`
-- -------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chat`.`messages` (

  `idMsg` INT PRIMARY KEY AUTO_INCREMENT ,

  `usersId` INT NOT NULL,

  `msg` TEXT NOT NULL);

-- -------------------------------------------------------
INSERT INTO users (login,password) VALUES('xx','yy');
INSERT INTO messages (usersId,msg) VALUES(1,'aaaaaaaaaaaaaaa');
INSERT INTO messages (usersId,msg) VALUES(1,'bbbbbbbbbbbbbbb');
INSERT INTO messages (usersId,msg) VALUES(1,'ccccccccccccccc');