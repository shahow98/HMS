/*
Navicat MySQL Data Transfer

Source Server         : kzh
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : hms

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-12-25 09:18:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `addr_id` varchar(36) NOT NULL,
  `householder_id` varchar(36) DEFAULT NULL,
  `collective_id` varchar(36) DEFAULT NULL,
  `province_name` varchar(36) NOT NULL,
  `city_name` varchar(36) NOT NULL,
  `district_name` varchar(36) NOT NULL,
  `town_name` varchar(36) DEFAULT NULL,
  `village_name` varchar(36) DEFAULT NULL,
  `expand` varchar(36) DEFAULT NULL,
  `immigratory_date` date NOT NULL,
  `evacuation_date` date DEFAULT NULL,
  PRIMARY KEY (`addr_id`),
  KEY `fk_householderid` (`householder_id`),
  KEY `collective_id` (`collective_id`),
  CONSTRAINT `fk_householderid` FOREIGN KEY (`householder_id`) REFERENCES `t_household` (`householder_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_address_ibfk_1` FOREIGN KEY (`collective_id`) REFERENCES `t_collective` (`collective_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_collective
-- ----------------------------
DROP TABLE IF EXISTS `t_collective`;
CREATE TABLE `t_collective` (
  `collective_id` varchar(36) NOT NULL,
  `collective_no` varchar(18) DEFAULT NULL,
  `collective_name` varchar(10) NOT NULL,
  PRIMARY KEY (`collective_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_household
-- ----------------------------
DROP TABLE IF EXISTS `t_household`;
CREATE TABLE `t_household` (
  `householder_id` varchar(36) NOT NULL,
  `householder_no` varchar(18) DEFAULT NULL,
  `personal_id` varchar(18) NOT NULL,
  PRIMARY KEY (`householder_id`),
  KEY `fk_householder` (`personal_id`),
  CONSTRAINT `t_household_ib3` FOREIGN KEY (`personal_id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `log_id` varchar(36) NOT NULL,
  `date` datetime NOT NULL,
  `account` varchar(18) DEFAULT NULL,
  `operation` text NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_parent
-- ----------------------------
DROP TABLE IF EXISTS `t_parent`;
CREATE TABLE `t_parent` (
  `self_id` varchar(18) NOT NULL,
  `father_id` varchar(18) NOT NULL,
  `mother_id` varchar(18) NOT NULL,
  `spouse_id` varchar(18) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`self_id`),
  KEY `fk_fatherid` (`father_id`),
  KEY `fk_motherid` (`mother_id`),
  KEY `t_parent_ibfk_4` (`spouse_id`),
  CONSTRAINT `t_parent_ibfk_1` FOREIGN KEY (`father_id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_parent_ibfk_2` FOREIGN KEY (`mother_id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_parent_ibfk_3` FOREIGN KEY (`self_id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_parent_ibfk_4` FOREIGN KEY (`spouse_id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_resident
-- ----------------------------
DROP TABLE IF EXISTS `t_resident`;
CREATE TABLE `t_resident` (
  `name` varchar(6) NOT NULL,
  `id` varchar(18) NOT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '1',
  `age` tinyint(4) NOT NULL DEFAULT '1',
  `birthday` date NOT NULL,
  `registry_date` date NOT NULL,
  `collective_id` varchar(36) DEFAULT NULL,
  `householder_id` varchar(36) DEFAULT NULL,
  `former_name` varchar(6) DEFAULT NULL,
  `religion` varchar(10) DEFAULT NULL,
  `national` varchar(3) DEFAULT NULL,
  `native_place` varchar(20) DEFAULT NULL,
  `birthplace` varchar(20) DEFAULT NULL,
  `stature` float DEFAULT '0',
  `education` varchar(10) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_collective` (`collective_id`),
  KEY `fk_householder` (`householder_id`) USING BTREE,
  CONSTRAINT `t_resident_ibfk_1` FOREIGN KEY (`collective_id`) REFERENCES `t_collective` (`collective_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `t_resident_ibfk_2` FOREIGN KEY (`householder_id`) REFERENCES `t_household` (`householder_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_syssetting
-- ----------------------------
DROP TABLE IF EXISTS `t_syssetting`;
CREATE TABLE `t_syssetting` (
  `id` varchar(36) NOT NULL,
  `setting_name` varchar(10) DEFAULT NULL,
  `setting_content` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(18) NOT NULL,
  `authority_level` int(1) NOT NULL DEFAULT '99',
  `passwd` varchar(25) NOT NULL,
  `account` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_resident` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
