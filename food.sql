/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : food

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 08/05/2022 21:06:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 'lky1', '12345612345', '130105', '河北省石家庄市新华区文三路 138 号东方通信大厦 7 楼 501 室', '河北省', '石家庄市', '新华区', 1);
INSERT INTO `address` VALUES (2, 'lky2', '12312312312', '110101', '北京市东城区666号', '北京市', '北京市', '东城区', 1);
INSERT INTO `address` VALUES (3, 'lky3', '12312313212', '110101', '北京市东城区东城大道666号', '北京市', '北京市', '东城区', 1);
INSERT INTO `address` VALUES (4, 'lky4', '12312313212', '140214', '山西省大同市云冈区云岗大道666号', '山西省', '大同市', '云冈区', 1);
INSERT INTO `address` VALUES (5, 'lky5', '12312313212', '110101', '北京市东城区123', '北京市', '北京市', '东城区', 1);
INSERT INTO `address` VALUES (6, 'lky6', '12312312312', '110102', '北京市西城区阳光大道666号', '北京市', '北京市', '西城区', 1);
INSERT INTO `address` VALUES (7, 'lky666', '12312312312', '140105', '山西省小店区幸福大道666号', '山西省', '太原市', '小店区', 1);
INSERT INTO `address` VALUES (8, 'lky777', '12312312312', '110101', '北京市东城区快乐大道777号', '北京市', '北京市', '东城区', 1);
INSERT INTO `address` VALUES (9, 'lky333', '12312312312', '130102', '河北省长安区阳光大道333号', '河北省', '石家庄市', '长安区', 1);
INSERT INTO `address` VALUES (10, 'lky111', '12312313212', '120102', '天津市河东区阳关大道111号', '天津市', '天津市', '河东区', 1);
INSERT INTO `address` VALUES (11, 'lky000', '12312313212', '110101', '北京市东城区阳关大道000号', '北京市', '北京市', '东城区', 1);
INSERT INTO `address` VALUES (12, '小黄鸭', '15179542036', '360902', '江西省袁州区幸福街666号', '江西省', '宜春市', '袁州区', 6);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_id` int(11) NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE,
  UNIQUE INDEX `categoryName`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (24, 'salad', 1);
INSERT INTO `category` VALUES (25, 'noodle', 1);
INSERT INTO `category` VALUES (26, 'pizza', 1);
INSERT INTO `category` VALUES (27, 'red wine', 1);
INSERT INTO `category` VALUES (28, 'cake', 1);
INSERT INTO `category` VALUES (29, 'steak', 1);

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_num` int(11) NOT NULL DEFAULT 0,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stat` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`d_id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (1, 'lky6', '15179510163', 6, '123123123', 1);

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `good_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_id` int(255) NOT NULL,
  `sales` int(11) NOT NULL DEFAULT 0,
  `s_id` int(11) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  UNIQUE INDEX `goodName`(`good_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (63, 'red wine', 'img/1651979350860.jpg', '520', 27, 0, 1, 'delicious');
INSERT INTO `good` VALUES (64, 'steak', 'img/1651980548384.jpg', '600', 29, 0, 1, 'delicious');
INSERT INTO `good` VALUES (65, 'salad', 'img/1651980857709.jpg', '200', 24, 0, 1, 'delicious');
INSERT INTO `good` VALUES (66, 'pasta', 'img/1651980961481.jpg', '300', 25, 0, 1, 'delicious');
INSERT INTO `good` VALUES (67, 'cake', 'img/1651981033458.jpg', '230', 28, 0, 1, 'delicious');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_Number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_price` decimal(11, 2) NOT NULL,
  `u_id` int(11) NOT NULL,
  `s_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下单时间',
  `order_Stat` int(255) NOT NULL DEFAULT 1,
  `good_total_price` decimal(10, 2) NOT NULL,
  `delivery_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_id` int(11) NULL DEFAULT NULL,
  `order_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接单时间',
  `pick_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取货时间',
  `arrive_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送达时间',
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (34, 'DM202205081158062590001', 300.00, 1, 1, 0, '2022-05-08 11:58:06', 2, 300.00, '0', NULL, '2022-05-08 12:00:48', NULL, NULL);
INSERT INTO `order` VALUES (35, 'DM202205081355194940001', 400.00, 1, 1, 0, '2022-05-08 13:55:19', 2, 400.00, '0', NULL, '2022-05-08 14:12:18', NULL, NULL);
INSERT INTO `order` VALUES (36, 'DM202205081410506780001', 700.00, 1, 1, 0, '2022-05-08 14:10:50', 1, 700.00, '0', NULL, NULL, NULL, NULL);
INSERT INTO `order` VALUES (37, 'DM202205081414146530001', 500.00, 1, 1, 0, '2022-05-08 14:14:14', 1, 500.00, '0', NULL, NULL, NULL, NULL);
INSERT INTO `order` VALUES (38, 'DM202205081418020660001', 500.00, 1, 1, 0, '2022-05-08 14:18:02', 1, 500.00, '0', NULL, NULL, NULL, NULL);
INSERT INTO `order` VALUES (39, 'DM202205081419364530001', 500.00, 1, 1, 0, '2022-05-08 14:19:36', 1, 500.00, '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_good
-- ----------------------------
DROP TABLE IF EXISTS `order_good`;
CREATE TABLE `order_good`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` int(11) NOT NULL,
  `g_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_good
-- ----------------------------
INSERT INTO `order_good` VALUES (71, 34, 66, 1);
INSERT INTO `order_good` VALUES (72, 35, 65, 2);
INSERT INTO `order_good` VALUES (73, 36, 65, 2);
INSERT INTO `order_good` VALUES (74, 36, 66, 1);
INSERT INTO `order_good` VALUES (75, 37, 65, 1);
INSERT INTO `order_good` VALUES (76, 37, 66, 1);
INSERT INTO `order_good` VALUES (77, 38, 65, 1);
INSERT INTO `order_good` VALUES (78, 38, 66, 1);
INSERT INTO `order_good` VALUES (79, 39, 65, 1);
INSERT INTO `order_good` VALUES (80, 39, 66, 1);

-- ----------------------------
-- Table structure for rider_order
-- ----------------------------
DROP TABLE IF EXISTS `rider_order`;
CREATE TABLE `rider_order`  (
  `r_o_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `o_id` int(11) NOT NULL,
  PRIMARY KEY (`r_o_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rider_order
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'user', 'user');
INSERT INTO `role` VALUES (2, 'shop_owner', 'owner');
INSERT INTO `role` VALUES (3, 'admin', 'admin');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `logo_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_id` int(11) NOT NULL,
  `license` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stat` int(11) NOT NULL DEFAULT 0,
  `delivery_cost` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `min_cost` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_sales` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `delivery_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE,
  UNIQUE INDEX `shopName`(`shop_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, 'img/1651979207510.jpg', 'Restaurant Ordering System', 4, '88688', 1, '0', '20', '3', '12312345612', 'France', '40', 'delicious');

-- ----------------------------
-- Table structure for shop_manager
-- ----------------------------
DROP TABLE IF EXISTS `shop_manager`;
CREATE TABLE `shop_manager`  (
  `s_m_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identity_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stat` int(255) NOT NULL DEFAULT 1,
  `r_id` int(11) NOT NULL DEFAULT 2,
  PRIMARY KEY (`s_m_id`) USING BTREE,
  UNIQUE INDEX `username`(`user_name`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `identity_number`(`identity_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_manager
-- ----------------------------
INSERT INTO `shop_manager` VALUES (1, 'boss', '123', '12312312313', '362202200002144052', 'lky', 1, 2);
INSERT INTO `shop_manager` VALUES (2, 'boss1', '123', '15428754951', '362154215779845126', 'boss', 1, 2);
INSERT INTO `shop_manager` VALUES (3, 'lky', '123', '12545632514', '362201254123652200', 'lky', 1, 2);
INSERT INTO `shop_manager` VALUES (4, 'lky1', '123', '15421365213', '362154214779845126', 'lky1', 1, 2);
INSERT INTO `shop_manager` VALUES (5, 'bob', '123', '15179548562', '362201254201254900', 'bob', 1, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `r_id` int(11) NOT NULL DEFAULT 1,
  `picSrc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NOT NULL DEFAULT 0,
  `stat` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lky', '16638208963', 'lky', '123', 1, 'img/1651989724508.jpg', 2, 1);
INSERT INTO `user` VALUES (3, 'admin', '18638614155', 'admin', '123', 3, NULL, 0, 1);
INSERT INTO `user` VALUES (5, 'lky1', '15179845215', 'lky1', '123', 1, 'img/1651904250846.png', 0, 1);
INSERT INTO `user` VALUES (6, 'xiaohuangya', '15176458295', '小黄鸭超爱吃', '123', 1, '/upload/小黄鸭_20210909221632.png', 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
