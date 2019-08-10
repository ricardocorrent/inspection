CREATE TABLE IF NOT EXISTS `user_permission` (
  `user_id` BINARY(16) NOT NULL,
  `permission_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`user_id`, `permission_id`)
--  INDEX `fk_users_has_permission_permission1_idx` (`permission_id` ASC) VISIBLE,
--  INDEX `fk_users_has_permission_users1_idx` (`user_id` ASC) VISIBLE,
--  CONSTRAINT `fk_users_has_permission_users1`
--    FOREIGN KEY (`user_id`)
--    REFERENCES `user` (`id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
--  CONSTRAINT `fk_users_has_permission_permission1`
--    FOREIGN KEY (`permission_id`)
--    REFERENCES `permission` (`id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
)
ENGINE = InnoDB;