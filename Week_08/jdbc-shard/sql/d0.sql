/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100236
 Source Host           : localhost:3306
 Source Schema         : d0

 Target Server Type    : MySQL
 Target Server Version : 100236
 File Encoding         : 65001

 Date: 09/12/2020 21:26:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_0
-- ----------------------------
DROP TABLE IF EXISTS `order_0`;
CREATE TABLE `order_0` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_1
-- ----------------------------
DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_10
-- ----------------------------
DROP TABLE IF EXISTS `order_10`;
CREATE TABLE `order_10` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_11
-- ----------------------------
DROP TABLE IF EXISTS `order_11`;
CREATE TABLE `order_11` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_12
-- ----------------------------
DROP TABLE IF EXISTS `order_12`;
CREATE TABLE `order_12` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_13
-- ----------------------------
DROP TABLE IF EXISTS `order_13`;
CREATE TABLE `order_13` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_14
-- ----------------------------
DROP TABLE IF EXISTS `order_14`;
CREATE TABLE `order_14` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_15
-- ----------------------------
DROP TABLE IF EXISTS `order_15`;
CREATE TABLE `order_15` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_2
-- ----------------------------
DROP TABLE IF EXISTS `order_2`;
CREATE TABLE `order_2` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_3
-- ----------------------------
DROP TABLE IF EXISTS `order_3`;
CREATE TABLE `order_3` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_4
-- ----------------------------
DROP TABLE IF EXISTS `order_4`;
CREATE TABLE `order_4` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_5
-- ----------------------------
DROP TABLE IF EXISTS `order_5`;
CREATE TABLE `order_5` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_6
-- ----------------------------
DROP TABLE IF EXISTS `order_6`;
CREATE TABLE `order_6` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_7
-- ----------------------------
DROP TABLE IF EXISTS `order_7`;
CREATE TABLE `order_7` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_8
-- ----------------------------
DROP TABLE IF EXISTS `order_8`;
CREATE TABLE `order_8` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_9
-- ----------------------------
DROP TABLE IF EXISTS `order_9`;
CREATE TABLE `order_9` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` bigint(20) NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` bigint(20) DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

SET FOREIGN_KEY_CHECKS = 1;
