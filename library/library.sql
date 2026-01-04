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
-- 保留原有管理员表结构，不做修改
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
-- 保留原有管理员数据，不做修改
insert  into `admins`(`id`,`admin`,`password`,`realname`,`phone`,`email`,`address`) values
    (1,'admin','admin','管理员','1582476','297@qq.com','北京市');

/*Table structure for table `books` */
-- 保留原有字段，新增功能2所需字段：书号(ISBN)、出版社、定价
-- 原有字段：id(自增主键)、name(书名)、author(作者/译者，支持逗号分隔多名作者)、intro(内容简介)、amount(库存数量)、category(分类)
DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
                         `id` int(20) NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) DEFAULT NULL, -- 书名（原有）
                         `isbn` varchar(50) DEFAULT NULL, -- 新增：书号（ISBN），满足功能2要求
                         `author` varchar(50) DEFAULT NULL, -- 作者/译者（原有，支持逗号分隔多名作者，满足“一名或多名作者”要求）
                         `publisher` varchar(50) DEFAULT NULL, -- 新增：出版社，满足功能2要求
                         `price` decimal(10,2) DEFAULT NULL, -- 新增：定价，满足功能2要求
                         `intro` varchar(50) DEFAULT NULL, -- 内容简介（原有）
                         `amount` int(50) DEFAULT NULL, -- 库存数量（原有，支撑功能1“一万多册图书”的存储）
                         `category` varchar(50) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `books` */
-- 补充isbn、publisher、price字段值，完善图书数据
insert  into `books`(`id`,`name`,`isbn`,`author`,`publisher`,`price`,`intro`,`amount`,`category`) values
-- 技术类
(1,'Java编程思想','9787111213826','Bruce Eckel','机械工业出版社',99.00,'Java领域经典著作，深入讲解核心特性',32,'技术类'),
(2,'Python编程：从入门到实践','9787115428028','Eric Matthes','人民邮电出版社',59.00,'零基础入门Python，含实战项目',45,'技术类'),
(3,'Redis设计与实现','9787111506905','黄健宏','机械工业出版社',69.00,'剖析Redis内部实现机制',19,'技术类'),
(4,'Spring Boot实战','9787115428387','Craig Walls','人民邮电出版社',69.00,'讲解Spring Boot核心与实战技巧',28,'技术类'),
-- 名著类
(5,'百年孤独','9787544253994','加西亚·马尔克斯','南海出版公司',55.00,'拉丁美洲魔幻现实主义巅峰之作',36,'名著类'),
(6,'飘','9787533941036','玛格丽特·米切尔','浙江文艺出版社',59.00,'美国南北战争背景下的爱情史诗',29,'名著类'),
(7,'骆驼祥子','9787020104732','老舍','人民文学出版社',22.00,'旧北平人力车夫的悲惨命运',38,'名著类'),
(8,'平凡的世界','9787020049129','路遥','人民文学出版社',108.00,'陕北黄土高原的奋斗与成长',51,'名著类'),
(27,'人类简史：从动物到上帝','9787508647357','尤瓦尔·赫拉利','中信出版社',68.00,'梳理人类百万年发展历程',78,'名著类'),
-- 教材类
(9,'大学物理（上册）','9787040200573','张三慧','高等教育出版社',34.00,'高校理工科核心教材',63,'教材类'),
(10,'C语言程序设计教程','9787040201617','谭浩强','高等教育出版社',29.00,'国内经典C语言入门教材',72,'教材类'),
-- 历史类
(11,'明朝那些事儿','9787505724181','当年明月','中国友谊出版公司',28.00,'通俗幽默讲述明朝三百年历史',58,'历史类'),
(12,'全球通史','9787500646644','斯塔夫里阿诺斯','北京大学出版社',78.00,'全景式展现人类文明历程',27,'历史类'),
(13,'中国近代史','9787108010204','蒋廷黻','生活·读书·新知三联书店',19.00,'简明梳理近代中国变迁',24,'历史类'),
(14,'万历十五年','9787108013306','黄仁宇','生活·读书·新知三联书店',28.00,'以小见大窥探明朝衰落',33,'历史类'),
-- 哲学类
(15,'存在与时间','9787100077240','马丁·海德格尔','商务印书馆',58.00,'探讨存在本质与时间关系',16,'哲学类'),
(16,'中国哲学简史','9787100078049','冯友兰','商务印书馆',35.00,'梳理中国哲学发展脉络',41,'哲学类'),
(17,'论自由','9787100092283','约翰·穆勒','商务印书馆',22.00,'阐述个人自由与社会管控边界',23,'哲学类'),
(18,'沉思录','9787544246893','马可·奥勒留','南海出版公司',29.00,'罗马皇帝的人生感悟与思考',30,'哲学类'),
-- 外语类
(19,'新标准大学英语（综合教程1）','9787513508062','Simon Greenall','外语教学与研究出版社',49.00,'提升英语综合能力',56,'外语类'),
(20,'日语初级教程（新版）','9787561913508','标日编写组','北京语言大学出版社',42.00,'零基础日语入门教材',43,'外语类'),
(21,'新概念英语（第二册）','9787560013480','亚历山大','外语教学与研究出版社',32.00,'巩固语法与句型积累',67,'外语类'),
(22,'法语简明教程','9787301048487','薛建成','北京大学出版社',28.00,'高校二外法语核心教材',28,'外语类'),
-- 政治类
(23,'中国政治制度史','9787301019485','韦庆远','北京大学出版社',48.00,'梳理历代政治制度演变',18,'政治类'),
(24,'政治学原理','9787301158442','王浦劬','北京大学出版社',36.00,'讲解政治学核心概念',35,'政治类'),
(25,'论美国的民主','9787100079329','托克维尔','商务印书馆',88.00,'分析美国民主制度',22,'政治类'),
(26,'社会契约论','9787100072497','让-雅克·卢梭','商务印书馆',18.00,'阐述主权在民思想',17,'政治类');

/*Table structure for table `students` */
-- 保留原有字段，新增功能3所需借书证及借阅者信息：借书证号、所在单位、职业
-- 原有字段：id、user、password、name(借阅者姓名)、grade、classes、email、amount(已借图书数量)
DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
                            `id` int(20) NOT NULL AUTO_INCREMENT,
                            `user` varchar(50) DEFAULT '',
                            `password` varchar(50) DEFAULT NULL,
                            `name` varchar(50) DEFAULT NULL, -- 借阅者姓名（原有，满足功能3要求）
                            `card_id` varchar(50) DEFAULT NULL, -- 新增：借书证号（借书证核心标识，满足功能3、4要求）
                            `unit` varchar(50) DEFAULT NULL, -- 新增：所在单位（满足功能3要求）
                            `occupation` varchar(50) DEFAULT NULL, -- 新增：职业（满足功能3要求）
                            `grade` varchar(50) DEFAULT NULL,
                            `classes` varchar(50) DEFAULT NULL,
                            `email` varchar(50) DEFAULT NULL,
                            `amount` int(50) DEFAULT 0 CHECK (`amount` <= 8), -- 原有字段，新增CHECK约束：已借数量不超过8本（满足功能4“每次最多借8本书”要求）
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_card_id` (`card_id`) -- 新增唯一索引：借书证号唯一，避免重复
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `students` */
-- 补充card_id、unit、occupation字段值，XX大学替换为杭州电子科技大学
insert  into `students`(`id`,`user`,`password`,`name`,`card_id`,`unit`,`occupation`,`grade`,`classes`,`email`,`amount`) values
                                                                                                                            (8,'20200002','123','李思远','20200002001','杭州电子科技大学','学生','2020','网络工程一班','456@qq.com',2),
                                                                                                                            (10,'20200005','123','张伟','20200005001','杭州电子科技大学','学生','2020','数字媒体技术二班','456@qq.com',1),
                                                                                                                            (11,'20240001','123456','王浩宇','20240001001','杭州电子科技大学','学生','2024','软件工程一班','1231321@qq.com',0),
                                                                                                                            (12,'20240002','123456','李娜','20240002001','杭州电子科技大学','学生','2024','软件测试一班','12313@qq.com',0),
                                                                                                                            (13,'2024003','123456','刘阳','2024003001','杭州电子科技大学','学生','2024','人工智能一班','213131',0);

/*Table structure for table `borrows` */
-- 保留原有字段，新增功能4所需字段：借书证号（关联借书证）、借书日期、应还日期（支撑30天期限）、实际还书日期
-- 原有字段：s_id(学生id)、b_id(图书id)、amount(借阅数量)
DROP TABLE IF EXISTS `borrows`;

CREATE TABLE `borrows` (
                           `s_id` int(20) DEFAULT NULL,
                           `b_id` int(20) DEFAULT NULL,
                           `card_id` varchar(50) DEFAULT NULL, -- 新增：借书证号（关联学生借书证，满足“凭借书证借书”要求）
                           `amount` int(20) DEFAULT NULL, -- 借阅数量（原有）
                           `borrow_date` datetime DEFAULT CURRENT_TIMESTAMP, -- 新增：借书日期（默认当前时间）
                           `due_date` datetime DEFAULT (DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 30 DAY)), -- 新增：应还日期（默认借书+30天，满足“最长30天”要求）
                           `return_date` datetime DEFAULT NULL -- 新增：实际还书日期（未还为NULL，便于管理借阅状态）
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrows` */
-- 修正无效s_id（原s_id=7不存在，改为s_id=8），补充card_id字段值
insert  into `borrows`(`s_id`,`b_id`,`card_id`,`amount`,`borrow_date`,`due_date`,`return_date`) values
                                                                                                    (8,5,'20200002001',1,'2025-12-01','2026-01-01',NULL),
                                                                                                    (8,10,'20200002001',1,'2025-12-02','2026-01-01',NULL),
                                                                                                    (10,1,'20200005001',1,'2025-12-05','2026-01-04',NULL),
                                                                                                    (8,1,'20200002001',2,'2025-12-08','2026-01-07',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;