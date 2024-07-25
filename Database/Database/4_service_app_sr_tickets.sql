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
-- Table structure for table `sr_tickets`
--

DROP TABLE IF EXISTS `sr_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sr_tickets` (
  `SR_TICKET_ID` int NOT NULL AUTO_INCREMENT,
  `SR_TICKET_TYPE` int DEFAULT NULL,
  `sr_description` varchar(255) DEFAULT NULL,
  `SR_STATUS_ID` int DEFAULT NULL,
  `sr_create_timestamp` datetime(6) DEFAULT NULL,
  `sr_create_by` int DEFAULT NULL,
  `sr_assigned_to` int DEFAULT NULL,
  `sr_updated_timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`SR_TICKET_ID`),
  KEY `SR_TICKET_TYPE` (`SR_TICKET_TYPE`),
  KEY `SR_STATUS_ID` (`SR_STATUS_ID`),
  KEY `FKkkjpym5ckg632lvi87xrwt5g7` (`sr_assigned_to`),
  KEY `FK6emo3noovvceqtx83kan6286i` (`sr_create_by`),
  CONSTRAINT `FK6emo3noovvceqtx83kan6286i` FOREIGN KEY (`sr_create_by`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `FKkkjpym5ckg632lvi87xrwt5g7` FOREIGN KEY (`sr_assigned_to`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `sr_tickets_ibfk_1` FOREIGN KEY (`SR_TICKET_TYPE`) REFERENCES `sr_ticket_type` (`TICKET_TYPE_ID`),
  CONSTRAINT `sr_tickets_ibfk_2` FOREIGN KEY (`SR_STATUS_ID`) REFERENCES `sr_ticket_status` (`TICKET_STATUS_ID`),
  CONSTRAINT `sr_tickets_ibfk_3` FOREIGN KEY (`SR_STATUS_ID`) REFERENCES `sr_ticket_status` (`TICKET_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sr_tickets`
--

LOCK TABLES `sr_tickets` WRITE;
/*!40000 ALTER TABLE `sr_tickets` DISABLE KEYS */;
INSERT INTO `sr_tickets` VALUES (1,1,'computer is not turning on.',2,'2024-07-16 11:25:46.064056',5,11,'2024-07-19 12:34:37.131053'),(6,1,'Regarding the Installation of maven',3,'2024-07-16 12:11:39.396058',2,6,'2024-07-19 11:59:22.809082'),(8,10,'Technology training issue!',3,'2024-07-17 05:03:04.064330',5,5,'2024-07-24 16:43:45.511418'),(9,9,'Network security breach',3,NULL,2,5,'2024-07-24 10:22:13.294312'),(14,1,'My printer is running away',4,'2024-07-18 10:31:59.195428',2,5,'2024-07-19 12:33:52.816214'),(15,9,'My Internet is going very slow.',3,'2024-07-18 10:35:05.243203',2,5,'2024-07-19 16:20:46.836345'),(16,2,'I need support',2,'2024-07-18 10:38:19.024112',2,11,'2024-07-19 11:31:23.972988'),(18,10,'Udemy link for java basics',2,'2024-07-18 10:43:26.942257',2,11,'2024-07-19 11:30:46.715348'),(19,10,'Udemy link for java basics',1,'2024-07-18 16:17:38.038774',4,6,'2024-07-18 17:44:35.786759'),(20,10,'Udemy link for angular basics',1,'2024-07-18 16:35:46.257290',4,6,'2024-07-18 17:42:27.580341'),(21,11,'my internet is very poors',4,'2024-07-18 16:39:42.368892',2,2,'2024-07-25 10:12:19.108884'),(22,10,'Udemy link for springboot basics _bye',2,'2024-07-18 16:47:26.063352',2,1,'2024-07-23 13:24:55.457875'),(24,1,'need hardware support',2,'2024-07-18 18:26:46.612161',2,6,'2024-07-19 11:01:03.737323'),(28,9,'I want to install antivirus',2,'2024-07-19 14:18:07.079444',5,11,'2024-07-19 14:18:15.570928'),(29,9,'Security needed.',4,'2024-07-19 15:13:46.756128',2,5,'2024-07-19 16:21:25.675621'),(33,17,'adfsdf',1,'2024-07-23 11:44:53.712960',12,NULL,'2024-07-23 11:44:53.712960'),(34,10,'Udemy link for angular Advanced',1,'2024-07-23 12:24:53.230274',11,NULL,'2024-07-23 12:24:53.230274'),(35,10,'Training guide for Angular framework',1,'2024-07-23 12:27:10.809930',11,NULL,'2024-07-23 12:27:33.484898'),(37,9,'Install antivirus in my computer',3,'2024-07-23 14:12:39.468379',2,5,'2024-07-24 16:50:55.753316'),(38,1,'System setup',4,'2024-07-23 14:38:49.487458',2,5,'2024-07-24 16:51:16.272260'),(39,1,'Monitor is not working properly',2,'2024-07-23 14:48:08.796082',2,6,'2024-07-24 10:19:01.607644'),(41,1,'need hardware support',1,'2024-07-23 14:59:23.677611',2,NULL,'2024-07-23 14:59:23.677611'),(42,11,'connectivity issue',1,'2024-07-23 15:02:24.092385',2,NULL,'2024-07-23 15:02:24.092385'),(43,9,'need security',1,'2024-07-23 15:19:22.268362',2,NULL,'2024-07-23 15:19:22.268362'),(45,1,'Installation of maven',1,'2024-07-24 18:54:15.177848',2,NULL,'2024-07-24 18:54:15.177848'),(50,1,'Need support',2,'2024-07-24 10:28:48.994846',5,5,'2024-07-24 10:29:41.245861'),(51,15,'Need to maintain company',4,'2024-07-24 10:29:33.864818',5,5,'2024-07-24 16:51:42.072787'),(53,2,'Need support for installing software',3,'2024-07-24 10:32:32.043055',5,5,'2024-07-24 17:42:18.064779'),(54,18,'Need someone to do housework',2,'2024-07-24 10:38:08.650836',6,6,'2024-07-24 10:38:14.644494'),(55,1,'Need support',4,'2024-07-24 11:55:29.147014',5,5,'2024-07-24 17:42:24.600972'),(57,1,'Need support',2,'2024-07-24 13:41:26.911974',5,12,'2024-07-24 16:38:38.092478'),(58,9,'I need security for network',3,'2024-07-24 13:45:59.389435',5,5,'2024-07-25 12:06:54.864164'),(61,10,'Please train',4,'2024-07-24 15:41:37.841582',5,5,'2024-07-25 09:59:16.902372'),(64,2,'Installation of Java',1,'2024-07-24 16:31:06.433614',6,NULL,'2024-07-24 16:31:06.433614'),(65,1,'need hardware support.',2,'2024-07-24 17:41:53.522930',5,17,'2024-07-24 17:41:58.859804'),(66,17,'Ship me a computer now',1,'2024-07-25 09:56:59.928508',5,NULL,'2024-07-25 10:00:25.461499'),(67,19,'Current off here',1,'2024-07-25 09:56:17.663382',5,NULL,'2024-07-25 10:00:32.996199'),(69,15,'I want to clean my cabin',2,'2024-07-25 09:58:48.397151',5,13,'2024-07-25 09:58:55.158789'),(70,19,'my computer is turning off',2,'2024-07-25 10:04:57.994682',18,13,'2024-07-25 10:06:25.813657'),(72,9,'I need antivirus installed now',4,'2024-07-25 10:08:19.751486',18,5,'2024-07-25 10:09:36.080207'),(73,1,'Maintaining CPU, h/w support',1,'2024-07-25 10:38:38.257339',19,NULL,'2024-07-25 10:43:26.431826'),(74,20,'Food 123',3,'2024-07-25 10:53:22.904178',19,12,'2024-07-25 10:56:58.205061'),(75,18,'sdfcsdf',1,'2024-07-25 10:57:23.016277',12,NULL,'2024-07-25 10:57:23.016277'),(76,15,'ertert',2,'2024-07-25 11:01:08.863314',12,12,'2024-07-25 11:01:52.413475');
/*!40000 ALTER TABLE `sr_tickets` ENABLE KEYS */;
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
