DROP SCHEMA IF EXISTS `tasks_db` ;

CREATE SCHEMA IF NOT EXISTS `tasks_db` character set utf8 collate utf8_general_ci;

USE `tasks_db` ;
-- -----------------------------------------------------
-- Table `tasks_db`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tasks_db`.`users` (

  `idUsers` INT PRIMARY KEY AUTO_INCREMENT ,
  
  `login` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `firstname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `lastname` VARCHAR(45) NOT NULL DEFAULT '' ,

  `email` VARCHAR(45) NOT NULL );
  -- -----------------------------------------------------
-- Table `tasks_db`.`Tasks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tasks_db`.`tasks` (

  `idTasks` INT PRIMARY KEY AUTO_INCREMENT ,

  `usersId` INT NOT NULL,
  
  `task` TEXT NOT NULL ,
  
  `dt` DATE NOT NULL ,
  
  `fixed` TINYINT(1)  NOT NULL DEFAULT false ,

  `recycled` TINYINT(1)  NOT NULL DEFAULT false ,
  
  `file` VARCHAR(255) DEFAULT '' );
  
  <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbc/tmsDB" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/tasks_db?characterEncoding=UTF-8" username="root"/>