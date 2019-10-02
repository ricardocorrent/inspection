CREATE TABLE IF NOT EXISTS `user_information` (
  `id` BINARY(16) NOT NULL,
  `user_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `target_information` (
  `id` BINARY(16) NOT NULL,
  `target_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`)
 ) ENGINE = InnoDB;
