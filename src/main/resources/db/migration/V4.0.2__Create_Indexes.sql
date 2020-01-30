CREATE INDEX `fk_target_has_tag_tag_idx` ON `target_tag`(`tag_id`);
CREATE INDEX `fk_target_has_tag_target_idx` ON `target_tag`(`target_id`);

CREATE INDEX `fk_rule_has_tag_tag_idx` ON `rule_tag`(`tag_id`);
CREATE INDEX `fk_rule_has_tag_rule_idx` ON `rule_tag`(`rule_id`);
