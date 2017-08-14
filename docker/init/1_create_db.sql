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