/*
SQLyog Ultimate
MySQL - 5.7.31-log : Database - online_library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online_library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `library`;

/*Table structure for table `admins` */

DROP TABLE IF EXISTS `admins`;

CREATE TABLE `admins` (
  `id` int(20) NOT NULL,
  `admin` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admins` */

insert  into `admins`(`id`,`admin`,`password`,`realname`,`phone`,`email`,`address`) values 
(1,'admin','admin','管理员','1582476','297@qq.com','北京市');

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `intro` varchar(50) DEFAULT NULL,
  `amount` int(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `books` */

insert  into `books`(`id`,`name`,`author`,`intro`,`amount`,`category`) values 
(1,'Java程序设计','李作者','《Java程序设计》是一本编程指导教材',25,'技术类'),
(2,'Web前端开发','王作者','《Web前端开发》是一本编程指导教材',42,'技术类'),
(3,'MySql数据库','张作者','《MySql数据库》是一本编程指导教材',17,'技术类'),
(4,'数据结构','滑作者','《数据结构》是一本编程指导教材',31,'技术类'),
(5,'红楼梦','曹作者','《红楼梦》是一本文学书籍',27,'名著类'),
(6,'西游记','吴作者','《西游记》是一本文学书籍',20,'名著类'),
(7,'三国演义','罗作者','《三国演义》是一本文学书籍',33,'名著类'),
(8,'水浒传','施作者','《水浒传》是一本文学书籍',41,'名著类'),
(9,'高等数学','李作者','《高等数学》是一本理工书籍',59,'教材类'),
(10,'线性代数','同作者','《线性代数》是一本理工书籍',48,'教材类'),
(11,'上下五千年','黄作者','《上下五千年》是一本历史书籍',39,'历史类'),
(12,'国史大纲','钱作者','《国史大纲》是一本历史书籍',21,'历史类'),
(13,'大秦帝国','孙作者','《大秦帝国》是一本历史书籍',56,'历史类'),
(14,'东京梦华录','孟作者','《东京梦华录》是一本历史书籍',47,'历史类'),
(15,'希腊的神话与传说','德国作者','《希腊的神话与传说》是一本哲学书籍',25,'哲学类'),
(16,'你的第一本哲学书','美国作者','《你的第一本哲学书》是一本哲学书籍',37,'哲学类'),
(17,'苏菲的世界','挪威作者','《苏菲的世界》是一本哲学书籍',52,'哲学类'),
(18,'哲学的故事','美国作者','《哲学的故事》是一本哲学书籍',66,'哲学类'),
(19,'大学英语（一）','范作者','《大学英语（一）》是一本外语书籍',52,'外语类'),
(20,'大学英语（二）','范作者','《大学英语（二）》是一本外语书籍',45,'外语类'),
(21,'大学英语（三）','范作者','《大学英语（三）》是一本外语书籍',47,'外语类'),
(22,'大学英语（四）','范作者','《大学英语（四）》是一本外语书籍',77,'外语类'),
(23,'理想国','李作者','《理想国》是一本政治书籍',42,'政治类'),
(24,'政治学','张作者','《政治学》是一本政治书籍',52,'政治类'),
(25,'君主论','王作者','《君主论》是一本政治书籍',27,'政治类'),
(26,'战争与和平法','滑作者','《战争与和平法》是一本政治书籍',35,'政治类'),
(27,'平凡的世界','路作者',NULL,96,'名著类');

/*Table structure for table `borrows` */

DROP TABLE IF EXISTS `borrows`;

CREATE TABLE `borrows` (
  `s_id` int(20) DEFAULT NULL,
  `b_id` int(20) DEFAULT NULL,
  `amount` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrows` */

insert  into `borrows`(`s_id`,`b_id`,`amount`) values 
(7,5,1),
(7,10,1),
(10,1,1),
(8,1,2);

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `classes` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `amount` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `students` */

insert  into `students`(`id`,`user`,`password`,`name`,`grade`,`classes`,`email`,`amount`) values 
(8,'20200002','123','李四','2020','网工一班','456@qq.com',2),
(10,'20200005','123','张三','2020','数媒二班','456@qq.com',1),
(11,'20240001','123456','王五','2024','软件工程1班','1231321@qq.com',0),
(12,'20240002','123456','李丽丽','2024','软件测试1班','12313@qq.com',0),
(13,'2024003','123456','丘陵','2024','附件阿斯蒂芬','213131',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
