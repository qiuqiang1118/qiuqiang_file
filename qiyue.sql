/*
 Navicat Premium Data Transfer

 Source Server         : qqdb
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : qiyue

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 30/10/2021 01:40:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for qy_file
-- ----------------------------
DROP TABLE IF EXISTS `qy_file`;
CREATE TABLE `qy_file`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` double NOT NULL,
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qy_file
-- ----------------------------
INSERT INTO `qy_file` VALUES (1, '2021-10-30 01:36:40.000000', '热门', 'C:\\Users\\qiangqiang\\Desktop', 569, 'jpg', '07d2a391-65bb-46ed-be51-d16213a9bba3');
INSERT INTO `qy_file` VALUES (2, '2021-10-21 01:39:10.000000', '动漫', 'C:\\Users\\qiangqiang\\Desktop', 575, 'jpg', '1e275688-f60a-4f56-9c1d-c2019641a2b7');

SET FOREIGN_KEY_CHECKS = 1;
