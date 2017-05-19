CREATE TABLE `SourceIT`.`USERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `LASTNAME` VARCHAR(45) NOT NULL,
  `LOGIN` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `USER_ROLE_ID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `LOGIN_UNIQUE` (`LOGIN` ASC),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC));


CREATE TABLE `SourceIT`.`USER_ROLES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ROLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ROLE_UNIQUE` (`ROLE` ASC));

ALTER TABLE `SourceIT`.`USERS`
CHANGE COLUMN `USER_ROLE_ID` `USER_ROLE_ID` INT NOT NULL ,
ADD INDEX `FK_USER_ROLE_idx` (`USER_ROLE_ID` ASC);
ALTER TABLE `SourceIT`.`USERS`
ADD CONSTRAINT `FK_USER_ROLE`
FOREIGN KEY (`USER_ROLE_ID`)
REFERENCES `SourceIT`.`USER_ROLES` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

CREATE TABLE `SourceIT`.`WARRANTY_PERIODS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `DAYS` INT NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `SourceIT`.`ITEM_TYPES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC));

CREATE TABLE `SourceIT`.`MANUFACTURES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC));

CREATE TABLE `SourceIT`.`ITEMS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `MANUFACTURE_ID` INT NOT NULL,
  `TYPE_ID` INT NOT NULL,
  `DATE_OF_SALE` DATE NOT NULL,
  `WARRANTY_PERIOD_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_MANUFATURE_ID_idx` (`MANUFACTURE_ID` ASC),
  INDEX `FK_TYPE_ID_idx` (`TYPE_ID` ASC),
  INDEX `FK_WARRANTY_PERIOD_idx` (`WARRANTY_PERIOD_ID` ASC),
  CONSTRAINT `FK_MANUFATURE_ID`
  FOREIGN KEY (`MANUFACTURE_ID`)
  REFERENCES `SourceIT`.`MANUFACTURES` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_TYPE_ID`
  FOREIGN KEY (`TYPE_ID`)
  REFERENCES `SourceIT`.`ITEM_TYPES` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_WARRANTY_PERIOD`
  FOREIGN KEY (`WARRANTY_PERIOD_ID`)
  REFERENCES `SourceIT`.`WARRANTY_PERIODS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `SourceIT`.`ITEMS`
ADD COLUMN `SERIAL_NUMBER` VARCHAR(45) NOT NULL AFTER `WARRANTY_PERIOD_ID`;

ALTER TABLE `SourceIT`.`ITEMS`
DROP FOREIGN KEY `FK_TYPE_ID`;
ALTER TABLE `SourceIT`.`ITEMS`
CHANGE COLUMN `TYPE_ID` `ITEM_TYPE_ID` INT(11) NOT NULL ;
ALTER TABLE `SourceIT`.`ITEMS`
ADD CONSTRAINT `FK_TYPE_ID`
FOREIGN KEY (`ITEM_TYPE_ID`)
REFERENCES `SourceIT`.`ITEM_TYPES` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `SourceIT`.`ITEMS`
CHANGE COLUMN `DATE_OF_SALE` `DATEOFSALE` DATE NOT NULL ;





