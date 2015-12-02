/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-12-02 17:48:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for led_order
-- ----------------------------
DROP TABLE IF EXISTS `led_order`;
CREATE TABLE `led_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `order_number` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单号',
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户名称',
  `order_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下单时间',
  `phone` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `produce_id` bigint(20) DEFAULT NULL COMMENT 'led产品外键',
  `price` double DEFAULT NULL COMMENT '进价',
  `sale` double DEFAULT NULL COMMENT '出售价格',
  `number` int(11) DEFAULT NULL COMMENT '出售数量',
  `order_account` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下单账号',
  `order_user` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下单人',
  `note` text COLLATE utf8mb4_bin COMMENT '描述',
  `remove` bit(1) DEFAULT NULL COMMENT '是否移除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='LED-订单';

-- ----------------------------
-- Records of led_order
-- ----------------------------
INSERT INTO `led_order` VALUES ('1', '2015-11-03 14:51:04', '20159913', '客户1', '2015-11-18 14:49:36', '13980023142', 'bogle.mail@bogle.com', '5', '7', '55', '50', '1', 'bogle', 0x31, '\0');
INSERT INTO `led_order` VALUES ('2', '2015-11-17 13:45:55', '20159117', '客户2', '2015-11-17 13:46:14', '13980023212', 'zhaoddd.mail@bogle.com', '6', '8', '66', '100', '1', 'bogle', 0x31, '\0');
INSERT INTO `led_order` VALUES ('3', '2015-11-17 20:36:42', '20159117', '客户2', '2015-11-17 13:46:14', '13980023212', 'zhaoddd.mail@bogle.com', '6', '8', '66', '100', null, null, null, '\0');
INSERT INTO `led_order` VALUES ('4', '2015-11-17 20:38:37', '!21212125911711', '客户2', '2015-11-17 13:46:14', '13980023212', 'zhaoddd.mail@bogle.com', '6', '8', '66', '100', null, null, null, '\0');
INSERT INTO `led_order` VALUES ('5', '2015-11-18 14:50:04', '32232323', '2323', '2015-11-05 09:25:48', '2323', '', '8', '10', '33', '23', null, null, null, '\0');
INSERT INTO `led_order` VALUES ('6', '2015-11-18 14:50:55', 'asdsadf', '322', '2015-11-12 06:25:27', '', '', '6', '8', '10', '10', null, null, null, '\0');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `producer` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生产商',
  `contact` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `typ` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '型号',
  `colour_temperature` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '色温',
  `bri` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '亮度',
  `price` double DEFAULT NULL COMMENT '价格',
  `note` text COLLATE utf8mb4_bin COMMENT '描述',
  `remove` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品：LED-产品';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2', 'producer-2', 'contact-2', '456@com2', 'phone-2', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-22', 'colour-2', 'bri-2', '4', 0x6E6F74652D32, '\0');
INSERT INTO `product` VALUES ('3', 'producer-3', 'contact-3', '456@com3', 'phone-3', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-23', 'colour-3', 'bri-3', '5', 0x6E6F74652D33, '\0');
INSERT INTO `product` VALUES ('4', 'producer-4', 'contact-4', '456@com4', 'phone-4', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-24', 'colour-4', 'bri-4', '6', 0x6E6F74652D34, '\0');
INSERT INTO `product` VALUES ('5', 'producer-5', 'contact-5', '456@com5', 'phone-5', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-25', 'colour-5', 'bri-5', '7', 0x6E6F74652D35, '\0');
INSERT INTO `product` VALUES ('6', 'producer-6', 'contact-6', '456@com6', 'phone-6', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-26', 'colour-6', 'bri-6', '8', 0x6E6F74652D36, '\0');
INSERT INTO `product` VALUES ('7', 'producer-7', 'contact-7', '456@com7', 'phone-7', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-27', 'colour-7', 'bri-7', '9', 0x6E6F74652D37, '\0');
INSERT INTO `product` VALUES ('8', 'producer-8', 'contact-8', '456@com8', 'phone-8', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-28', 'colour-8', 'bri-8', '10', 0x6E6F74652D38, '\0');
INSERT INTO `product` VALUES ('9', 'producer-9', 'contact-9', '456@com9', 'phone-9', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-29', 'colour-9', 'bri-9', '11', 0x6E6F74652D39, '\0');
INSERT INTO `product` VALUES ('10', 'producer-10', 'contact-10', '456@com10', 'phone-10', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-210', 'colour-10', 'bri-10', '12', 0x6E6F74652D3130, '\0');
INSERT INTO `product` VALUES ('11', 'producer-11', 'contact-11', '456@com11', 'phone-11', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-211', 'colour-11', 'bri-11', '13', 0x6E6F74652D3131, '\0');
INSERT INTO `product` VALUES ('12', 'producer-12', 'contact-12', '456@com12', 'phone-12', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-212', 'colour-12', 'bri-12', '14', 0x6E6F74652D3132, '\0');
INSERT INTO `product` VALUES ('13', 'producer-13', 'contact-13', '456@com13', 'phone-13', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-213', 'colour-13', 'bri-13', '15', 0x6E6F74652D3133, '\0');
INSERT INTO `product` VALUES ('14', 'producer-14', 'contact-14', '456@com14', 'phone-14', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-214', 'colour-14', 'bri-14', '16', 0x6E6F74652D3134, '\0');
INSERT INTO `product` VALUES ('15', 'producer-15', 'contact-15', '456@com15', 'phone-15', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-215', 'colour-15', 'bri-15', '17', 0x6E6F74652D3135, '\0');
INSERT INTO `product` VALUES ('16', 'producer-16', 'contact-16', '456@com16', 'phone-16', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-216', 'colour-16', 'bri-16', '18', 0x6E6F74652D3136, '\0');
INSERT INTO `product` VALUES ('17', 'producer-17', 'contact-17', '456@com17', 'phone-17', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-217', 'colour-17', 'bri-17', '19', 0x6E6F74652D3137, '\0');
INSERT INTO `product` VALUES ('18', 'producer-18', 'contact-18', '456@com18', 'phone-18', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-218', 'colour-18', 'bri-18', '20', 0x6E6F74652D3138, '\0');
INSERT INTO `product` VALUES ('19', 'producer-19', 'contact-19', '456@com19', 'phone-19', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-219', 'colour-19', 'bri-19', '21', 0x6E6F74652D3139, '\0');
INSERT INTO `product` VALUES ('20', 'producer-20', 'contact-20', '456@com20', 'phone-20', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-220', 'colour-20', 'bri-20', '22', 0x6E6F74652D3230, '\0');
INSERT INTO `product` VALUES ('21', 'producer-21', 'contact-21', '456@com21', 'phone-21', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-221', 'colour-21', 'bri-21', '23', 0x6E6F74652D3231, '\0');
INSERT INTO `product` VALUES ('22', 'producer-22', 'contact-22', '456@com22', 'phone-22', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-222', 'colour-22', 'bri-22', '24', 0x6E6F74652D3232, '\0');
INSERT INTO `product` VALUES ('23', 'producer-23', 'contact-23', '456@com23', 'phone-23', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-223', 'colour-23', 'bri-23', '25', 0x6E6F74652D3233, '\0');
INSERT INTO `product` VALUES ('24', 'producer-24', 'contact-24', '456@com24', 'phone-24', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-224', 'colour-24', 'bri-24', '26', 0x6E6F74652D3234, '\0');
INSERT INTO `product` VALUES ('25', 'producer-25', 'contact-25', '456@com25', 'phone-25', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-225', 'colour-25', 'bri-25', '27', 0x6E6F74652D3235, '\0');
INSERT INTO `product` VALUES ('26', 'producer-26', 'contact-26', '456@com26', 'phone-26', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-226', 'colour-26', 'bri-26', '28', 0x6E6F74652D3236, '\0');
INSERT INTO `product` VALUES ('27', 'producer-27', 'contact-27', null, 'phone-27', '2015-10-19 15:33:21', '2015-10-19 15:33:21', 'typ-226', 'colour-27', 'bri-27', '36', 0x6E6F74652D3237, '\0');
INSERT INTO `product` VALUES ('28', 'producer-28', 'contact-28', '456@com28', 'phone-28', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-228', 'colour-28', 'bri-28', '30', 0x6E6F74652D3238, '\0');
INSERT INTO `product` VALUES ('29', 'producer-29', 'contact-29', '456@com29', 'phone-29', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-229', 'colour-29', 'bri-29', '31', 0x6E6F74652D3239, '\0');
INSERT INTO `product` VALUES ('30', 'producer-30', 'contact-30', '456@com30', 'phone-30', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-230', 'colour-30', 'bri-30', '32', 0x6E6F74652D3330, '\0');
INSERT INTO `product` VALUES ('31', 'producer-31', 'contact-31', '456@com31', 'phone-31', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-231', 'colour-31', 'bri-31', '33', 0x6E6F74652D3331, '\0');
INSERT INTO `product` VALUES ('32', 'producer-32', 'contact-32', '456@com32', 'phone-32', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-232', 'colour-32', 'bri-32', '34', 0x6E6F74652D3332, '\0');
INSERT INTO `product` VALUES ('33', 'producer-33', 'contact-33', '456@com33', 'phone-33', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-233', 'colour-33', 'bri-33', '35', 0x6E6F74652D3333, '\0');
INSERT INTO `product` VALUES ('34', 'producer-34', 'contact-34', '456@com34', 'phone-34', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-234', 'colour-34', 'bri-34', '36', 0x6E6F74652D3334, '\0');
INSERT INTO `product` VALUES ('35', 'producer-35', 'contact-35', '456@com35', 'phone-35', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-235', 'colour-35', 'bri-35', '37', 0x6E6F74652D3335, '\0');
INSERT INTO `product` VALUES ('36', 'producer-36', 'contact-36', '456@com36', 'phone-36', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-236', 'colour-36', 'bri-36', '38', 0x6E6F74652D3336, '\0');
INSERT INTO `product` VALUES ('37', 'producer-37', 'contact-37', '456@com37', 'phone-37', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-237', 'colour-37', 'bri-37', '39', 0x6E6F74652D3337, '\0');
INSERT INTO `product` VALUES ('38', 'producer-38', 'contact-38', '456@com38', 'phone-38', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-238', 'colour-38', 'bri-38', '40', 0x6E6F74652D3338, '\0');
INSERT INTO `product` VALUES ('39', 'producer-39', 'contact-39', '456@com39', 'phone-39', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-239', 'colour-39', 'bri-39', '41', 0x6E6F74652D3339, '\0');
INSERT INTO `product` VALUES ('40', 'producer-40', 'contact-40', '456@com40', 'phone-40', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-240', 'colour-40', 'bri-40', '42', 0x6E6F74652D3430, '\0');
INSERT INTO `product` VALUES ('41', 'producer-41', 'contact-41', '456@com41', 'phone-41', '2015-10-09 15:55:59', '2015-10-15 15:56:01', 'typ-241', 'colour-41', 'bri-41', '43', 0x6E6F74652D3431, '\0');
INSERT INTO `product` VALUES ('42', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:52:29', '2015-10-19 19:52:29', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('43', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:52:40', '2015-10-19 19:52:40', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('44', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:52:45', '2015-10-19 19:52:45', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('45', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:52:56', '2015-10-19 19:52:56', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('46', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:53:04', '2015-10-19 19:53:04', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('47', 'aaa', 'aaaaaa', '', '', '2015-10-19 19:53:16', '2015-10-19 19:53:16', 'werwe', 'wewer', 'werwe', '23', null, '\0');
INSERT INTO `product` VALUES ('48', '1', '1', '', '', '2015-10-20 09:40:31', '2015-10-20 09:40:31', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('49', '1', '1', '', '', '2015-10-20 09:40:54', '2015-10-20 09:40:54', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('50', '1', '1', '', '', '2015-10-20 09:41:43', '2015-10-20 09:41:43', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('51', '1', '1', '', '', '2015-10-20 09:43:31', '2015-10-20 09:43:31', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('52', '1', '1', '', '', '2015-10-20 09:44:17', '2015-10-20 09:44:17', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('53', '1', '1', '', '', '2015-10-20 09:45:18', '2015-10-20 09:45:18', '11', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('54', '1', '1', '', '', '2015-10-20 09:45:56', '2015-10-20 09:45:56', '11', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('55', '2', '2', '', '', '2015-10-20 10:01:36', '2015-10-20 10:01:36', '2', '2', '232', '2', null, '\0');
INSERT INTO `product` VALUES ('56', '1', '2', '', '', '2015-10-20 10:02:40', '2015-10-20 10:02:40', '3', '4', '5', '6', null, '\0');
INSERT INTO `product` VALUES ('57', '1', '2', '', '', '2015-10-20 10:02:45', '2015-10-20 10:02:45', '3', '4', '5', '6', null, '\0');
INSERT INTO `product` VALUES ('58', '1', '2', '', '', '2015-10-20 10:03:00', '2015-10-20 10:03:00', '3', '4', '5', '6', null, '\0');
INSERT INTO `product` VALUES ('59', '1', '2', '', '', '2015-10-20 10:03:31', '2015-10-20 10:03:31', '11', '1', '1', '2', null, '\0');
INSERT INTO `product` VALUES ('60', '1', '2', '', '', '2015-10-21 16:50:11', '2015-10-21 16:50:11', '1', '2', '2', '3', null, '\0');
INSERT INTO `product` VALUES ('61', '3', '3', '', '', '2015-10-21 16:50:24', '2015-10-21 16:50:24', '3', '4', '5', '2', null, '\0');
INSERT INTO `product` VALUES ('62', '2', '3', '', '', '2015-10-21 16:50:32', '2015-10-21 16:50:32', '4', '5', '6', '7', null, '\0');
INSERT INTO `product` VALUES ('100', 'producer-1', 'contact-1', '123@com1', 'phone-1', '2015-10-12 15:55:27', '2015-10-12 15:55:29', 'typ-11', 'colour-1', 'bri-1', '2', 0x6E6F74652D31, '\0');
INSERT INTO `product` VALUES ('101', '12', '1', '', '', '2015-10-29 09:56:57', '2015-10-29 09:56:57', '1', '1', '1', '1', null, '\0');
INSERT INTO `product` VALUES ('102', '1', '1', '', '', '2015-10-29 10:08:33', '2015-10-29 10:08:33', '1', '1', '1', '1', null, '');
INSERT INTO `product` VALUES ('103', '1', '1', '', '', '2015-10-29 10:08:44', '2015-10-29 10:08:44', '1', '1', '1', '1', null, '');
INSERT INTO `product` VALUES ('104', '1', '1', '', '', '2015-10-29 10:10:21', '2015-10-29 10:10:21', '1', '1', '1', '1', null, '');
INSERT INTO `product` VALUES ('105', '1', '1', '', '', '2015-10-29 10:12:47', '2015-10-29 10:12:47', '1', '1', '1', '1', null, '');
INSERT INTO `product` VALUES ('106', '1111', '111', '', '', '2015-10-29 10:18:13', '2015-10-29 10:18:13', '111', '111', '111', '1', null, '');
INSERT INTO `product` VALUES ('107', '111', '11', '111@qq.com', '11', '2015-10-29 15:49:36', '2015-10-29 15:49:36', '11', '11', '11', '11', null, '');
INSERT INTO `product` VALUES ('108', '222', '11', '', '11', '2015-11-02 15:04:48', '2015-11-02 15:04:48', '11', '11', '11', '111', null, '');
INSERT INTO `product` VALUES ('109', '11', '11', '', '11', '2015-11-16 20:47:50', '2015-11-16 20:47:50', '11', '11', '11', '11', null, '');
