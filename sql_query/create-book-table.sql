CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `test`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`id`)) 
ENGINE = InnoDB 
DEFAULT CHARACTER SET = utf8;