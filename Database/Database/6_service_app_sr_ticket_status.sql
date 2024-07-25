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
-- Table structure for table `sr_ticket_status`
--

DROP TABLE IF EXISTS `sr_ticket_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sr_ticket_status` (
  `TICKET_STATUS_ID` int NOT NULL AUTO_INCREMENT,
  `ticket_status_name` varchar(255) DEFAULT NULL,
  `TICKET_STATUS_DESCRIPITION` varchar(255) DEFAULT NULL,
  `TICKET_STATUS_CREATE_TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TICKET_STATUS_CREATE_BY` int DEFAULT NULL,
  PRIMARY KEY (`TICKET_STATUS_ID`),
  KEY `TICKET_STATUS_CREATE_BY` (`TICKET_STATUS_CREATE_BY`),
  CONSTRAINT `sr_ticket_status_ibfk_1` FOREIGN KEY (`TICKET_STATUS_CREATE_BY`) REFERENCES `employee` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sr_ticket_status`
--

LOCK TABLES `sr_ticket_status` WRITE;
/*!40000 ALTER TABLE `sr_ticket_status` DISABLE KEYS */;
INSERT INTO `sr_ticket_status` VALUES (1,'In-progress','Your ticket is under progress','2024-07-15 10:42:17',5),(2,'Assigned','Your ticket is assigned to an admin','2024-07-15 10:42:47',5),(3,'Approved','Your ticket is approved by an admin','2024-07-15 10:43:12',5),(4,'Rejected','Your ticket is rejected by an admin','2024-07-15 10:43:25',5);
/*!40000 ALTER TABLE `sr_ticket_status` ENABLE KEYS */;
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
