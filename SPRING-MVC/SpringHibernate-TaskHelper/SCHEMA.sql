DROP SCHEMA IF EXISTS `test` ;

CREATE SCHEMA IF NOT EXISTS `test` character set utf8 collate utf8_general_ci;

USE `test` ;
-- -----------------------------------------------------
-- Table `test`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`users` (

  `idUsers` INT PRIMARY KEY AUTO_INCREMENT ,
  
  `login` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `firstname` VARCHAR(45) DEFAULT '' ,

  `lastname` VARCHAR(45) DEFAULT '' ,

  `email` VARCHAR(45));
  -- -----------------------------------------------------
-- Table `test`.`Tasks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`employee` (

  `idTasks` INT PRIMARY KEY AUTO_INCREMENT ,

  `usersId` INT NOT NULL,

  `task` TEXT NOT NULL ,

  `dt` DATE NOT NULL ,

  `fixed` TINYINT(1)  NOT NULL DEFAULT false ,

  `recycled` TINYINT(1)  NOT NULL DEFAULT false ,

  `file` VARCHAR(255));