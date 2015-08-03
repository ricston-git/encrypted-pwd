-- -----------------------------------------------------
-- Table `Contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Contacts`;
CREATE TABLE `Contacts` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(80) NOT NULL ,
  `email` VARCHAR(80) NULL DEFAULT NULL ,
  `company` VARCHAR(80) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;
