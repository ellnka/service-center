CREATE TABLE `USERS` (
  `ID`            INT         NOT NULL AUTO_INCREMENT,
  `NAME`          VARCHAR(45) NOT NULL,
  `LASTNAME`      VARCHAR(45) NOT NULL,
  `LOGIN`         VARCHAR(45) NOT NULL,UNIQUE(LOGIN),
  `PASSWORD`      VARCHAR(45) NOT NULL,
  `EMAIL`         VARCHAR(45) NOT NULL,UNIQUE(EMAIL),
  `USER_ROLE_ID`  INT NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `USER_ROLES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ROLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ROLE_UNIQUE` (`ROLE` ASC));

ALTER TABLE `USERS` ADD FOREIGN KEY (USER_ROLE_ID) REFERENCES USER_ROLES (ID ASC);

ALTER TABLE `USERS`
ADD CONSTRAINT `FK_USER_ROLE`
FOREIGN KEY (`USER_ROLE_ID`)
REFERENCES `USER_ROLES` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

CREATE TABLE `WARRANTY_PERIODS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `WPNAME` VARCHAR(45) NOT NULL,
  `DAYS` INT NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `ITEM_TYPES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ITEMTYPENAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ITEM_TYPE_NAME_UNIQUE` (`ITEMTYPENAME` ASC));

CREATE TABLE `MANUFACTURES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `MANUFACTURENAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `MANUFACTURES_NAME_UNIQUE` (`MANUFACTURENAME` ASC));

CREATE TABLE `ITEMS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `MANUFACTURE_ID` INT NOT NULL,
  `ITEM_TYPE_ID` INT NOT NULL,
  `DATEOFSALE` DATE NOT NULL,
  `WARRANTY_PERIOD_ID` INT NOT NULL,
  `SERIALNUMBER` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_MANUFATURE_ID_idx` (`MANUFACTURE_ID` ASC),
  INDEX `FK_ITEM_TYPE_ID_idx` (`ITEM_TYPE_ID` ASC),
  INDEX `FK_WARRANTY_PERIOD_idx` (`WARRANTY_PERIOD_ID` ASC),
  CONSTRAINT `FK_MANUFATURE_ID`
  FOREIGN KEY (`MANUFACTURE_ID`)
  REFERENCES `MANUFACTURES` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_ITEM_TYPE_ID`
  FOREIGN KEY (`ITEM_TYPE_ID`)
  REFERENCES `ITEM_TYPES` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_WARRANTY_PERIOD`
  FOREIGN KEY (`WARRANTY_PERIOD_ID`)
  REFERENCES `WARRANTY_PERIODS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `ORDERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL,
  `DATE` DATE NOT NULL,
  `WARRANTY` BOOLEAN NOT NULL,
  `STATUS` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ID`));

ALTER TABLE `ORDERS`
ADD INDEX `FK_ITEM_idx` (`ITEM_ID` ASC);
ALTER TABLE `ORDERS`
ADD CONSTRAINT `FK_ITEM`
FOREIGN KEY (`ITEM_ID`)
REFERENCES `ITEMS` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ORDERS`
ADD INDEX `FK_USER_idx` (`USER_ID` ASC);
ALTER TABLE `ORDERS`
ADD CONSTRAINT `FK_USER`
FOREIGN KEY (`USER_ID`)
REFERENCES `USERS` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ORDERS`
ADD COLUMN `AMOUNT` INT(20) NULL AFTER `STATUS`;

