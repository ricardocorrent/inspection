CREATE TABLE IF NOT EXISTS `inspection_information` (
  `id` BINARY(16) NOT NULL,
  `inspection_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`, `inspection_id`)
) ENGINE = InnoDB;