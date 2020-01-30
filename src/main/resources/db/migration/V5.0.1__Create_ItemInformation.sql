CREATE TABLE IF NOT EXISTS `item_information` (
  `id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `item_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`, `item_id`))
ENGINE = InnoDB;
