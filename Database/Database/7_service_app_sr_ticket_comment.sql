CREATE DATABASE  IF NOT EXISTS `service_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `service_app`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: service_app
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `sr_ticket_comment`
--

DROP TABLE IF EXISTS `sr_ticket_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sr_ticket_comment` (
  `TICKET_COMMENT_ID` int NOT NULL AUTO_INCREMENT,
  `ticket_id` int DEFAULT NULL,
  `TICKET_COMMENT` varchar(255) DEFAULT NULL,
  `TICKET_COMMENT_CREATE_TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TICKET_COMMENT_CREATE_BY` int DEFAULT NULL,
  PRIMARY KEY (`TICKET_COMMENT_ID`),
  KEY `TICKET_COMMENT_CREATE_BY` (`TICKET_COMMENT_CREATE_BY`),
  KEY `FKs6wjwfowx0xj7g0ee51quradk` (`ticket_id`),
  CONSTRAINT `FKs6wjwfowx0xj7g0ee51quradk` FOREIGN KEY (`ticket_id`) REFERENCES `sr_tickets` (`SR_TICKET_ID`),
  CONSTRAINT `sr_ticket_comment_ibfk_1` FOREIGN KEY (`TICKET_COMMENT_CREATE_BY`) REFERENCES `employee` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sr_ticket_comment`
--

LOCK TABLES `sr_ticket_comment` WRITE;
/*!40000 ALTER TABLE `sr_ticket_comment` DISABLE KEYS */;
INSERT INTO `sr_ticket_comment` VALUES (1,6,'On my way','2024-07-19 07:01:02',6),(2,14,'Do it yourself','2024-07-19 07:03:53',5),(3,14,'Do it yourself','2024-07-19 07:08:35',5),(4,8,'hello','2024-07-19 10:36:12',5),(5,8,'','2024-07-19 10:39:42',5),(6,8,'a','2024-07-19 10:40:30',5),(7,8,'rejected','2024-07-19 10:44:34',5),(8,9,'rejected','2024-07-19 10:46:04',5),(9,15,'security approved','2024-07-19 10:50:47',5),(10,29,'Security needed rejected','2024-07-19 10:51:26',5),(11,8,'Will sent someone today','2024-07-24 04:49:56',5),(12,9,'install antivirus','2024-07-24 04:52:13',5),(13,9,'install antivirus','2024-07-24 04:53:01',5),(14,8,'will give tech train','2024-07-24 11:13:46',5),(15,37,'i will install','2024-07-24 11:20:56',5),(16,38,'i wont setup','2024-07-24 11:21:16',5),(17,51,'no i wont let you','2024-07-24 11:21:42',5),(18,53,'i approve','2024-07-24 12:12:18',5),(19,55,'i reject','2024-07-24 12:12:25',5),(20,61,'not a valid request','2024-07-25 04:29:17',5),(21,72,'install it yourself','2024-07-25 04:39:36',5),(22,21,'spelling mistake','2024-07-25 04:42:19',2),(23,74,'','2024-07-25 05:26:58',12),(24,74,'','2024-07-25 05:26:58',12),(25,74,'','2024-07-25 05:26:58',12),(26,74,'','2024-07-25 05:26:58',12),(27,74,'','2024-07-25 05:26:58',12),(28,74,'','2024-07-25 05:26:58',12),(29,74,'','2024-07-25 05:26:58',12),(30,74,'','2024-07-25 05:26:58',12),(31,74,'','2024-07-25 05:26:58',12),(32,74,'','2024-07-25 05:26:58',12),(33,74,'','2024-07-25 05:26:58',12),(34,74,'','2024-07-25 05:26:58',12),(35,74,'','2024-07-25 05:26:58',12),(36,74,'','2024-07-25 05:26:58',12),(37,74,'','2024-07-25 05:26:58',12),(38,74,'','2024-07-25 05:26:58',12),(39,74,'','2024-07-25 05:26:58',12),(40,74,'','2024-07-25 05:26:58',12),(41,74,'','2024-07-25 05:26:58',12),(42,74,'','2024-07-25 05:26:58',12),(43,74,'','2024-07-25 05:26:58',12),(44,74,'','2024-07-25 05:26:58',12),(45,74,'','2024-07-25 05:26:59',12),(46,74,'','2024-07-25 05:26:59',12),(47,74,'','2024-07-25 05:27:03',12),(48,74,'','2024-07-25 05:27:04',12),(49,74,'','2024-07-25 05:27:04',12),(50,74,'','2024-07-25 05:27:05',12),(51,58,'will send someone by noon','2024-07-25 06:36:55',5);
/*!40000 ALTER TABLE `sr_ticket_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-25 12:14:56
