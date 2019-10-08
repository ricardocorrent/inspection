CREATE TABLE IF NOT EXISTS `item` (
  `id` BINARY(16) NOT NULL,
  `rule_id` BINARY(16) NOT NULL,
  `code` VARCHAR(50) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `parent_id` BINARY(16) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
