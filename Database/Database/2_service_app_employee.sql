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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `COUNTRY_CODE` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  UNIQUE KEY `USERNAME` (`username`),
  KEY `FK2fcqw2e4ydfpymigsphh46f8t` (`COUNTRY_CODE`),
  CONSTRAINT `FK2fcqw2e4ydfpymigsphh46f8t` FOREIGN KEY (`COUNTRY_CODE`) REFERENCES `country` (`COUNTRY_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Ben','Tennyson','ben.tennyson@polus.com','Software Engineer Trainee','Kerala','75xxxxxxxx','benten','bentenpassword','2024-07-11 18:37:34','IND','Ben Tennyson'),(2,'Manesh','M S','manesh.ms@polus.com','Software Engineer Trainee','Kerala','75xxxxxxxx','maneshms','maneshmspassword','2024-07-12 10:56:26','IND','Manesh M S'),(3,'Sheena','J','sheena.j@polus.com','Software Engineer Trainee','Kerala','89xxxxxxxx','sheenaj','sheenajpassword','2024-07-12 12:01:37','IND','Sheena J'),(4,'Shyla','M','shyla.m@polus.com','Software Engineer Trainee','Kerala','89xxxxxxxx','shylam','shylampassword','2024-07-12 12:19:55','IND','Shyla M'),(5,'Sasi','Kumar','sasikumar@polus.com','Backend developer','TamilNadu','89xxxxxxxx','sasikumar','sasikumarpassword','2024-07-12 17:30:04','IND','Sasi Kumar'),(6,'Winsley','John','winsleyjohn@polus.com','Frontend developer','Kerala','89xxxxxxxx','winsleyjohn','winsleyjohnpassword','2024-07-15 11:36:58','IND','Winsley John'),(7,'Rock','John','rockjohn@polus.com','Frontend developer','Kerala','89xxxxxxxx','rockjohn','rockjohnpassword','2024-07-15 11:40:02','IND','Rock John'),(8,'aneena','joseph','aneena@gmail.com','Trainee','Kerala','8975799965','aneena','aneenapassword','2024-07-15 11:49:02','IND','Aneena Joseph'),(9,'aneena','joseph','aneena@gmail.com','Trainee','Kerala','9778888888','aneenaJ','aneenapassword','2024-07-16 10:23:24','IND','Aneena Joseph'),(10,'veena','V','veena@gmail.com','Trainee','Kerala','8975799965','veena','veenapassword','2024-07-16 12:26:24','IND','Veena V'),(11,'Arun','Raj','arun.raj@polus.com','Software Engineer','Kerala','75xxxxxxxx','arunraj','arunrajpassword','2024-07-17 04:52:10','IND','Arun Raj'),(12,'Arun','Raj','arunraj@email.com','test desc','123kerala','9876543210','arunrajpolus','password123','2024-07-23 11:25:40','IND','Arun Raj'),(13,'jimbru','kuttan','jimbru@gmail.com','Developer','Kerala','9999999999','jimbru','jimbrupassword','2024-07-23 12:42:33','IND','jimbru kuttan'),(14,'Habin George','George','habin.george@polus.com','BA','Kerala','9876543210','habin','habinpassword','2024-07-24 17:27:08','IND',NULL),(15,'Habin George','George','habin.george@polus.com','Business Analyst','Kerala','7523588978','habingeorge','habingeorgepassword','2024-07-24 17:32:32','IND',NULL),(16,'Habin George','George','habin.george@polus.com','Business Analyst','Kerala','7523588978','habingeorge1','habingeorgepassword','2024-07-24 17:33:43','IND',NULL),(17,'Habin','George','habin.george@polus.com','Business Analyst','Kerala','7523588978','habingeorge12','habingeorgepassword','2024-07-24 17:37:14','IND','Habin George'),(18,'Roshan','Andrews','roshan@polus.com','Trainee','Kerala','8975799965','roshan','roshanpassword','2024-07-25 10:04:12','IND','Roshan Andrews'),(19,'Polus','Solutions','polus@email.com','company','kerala','2587413695','polus','Polus@123','2024-07-25 10:36:49','IND','Polus Solutions');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
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
