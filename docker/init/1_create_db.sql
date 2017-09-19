CREATE DATABASE IF NOT EXISTS `hello`;

DROP TABLE IF EXISTS `hello`.`hello`;

CREATE TABLE `hello`.`hello` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='hello example';

CREATE TABLE `hello`.`init_test` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='hello example';

CREATE TABLE `hello`.`user` (
  `id`         BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45)  NOT NULL,
  `age`        INT          NULL,
  `email`      VARCHAR(200) NULL,
  `created_at` DATETIME     NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='hello example';