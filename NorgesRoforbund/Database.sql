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

DROP DATABASE IF EXISTS norges_roforbund;
CREATE DATABASE norges_roforbund;
USE norges_roforbund;

--
-- Table structure for table `account_club`
--

DROP TABLE IF EXISTS `account_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `account_club` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_club`
--

LOCK TABLES `account_club` WRITE;
/*!40000 ALTER TABLE `account_club` DISABLE KEYS */;
INSERT INTO `account_club` VALUES (37,'aalesunds'),(26,'alvøen'),(1,'arbeidernes'),(42,'arendals'),(21,'årungen'),(27,'askøy'),(3,'bærum'),(45,'bergen medisinsk'),(28,'bergens'),(2,'bestumkilen'),(29,'brønnøy'),(44,'bsi'),(4,'christiania'),(10,'drammen'),(30,'fana'),(5,'fornebu'),(17,'fredriksstad'),(18,'haldens'),(22,'hamar'),(31,'holmen'),(11,'horten'),(23,'kongsvinger'),(43,'kristiansand'),(38,'kristiansund'),(32,'kvinneherad'),(12,'larvik'),(24,'lillehammer'),(19,'moss'),(39,'namsos'),(46,'nhh_crew'),(40,'nidaros'),(6,'nsr'),(47,'ntnui'),(48,'oktagon'),(7,'ormsund'),(33,'os'),(8,'oslo'),(49,'oslostudentenes_idrettsklubb'),(13,'porsgrunn'),(25,'randsfjorden'),(14,'risør'),(15,'sandefjord'),(20,'sarpsborg'),(50,'sjøkrigsskolen'),(9,'skullerud'),(36,'stavanger'),(35,'terje_viken'),(16,'tønsberg'),(41,'trondhjems'),(34,'voss');
/*!40000 ALTER TABLE `account_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `account_role` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `name` varchar(100) NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,'superuser'),(2,'trener'),(3,'utøver');
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_user`
--

DROP TABLE IF EXISTS `account_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `account_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` mediumblob NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(254) NOT NULL,
  `year_of_birth` int NOT NULL,
  `date_joined` datetime(6) NULL,
  `club_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `account_user_club_id_950bf5a5_fk_account_club_id` (`club_id`),
  CONSTRAINT `account_user_club_id_950bf5a5_fk_account_club_id` FOREIGN KEY (`club_id`) REFERENCES `account_club` (`id`),
  KEY `account_user_role_id_950bf5a5_fk_account_role_id` (`role_id`),
  CONSTRAINT `account_user_role_id_950bf5a5_fk_account_role_id` FOREIGN KEY (`role_id`) REFERENCES `account_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_user`
--

LOCK TABLES `account_user` WRITE;
/*!40000 ALTER TABLE `account_user` DISABLE KEYS */;
INSERT INTO `account_user` VALUES (1,'','NR','NR','nr@nr.com',1980,'2021-01-14 05:45:47.000000',NULL,1,'C308375024AA2C143A307970A857EF47'),(2,'','Ammar','Haddad','ammar@gmail.com',1996,'2021-01-14 05:45:47.000000',43,2,'C308375024AA2C143A307970A857EF47'),(3,'','Issa','Alissa','issa@gmail.com',1990,'2021-01-14 05:45:48.000000',43,3,'C308375024AA2C143A307970A857EF47'),(4,'','Halldor','Ulland','halldor@gmail.com',1997,'2021-01-14 05:45:48.000000',43,3,'C308375024AA2C143A307970A857EF47'),(5,'','Rikke','Paulsen','rikke@gmail.com',1998,'2021-01-14 05:45:49.000000',43,3,'C308375024AA2C143A307970A857EF47');
/*!40000 ALTER TABLE `account_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry_class`
--

DROP TABLE IF EXISTS `registry_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `registry_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry_class`
--

LOCK TABLES `registry_class` WRITE;
/*!40000 ALTER TABLE `registry_class` DISABLE KEYS */;
INSERT INTO `registry_class` VALUES (3,'junior_a_gutter'),(4,'junior_a_jenter'),(5,'junior_b_gutter'),(6,'junior_b_jenter'),(7,'junior_c_gutter'),(8,'junior_c_jenter'),(2,'senior_kvinner'),(1,'senior_menn');
/*!40000 ALTER TABLE `registry_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry_test`
--

DROP TABLE IF EXISTS `registry_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `registry_test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `has_time` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry_test`
--

LOCK TABLES `registry_test` WRITE;
/*!40000 ALTER TABLE `registry_test` DISABLE KEYS */;
INSERT INTO `registry_test` VALUES (1,'5000_watt',0),(2,'5000_tid',1),(3,'2000_watt',0),(4,'2000_tid',1),(5,'60_watt',0),(6,'ligg_ro_prosent',0),(7,'ligg_ro_kg',0),(8,'knebøy_prosent',0),(9,'knebøy_kg',0),(10,'antall_beveg',0),(11,'sargeant_cm',0),(12,'3000_sek',0),(13,'3000_tid',1),(14,'antall_kr_hev',0),(15,'60_roergo',0);
/*!40000 ALTER TABLE `registry_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry_testdata`
--

DROP TABLE IF EXISTS `registry_testdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `registry_testdata` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL,
  `date_inserted` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `class_id` int DEFAULT NULL,
  `club_id` int DEFAULT NULL,
  `utøver_id` int DEFAULT NULL,
  `week_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `registry_testdata_year_week_id_class_id_utøver_id_06c5a2c5_uniq` (`year`,`week_id`,`class_id`,`utøver_id`),
  KEY `registry_testdata_week_id_3f9e243a_fk_registry_week_id` (`week_id`),
  KEY `registry_testdata_class_id_2d72d617_fk_registry_class_id` (`class_id`),
  KEY `registry_testdata_club_id_f6a3aabb_fk_account_club_id` (`club_id`),
  KEY `registry_testdata_utøver_id_a37908f9_fk_account_user_id` (`utøver_id`),
  CONSTRAINT `registry_testdata_class_id_2d72d617_fk_registry_class_id` FOREIGN KEY (`class_id`) REFERENCES `registry_class` (`id`),
  CONSTRAINT `registry_testdata_club_id_f6a3aabb_fk_account_club_id` FOREIGN KEY (`club_id`) REFERENCES `account_club` (`id`),
  CONSTRAINT `registry_testdata_utøver_id_a37908f9_fk_account_user_id` FOREIGN KEY (`utøver_id`) REFERENCES `account_user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `registry_testdata_week_id_3f9e243a_fk_registry_week_id` FOREIGN KEY (`week_id`) REFERENCES `registry_week` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry_testdata`
--

LOCK TABLES `registry_testdata` WRITE;
/*!40000 ALTER TABLE `registry_testdata` DISABLE KEYS */;
INSERT INTO `registry_testdata` VALUES (1,2020,'2021-01-14 05:45:49.000000','2021-01-14 05:45:49.000000',1,43,3,1),(2,2021,'2021-01-14 05:45:49.000000','2021-01-14 05:45:49.000000',2,43,4,2),(3,2021,'2021-01-14 05:45:49.000000','2021-01-14 05:45:49.000000',3,43,5,3),(4,2021,'2021-01-14 05:46:25.000000','2021-01-14 05:46:25.000000',8,43,3,2);
/*!40000 ALTER TABLE `registry_testdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry_testdataresult`
--

DROP TABLE IF EXISTS `registry_testdataresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `registry_testdataresult` (
  `id` int NOT NULL AUTO_INCREMENT,
  `test_result` double DEFAULT NULL,
  `test_time` time(6) DEFAULT NULL,
  `test_id` int DEFAULT NULL,
  `testdata_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `registry_testdataresult_test_id_d21396a9_fk_registry_test_id` (`test_id`),
  KEY `registry_testdatares_testdata_id_d9695540_fk_registry_` (`testdata_id`),
  CONSTRAINT `registry_testdatares_testdata_id_d9695540_fk_registry_` FOREIGN KEY (`testdata_id`) REFERENCES `registry_testdata` (`id`),
  CONSTRAINT `registry_testdataresult_test_id_d21396a9_fk_registry_test_id` FOREIGN KEY (`test_id`) REFERENCES `registry_test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry_testdataresult`
--

LOCK TABLES `registry_testdataresult` WRITE;
/*!40000 ALTER TABLE `registry_testdataresult` DISABLE KEYS */;
INSERT INTO `registry_testdataresult` VALUES (1,77,NULL,1,1),(2,88,NULL,9,1),(3,99,NULL,3,1),(4,90,NULL,8,2),(5,80,NULL,5,3);
/*!40000 ALTER TABLE `registry_testdataresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry_week`
--

DROP TABLE IF EXISTS `registry_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `registry_week` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` smallint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  CONSTRAINT `registry_week_chk_1` CHECK ((`number` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry_week`
--

LOCK TABLES `registry_week` WRITE;
/*!40000 ALTER TABLE `registry_week` DISABLE KEYS */;
INSERT INTO `registry_week` VALUES (1,2),(2,11),(3,44);
/*!40000 ALTER TABLE `registry_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'norges_roforbund'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-16  7:39:19
