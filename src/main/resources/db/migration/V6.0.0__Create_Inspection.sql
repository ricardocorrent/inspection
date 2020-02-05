CREATE TABLE IF NOT EXISTS `inspection` (
  `id` BINARY(16) NOT NULL,
  `user_id` BINARY(16) NOT NULL,
  `target_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `draft` boolean NOT NULL,
  `sync_quantities` INT NULL,
  `note` TEXT(4000) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB;
