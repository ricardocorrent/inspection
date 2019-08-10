set @r = unhex(replace(uuid(),'-',''));
set @s = unhex(replace(uuid(),'-',''));
INSERT INTO `user`
(`id`, `user_name`, `full_name`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) VALUES
    (concat(substr(@r, 7, 2), substr(@r, 5, 2),substr(@r, 1, 4), substr(@r, 9, 8)), 'ricardo', 'Ricardo Corrent', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', '1', '1', '1', '1'),
    (concat(substr(@s, 7, 2), substr(@s, 5, 2),substr(@s, 1, 4), substr(@s, 9, 8)), 'admin', 'Sys admin', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', '1', '1', '1', '1');

set @a = unhex(replace(uuid(),'-',''));
INSERT INTO `permission` (`id`,`description`) VALUES
(concat(substr(@a, 7, 2), substr(@a, 5, 2),substr(@a, 1, 4), substr(@a, 9, 8)),"ADMIN");

set @o = unhex(replace(uuid(),'-',''));
INSERT INTO `permission` (`id`,`description`) VALUES
(concat(substr(@o, 7, 2), substr(@o, 5, 2),substr(@o, 1, 4), substr(@o, 9, 8)), "MANAGER");

set @u = unhex(replace(uuid(),'-',''));
INSERT INTO `permission` (`id`,`description`) VALUES
(concat(substr(@u, 7, 2), substr(@u, 5, 2),substr(@u, 1, 4), substr(@u, 9, 8)), "COMMONS");

INSERT INTO `user_permission` (`user_id`, `permission_id`) VALUES
	(concat(substr(@r, 7, 2), substr(@r, 5, 2),substr(@r, 1, 4), substr(@r, 9, 8)), concat(substr(@a, 7, 2), substr(@a, 5, 2),substr(@a, 1, 4), substr(@a, 9, 8))),
	(concat(substr(@s, 7, 2), substr(@s, 5, 2),substr(@s, 1, 4), substr(@s, 9, 8)), concat(substr(@a, 7, 2), substr(@a, 5, 2),substr(@a, 1, 4), substr(@a, 9, 8))),
	(concat(substr(@r, 7, 2), substr(@r, 5, 2),substr(@r, 1, 4), substr(@r, 9, 8)), concat(substr(@o, 7, 2), substr(@o, 5, 2),substr(@o, 1, 4), substr(@o, 9, 8)));
