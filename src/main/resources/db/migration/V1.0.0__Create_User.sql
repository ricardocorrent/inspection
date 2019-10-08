CREATE TABLE IF NOT EXISTS `user` (
  `id` BINARY(16) NOT NULL,
  `user_name` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `account_non_expired` TINYINT NULL DEFAULT NULL,
  `account_non_locked` TINYINT NULL DEFAULT NULL,
  `credentials_non_expired` TINYINT NULL DEFAULT NULL,
  `enabled` TINYINT NULL DEFAULT NULL,
  `created_at` DATETIME,
  `updated_at` DATETIME,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `permission` (
  `id` BINARY(16) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT 'null',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;