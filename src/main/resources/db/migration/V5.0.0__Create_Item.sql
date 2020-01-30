CREATE TABLE IF NOT EXISTS `item` (
  `id` BINARY(16) NOT NULL,
  `rule_id` BINARY(16) NOT NULL,
  `code` VARCHAR(100) NOT NULL,
  `description` VARCHAR(250) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`, `rule_id`)
) ENGINE = InnoDB;
