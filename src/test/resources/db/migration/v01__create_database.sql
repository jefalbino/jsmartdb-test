SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `jsmartdb` ;
CREATE SCHEMA IF NOT EXISTS `jsmartdb` DEFAULT CHARACTER SET utf8 ;
USE `jsmartdb` ;

-- -----------------------------------------------------
-- Table `jsmartdb`.`gama`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`gama` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`gama` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`alpha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`alpha` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`alpha` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  `date` DATETIME NULL ,
  `gama_id` BIGINT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `gama_id` (`gama_id` ASC) ,
  CONSTRAINT `gama_id`
    FOREIGN KEY (`gama_id` )
    REFERENCES `jsmartdb`.`gama` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`beta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`beta` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`beta` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  `alpha_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `alpha_id_fk` (`alpha_id` ASC) ,
  CONSTRAINT `alpha_id_fk`
    FOREIGN KEY (`alpha_id` )
    REFERENCES `jsmartdb`.`alpha` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`delta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`delta` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`delta` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`epsilon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`epsilon` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`epsilon` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`gama_delta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`gama_delta` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`gama_delta` (
  `gama_id` BIGINT NOT NULL ,
  `delta_id` INT NOT NULL ,
  PRIMARY KEY (`gama_id`, `delta_id`) ,
  INDEX `gama_id_join_fk` (`gama_id` ASC) ,
  INDEX `delta_id_join_fk` (`delta_id` ASC) ,
  CONSTRAINT `gama_id_join_fk`
    FOREIGN KEY (`gama_id` )
    REFERENCES `jsmartdb`.`gama` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `delta_id_join_fk`
    FOREIGN KEY (`delta_id` )
    REFERENCES `jsmartdb`.`delta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`iota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`iota` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`iota` (
  `alpha_id` INT NOT NULL ,
  `epsilon_id` INT NOT NULL ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`alpha_id`, `epsilon_id`) ,
  INDEX `alpha_id_join_fk2` (`alpha_id` ASC) ,
  INDEX `epsilon_id_join_fk` (`epsilon_id` ASC) ,
  CONSTRAINT `alpha_id_join_fk2`
    FOREIGN KEY (`alpha_id` )
    REFERENCES `jsmartdb`.`alpha` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `epsilon_id_join_fk`
    FOREIGN KEY (`epsilon_id` )
    REFERENCES `jsmartdb`.`epsilon` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`omega`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`omega` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`omega` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `idn` INT NOT NULL ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`teta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`teta` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`teta` (
  `id` BIGINT NOT NULL ,
  `idn` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`idn`, `id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`fi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`fi` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`fi` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`tau`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`tau` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`tau` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fi_id` INT NULL ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fi_id_fk` (`fi_id` ASC) ,
  CONSTRAINT `fi_id_fk`
    FOREIGN KEY (`fi_id` )
    REFERENCES `jsmartdb`.`fi` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`sigma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`sigma` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`sigma` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`ro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`ro` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`ro` (
  `tau_id` INT NOT NULL ,
  `sigma_id` INT NOT NULL ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`sigma_id`, `tau_id`) ,
  INDEX `tau_id_fk` (`tau_id` ASC) ,
  INDEX `sigma_id_fk` (`sigma_id` ASC) ,
  CONSTRAINT `tau_id_fk`
    FOREIGN KEY (`tau_id` )
    REFERENCES `jsmartdb`.`tau` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sigma_id_fk`
    FOREIGN KEY (`sigma_id` )
    REFERENCES `jsmartdb`.`sigma` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`psi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`psi` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`psi` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `tau_id` INT NULL ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fi_tau_id_fk` (`tau_id` ASC) ,
  CONSTRAINT `fi_tau_id_fk`
    FOREIGN KEY (`tau_id` )
    REFERENCES `jsmartdb`.`tau` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`pi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`pi` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`pi` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `strng` VARCHAR(45) NULL ,
  `txt` TEXT NULL ,
  `intgr` INT NULL ,
  `lng` BIGINT NULL ,
  `flt` FLOAT NULL ,
  `dbl` DOUBLE NULL ,
  `bl` TINYINT(1)  NULL ,
  `dcml` DECIMAL NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jsmartdb`.`tau_pi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jsmartdb`.`tau_pi` ;

CREATE  TABLE IF NOT EXISTS `jsmartdb`.`tau_pi` (
  `tau_id` INT NOT NULL ,
  `pi_id` INT NOT NULL ,
  PRIMARY KEY (`tau_id`, `pi_id`) ,
  INDEX `tau_pi_id_fk` (`tau_id` ASC) ,
  INDEX `pi_tau_id_fk` (`pi_id` ASC) ,
  CONSTRAINT `tau_pi_id_fk`
    FOREIGN KEY (`tau_id` )
    REFERENCES `jsmartdb`.`tau` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pi_tau_id_fk`
    FOREIGN KEY (`pi_id` )
    REFERENCES `jsmartdb`.`pi` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
