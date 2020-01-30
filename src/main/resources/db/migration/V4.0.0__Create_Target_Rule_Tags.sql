CREATE TABLE IF NOT EXISTS `target_tag` (
  `target_id` BINARY(16) NOT NULL,
  `tag_id` BINARY(16) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`target_id`, `tag_id`)
  )ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `rule_tag` (
  `rule_id` BINARY(16) NOT NULL,
  `tag_id` BINARY(16) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` VARCHAR(45) NULL,
  PRIMARY KEY (`rule_id`, `tag_id`)
)ENGINE = InnoDB;
