SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `member`;
SET foreign_key_checks = 1;

CREATE TABLE `member` (
	`member_id`	VARCHAR(256)	NOT NULL PRIMARY KEY,
	`member_email` VARCHAR(256) NOT NULL,
    `member_role` VARCHAR(256) NOT NULL,
	`mebmer_pw`	VARCHAR(256)	NOT NULL,
	`member_nickname`	VARCHAR(256)	NOT NULL,
	`member_introduce`	LONGTEXT	NULL
);