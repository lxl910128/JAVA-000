/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100236
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 100236
 File Encoding         : 65001

 Date: 25/11/2020 22:35:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字 分号分割',
  `type_id` varchar(64) NOT NULL COMMENT '商品分类ID,定长类ip点分表示',
  `stock` int(255) NOT NULL DEFAULT 0 COMMENT '库存',
  `store_id` bigint(20) NOT NULL COMMENT '商品所属店铺',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '商品枚举状态',
  `price` decimal(10,2) NOT NULL COMMENT '价格 2位小数 10位整数',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `storeIdIdx` (`store_id`) USING BTREE,
  KEY `typeIdx` (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` varchar(64) NOT NULL COMMENT '类型id ip点分法表示',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级ID',
  `parent` blob NOT NULL COMMENT '是否为根标签',
  `name` varchar(64) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`),
  KEY `parentIdx` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(0) DEFAULT NULL COMMENT '收货手机号',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '订单状态 枚举',
  `remarks` text DEFAULT NULL COMMENT '备注信息',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  PRIMARY KEY (`id`),
  KEY `userIdx` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` varchar(64) NOT NULL COMMENT '订单id',
  `goods_name` varchar(64) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL COMMENT '价格 2位小数 10位整数',
  `store_id` bigint(20) NOT NULL COMMENT '商品所属店铺',
  `type_id` varchar(64) NOT NULL COMMENT '商品分类ID,定长类ip点分表示',
  PRIMARY KEY (`id`),
  KEY `orderIdx` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `store_account` varchar(64) NOT NULL COMMENT '店铺账户',
  `name` varchar(64) NOT NULL COMMENT '店铺名称',
  `keyword` varchar(255) DEFAULT NULL COMMENT '店铺关键字',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '枚举店铺状态',
  `remarks` varchar(255) DEFAULT NULL COMMENT '各种信息',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

-- ----------------------------
-- Table structure for store_goods
-- ----------------------------
DROP TABLE IF EXISTS `store_goods`;
CREATE TABLE `store_goods` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `store_id` varchar(64) NOT NULL COMMENT '店铺ID',
  `goods_id` varchar(64) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`),
  KEY `storeIdx` (`store_id`) USING BTREE,
  KEY `goodsIdx` (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺商品关联表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '账号状态，枚举值',
  `type` int(11) NOT NULL DEFAULT 1 COMMENT '用户分类，枚举值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_login` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `address` text DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(32) NOT NULL COMMENT '手机号',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `accountIdx` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
