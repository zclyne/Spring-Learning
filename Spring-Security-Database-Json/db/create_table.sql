CREATE DATABASE `spring_security` DEFAULT CHARACTER SET utf8;

USE `spring_security`;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'Adam', 'beijing', 'adam','$2a$10$L7t7wb0BZpOjVjKve8b7Y.v2S6WxOvG0xeWW2LgeBiM676t9aqzxW', 'ROLE_USER');
INSERT INTO `user` VALUES ('2', 'SuperMan', 'shanghang', 'super','$2a$10$L7t7wb0BZpOjVjKve8b7Y.v2S6WxOvG0xeWW2LgeBiM676t9aqzxW', 'ROLE_USER,ROLE_ADMIN');
COMMIT;