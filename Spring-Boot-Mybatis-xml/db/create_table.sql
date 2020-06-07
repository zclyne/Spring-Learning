CREATE DATABASE `spring_boot_mybatis` DEFAULT CHARACTER SET utf8;

USE `spring_boot_mybatis`;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '教师姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `teacher` VALUES (1, 'Adam');
INSERT INTO `teacher` VALUES (2, 'Bob');

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '课程名',
  `teacher_id` bigint(20) DEFAULT NULL COMMENT '授课老师的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `course` VALUES (1, 'Math', 1);
INSERT INTO `course` VALUES (2, 'English', 2);
INSERT INTO `course` VALUES (3, 'Chemistry', 2);