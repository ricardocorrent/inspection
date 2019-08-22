CREATE TABLE IF NOT EXISTS `tag` (
  `id` BINARY(16) NOT NULL,
  `title` VARCHAR(15) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB