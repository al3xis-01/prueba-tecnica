/*
 Navicat Premium Data Transfer

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:4306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 30/12/2023 11:14:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `id_status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `id_status`) VALUES (1, 'Admin', 1);
INSERT INTO `role` (`id`, `name`, `id_status`) VALUES (2, 'Empleado', 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_user_status` int NOT NULL DEFAULT '1',
  `id_user_role` int unsigned NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk2` (`email`),
  KEY `user_role_id_fk` (`id_user_role`),
  CONSTRAINT `user_role_id_fk` FOREIGN KEY (`id_user_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `full_name`, `email`, `password`, `id_user_status`, `id_user_role`, `username`) VALUES (1, 'Administrador', 'admin@servifacil.com.mx', '$2y$10$/CcF9wcKzvWVMu5C/BlfMe4lHgAQakuxRJemaTCgDd7JYFXWbOJhW', 1, 1, 'admin');
INSERT INTO `user` (`id`, `full_name`, `email`, `password`, `id_user_status`, `id_user_role`, `username`) VALUES (2, 'Empleado 1', 'empleado1@servifacil.com.mx', '$2y$10$1IRwBeKMCODcL/ZEvEYqZ..Y8UJoYx.xCc9jpIxBfWYVHX60xjlUm', 1, 2, 'empleado1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
