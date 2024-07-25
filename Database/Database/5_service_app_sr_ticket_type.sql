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
-- Table structure for table `sr_ticket_type`
--

DROP TABLE IF EXISTS `sr_ticket_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sr_ticket_type` (
  `TICKET_TYPE_ID` int NOT NULL AUTO_INCREMENT,
  `ticket_type_name` varchar(255) DEFAULT NULL,
  `TICKET_TYPE_DESCRIPITION` varchar(255) DEFAULT NULL,
  `TICKET_TYPE_CREATE_TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TICKET_TYPE_CREATE_BY` int DEFAULT NULL,
  PRIMARY KEY (`TICKET_TYPE_ID`),
  KEY `TICKET_TYPE_CREATE_BY` (`TICKET_TYPE_CREATE_BY`),
  CONSTRAINT `sr_ticket_type_ibfk_1` FOREIGN KEY (`TICKET_TYPE_CREATE_BY`) REFERENCES `employee` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sr_ticket_type`
--

LOCK TABLES `sr_ticket_type` WRITE;
/*!40000 ALTER TABLE `sr_ticket_type` DISABLE KEYS */;
INSERT INTO `sr_ticket_type` VALUES (1,'Hardware Support','Troubleshooting issues with desktops, laptops, mobile devices, and company-issued software','2024-07-15 06:31:46',5),(2,'Software Support','Install and configure software, and ensure employees have the tools they need to work effectively.','2024-07-15 06:34:01',5),(9,'Network Security','implements firewalls, antivirus software, and other measures to protect the company data from cyberattacks and unauthorized access.\n','2024-07-15 06:42:25',5),(10,'Technology Training','IT can provide training programs to help employees learn new software, use technology more effectively, and stay up-to-date on security best practices.\n','2024-07-15 06:43:13',5),(11,'Network&Connectivity','Wi-Fi Problems, Internet Connectivity Issues and VPN Access Problems.','2024-07-15 06:44:12',5),(14,'Company Maintenance','This service is to provide essential maintenance in our company','2024-07-19 13:30:12',5),(15,'Company Maintenance','This service is to provide essential maintenance in our company','2024-07-22 05:04:40',5),(16,'Training','training','2024-07-22 06:12:02',5),(17,'Shipping','Delivery delay ,Damaged package ,Refund requested ','2024-07-22 10:08:00',5),(18,'Housework','Need someone to do housework','2024-07-24 05:04:01',5),(19,'Electricity','Need electrician','2024-07-24 10:44:14',5),(20,'Food','Food for workers','2024-07-25 04:29:57',5);
/*!40000 ALTER TABLE `sr_ticket_type` ENABLE KEYS */;
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
