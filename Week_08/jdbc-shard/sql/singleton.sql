/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100236
 Source Host           : localhost:3306
 Source Schema         : singleton

 Target Server Type    : MySQL
 Target Server Version : 100236
 File Encoding         : 65001

 Date: 09/12/2020 21:50:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
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
