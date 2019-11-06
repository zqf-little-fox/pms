/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.15 : Database - crmpro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crmpro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crmpro`;

/*Table structure for table `analysis` */

DROP TABLE IF EXISTS `analysis`;

CREATE TABLE `analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proname` varchar(64) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `simpledis` varchar(320) DEFAULT NULL,
  `detaileddis` varchar(320) DEFAULT NULL,
  `addtime` date DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  `remark` varchar(320) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `analysis_ibfk_1` FOREIGN KEY (`id`) REFERENCES `project` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `analysis` */

insert  into `analysis`(`id`,`proname`,`title`,`simpledis`,`detaileddis`,`addtime`,`updatetime`,`remark`) values (1,'联通收费系统','联通收费系统需求','协同办公，可以提高工作效率。','可以进行无纸化办公。',NULL,'2019-10-24','目前还在需求确认阶段，详细内容参考需求文档'),(2,'ERP','ERP需求分析','ERP想法包含具体功能：考勤 日常管理  流程审批','考勤：和打卡机连接实时数据同步',NULL,'2019-10-24','需要跟进，完善需求，哈哈'),(3,'商城','商城需求分析','测试','测试','2019-10-24','2019-10-25','测试');

/*Table structure for table `archives` */

DROP TABLE IF EXISTS `archives`;

CREATE TABLE `archives` (
  `dnum` varchar(64) NOT NULL,
  `landline` varchar(32) DEFAULT NULL COMMENT '固话',
  `school` varchar(32) DEFAULT NULL COMMENT '毕业院校',
  `zhuanye` varchar(32) DEFAULT NULL COMMENT '专业',
  `sosperson` varchar(32) DEFAULT NULL COMMENT '紧急联系人',
  `biyedate` date DEFAULT NULL COMMENT '毕业时间',
  `zzmm` varchar(32) DEFAULT NULL COMMENT '政治面貌',
  `minzu` varchar(32) DEFAULT NULL COMMENT '民族',
  `xueli` varchar(32) DEFAULT NULL COMMENT '学历',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `emp_fk` int(11) DEFAULT NULL COMMENT '员工外键',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `hirdate` date DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`dnum`),
  KEY `emp_fk` (`emp_fk`),
  CONSTRAINT `archives_ibfk_1` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `archives` */

insert  into `archives`(`dnum`,`landline`,`school`,`zhuanye`,`sosperson`,`biyedate`,`zzmm`,`minzu`,`xueli`,`email`,`emp_fk`,`remark`,`hirdate`) values ('123-abc','010-110','联合大学','生物科学','仲琪','2018-05-15','群众','汉族','大专','lqf_deyouxiang@163.com',2,'比较年轻不沉稳需要历练','2019-01-01'),('190-www','0531-120','北京大学','电子工程','李程','2018-09-11','党员','回族','本科','567@165.com',5,'学习能力强,重点培养','2019-01-03'),('456-def','0531-110','北京大学','软件工程','张三','2018-09-09','群众','维吾尔族','本科','123@163.com',1,'学习能力强,重点培养','2019-01-01'),('789-jjj','0531-110','北京大学','软件工程','张三','2018-09-09','群众','维吾尔族','本科','123@163.com',3,'学习能力强,重点培养','2019-01-01'),('900-kkk','0531-119','北京大学','航天技术','马亮','2018-09-10','团员','满族','本科','345@164.com',4,'啥也不会，找个机会开走','2019-01-02');

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_fk` int(11) DEFAULT NULL,
  `attname` varchar(32) DEFAULT NULL,
  `attdis` varchar(128) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhdq50yhhjhdiyfm03xjohqlki` (`pro_fk`),
  CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`pro_fk`) REFERENCES `project` (`pid`),
  CONSTRAINT `FKhdq50yhhjhdiyfm03xjohqlki` FOREIGN KEY (`pro_fk`) REFERENCES `project` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `attachment` */

insert  into `attachment`(`id`,`pro_fk`,`attname`,`attdis`,`remark`,`path`) values (2,1,'图片','美女图片','好好看，好好学习。','535f9775-3f99-439a-87f7-eade5d250209_2.经营为什么需要哲学（二）-2010年6月北京.docx'),(3,1,'表笑','表笑的报销','反反复复付付','f8f1ca54-c05d-4615-b3e2-bfbfd20252ea_附件1  个税专项附加扣除信息表-模板.xlsx'),(4,2,'一样一样','以以','uuuuu','bd830b44-fe41-40a4-983e-ca682e1d9395_新建文本文档.txt'),(5,2,'流程图','通天塔','柔柔弱弱若若若','e793907a-80ae-4469-85c6-61044370f87a_模板.doc'),(6,1,'兔兔','大白兔','发发发发发发付','cc2637ad-8129-493f-9167-1270bbe784a4_903Java座位图.xlsx'),(7,2,'测试','测试','测试','D:\\idea\\pms\\src\\main\\webapp\\upload\\ac46f90a8e5041efb1b5faeefbff4e4e'),(8,3,'测试1','测试1','测试10-29','D:\\idea\\pms\\src\\main\\webapp\\upload\\2ca05bb3dc144c99959b78ae892083bb'),(9,3,'测试1','测试1','测试','D:\\idea\\pms\\src\\main\\webapp\\upload\\60c6ced08a4a4f8e829e100e0fd833e9'),(10,1,'测试','测试1','测试','D:\\idea\\pms\\src\\main\\webapp\\upload\\71655d32a34e416b91a5043c5f65dad41.jpg');

/*Table structure for table `baoxiao` */

DROP TABLE IF EXISTS `baoxiao`;

CREATE TABLE `baoxiao` (
  `bxid` varchar(64) NOT NULL,
  `paymode` varchar(32) DEFAULT NULL COMMENT '类型',
  `totalmoney` double DEFAULT NULL COMMENT '总金额',
  `bxtime` date DEFAULT NULL COMMENT '报销时间',
  `bxremark` varchar(100) DEFAULT NULL COMMENT '报销备注',
  `bxstatus` int(11) DEFAULT '0' COMMENT '报销状态',
  `emp_fk` int(11) DEFAULT NULL COMMENT '发起报销人',
  `result` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`bxid`),
  KEY `emp_fk` (`emp_fk`),
  CONSTRAINT `baoxiao_ibfk_1` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `baoxiao` */

insert  into `baoxiao`(`bxid`,`paymode`,`totalmoney`,`bxtime`,`bxremark`,`bxstatus`,`emp_fk`,`result`) values ('13c7129f05dc4fb38dbc823af33fdbd6','办公采购',2345,'2019-10-26','测试一下结果',0,1,NULL),('5fa64b67-0864-4a45-b3bf-ca617300dd13','办公采购',456634,'2019-08-08','呃呃呃呃呃反反复复',1,2,'44444'),('768a08dc8f0d4b9bac530a7a942fbeac','其他',100000,'2019-10-26','测试测试',0,1,NULL),('7c850c79dfbc44589acb8097286e9595','办公采购',100000,'2019-10-26','购物100000元，有点多啊',0,1,NULL),('8a0d540f41b4476c9a3769a702735e58','出差票据',2000,'2019-10-26','小吃',0,1,NULL),('9b578a5e84f64fefad4217e73962ad5e','出差票据',3000,'2019-10-26','测试测试测试测试测试测试测试测试测试删除',0,1,NULL),('a56146b94571423abd2f8eed7eda025b','其他',290,'2019-10-26','公司采购，花费290元',0,1,NULL),('c13e844c-3a13-480e-b8e9-9839c08d695a','办公采购',3455,'2019-09-10','出差去岘港吃饭喝酒',2,2,'反反复复'),('dcee798b9b4f4e2cafc3783fd3c613ab','出差票据',100000,'2019-10-26','测试',0,1,NULL),('e71a0c55-044f-4d56-855f-bd931fd43a36','办公采购',123,'2019-01-01','发发发发发发付付深V许昌县后天发货',2,2,'555555');

/*Table structure for table `benchmarking` */

DROP TABLE IF EXISTS `benchmarking`;

CREATE TABLE `benchmarking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(50) NOT NULL COMMENT '公司名称',
  `sales_amount` double DEFAULT NULL COMMENT '营业额',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `business` varchar(50) DEFAULT NULL COMMENT '主要业务',
  `priority` varchar(100) DEFAULT NULL COMMENT '优势',
  `disadvantage` varchar(100) DEFAULT NULL COMMENT '劣势',
  `state` varchar(30) DEFAULT NULL COMMENT '行业地位',
  `emp_count` int(11) DEFAULT NULL COMMENT '员工数量',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `simple_desc` varchar(255) DEFAULT NULL COMMENT '简单描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `benchmarking` */

insert  into `benchmarking`(`id`,`company_name`,`sales_amount`,`year`,`business`,`priority`,`disadvantage`,`state`,`emp_count`,`create_time`,`simple_desc`) values (2,'新东方',215,2018,'英语培训，教育培训','名气大','人员臃肿','龙头老大',10000,NULL,'新东方是国内第一家在美国上市的教育企业'),(3,'中公教育',150,2018,'公务员培训，IT培训','包吃包住','吃的不好','龙头老大',40000,NULL,'第一家在国内上市的教育企业'),(4,'达内',200,2018,'IT培训','老牌企业','视频教学','老牌企业',8000,NULL,'是国内利润最高的教育企业'),(5,'学而思',180,2018,'K12教育','名师在线','不能及时解决问题','后起之秀',6000,NULL,'国内K12教育比较优秀的企业');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comname` varchar(128) DEFAULT NULL,
  `companyperson` varchar(32) DEFAULT NULL,
  `comaddress` varchar(128) DEFAULT NULL,
  `comphone` varchar(32) DEFAULT NULL,
  `camera` varchar(128) DEFAULT NULL,
  `present` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `addtime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`id`,`comname`,`companyperson`,`comaddress`,`comphone`,`camera`,`present`,`remark`,`addtime`) values (4,'用友软件','刘老师','北京市','1111111','010-11','物联网企业','最早的办公软件公司','2018-12-26'),(5,'浪潮软件','孙老师','济南市','3333333','010-22','服务器企业','比较成熟的服务器技术和软件开发技术','2018-12-26'),(6,'中科软','王老师','上海市','5555555','010-33','外包公司','没有自主研发，主要是外包','2018-12-26'),(9,'小米','雷军','中关村软件园23号楼','19999999','010-9999','最大的手机组装工场','香港刚刚上市，有钱尽量套取。','2018-12-26');

/*Table structure for table `datacollect` */

DROP TABLE IF EXISTS `datacollect`;

CREATE TABLE `datacollect` (
  `daid` int(11) NOT NULL AUTO_INCREMENT,
  `dacname` varchar(32) DEFAULT NULL COMMENT '目标公司名称',
  `daturnover` double DEFAULT NULL COMMENT '营业额',
  `datime` date DEFAULT NULL COMMENT '年度',
  `dabusiness` varchar(128) DEFAULT NULL COMMENT '主要业务',
  `dasuperiority` varchar(128) DEFAULT NULL COMMENT '优势',
  `dainforiority` varchar(128) DEFAULT NULL COMMENT '劣势',
  `dasort` int(11) DEFAULT NULL COMMENT '行业排名',
  `empcount` int(11) DEFAULT NULL COMMENT '员工数量',
  `buildtime` date DEFAULT NULL COMMENT '企业创建时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '简单描述',
  `daother` varchar(128) DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`daid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `datacollect` */

insert  into `datacollect`(`daid`,`dacname`,`daturnover`,`datime`,`dabusiness`,`dasuperiority`,`dainforiority`,`dasort`,`empcount`,`buildtime`,`remark`,`daother`) values (1,'浪潮集团',10,'2015-01-01','软件研发','国家扶持','自主能力较差',1,3000,'1980-09-09','创建时间较长，市场比较稳定','2015年-浪潮集团详细调研说明书.doc'),(2,'浪潮集团',20,'2016-01-01','软件研发,服务器研发','国家扶持','自主能力较差',1,3001,'1980-09-10','创建时间较长，市场比较稳定','2016年-浪潮集团详细调研说明书.doc'),(3,'浪潮集团',50,'2017-01-01','软件研发，软件销售','国家扶持','自主能力较差',1,3002,'1980-09-11','创建时间较长，市场比较稳定','2017年-浪潮集团详细调研说明书.doc'),(4,'浪潮集团',70,'2018-01-01','软件研发','国家扶持','自主能力较差',1,3003,'1980-09-12','创建时间较长，市场比较稳定','2018年-浪潮集团详细调研说明书.doc'),(5,'浪潮集团',8,'2014-01-01','软件研发','国家扶持','自主能力较差',1,3004,'1980-09-13','创建时间较长，市场比较稳定','2014年-浪潮集团详细调研说明书.doc'),(6,'中科软',30,'1018-01-01','软件外包服务','技术人员充沛',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `deptno` int(11) NOT NULL,
  `dname` varchar(32) DEFAULT NULL,
  `dlocation` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`deptno`,`dname`,`dlocation`) values (10,'开发部','北京'),(20,'市场部','广州'),(30,'总裁办','上海');

/*Table structure for table `email` */

DROP TABLE IF EXISTS `email`;

CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) DEFAULT NULL,
  `ename` varchar(32) DEFAULT NULL,
  `sendtime` date DEFAULT NULL,
  `content` varchar(128) DEFAULT NULL,
  `emp_fk` int(11) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwdib3s3ugq70b5h2ocn0rfvb` (`emp_fk`),
  CONSTRAINT `email_ibfk_1` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`),
  CONSTRAINT `FKbwdib3s3ugq70b5h2ocn0rfvb` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `email` */

insert  into `email`(`id`,`title`,`ename`,`sendtime`,`content`,`emp_fk`,`path`) values (1,'发件测试','lqf_deyouxiang@163.com','2019-10-29','测试',1,'D:\\idea\\pms\\src\\main\\webapp\\upload\\9506828922c343bb873cc9896e52823d1.jpg'),(2,'发件测试','lqf_deyouxiang@163.com','2019-10-29','测试测试',1,'D:\\idea\\pms\\src\\main\\webapp\\upload\\cb3adb5fd432491ab43e8841493aa83b1.jpg'),(3,'发件测试','lqf_deyouxiang@163.com','2019-10-29','测试测试',1,'D:\\idea\\pms\\src\\main\\webapp\\upload\\1c00a33729364588ad83e2cd2c172c9a1.jpg'),(4,'发件测试','lqf_deyouxiang@163.com','2019-10-29','测试',1,'D:\\idea\\pms\\src\\main\\webapp\\upload\\cbd40f97ee5f43bd860fc567744597e51.jpg');

/*Table structure for table `emp_role` */

DROP TABLE IF EXISTS `emp_role`;

CREATE TABLE `emp_role` (
  `eid` int(11) NOT NULL DEFAULT '0',
  `rid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`eid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `emp_role_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`eid`),
  CONSTRAINT `emp_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp_role` */

insert  into `emp_role`(`eid`,`rid`) values (10,13),(9,14);

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(32) DEFAULT NULL,
  `esex` varchar(32) DEFAULT NULL,
  `eage` int(11) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `pnum` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `p_fk` int(11) DEFAULT NULL,
  `d_fk` int(11) DEFAULT NULL,
  `l_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`eid`),
  KEY `FKt4aodqf7acjpmo7iejdmg3k78` (`p_fk`),
  KEY `d_fk` (`d_fk`),
  KEY `l_fk` (`l_fk`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`p_fk`) REFERENCES `position` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`d_fk`) REFERENCES `dept` (`deptno`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`l_fk`) REFERENCES `level` (`jid`),
  CONSTRAINT `FKt4aodqf7acjpmo7iejdmg3k78` FOREIGN KEY (`p_fk`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`eid`,`ename`,`esex`,`eage`,`telephone`,`hiredate`,`pnum`,`username`,`password`,`remark`,`p_fk`,`d_fk`,`l_fk`) values (1,'李四','0',45,'2344','2018-12-03','5767','433','433','234324',4,10,4),(2,'张三','1',45,'45','2018-12-25','45','45','45','45645',1,10,1),(3,'赵四','0',34,'123','2019-01-07','98','123','123','werewolf',5,20,2),(4,'刘恩能够','0',45,'345','2019-01-16','89','124','56','werewolf',2,30,3),(5,'王五','1',56,'456',NULL,'909','125','78','温热无若',3,10,4),(6,'小孙子','1',23,'123213',NULL,'123213','zsf123','000000','这个人老牛逼了会做算法题',5,10,4),(7,'444','1',44,'44',NULL,'44','44','44','444444',2,20,2),(8,'55','1',55,'55','2019-01-08','55','55','55','5555555',1,10,1),(9,'张珊','1',23,'1234435352','2019-10-31','52154657365746','zs','123','初级程序员',1,NULL,NULL),(10,'徐坊','1',26,'3241432141','2019-10-31','32134321432543','xf','123','测试',6,NULL,NULL);

/*Table structure for table `evaluate` */

DROP TABLE IF EXISTS `evaluate`;

CREATE TABLE `evaluate` (
  `evaid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价id',
  `forum_fk` int(11) DEFAULT NULL COMMENT '帖子外键',
  `emp_fk4` int(11) DEFAULT NULL COMMENT '评价人外键',
  `evaid_fk` int(11) DEFAULT NULL COMMENT '回复外键',
  `evacontent` varchar(256) DEFAULT NULL COMMENT '评价内容',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `evatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `evastatus` int(11) DEFAULT '0' COMMENT '评价状态',
  PRIMARY KEY (`evaid`),
  KEY `emp_fk4` (`emp_fk4`),
  KEY `forum_fk` (`forum_fk`),
  KEY `evaid_fk` (`evaid_fk`),
  CONSTRAINT `evaluate_ibfk_1` FOREIGN KEY (`emp_fk4`) REFERENCES `employee` (`eid`),
  CONSTRAINT `evaluate_ibfk_2` FOREIGN KEY (`forum_fk`) REFERENCES `forumpost` (`forumid`),
  CONSTRAINT `evaluate_ibfk_3` FOREIGN KEY (`evaid_fk`) REFERENCES `evaluate` (`evaid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `evaluate` */

insert  into `evaluate`(`evaid`,`forum_fk`,`emp_fk4`,`evaid_fk`,`evacontent`,`updatetime`,`evatime`,`evastatus`) values (1,2,5,NULL,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:08:33',NULL),(2,2,2,1,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:08:27',NULL),(3,2,3,1,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:08:25',NULL),(6,2,4,NULL,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:08:24',0),(7,2,1,6,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:08:23',0),(8,2,2,NULL,'<p>发发发发发发付</p>',NULL,'2019-01-07 11:05:34',0),(9,2,2,NULL,'<p>反反复复</p><p>反反复复</p>',NULL,'2019-01-07 11:10:07',0),(10,3,2,NULL,'<p>反反复复付付</p>',NULL,'2019-01-07 11:14:50',0);

/*Table structure for table `forumpost` */

DROP TABLE IF EXISTS `forumpost`;

CREATE TABLE `forumpost` (
  `forumid` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `forumtitle` varchar(32) NOT NULL COMMENT '帖子的标题',
  `forumcontent` text NOT NULL COMMENT '帖子的内容',
  `emp_fk3` int(11) NOT NULL COMMENT '发帖人',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT '0' COMMENT '帖子状态',
  PRIMARY KEY (`forumid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `forumpost` */

insert  into `forumpost`(`forumid`,`forumtitle`,`forumcontent`,`emp_fk3`,`createtime`,`status`) values (2,'投入让人','<p>发发发发发发付多岁的爽肤水</p>',2,'2019-01-04 15:54:23',0),(3,'反反复复','<p>反反复复</p>',2,'2019-01-04 16:01:28',0),(4,'附近反复讲','<p>发的顺丰科技的史莱克</p><p>第三方就离开的说法</p><p>是的房间里看电视</p>',2,'2019-01-04 16:16:18',0),(6,'333333','<p>333333333<br/></p>',2,'2019-01-04 16:50:10',0),(7,'444','<p>4444</p>',2,'2019-01-04 17:48:12',0),(8,'444','<p>44444</p>',2,'2019-01-04 17:48:18',0),(9,'444','<p>5555</p>',2,'2019-01-04 17:50:03',0),(10,'666','<p>666666</p>',2,'2019-01-04 17:53:49',0),(11,'555','<p>55555</p>',2,'2019-01-04 18:22:04',0),(12,'333333','<p>ttttttttttt</p>',2,'2019-01-04 18:32:31',0),(13,'杯子出售','<p>杯子在呢么名史莱克的减肥的流口水</p>',2,'2019-01-07 09:01:37',0),(14,'发贴测试','<p>测测测试测试</p><p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/></p>',1,'2019-10-29 21:05:31',0);

/*Table structure for table `function` */

DROP TABLE IF EXISTS `function`;

CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proname` varchar(32) DEFAULT NULL,
  `analysisname` varchar(32) DEFAULT NULL,
  `modele_fk` int(11) DEFAULT NULL,
  `functionname` varchar(32) DEFAULT NULL,
  `level` varchar(32) DEFAULT NULL,
  `simpledis` varchar(256) DEFAULT NULL,
  `detaileddis` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjonpxsiii6o3xv16nttvg500w` (`modele_fk`),
  CONSTRAINT `FKjonpxsiii6o3xv16nttvg500w` FOREIGN KEY (`modele_fk`) REFERENCES `module` (`id`),
  CONSTRAINT `function_ibfk_1` FOREIGN KEY (`modele_fk`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `function` */

insert  into `function`(`id`,`proname`,`analysisname`,`modele_fk`,`functionname`,`level`,`simpledis`,`detaileddis`,`remark`) values (1,'1','1',2,'收费标准制定','高','3333','333','33333'),(2,'2','2',4,'删除员工信息','高','反反复复','凤飞飞','反反复复'),(3,'2','2',5,'权限添加','高','啊啊啊啊','反反复复','灌灌灌灌'),(4,'3','3',7,'测试测试','高','测试测试','测试','测试');

/*Table structure for table `indexvalue` */

DROP TABLE IF EXISTS `indexvalue`;

CREATE TABLE `indexvalue` (
  `in_id` int(11) NOT NULL AUTO_INCREMENT,
  `in_turnover` double DEFAULT NULL COMMENT '目标营业额',
  `in_business` varchar(128) DEFAULT NULL COMMENT '主要业务方向',
  `comname_fk` int(11) DEFAULT NULL COMMENT '对比企业名称',
  `in_remark` varchar(256) DEFAULT NULL COMMENT '简单说明',
  `in_file` varchar(128) DEFAULT NULL COMMENT '附件',
  `emp_fk5` int(11) DEFAULT NULL COMMENT '指标制定人',
  `in_starttime` date DEFAULT NULL COMMENT '开始时间',
  `in_endtime` date DEFAULT NULL COMMENT '截止时间',
  `in_updatetime` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`in_id`),
  KEY `comname_fk` (`comname_fk`),
  KEY `emp_fk5` (`emp_fk5`),
  CONSTRAINT `indexvalue_ibfk_1` FOREIGN KEY (`comname_fk`) REFERENCES `datacollect` (`daid`),
  CONSTRAINT `indexvalue_ibfk_2` FOREIGN KEY (`emp_fk5`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `indexvalue` */

insert  into `indexvalue`(`in_id`,`in_turnover`,`in_business`,`comname_fk`,`in_remark`,`in_file`,`emp_fk5`,`in_starttime`,`in_endtime`,`in_updatetime`) values (4,75,'软件开发服务器销售',4,'扩展业务为服务器销售','a1e97cf3-ded9-4f53-860f-5e95b4fafa00_abc.xls',2,'2019-01-01','2019-06-30',NULL);

/*Table structure for table `level` */

DROP TABLE IF EXISTS `level`;

CREATE TABLE `level` (
  `jid` int(11) NOT NULL AUTO_INCREMENT,
  `jname` varchar(32) DEFAULT NULL,
  `jdis` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`jid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `level` */

insert  into `level`(`jid`,`jname`,`jdis`) values (1,'p1','实习生'),(2,'p2','初级程序员'),(3,'m1','项目组长'),(4,'m2','项目负责人');

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proname` varchar(32) DEFAULT NULL,
  `analysis_fk` int(11) DEFAULT NULL,
  `modname` varchar(32) DEFAULT NULL,
  `level` varchar(32) DEFAULT NULL,
  `simpledis` varchar(256) DEFAULT NULL,
  `detaileddis` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK986a0ufpo55087x7uc0ous7nb` (`analysis_fk`),
  CONSTRAINT `FK986a0ufpo55087x7uc0ous7nb` FOREIGN KEY (`analysis_fk`) REFERENCES `analysis` (`id`),
  CONSTRAINT `module_ibfk_1` FOREIGN KEY (`analysis_fk`) REFERENCES `analysis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `module` */

insert  into `module`(`id`,`proname`,`analysis_fk`,`modname`,`level`,`simpledis`,`detaileddis`,`remark`) values (2,'1',1,'系统管理','低','权限分配，资源创建，','怎么分配权限，怎么创建资源的详细描述','当前模块是我们项目中最后模块，不着急'),(3,'1',1,'收费管理','中','通讯计费标准制定，实施，','通讯计费标准制定，实施，','通讯计费标准制定，实施，'),(4,'2',2,'员工管理','高','员工的考勤管理，工资管理，日常办公管理','员工的考勤管理，工资管理，日常办公管理','员工的考勤管理，工资管理，日常办公管理'),(5,'2',2,'权限管理','高','吾问无为谓','反反复复付','反反复复付'),(7,'3',3,'测试','高','测试','测试','测试');

/*Table structure for table `msg` */

DROP TABLE IF EXISTS `msg`;

CREATE TABLE `msg` (
  `msgid` int(11) NOT NULL AUTO_INCREMENT,
  `sendp` int(32) DEFAULT NULL,
  `recvp` int(32) DEFAULT NULL,
  `mark` int(11) DEFAULT '0',
  `msgcontent` varchar(32) DEFAULT NULL,
  `msgtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`msgid`),
  KEY `sendp` (`sendp`),
  KEY `recvp` (`recvp`),
  CONSTRAINT `msg_ibfk_1` FOREIGN KEY (`sendp`) REFERENCES `employee` (`eid`),
  CONSTRAINT `msg_ibfk_2` FOREIGN KEY (`recvp`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `msg` */

insert  into `msg`(`msgid`,`sendp`,`recvp`,`mark`,`msgcontent`,`msgtime`) values (1,2,1,0,'反反复复','2019-01-04 00:00:00');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `ntitle` varchar(32) DEFAULT NULL,
  `remark` varchar(320) DEFAULT NULL,
  `ndate` date DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

insert  into `notice`(`nid`,`ntitle`,`remark`,`ndate`) values (1,'提升张三为副总裁','张三的接口丽枫酒店\r\n的实例会计法独立思考\r\n的借款方\r\n冻死了空间发的是\r\n独立思考解放东路开始','2019-01-03'),(2,'降薪公告','关于集团为了缩减开始，特发出降薪通告\r\n1:10年员工降薪30%\r\n2：新员工降薪45%','2019-01-01'),(4,'年终考核','总结大家一年中工作内容，成绩。突出贡献','2018-12-31'),(5,'集团考勤通告','张三今天迟到了5分钟，本次事故纳入年终考核，罚款500元','2019-01-03'),(6,'节约使用办公用品倡议','为了迎接互联网寒冬，我们要扎进裤腰带，共同度过','2019-01-03'),(7,'测试','测试','2019-10-29'),(8,'测试','测试','2019-10-29'),(9,'测试','测试','2019-10-29'),(10,'测试','测试','2019-10-29'),(11,'测试','测试','2019-10-29'),(12,'测试','测试','2019-10-29'),(13,'村上春树','测试测试测试测试','2019-10-29');

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `position` */

insert  into `position`(`id`,`name`) values (1,'初级开发工程师'),(2,'中级开发工程师'),(3,'高级开发工程师'),(4,'项目经理'),(5,'项目总监'),(6,' 其他');

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(128) DEFAULT NULL,
  `comname` int(11) DEFAULT NULL,
  `comper` varchar(64) DEFAULT NULL,
  `emp_fk1` int(11) DEFAULT NULL,
  `empcount` int(11) DEFAULT NULL,
  `starttime` date DEFAULT NULL,
  `buildtime` date DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `level` varchar(32) DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `emp_fk` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `emp_fk1` (`emp_fk1`),
  KEY `FKtirudsu3khj865hrqeamn153y` (`comname`),
  KEY `FKj4vtyyp6ew24vggobfcmav1be` (`emp_fk`),
  CONSTRAINT `FKj4vtyyp6ew24vggobfcmav1be` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`),
  CONSTRAINT `FKtirudsu3khj865hrqeamn153y` FOREIGN KEY (`comname`) REFERENCES `customer` (`id`),
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`comname`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`pid`,`pname`,`comname`,`comper`,`emp_fk1`,`empcount`,`starttime`,`buildtime`,`cost`,`level`,`endtime`,`remark`,`emp_fk`) values (1,'联通收费系统',5,'孙老师',NULL,4,'2019-09-09','2018-09-09',444,'紧急','2019-09-24','333333333333',1),(2,'ERP',4,'刘老师',NULL,5,'2018-09-09','2018-10-10',34,'2','2020-09-09','进行中',1),(3,'商城',6,'王老师',NULL,5,'1998-09-09','1998-09-09',3456,'3','2022-09-09','不着急',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rolename` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `roledis` varchar(32) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleid`,`rolename`,`roledis`,`status`) values (12,'测试人员','拥有基本权限',1),(13,'董事长','拥有所有权限信息',1),(14,'程序员','',1);

/*Table structure for table `role_sources` */

DROP TABLE IF EXISTS `role_sources`;

CREATE TABLE `role_sources` (
  `rid` int(11) NOT NULL DEFAULT '0',
  `sid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`,`sid`),
  KEY `sid` (`sid`),
  CONSTRAINT `role_sources_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `role_sources_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `sources` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_sources` */

insert  into `role_sources`(`rid`,`sid`) values (12,1),(13,1),(14,1),(12,2),(13,2),(14,2),(12,3),(13,3),(14,3),(12,4),(13,4),(14,4),(13,5),(14,5),(13,6),(13,7),(13,8),(14,8),(12,9),(13,9),(14,9),(12,10),(13,10),(14,10),(12,11),(13,11),(14,11),(12,12),(13,12),(14,12),(12,13),(13,13),(14,13),(13,14),(13,15),(12,16),(13,16),(14,16),(13,17),(13,18),(13,19),(13,20),(13,21),(14,21),(12,27),(13,27),(14,27),(13,28),(13,29),(14,29),(13,30),(14,30),(13,31),(14,31),(13,32),(13,33),(13,34),(13,35),(13,36),(13,37),(14,37),(13,38),(14,38);

/*Table structure for table `sources` */

DROP TABLE IF EXISTS `sources`;

CREATE TABLE `sources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(128) DEFAULT NULL COMMENT '资源路径',
  `remark` varchar(32) DEFAULT NULL COMMENT '资源备注',
  `pid` int(11) DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `sources` */

insert  into `sources`(`id`,`name`,`url`,`remark`,`pid`,`icon`) values (1,'oa办公协同系统',NULL,'系统名称',0,NULL),(2,'项目管理',NULL,'项目管理',1,NULL),(3,'日常办公',NULL,'日常办公',1,NULL),(4,'消息管理',NULL,'信息箱',1,NULL),(5,'客户信息管理',NULL,'客户信息管理',1,NULL),(6,'系统管理',NULL,'系统管理',1,NULL),(7,'对标管理',NULL,'对标管理',1,NULL),(8,'我的信息',NULL,'我的信息',1,NULL),(9,'基本信息管理','/pms/pro/list','项目基本信息',2,NULL),(10,'需求信息管理','/pms/anal/list','项目需求分析',2,NULL),(11,'模块管理','/pms/mod/list','模块管理',2,NULL),(12,'功能管理','/pms/func/list','功能管理',2,NULL),(13,'附件管理','/pms/attach/list','附件管理',2,NULL),(14,'创建任务','/pms/task-add.jsp','创建任务',3,NULL),(15,'任务信息','/pms/task/list','已发布任务',3,NULL),(16,'我的任务','/pms/task/myList','我的任务',3,NULL),(17,'通知公告',NULL,'通知公告',3,NULL),(18,'档案管理','/pms/archives/list','档案管理',3,NULL),(19,'我的档案',NULL,'我的档案',3,NULL),(20,'报销审批',NULL,'报销审批',3,NULL),(21,'我的报销','/pms/bx/list','我的报销',3,NULL),(27,'发信息','/pms/message-seed.jsp','仅仅使用javamail发送邮件就ok啦',4,NULL),(28,'发帖页面','/pms/fatie.jsp','发帖页面',3,NULL),(29,'发件箱','/pms/email/list','发件箱',4,NULL),(30,'收件箱','/pms/email/giveList','收件箱',4,NULL),(31,'客户信息','/pms/cust/list','客户信息',5,NULL),(32,'人员管理','/pms/user.jsp','人员管理',6,NULL),(33,'权限维护','/pms/pm.jsp','权限维护',6,NULL),(34,'角色管理','/pms/role/list','角色管理',6,NULL),(35,'对标信息管理','/pms/benchmarking/list','对标信息管理',7,NULL),(36,'数据统计','/pms/duibiao-result.jsp','数据统计',7,NULL),(37,'信息查看','','信息查看',8,NULL),(38,'修改密码','','修改密码',8,NULL);

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `level` varchar(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `fun_fk` int(11) DEFAULT NULL COMMENT '具体的功能外键',
  `emp_fk2` int(11) DEFAULT NULL COMMENT '具体实现功能的人',
  `emp_fk` int(11) NOT NULL COMMENT '分配任务的人',
  `tasktitle` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '任务的状态0,1,2',
  PRIMARY KEY (`id`),
  KEY `emp_fk2` (`emp_fk2`),
  KEY `FK8pqhf0pdn5r478vp69g6sip3` (`fun_fk`),
  KEY `FKcpxt4jxiaoqj5nwny5ai74drj` (`emp_fk`),
  CONSTRAINT `FK8pqhf0pdn5r478vp69g6sip3` FOREIGN KEY (`fun_fk`) REFERENCES `function` (`id`),
  CONSTRAINT `FKcpxt4jxiaoqj5nwny5ai74drj` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`fun_fk`) REFERENCES `function` (`id`),
  CONSTRAINT `task_ibfk_3` FOREIGN KEY (`emp_fk2`) REFERENCES `employee` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `task` */

insert  into `task`(`id`,`starttime`,`endtime`,`level`,`remark`,`fun_fk`,`emp_fk2`,`emp_fk`,`tasktitle`,`status`) values (1,'2019-09-09','2019-10-10','低','慢慢做,不急',1,2,1,'收费标准全部功能',1),(4,'2019-09-10','2019-09-20','中','权限信息中没有使用shiro和ztree，需要使用这些技术完成',3,2,1,'完善人员权限信息',2),(5,'2019-10-23','2019-10-26','高','参数',1,4,1,'cs',0),(6,'2019-10-27','2019-10-29','高','添加管理员权限',3,1,1,'权限添加功能',2),(7,'2019-10-23','2019-09-24','高','收费标准制定',1,1,1,'收费',1),(8,'2019-10-01','2019-10-26','高','测试',4,1,1,'测试',1),(9,'2019-10-26','2019-10-29','低','测试',2,1,1,'测试',2),(10,'2019-10-26','2019-10-31','高','测试',1,1,1,'收费',0),(11,'2019-10-23','2019-09-24','高','测试',2,5,1,'测试',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
