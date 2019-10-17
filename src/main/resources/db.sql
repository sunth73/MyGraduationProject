/*
SQLyog v10.2 
MySQL - 5.7.17-log : Database - db_pro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_pro` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `id` */

CREATE TABLE `id` (
  `id` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `e` varchar(10) DEFAULT NULL,
  `n` varchar(10) DEFAULT NULL,
  `u` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `id` */

insert  into `id`(`id`,`date`,`name`,`e`,`n`,`u`) values ('1','2019-08-12','A','1','1','1'),('2','2019-08-12','B',NULL,NULL,NULL),('3','2019-08-13','A','1','1','1'),('4','2019-08-13','B','1','1','1');

/*Table structure for table `t_attendance` */

CREATE TABLE `t_attendance` (
  `t_id` varchar(20) NOT NULL,
  `t_date` date DEFAULT NULL COMMENT '日期',
  `t_week` varchar(20) DEFAULT NULL COMMENT '星期',
  `t_work_state` varchar(10) DEFAULT NULL COMMENT '0出勤1缺勤2请假',
  `t_state` varchar(10) DEFAULT NULL COMMENT '0正常1迟到2早退3迟到早退',
  `t_cause` varchar(200) DEFAULT NULL COMMENT '异常原因',
  `t_leave` varchar(10) DEFAULT NULL COMMENT '0请假1未请假',
  `t_stu_id` varchar(20) DEFAULT NULL COMMENT '学生id',
  `t_sub_id` varchar(20) DEFAULT NULL COMMENT '课程id',
  `t_num` varchar(10) DEFAULT NULL COMMENT '节次',
  `t_class` varchar(20) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_attendance` */

insert  into `t_attendance`(`t_id`,`t_date`,`t_week`,`t_work_state`,`t_state`,`t_cause`,`t_leave`,`t_stu_id`,`t_sub_id`,`t_num`,`t_class`) values ('1','2019-05-23','','0','1','',NULL,'1555339884979997','001','1','1701'),('1558602479136233','2019-05-23','','0','2','','','1555339884979997','001','2','1701'),('1558602479142868','2019-05-23','','1','','','','1555571775472691','001','2','1701'),('1558602479145838','2019-05-23','','1','','看病','0','1555665735150027','001','2','1701'),('1558602677156293','2019-05-23','','','','','','1555339884979997','003','3','1701'),('1558602677159382','2019-05-23','','1','','','','1555571775472691','003','3','1701'),('1558602677163048','2019-05-23','','1','','','0','1555665735150027','003','3','1701'),('1558602727945007','2019-05-23','','','','','','1555339884979997','004','4','1701'),('1558602727948605','2019-05-23','','1','','','','1555571775472691','004','4','1701'),('1558602727950502','2019-05-23','','1','','','0','1555665735150027','004','4','1701'),('1558762198150294','2019-05-25','','0','','好好学习，天天向上','','1555339884979997','003','1','1701'),('1558762198154771','2019-05-25','','1','','','','1555571775472691','003','1','1701'),('1558762198155376','2019-05-25','','2','','','','1555665735150027','003','1','1701'),('2','2019-05-23','','1',NULL,'有事请假','','1555571775472691','001','1','1701');

/*Table structure for table `t_auth` */

CREATE TABLE `t_auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(20) DEFAULT NULL,
  `authPath` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `authDescription` varchar(200) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

/*Data for the table `t_auth` */

insert  into `t_auth`(`authId`,`authName`,`authPath`,`parentId`,`authDescription`,`state`,`iconCls`) values (1,'某系统','',-1,NULL,'closed','icon-home'),(2,'权限管理','',1,NULL,'closed','icon-permission'),(3,'公共管理','',1,'我擦','closed','icon-student'),(4,'信息管理','',1,'','closed','icon-item'),(5,'成绩管理','',1,'','closed','icon-course'),(6,'住宿管理','dromsource.html',3,NULL,'open','icon-item'),(7,'教师信息管理','teacher.html',3,NULL,'open','icon-item'),(8,'助学金管理','',1,NULL,'closed','icon-item'),(9,'工作日志管理','',1,'','closed','icon-item'),(10,'考勤管理','',1,'','closed','icon-item'),(11,'成绩查看','score.html',5,'','open','icon-item'),(12,'用户管理','userManage.html',2,NULL,'open','icon-userManage'),(13,'角色管理','roleManage.html',2,NULL,'open','icon-roleManage'),(14,'菜单管理','menuManage.html',2,NULL,'open','icon-menuManage'),(17,'学生信息管理','student.html',3,NULL,'open','icon-item'),(18,'班级信息维护','class.html',3,NULL,'open','icon-item'),(19,'宿舍信息维护','dorm.html',3,NULL,'open','icon-item'),(28,'助学金申请','subsidyforapplication.html',8,'','open','icon-item'),(29,'助学金投票','subsidy.html',8,'','open','icon-item'),(30,'助学金公示','subsidyresult.html',8,'','open','icon-item'),(32,'公告管理','',1,'','closed','icon-item'),(33,'公告','gonggao.html',32,'','open','icon-item'),(34,'发布公告','addnotice.html',32,'','open','icon-item'),(35,'公告管理','notice.html',32,'','open','icon-item'),(36,'留言管理','',1,'','closed','icon-item'),(37,'留言查看','news.html',36,'','open','icon-item'),(39,'留言管理','newsforsend.html',36,'','open','icon-item'),(100,'修改密码','',1,NULL,'open','icon-modifyPassword'),(101,'安全退出','',1,NULL,'open','icon-exit'),(102,'考试管理','exam.html',3,'','open','icon-item'),(103,'成绩录入','addscore.html',5,'','open','icon-item'),(104,'查看成绩','selectscore.html',5,'','open','icon-item'),(106,'个人信息管理','myInfomation.html',4,'','open','icon-item'),(108,'工作日志','worklog.html',9,'','open','icon-item'),(110,'考勤录入','addattendance.html',10,'','open','icon-item'),(111,'考勤统计','lookattendance.html',10,'','open','icon-item'),(112,'个人考勤查看','oneselfattendance.html',10,'','open','icon-item'),(113,'助学金信息管理','addsubsidy.html',3,'','open','icon-item');

/*Table structure for table `t_class` */

CREATE TABLE `t_class` (
  `tc_id` varchar(20) NOT NULL,
  `tc_class_num` varchar(20) DEFAULT NULL COMMENT '班级名称',
  `tc_user_name` varchar(20) DEFAULT NULL COMMENT '班主任名称',
  `tc_grade_num` varchar(20) DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`tc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_class` */

insert  into `t_class`(`tc_id`,`tc_class_num`,`tc_user_name`,`tc_grade_num`) values ('1','1701','wangwu','17'),('1555936860137576','1703','wangwu','17'),('1555936870776250','1704','wangwu','17'),('1557751639109594','1702','wangwu','17');

/*Table structure for table `t_classname` */

CREATE TABLE `t_classname` (
  `class_id` int(4) NOT NULL AUTO_INCREMENT,
  `class_num` varchar(10) DEFAULT NULL COMMENT '班级号',
  `class_name` varchar(10) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_classname` */

/*Table structure for table `t_course` */

CREATE TABLE `t_course` (
  `cou_id` int(20) NOT NULL AUTO_INCREMENT,
  `cou_name` varchar(20) DEFAULT NULL COMMENT '课程名称',
  `cou_class` varchar(20) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

/*Table structure for table `t_dorm` */

CREATE TABLE `t_dorm` (
  `dorm_id` varchar(20) NOT NULL,
  `dorm_num` varchar(20) DEFAULT NULL COMMENT '宿舍号',
  `dorm_class_num` varchar(20) DEFAULT NULL COMMENT '关联班级号',
  `dorm_user_id` varchar(20) DEFAULT NULL COMMENT '宿舍负责人',
  PRIMARY KEY (`dorm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_dorm` */

insert  into `t_dorm`(`dorm_id`,`dorm_num`,`dorm_class_num`,`dorm_user_id`) values ('1','010101','1701','2'),('1555940833264573','010103','1704','2'),('2','010102','1701','2');

/*Table structure for table `t_dorm_admin` */

CREATE TABLE `t_dorm_admin` (
  `t_id` varchar(20) NOT NULL,
  `t_drom_id` varchar(20) DEFAULT NULL,
  `t_user_id` varchar(20) DEFAULT NULL,
  `t_class_num` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_dorm_admin` */

/*Table structure for table `t_dorm_score` */

CREATE TABLE `t_dorm_score` (
  `tds_id` varchar(20) NOT NULL,
  `tds_dorm_id` varchar(20) DEFAULT NULL COMMENT '宿舍id',
  `tds_date` varchar(20) DEFAULT NULL COMMENT '年月',
  `tds_grade` varchar(20) DEFAULT NULL COMMENT '评分',
  `tds_evaluate` varchar(200) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`tds_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_dorm_score` */

insert  into `t_dorm_score`(`tds_id`,`tds_dorm_id`,`tds_date`,`tds_grade`,`tds_evaluate`) values ('1','1','2019-02','97','表现良好'),('2','2','2019-03','12','唉，好难呀');

/*Table structure for table `t_exam` */

CREATE TABLE `t_exam` (
  `e_id` varchar(20) NOT NULL,
  `e_name` varchar(30) DEFAULT NULL,
  `e_type` varchar(10) DEFAULT NULL,
  `e_class` varchar(20) DEFAULT NULL,
  `e_datetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_exam` */

insert  into `t_exam`(`e_id`,`e_name`,`e_type`,`e_class`,`e_datetime`) values ('1557647314652369','高考',NULL,'17','2019-05-12'),('1557751659735944','期末预测',NULL,'17','2019-05-13');

/*Table structure for table `t_exam_type` */

CREATE TABLE `t_exam_type` (
  `et_id` varchar(20) NOT NULL,
  `et_card` varchar(20) DEFAULT NULL,
  `et_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`et_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_exam_type` */

/*Table structure for table `t_news` */

CREATE TABLE `t_news` (
  `id` varchar(20) NOT NULL,
  `news` varchar(2000) DEFAULT NULL,
  `t_date` datetime DEFAULT NULL,
  `send_id` int(11) DEFAULT NULL,
  `receive_id` int(11) DEFAULT NULL,
  `is_show` varchar(2) DEFAULT NULL COMMENT '0 已读 1 未读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

insert  into `t_news`(`id`,`news`,`t_date`,`send_id`,`receive_id`,`is_show`) values ('1557219993312397','哈哈哈哈哈或或或或或或或或或或或或或或或或','2019-05-07 17:06:33',1,3,'0'),('1557221679991931','gaadadadadad','2019-05-07 17:34:40',3,1,'0'),('1557222159217538','哈哈哈哈额鹅鹅鹅','2019-05-07 17:42:39',3,1,'1'),('2','哈哈哈哈哈或或或或或或或或或或或或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或','2019-05-06 22:05:29',2,1,'0'),('3','恩恩嫩恩恩嫩嗯呢嫩嫩嗯嗯呢嫩嫩嗯嗯呢嗯嗯呢嫩嫩恩恩嫩恩恩嫩恩恩嫩恩恩你','2019-05-07 15:49:32',1,2,'0');

/*Table structure for table `t_news_response` */

CREATE TABLE `t_news_response` (
  `id` varchar(20) NOT NULL,
  `news` varchar(2000) DEFAULT NULL,
  `t_date` datetime DEFAULT NULL,
  `send_id` int(11) DEFAULT NULL,
  `receive_id` int(11) DEFAULT NULL,
  `is_show` varchar(2) DEFAULT NULL COMMENT '0 已读 1 未读',
  `news_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_news_response` */

insert  into `t_news_response`(`id`,`news`,`t_date`,`send_id`,`receive_id`,`is_show`,`news_id`) values ('1','知道了','2019-05-06 21:38:08',1,2,'1','1'),('2','好的','2019-05-06 21:50:23',2,1,'1','1');

/*Table structure for table `t_notice` */

CREATE TABLE `t_notice` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) NOT NULL,
  `text` varchar(10000) NOT NULL,
  `creator` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_show` varchar(2) DEFAULT NULL COMMENT '0:显示 1：不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_notice` */

insert  into `t_notice`(`id`,`title`,`text`,`creator`,`create_time`,`is_show`) values ('1557111203038010','测试呀','<div class=\"para\" style=\"font-size:14px;color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Linux是一套免费使用和自由传播的<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%B1%BBUnix\">类Unix</a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F/192\">操作系统</a>，是一个基于<a target=\"_blank\" href=\"https://baike.baidu.com/item/POSIX\">POSIX</a>和<a target=\"_blank\" href=\"https://baike.baidu.com/item/UNIX\">UNIX</a>的多用户、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%A4%9A%E4%BB%BB%E5%8A%A1/1011764\">多任务</a>、支持<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%A4%9A%E7%BA%BF%E7%A8%8B/1190404\">多线程</a>和多<a target=\"_blank\" href=\"https://baike.baidu.com/item/CPU\">CPU</a>的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持<a target=\"_blank\" href=\"https://baike.baidu.com/item/32%E4%BD%8D/5812218\">32位</a>和<a target=\"_blank\" href=\"https://baike.baidu.com/item/64%E4%BD%8D\">64位</a>硬件。Linux继承了<a target=\"_blank\" href=\"https://baike.baidu.com/item/Unix\">Unix</a>以<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%BD%91%E7%BB%9C/143243\">网络</a>为核心的设计思想，是一个性能稳定的多用户网络操作系统。\n</div>\n<div class=\"para\" style=\"font-size:14px;color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	Linux操作系统诞生于1991 年10 月5 日（这是第一次正式向外公布时间）。Linux存在着许多不同的Linux版本，但它们都使用了<a target=\"_blank\" href=\"https://baike.baidu.com/item/Linux%E5%86%85%E6%A0%B8\">Linux内核</a>。Linux可安装在各种计算机硬件设备中，比如<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%89%8B%E6%9C%BA/6342\">手机</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%B9%B3%E6%9D%BF%E7%94%B5%E8%84%91/1348389\">平板电脑</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%B7%AF%E7%94%B1%E5%99%A8/108294\">路由器</a>、视频游戏控制台、台式计算机、大型机和超级计算机。\n</div>\n<div class=\"para\" style=\"font-size:14px;color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;严格来讲，Linux这个词本身只表示Linux内核，但实际上人们已经习惯了用Linux来形容整个基于Linux内核，并且使用<a target=\"_blank\" href=\"https://baike.baidu.com/item/GNU\">GNU</a>工程各种工具和数据库的操作系统。\n</div>',1,'2019-05-06 10:53:23','0'),('1557117693911977','easyui简介','<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\n	&nbsp;&nbsp;&nbsp;&nbsp;easyui是一种基于jQuery、Angular.、Vue和React的用户界面插件集合。\n</p>\n<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\n	easyui为创建现代化，互动，JavaScript应用程序，提供必要的功能。使用easyui你不需要写很多代码，你只需要通过编写一些简单HTML标记，就可以定义用户界面。easyui是个完美支持HTML5网页的完整框架。easyui节省您网页开发的时间和规模。easyui很简单但功能强大的。\n</p>',1,'2019-05-06 12:41:34','0'),('1557840152563649','测试一下','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 试试而已',3,'2019-05-14 21:22:33','0'),('1557914021257372','测试呀','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 哈哈哈哈哈或或或或或或或或或或或或或或或或哈哈哈哈哈哈哈哈哈哈或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或哈哈哈哈哈哈哈哈哈哈或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或哈哈哈哈哈哈哈哈哈哈或或或或或哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或',1,'2019-05-15 17:53:41','0');

/*Table structure for table `t_role` */

CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `authIds` varchar(500) DEFAULT NULL,
  `roleDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`roleId`,`roleName`,`authIds`,`roleDescription`) values (1,'超级管理员','1,2,12,13,14,3,6,7,17,18,19,102,113,4,106,5,11,103,104,8,28,29,30,9,108,32,33,34,35,36,37,39,100,101','具有最高权限'),(2,'宿舍管理员','1,3,6,5,100,101','管理学生宿舍信息'),(3,'书记','1,9,108,32,33,34,35,36,37,39,100,101','查看教师日报'),(4,'教师','1,3,6,4,106,5,103,104,8,28,29,30,9,108,10,111,32,33,34,35,36,37,39,100,101','讲课的'),(5,'班长','1,3,6,4,106,5,103,104,8,28,29,30,9,108,10,110,32,33,34,35,36,37,39,100,101',NULL),(9,'学生','1,3,6,4,106,5,104,8,28,29,30,10,112,32,33,36,37,39,100,101','你懂的');

/*Table structure for table `t_score` */

CREATE TABLE `t_score` (
  `rec_id` varchar(20) NOT NULL,
  `s_stu_id` varchar(20) DEFAULT NULL COMMENT '学生id',
  `s_sub_code` varchar(20) DEFAULT NULL COMMENT '课程编号',
  `s_score` varchar(10) DEFAULT NULL COMMENT '分数',
  `s_semester` varchar(20) DEFAULT NULL COMMENT '学期',
  `s_exam_id` varchar(20) DEFAULT NULL COMMENT '考试',
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_score` */

insert  into `t_score`(`rec_id`,`s_stu_id`,`s_sub_code`,`s_score`,`s_semester`,`s_exam_id`) values ('1','1555339884979997','001','100','2019-04-15','1557647314652369'),('10','1555571775472691','003','85','2019-04-15','1557647314652369'),('11','1555571775472691','004','96','2019-04-15','1557647314652369'),('12','1555571775472691','005','93','2019-04-15','1557647314652369'),('13','1555571775472691','006','89','2019-04-15','1557647314652369'),('14','1555571775472691','010','100','2019-04-15','1557647314652369'),('1557751659735280','1555339884979997','002','67',NULL,'1557751659735944'),('1557751659735476','1555339884979997','001','89',NULL,'1557751659735944'),('1557751659735917','1555339884979997','010','99',NULL,'1557751659735944'),('1557751659750014','1555339884979997','009','79',NULL,'1557751659735944'),('1557751659750025','1555339884979997','003','89',NULL,'1557751659735944'),('1557751659750290','1555339884979997','007','89',NULL,'1557751659735944'),('1557751659750615','1555339884979997','004','78',NULL,'1557751659735944'),('1557751659750623','1555339884979997','005','88',NULL,'1557751659735944'),('1557751659750648','1555339884979997','008','88',NULL,'1557751659735944'),('1557751659750701','1555339884979997','006','67',NULL,'1557751659735944'),('1557751659766058','1555571775472691','004','89',NULL,'1557751659735944'),('1557751659766133','1555571775472691','010','87',NULL,'1557751659735944'),('1557751659766275','1555571775472691','002','88',NULL,'1557751659735944'),('1557751659766761','1555571775472691','005','89',NULL,'1557751659735944'),('1557751659766762','1555571775472691','003','97',NULL,'1557751659735944'),('1557751659766815','1555571775472691','001','98',NULL,'1557751659735944'),('1557751659781018','1555571775472691','009','92',NULL,'1557751659735944'),('1557751659781088','1555665735150027','010','96',NULL,'1557751659735944'),('1557751659781526','1555571775472691','006','78',NULL,'1557751659735944'),('1557751659781570','1555571775472691','007','96',NULL,'1557751659735944'),('1557751659781847','1555665735150027','001','100',NULL,'1557751659735944'),('1557751659781998','1555571775472691','008','89',NULL,'1557751659735944'),('1557751659797001','1555665735150027','002','89',NULL,'1557751659735944'),('1557751659797054','1555665735150027','004','85',NULL,'1557751659735944'),('1557751659797304','1555665735150027','006','75',NULL,'1557751659735944'),('1557751659797518','1555665735150027','003','78',NULL,'1557751659735944'),('1557751659797519','1555665735150027','005','91',NULL,'1557751659735944'),('1557751659813192','1555665735150027','007','95',NULL,'1557751659735944'),('1557751659813284','1557751254567209','002','96',NULL,'1557751659735944'),('1557751659813330','1557751254567209','010','99',NULL,'1557751659735944'),('1557751659813755','1555665735150027','008','92',NULL,'1557751659735944'),('1557751659813768','1555665735150027','009','86',NULL,'1557751659735944'),('1557751659813793','1557751254567209','001','98',NULL,'1557751659735944'),('1557751659828033','1557751254567209','008','89',NULL,'1557751659735944'),('1557751659828149','1557751254567209','006','100',NULL,'1557751659735944'),('1557751659828590','1557751254567209','003','70',NULL,'1557751659735944'),('1557751659828615','1557751254567209','007','78',NULL,'1557751659735944'),('1557751659828638','1557751254567209','005','78',NULL,'1557751659735944'),('1557751659828874','1557751254567209','004','98',NULL,'1557751659735944'),('1557751659844106','1557751307505057','002','99',NULL,'1557751659735944'),('1557751659844453','1557751307505057','004','78',NULL,'1557751659735944'),('1557751659844545','1557751307505057','003','67',NULL,'1557751659735944'),('1557751659844551','1557751307505057','010','100',NULL,'1557751659735944'),('1557751659844585','1557751254567209','009','96',NULL,'1557751659735944'),('1557751659844759','1557751307505057','001','99',NULL,'1557751659735944'),('1557751659860144','1557751307505057','005','98',NULL,'1557751659735944'),('1557751659860556','1557751362348530','001','96',NULL,'1557751659735944'),('1557751659860633','1557751307505057','009','90',NULL,'1557751659735944'),('1557751659860716','1557751362348530','002','100',NULL,'1557751659735944'),('1557751659860775','1557751307505057','006','78',NULL,'1557751659735944'),('1557751659860859','1557751307505057','007','70',NULL,'1557751659735944'),('1557751659860921','1557751307505057','008','84',NULL,'1557751659735944'),('1557751659860951','1557751362348530','010','100',NULL,'1557751659735944'),('1557751659875011','1557751362348530','007','84',NULL,'1557751659735944'),('1557751659875061','1557751362348530','005','88',NULL,'1557751659735944'),('1557751659875364','1557751362348530','004','89',NULL,'1557751659735944'),('1557751659875592','1557751362348530','003','86',NULL,'1557751659735944'),('1557751659875779','1557751362348530','008','90',NULL,'1557751659735944'),('1557751659875884','1557751362348530','006','92',NULL,'1557751659735944'),('1557751659891989','1557751362348530','009','87',NULL,'1557751659735944'),('2','1555339884979997','002','99','2019-04-15','1557647314652369'),('3','1555339884979997','003','80','2019-04-15','1557647314652369'),('4','1555339884979997','004','86','2019-04-15','1557647314652369'),('5','1555339884979997','005','98','2019-04-15','1557647314652369'),('6','1555339884979997','006','86','2019-04-15','1557647314652369'),('7','1555339884979997','010','100','2019-04-15','1557647314652369'),('8','1555571775472691','001','99','2019-04-15','1557647314652369'),('9','1555571775472691','002','85','2019-04-15','1557647314652369');

/*Table structure for table `t_student` */

CREATE TABLE `t_student` (
  `stu_id` varchar(36) NOT NULL COMMENT '学生id',
  `stu_card_num` varchar(20) DEFAULT NULL COMMENT '学生卡号',
  `stu_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `stu_sex` varchar(4) DEFAULT NULL COMMENT '1:男2:女',
  `stu_nation` varchar(10) DEFAULT NULL COMMENT '民族',
  `stu_birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `stu_place` varchar(10) DEFAULT NULL COMMENT '籍贯',
  `stu_goyear` date DEFAULT NULL COMMENT '入学年份',
  `stu_address` varchar(100) DEFAULT NULL COMMENT '住址',
  `stu_class` varchar(10) DEFAULT NULL COMMENT '班级',
  `stu_policital` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `stu_job` varchar(20) DEFAULT NULL COMMENT '职务',
  `stu_dorm` varchar(20) DEFAULT NULL COMMENT '宿舍地址',
  `stu_phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `stu_qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `stu_email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `stu_wchart` varchar(20) DEFAULT NULL COMMENT '微信号',
  `stu_parent` varchar(20) DEFAULT NULL COMMENT '家长姓名',
  `stu_parent_phone` varchar(20) DEFAULT NULL COMMENT '家长联系方式',
  `stu_other` varchar(200) DEFAULT NULL COMMENT '备注',
  `stu_photo_url` varchar(50) DEFAULT NULL COMMENT '相片地址',
  `stu_uname` varchar(20) DEFAULT NULL COMMENT '登录名',
  `stu_people_card` varchar(20) DEFAULT NULL COMMENT '身份证好',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`stu_id`,`stu_card_num`,`stu_name`,`stu_sex`,`stu_nation`,`stu_birthday`,`stu_place`,`stu_goyear`,`stu_address`,`stu_class`,`stu_policital`,`stu_job`,`stu_dorm`,`stu_phone`,`stu_qq`,`stu_email`,`stu_wchart`,`stu_parent`,`stu_parent_phone`,`stu_other`,`stu_photo_url`,`stu_uname`,`stu_people_card`) values ('1555339884979997','1445201','赵六','男','汉族','2019-04-15 08:00:00','河南','2019-04-15','河南','1701','共青团员','学生','010101','17896354578','11111','123456789@qq.com','17896354578','张一','17601052923','','static/upload/20190525233400058949.jpg','zhaoliu','415895647858985'),('1555571775472691','1445202','李华','1','汉族','2019-04-18 08:00:00','河南','2019-04-18','河南','1701','共青团员','学生','010102','17896354578','11111','123456789@qq.com','17896354578','张一','15652759812','哈哈哈哈',NULL,'lihua',NULL),('1555665735150027','1445203','朱元杰','1','汉族','2017-04-11 08:00:00','河南','2019-04-16','河南','1701','其它','学生','010103','17896354578','11111','123456784@qq.com','17896354578','张一','17896354579','哈哈哈',NULL,'zhuyuanjie',NULL),('1557751254567209','1445204','王华','1','汉族','2019-05-13 08:00:00','北京','2019-05-13','中国','1702','共青团员','学生','010101','123456','123456','123@11.com','123','王','11','',NULL,'wanghua',NULL),('1557751307505057','1445205','赵丽','1','汉族','2019-05-13 08:00:00','','2019-05-13','010102','1702','党员','学生','010101','','','','','','','',NULL,'zhaoli1',NULL),('1557751362348530','1445206','李一','1','回族','2019-05-05 08:00:00','','2019-05-08','','1702','其它','学生','010103','','','','','','','',NULL,'liyi',NULL);

/*Table structure for table `t_student_subsidy` */

CREATE TABLE `t_student_subsidy` (
  `rec_id` varchar(20) NOT NULL,
  `t_user_id` varchar(20) DEFAULT NULL COMMENT '投票人id',
  `t_subsidy_id` varchar(20) DEFAULT NULL COMMENT '助学金id',
  `t_stu_card_num` varchar(20) DEFAULT NULL COMMENT '申请人学号',
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_student_subsidy` */

insert  into `t_student_subsidy`(`rec_id`,`t_user_id`,`t_subsidy_id`,`t_stu_card_num`) values ('1555913367277764','4','1','1445201'),('1558772603688775','57','1558772555284964','1445203'),('1558773682767740','58','1558772555284964','1445204'),('1558773791242236','60','1558772555284964','1445204'),('1558773795910497','60','1558772555284964','1445206'),('1558773978403495','50','1558772555284964','1445203'),('1558773980859627','50','1558772555284964','1445202');

/*Table structure for table `t_subject` */

CREATE TABLE `t_subject` (
  `id` varchar(20) NOT NULL,
  `sub_code` varchar(20) DEFAULT NULL COMMENT '编码',
  `sub_name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_subject` */

insert  into `t_subject`(`id`,`sub_code`,`sub_name`) values ('1','001','语文'),('10','010','体育'),('2','002','数学'),('3','003','英语'),('4','004','物理'),('5','005','化学'),('6','006','生物'),('7','007','地理'),('8','008','历史'),('9','009','政治');

/*Table structure for table `t_subsidy` */

CREATE TABLE `t_subsidy` (
  `tsu_id` varchar(20) NOT NULL,
  `tsu_star_date` varchar(20) DEFAULT NULL COMMENT '开始时间',
  `tsu_end_date` varchar(20) DEFAULT NULL COMMENT '截止日期',
  `tsu_subsidy` varchar(20) DEFAULT NULL COMMENT '名称',
  `tsu_subsidy_code` varchar(20) DEFAULT NULL COMMENT '编号',
  `tsu_state` varchar(2) DEFAULT NULL COMMENT '0正常1结束',
  `tsu_num` varchar(20) DEFAULT NULL COMMENT '名额',
  `tsu_money` varchar(10) DEFAULT NULL COMMENT '人均金额',
  PRIMARY KEY (`tsu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_subsidy` */

insert  into `t_subsidy`(`tsu_id`,`tsu_star_date`,`tsu_end_date`,`tsu_subsidy`,`tsu_subsidy_code`,`tsu_state`,`tsu_num`,`tsu_money`) values ('1','2019-03-01','2019-05-01','xx助学金','1','1','2','1200'),('1558772555284964','2019-05-20','2019-05-28','测试',NULL,'0','1000','1000');

/*Table structure for table `t_subsidy_application` */

CREATE TABLE `t_subsidy_application` (
  `id` varchar(20) NOT NULL,
  `t_stu_card_num` varchar(20) DEFAULT NULL COMMENT '学号',
  `t_application_date` varchar(20) DEFAULT NULL COMMENT '申请日期',
  `t_describe` varchar(1000) DEFAULT NULL COMMENT '申请描述',
  `t_grade` varchar(10) DEFAULT NULL COMMENT '评分',
  `t_class_num` varchar(10) DEFAULT NULL COMMENT '班级',
  `t_tsu_id` varchar(20) DEFAULT NULL COMMENT '助学金id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_subsidy_application` */

insert  into `t_subsidy_application`(`id`,`t_stu_card_num`,`t_application_date`,`t_describe`,`t_grade`,`t_class_num`,`t_tsu_id`) values ('1555910086948621','1445201','2019-04-22','哈哈哈哈','8','1701','1'),('1558772593126400','1445203','2019-05-25','测试','2','1701','1558772555284964'),('1558773672286820','1445204','2019-05-25','HHH','2','1702','1558772555284964'),('1558773782074594','1445206','2019-05-25','测试','1','1702','1558772555284964'),('1558773967877889','1445202','2019-05-25','测试','1','1701','1558772555284964');

/*Table structure for table `t_subsidy_result` */

CREATE TABLE `t_subsidy_result` (
  `id` varchar(20) NOT NULL,
  `ts_tsu_id` varchar(20) DEFAULT NULL COMMENT '助学金id',
  `ts_stu_id` varchar(20) DEFAULT NULL COMMENT '学生id',
  `ts_class` varchar(20) DEFAULT NULL COMMENT '班级',
  `ts_stu_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `ts_money` varchar(10) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_subsidy_result` */

insert  into `t_subsidy_result`(`id`,`ts_tsu_id`,`ts_stu_id`,`ts_class`,`ts_stu_name`,`ts_money`) values ('1','1','1445201','1701','赵六','1000');

/*Table structure for table `t_teacher` */

CREATE TABLE `t_teacher` (
  `tea_id` varchar(20) NOT NULL,
  `tea_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `tea_login_name` varchar(20) DEFAULT NULL COMMENT '登录名',
  `tea_calss` varchar(10) DEFAULT NULL COMMENT '班级',
  `tea_class_num` varchar(10) DEFAULT NULL COMMENT '班级人数',
  `tea_card_num` varchar(20) DEFAULT NULL COMMENT '工号',
  `tea_sex` varchar(2) DEFAULT NULL COMMENT '1:男2:女',
  `tea_subject` varchar(10) DEFAULT NULL COMMENT '专业',
  `tea_policital` varchar(10) DEFAULT NULL COMMENT '政治面貌',
  `tea_phone` varchar(20) DEFAULT NULL,
  `tea_qq` varchar(20) DEFAULT NULL,
  `tea_wchart` varchar(20) DEFAULT NULL,
  `tea_university` varchar(50) DEFAULT NULL COMMENT '毕业院校',
  `tea_education` varchar(20) DEFAULT NULL COMMENT '学历',
  `tea_address` varchar(100) DEFAULT NULL COMMENT '住址',
  `tea_phote_url` varchar(50) DEFAULT NULL COMMENT '照片地址',
  `tea_mail` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `tea_people_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`tea_id`,`tea_name`,`tea_login_name`,`tea_calss`,`tea_class_num`,`tea_card_num`,`tea_sex`,`tea_subject`,`tea_policital`,`tea_phone`,`tea_qq`,`tea_wchart`,`tea_university`,`tea_education`,`tea_address`,`tea_phote_url`,`tea_mail`,`tea_people_card`) values ('1555428405990865','王五','wangwu','1701','1701','145878578','男','语文','党员','17896887885','963852741','968748541','北京大学','本科','北京市','static/upload/20190525233400034541.jpg','xxx','412824199210214589');

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(36) DEFAULT NULL,
  `userType` tinyint(4) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `userDescription` varchar(200) DEFAULT NULL,
  `userMail` varchar(50) DEFAULT NULL,
  `userPhone` varchar(20) DEFAULT NULL,
  `empname` varchar(20) DEFAULT NULL,
  `flag` varchar(2) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_t_user` (`roleId`),
  CONSTRAINT `FK_t_user` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`userName`,`password`,`userType`,`roleId`,`userDescription`,`userMail`,`userPhone`,`empname`,`flag`,`creator`,`createTime`,`updateTime`) values (1,'zhangsan','123456',2,1,'','123@ee.com','84451285564','张三','2',NULL,NULL,NULL),(2,'lisi','123456',2,2,'1231313','11@11.com','12345678901','李四','2',NULL,NULL,NULL),(3,'wangwu','123456',2,4,'guanli元','xxx','17896887885','王五','2',NULL,NULL,'2019-05-25 23:34:35'),(4,'zhaoliu','123456',2,9,'哈哈哈','123456789@qq.com','17896354578','赵六',NULL,NULL,NULL,'2019-05-25 23:34:58'),(50,'lihua','123456',2,9,NULL,'123456789@qq.com','17896354578','李华',NULL,NULL,'2019-04-18 15:16:15','2019-04-19 17:21:09'),(57,'zhuyuanjie','123456',2,9,NULL,'123456784@qq.com','17896354578','朱元杰',NULL,NULL,'2019-04-19 17:22:15',NULL),(58,'wanghua','123456',2,9,NULL,'123@11.com','123456','王华',NULL,NULL,'2019-05-13 20:40:55','2019-05-13 20:46:56'),(59,'zhaoli1','123456',2,9,NULL,'','','赵丽',NULL,NULL,'2019-05-13 20:41:48','2019-05-13 20:47:04'),(60,'liyi','123456',2,9,'','242343@42.COM','5234234234234','李一',NULL,NULL,'2019-05-13 20:42:42','2019-05-13 20:43:02'),(61,'lidakang','123456',2,3,'','xxx@cc.com','110119120','李达康',NULL,NULL,NULL,NULL),(63,'sunwukong','123456',2,NULL,'','aasasa@qq.com','44444444444','孙悟空',NULL,NULL,NULL,NULL);

/*Table structure for table `t_user_role` */

CREATE TABLE `t_user_role` (
  `rec` varchar(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rec`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`rec`,`user_id`,`role_id`) values ('',58,NULL),('1',1,1),('10',61,3),('1558755999028908',63,5),('1558755999047009',63,9),('1558755999049669',63,4),('1558755999050349',63,3),('1558755999051753',63,2),('1558755999052838',63,1),('1558756786050534',4,5),('1558756786055283',4,9),('2',2,2),('3',3,4),('4',5,9),('5',50,9),('6',57,9),('7',58,9),('8',59,9),('9',60,9);

/*Table structure for table `t_work_log` */

CREATE TABLE `t_work_log` (
  `t_id` varchar(20) NOT NULL,
  `log_type` varchar(10) DEFAULT NULL COMMENT '0日报1周报2月报',
  `log_date` date DEFAULT NULL,
  `log_value` varchar(2000) DEFAULT NULL,
  `log_user_id` varchar(20) DEFAULT NULL,
  `log_flag` varchar(10) DEFAULT NULL COMMENT '0未填写1保存2提交',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_work_log` */

insert  into `t_work_log`(`t_id`,`log_type`,`log_date`,`log_value`,`log_user_id`,`log_flag`) values ('1','0','2019-05-21','哈哈哈哈','3','2'),('2','0','2019-05-22','嗯嗯嗯','3','1'),('3','0','2019-05-23','ces11','3','0'),('4','1','2019-05-17',NULL,'3','2'),('5','1','2019-05-24',NULL,'3','1'),('6','1','2019-05-31',NULL,'3','0'),('7','2','2019-03-31',NULL,'3','2'),('8','2','2019-04-30',NULL,'3','1'),('9','2','2019-05-31',NULL,'3','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
