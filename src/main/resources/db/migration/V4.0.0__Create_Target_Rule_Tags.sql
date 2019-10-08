CREATE TABLE IF NOT EXISTS `target_tag` (
  `target_id` BINARY(16) NOT NULL,
  `tag_id` BINARY(16) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`target_id`, `tag_id`)
  )ENGINE = InnoDB;

CREATE INDEX `fk_target_has_tag_tag_idx` ON `target_tag`(`tag_id`);
CREATE INDEX `fk_target_has_tag_target_idx` ON `target_tag`(`target_id`);

CREATE TABLE IF NOT EXISTS `rule_tag` (
  `rule_id` BINARY(16) NOT NULL,
  `tag_id` BINARY(16) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` VARCHAR(45) NULL,
  PRIMARY KEY (`rule_id`, `tag_id`)
)ENGINE = InnoDB;

CREATE INDEX `fk_rule_has_tag_tag_idx` ON `rule_tag`(`tag_id`);
CREATE INDEX `fk_rule_has_tag_rule_idx` ON `rule_tag`(`rule_id`);
