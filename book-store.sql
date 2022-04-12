-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: book_store
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(20) NOT NULL COMMENT '管理员用户名',
  `password` varchar(20) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `name` varchar(50) NOT NULL COMMENT '图书名',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `publish_time` datetime NOT NULL COMMENT '出版时间',
  `cover_link` varchar(1024) DEFAULT NULL COMMENT '图书封面链接',
  `markup_link` varchar(1024) DEFAULT NULL COMMENT '图书备注链接',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `sale_amount` int DEFAULT '0' COMMENT '销售量',
  `stock` int DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  CONSTRAINT `book_chk_1` CHECK ((`price` >= 0)),
  CONSTRAINT `book_chk_2` CHECK ((`sale_amount` >= 0)),
  CONSTRAINT `book_chk_3` CHECK ((`stock` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='图书表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'平凡的世界','路遥','1986-01-01 00:00:00','/imgs/平凡的世界.jpg','/WEB-INF/BooksMarkup/平凡的世界.txt',50,100,0),(2,'京华烟云','林语堂','1939-01-01 00:00:00','/imgs/京华烟云.jpg','/WEB-INF/BooksMarkup/京华烟云.txt',50,22,78),(3,'古船','张炜','1987-08-01 00:00:00','/imgs/古船.jpg','/WEB-INF/BooksMarkup/古船.txt',60,4,96),(4,'芙蓉镇','古华','1981-01-01 00:00:00','/imgs/芙蓉镇.jpg','/WEB-INF/BooksMarkup/芙蓉镇.txt',60,1,99),(5,'白鹿原','陈忠实','1988-04-01 00:00:00','/imgs/白鹿原.jpg','/WEB-INF/BooksMarkup/白鹿原.txt',50,4,96);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '交易编号',
  `user_id` int DEFAULT NULL COMMENT '本次交易的用户id',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `cost_amount` decimal(10,0) DEFAULT NULL COMMENT '交易额',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trade_chk_1` CHECK ((`cost_amount` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='交易表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (1,1,'2021-06-29 16:49:58',10),(5,1,'2021-07-02 19:36:39',1),(6,1,'2021-07-02 22:41:53',100),(7,1,'2021-07-02 22:44:19',100),(8,1,'2021-07-02 23:07:03',150),(9,1,'2021-07-02 23:07:49',150),(10,1,'2021-07-02 23:14:09',150),(11,1,'2021-07-02 23:16:30',150),(12,1,'2021-07-02 23:23:52',50),(13,1,'2021-07-02 23:24:19',50),(14,1,'2021-07-02 23:33:39',50),(15,1,'2021-07-02 23:37:27',100),(16,1,'2021-07-02 23:37:45',100),(17,1,'2021-07-02 23:37:59',100),(18,1,'2021-07-02 23:38:15',100),(19,1,'2021-07-02 23:38:54',200),(20,1,'2021-07-02 23:38:58',200),(21,1,'2021-07-02 23:39:12',200),(22,1,'2021-07-02 23:39:49',100),(23,1,'2021-07-02 23:39:57',100),(24,1,'2021-07-02 23:40:41',100),(25,1,'2021-07-02 23:40:50',100),(26,1,'2021-07-02 23:41:34',0),(27,1,'2021-07-02 23:43:14',50),(28,1,'2021-07-02 23:43:16',50),(29,1,'2021-07-02 23:43:17',50),(30,1,'2021-07-02 23:43:17',50),(31,1,'2021-07-02 23:43:18',50),(32,1,'2021-07-02 23:43:37',50),(33,1,'2021-07-02 23:44:04',50),(34,1,'2021-07-02 23:47:32',50),(35,1,'2021-07-02 23:47:38',50),(36,1,'2021-07-02 23:47:57',50),(37,1,'2021-07-02 23:48:19',50),(38,1,'2021-07-02 23:52:41',50),(39,1,'2021-07-02 23:52:48',50),(40,1,'2021-07-02 23:53:24',50),(41,1,'2021-07-02 23:55:54',50),(42,1,'2021-07-02 23:56:06',50),(43,1,'2021-07-02 23:56:41',50),(44,1,'2021-07-02 23:56:52',50),(45,1,'2021-07-02 23:57:34',50),(46,1,'2021-07-02 23:59:50',50),(47,1,'2021-07-03 00:02:08',50),(48,1,'2021-07-03 00:02:21',0),(49,1,'2021-07-03 00:03:12',0),(50,1,'2021-07-03 00:04:33',50),(51,1,'2021-07-03 00:29:16',1),(52,1,'2021-07-03 00:30:54',4),(53,1,'2021-07-03 00:32:11',2),(54,4,'2021-07-03 00:40:05',50),(55,4,'2021-07-03 00:42:00',250),(56,4,'2021-07-03 09:11:08',100),(57,4,'2021-07-03 09:11:35',50),(58,4,'2021-07-03 09:13:09',100),(59,4,'2021-07-03 09:14:26',50),(60,4,'2021-07-03 09:15:03',50),(61,4,'2021-07-03 09:15:43',50),(62,4,'2021-07-03 09:16:21',50),(63,4,'2021-07-03 09:17:23',50),(64,4,'2021-07-03 09:17:54',50),(65,4,'2021-07-03 09:18:14',0),(66,4,'2021-07-03 09:18:52',0),(67,4,'2021-07-03 09:19:11',50),(68,4,'2021-07-03 09:19:51',50),(69,4,'2021-07-03 09:20:31',50),(70,4,'2021-07-03 09:21:29',50),(71,4,'2021-07-03 09:22:02',50),(72,4,'2021-07-03 09:22:34',50),(73,4,'2021-07-03 09:23:21',50),(74,4,'2021-07-03 09:23:50',50),(75,4,'2021-07-03 09:25:13',50),(76,4,'2021-07-03 09:25:57',50),(77,4,'2021-07-03 09:27:23',50),(78,4,'2021-07-03 09:28:42',50),(79,4,'2021-07-03 09:31:40',50),(80,4,'2021-07-03 09:32:03',50),(81,4,'2021-07-03 09:42:27',50),(82,4,'2021-07-03 09:44:46',50),(83,4,'2021-07-03 09:45:32',50),(84,4,'2021-07-03 09:47:11',50),(85,4,'2021-07-03 11:10:21',50),(86,1,'2021-07-03 14:35:52',0),(87,4,'2021-07-03 16:08:47',270),(88,4,'2021-07-03 16:20:46',370),(89,4,'2021-07-03 17:00:40',50),(90,4,'2021-07-03 17:06:10',50),(91,2,'2021-07-03 22:52:42',100),(92,2,'2021-07-08 10:22:27',160),(93,4,'2021-07-10 10:30:39',0),(94,4,'2021-07-10 16:46:16',0),(97,4,'2021-07-10 20:37:09',0);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_item`
--

DROP TABLE IF EXISTS `trade_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '交易项编号',
  `trade_id` int DEFAULT NULL COMMENT '此交易项对应的交易id',
  `book_name` varchar(50) DEFAULT NULL COMMENT '书名',
  `purchase_amount` int DEFAULT NULL COMMENT '购买数量',
  `cost_amount` decimal(10,0) DEFAULT NULL COMMENT '交易额',
  PRIMARY KEY (`id`),
  KEY `trade_id` (`trade_id`),
  KEY `book_name` (`book_name`),
  CONSTRAINT `trade_item_ibfk_1` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trade_item_ibfk_2` FOREIGN KEY (`book_name`) REFERENCES `book` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trade_item_chk_1` CHECK ((`purchase_amount` >= 0)),
  CONSTRAINT `trade_item_chk_2` CHECK ((`cost_amount` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='交易项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_item`
--

LOCK TABLES `trade_item` WRITE;
/*!40000 ALTER TABLE `trade_item` DISABLE KEYS */;
INSERT INTO `trade_item` VALUES (2,1,'白鹿原',1,1),(3,15,'平凡的世界',1,50),(4,15,'京华烟云',1,50),(5,16,'平凡的世界',1,50),(6,16,'京华烟云',1,50),(7,17,'平凡的世界',1,50),(8,17,'京华烟云',1,50),(9,18,'平凡的世界',1,50),(10,18,'京华烟云',1,50),(11,19,'平凡的世界',2,100),(12,19,'京华烟云',2,100),(13,20,'平凡的世界',2,100),(14,20,'京华烟云',2,100),(15,21,'平凡的世界',2,100),(16,21,'京华烟云',2,100),(17,22,'平凡的世界',2,100),(18,23,'平凡的世界',2,100),(19,24,'平凡的世界',2,100),(20,25,'平凡的世界',2,100),(21,27,'平凡的世界',1,50),(22,28,'平凡的世界',1,50),(23,29,'平凡的世界',1,50),(24,30,'平凡的世界',1,50),(25,31,'平凡的世界',1,50),(26,32,'平凡的世界',1,50),(27,33,'平凡的世界',1,50),(28,34,'平凡的世界',1,50),(29,35,'平凡的世界',1,50),(30,36,'平凡的世界',1,50),(31,37,'平凡的世界',1,50),(32,38,'平凡的世界',1,50),(33,39,'平凡的世界',1,50),(34,40,'平凡的世界',1,50),(35,41,'平凡的世界',1,50),(36,42,'平凡的世界',1,50),(37,43,'平凡的世界',1,50),(38,44,'平凡的世界',1,50),(39,45,'平凡的世界',1,50),(40,46,'平凡的世界',1,50),(41,47,'平凡的世界',1,50),(42,50,'平凡的世界',1,50),(43,51,'平凡的世界',1,1),(44,52,'平凡的世界',4,4),(45,53,'平凡的世界',2,2),(46,54,'平凡的世界',1,50),(47,55,'平凡的世界',5,250),(48,56,'平凡的世界',1,50),(49,56,'京华烟云',1,50),(50,57,'京华烟云',1,50),(51,58,'平凡的世界',2,100),(52,59,'平凡的世界',1,50),(53,60,'京华烟云',1,50),(54,61,'平凡的世界',1,50),(55,62,'平凡的世界',1,50),(56,63,'平凡的世界',1,50),(57,64,'平凡的世界',1,50),(58,67,'平凡的世界',1,50),(59,68,'平凡的世界',1,50),(60,69,'平凡的世界',1,50),(61,70,'平凡的世界',1,50),(62,71,'平凡的世界',1,50),(63,72,'平凡的世界',1,50),(64,73,'平凡的世界',1,50),(65,74,'平凡的世界',1,50),(66,75,'平凡的世界',1,50),(67,76,'平凡的世界',1,50),(68,77,'平凡的世界',1,50),(69,78,'平凡的世界',1,50),(70,79,'平凡的世界',1,50),(71,80,'平凡的世界',1,50),(72,85,'平凡的世界',1,50),(73,87,'平凡的世界',1,50),(74,87,'京华烟云',1,50),(75,87,'芙蓉镇',1,60),(76,87,'白鹿原',1,50),(77,87,'古船',1,60),(78,88,'平凡的世界',2,100),(79,88,'京华烟云',2,100),(80,88,'白鹿原',1,50),(81,88,'古船',2,120),(82,89,'平凡的世界',1,50),(83,90,'平凡的世界',1,50),(84,91,'京华烟云',2,100),(85,92,'白鹿原',2,100),(86,92,'古船',1,60);
/*!40000 ALTER TABLE `trade_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `balance` decimal(10,0) DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  CONSTRAINT `user_chk_1` CHECK ((`balance` >= 0)),
  CONSTRAINT `user_chk_2` CHECK ((`balance` >= 0)),
  CONSTRAINT `user_chk_3` CHECK ((`balance` >= 0)),
  CONSTRAINT `user_chk_4` CHECK ((`balance` >= 0)),
  CONSTRAINT `user_chk_5` CHECK ((`balance` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Shrink','Shrink',100000),(2,'Shrink9','Shrink9',4740),(4,'e','e',10007923),(5,'a','a',0),(6,'dasdasd','123',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-12 21:30:27
