CREATE TABLE IF NOT EXISTS `permission` (
  `id` BINARY(16) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT 'null',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;