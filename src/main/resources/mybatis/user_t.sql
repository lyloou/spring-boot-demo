# Host: localhost  (Version: 5.5.40)
# Date: 2019-02-20 18:38:59
# Generator: MySQL-Front 5.3  (Build 4.120)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "user_t"
#

CREATE DATABASE IF NOT EXISTS chapter12;
use chapter12;
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "user_t"
#

INSERT INTO `user_t` VALUES (1,'test','sfasgfaf',24),(2,'test2','abcdefg',25),(3,'test3','abcdefg',25),(12,'test','sfasgfaf',24),(13,'test2','abcdefg',25),(14,'test3','abcdefg',25);
