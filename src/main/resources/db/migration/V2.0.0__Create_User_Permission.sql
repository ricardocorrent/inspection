CREATE TABLE IF NOT EXISTS `user_permission` (
  `user_id` BINARY(16) NOT NULL,
  `permission_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`user_id`, `permission_id`)
)ENGINE = InnoDB;
