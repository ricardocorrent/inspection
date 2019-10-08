CREATE TABLE IF NOT EXISTS `tag` (
  `id` BINARY(16) NOT NULL,
  `title` VARCHAR(15) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `target` (
  `id` BINARY(16) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `rule` (
  `id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

