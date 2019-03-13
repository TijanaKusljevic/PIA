CREATE DATABASE  IF NOT EXISTS `partneri` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `partneri`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: partneri
-- ------------------------------------------------------
-- Server version	5.6.40-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `donatorski`
--

DROP TABLE IF EXISTS `donatorski`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donatorski` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kompanija` int(11) NOT NULL,
  `vrednost` decimal(8,0) NOT NULL,
  `opis` mediumtext NOT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `datum` date NOT NULL,
  `status` int(11) NOT NULL,
  `istice` date NOT NULL,
  `komentar` tinytext,
  `paket` int(11) NOT NULL,
  `isporuka` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kljuc1_idx` (`kompanija`),
  KEY `kljuc2_idx` (`paket`),
  KEY `kljuc3_idx` (`status`),
  CONSTRAINT `kljuc1` FOREIGN KEY (`kompanija`) REFERENCES `kompanija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc2` FOREIGN KEY (`paket`) REFERENCES `paket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc3` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donatorski`
--

LOCK TABLES `donatorski` WRITE;
/*!40000 ALTER TABLE `donatorski` DISABLE KEYS */;
INSERT INTO `donatorski` VALUES (1,11,4500,'Racunari za laboratorije',15,'2017-06-02',11,'2019-06-02','Kompanija koristi fotografije laboratorija za sopstvenu promociju.',3,'2017-06-02'),(2,8,13000,'Oprema za laboratorije za studente Telekomunikacija',8,'2017-04-11',11,'2018-04-11','',4,'2017-04-11'),(3,5,1000,'Mikrokontroleri najviseg kvaliteta za osetvljiva racunanja.',3,'2017-10-25',11,'2018-10-25','',5,'2017-10-25'),(4,16,8000,'Kamere za video nadzor',10,'2016-08-01',7,'2018-08-01','Kamere imaju garanciju 5 godina',2,'2016-08-01'),(5,19,12000,'Racunari za laboratorije',20,'2016-08-19',12,'2018-08-19','',1,'2016-08-19'),(6,22,600,'Stolice',40,'2017-09-29',11,'2018-09-29','',5,'2017-09-29'),(7,28,8000,'Projektori i platna',10,'2016-12-01',12,'2018-12-01','Vrlo visko kvalitet',2,'2016-12-01'),(8,27,10500,'Klima uredjaji',50,'2016-11-19',12,'2018-11-19','Los kvalitet',1,'2016-11-19'),(9,13,5000,'Mikrokontroleri za biomedicinska ispitivanja',30,'2015-01-06',12,'2017-01-06','',3,'2015-01-28');
/*!40000 ALTER TABLE `donatorski` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ima`
--

DROP TABLE IF EXISTS `ima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ima` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stavka` varchar(256) NOT NULL,
  `paket` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kljuc4_idx` (`paket`),
  KEY `kljuc5_idx` (`stavka`),
  CONSTRAINT `kljuc4` FOREIGN KEY (`paket`) REFERENCES `paket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc5` FOREIGN KEY (`stavka`) REFERENCES `stavka` (`naziv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ima`
--

LOCK TABLES `ima` WRITE;
/*!40000 ALTER TABLE `ima` DISABLE KEYS */;
INSERT INTO `ima` VALUES (1,'baner',1),(2,'dogadjaji',1),(3,'logo na panou',1),(4,'logo u brosuri',1),(5,'predavanja',1),(6,'dogadjaji',2),(7,'logo na panou',2),(8,'predavanja',2),(9,'dogadjaji',3),(10,'logo na panou',3),(11,'baner',4),(12,'dogadjaji',4),(13,'logo na panou',4),(14,'logo u brosuri',4),(15,'predavanja',4),(16,'baner',5);
/*!40000 ALTER TABLE `ima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kompanija`
--

DROP TABLE IF EXISTS `kompanija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kompanija` (
  `id` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `adresa` varchar(64) NOT NULL,
  `grad` varchar(45) NOT NULL,
  `postanski` int(11) NOT NULL,
  `zemlja` varchar(45) NOT NULL,
  `ziro` varchar(45) NOT NULL,
  `valuta` varchar(8) NOT NULL,
  `pib` varchar(45) NOT NULL,
  `telefon1` varchar(45) NOT NULL,
  `email1` varchar(45) DEFAULT NULL,
  `email2` varchar(45) DEFAULT NULL,
  `email3` varchar(45) DEFAULT NULL,
  `email4` varchar(45) DEFAULT NULL,
  `email5` varchar(45) DEFAULT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `kemail` varchar(45) NOT NULL,
  `ktelefon` varchar(45) NOT NULL,
  `telefon2` varchar(45) DEFAULT NULL,
  `telefon3` varchar(45) DEFAULT NULL,
  `telefon4` varchar(45) DEFAULT NULL,
  `telefon5` varchar(45) DEFAULT NULL,
  `logo` varchar(1024) DEFAULT NULL,
  `opis` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kompanija`
--

LOCK TABLES `kompanija` WRITE;
/*!40000 ALTER TABLE `kompanija` DISABLE KEYS */;
INSERT INTO `kompanija` VALUES (1,'Nordeus','Makednoska 1','Beograd',11000,'Srbija','1234567','RSD','12345','064123456','nordeus@hotmail.com','','','','','Ana','Ilic','ana@yahoo.com','064123456','','','','','nordeus-logo-r225x.jpg','Nordeus je nezavisna kompanija za razvoj video igara i tvorac igre Top Eleven.'),(2,'Microsoft','Svetogorska 1','Beograd',11000,'SAD','1234567','USD','12345','064123456','microsoft@hotmail.com','','','','','Ivan','Djuric','ivan@yahoo.com','064123456','','','','','microsoft_144x86.jpg','Microsoft Development Center Serbia - MDCS je razvojnii centar komanije se nalazi u Srbiji'),(3,'Asseco','Budimska 1','Beograd',11000,'Holandija','1234567','EUR','12345','064123489','asseco@hotmail.com','','','','','Tanja','Arsic','tanja@yahoo.com','064190456','','','','','asseco-logo.jpg','Asseco je komanija koja se pretezno bavi sistemima placanja i njihovom softverskom implementacijom'),(4,'Mera','Nusiceva 1','Kragujevac',11000,'Rusija','12398767','RUB','12315','064123479','mera@hotmail.com','mera1@hotmail.com','mera2@hotmail.com','','','Vida','Pesic','vida@yahoo.com','064190986','','','','','mera.jpg','Firma koja se bavi pisanjem softvera sa velikim brojem zaposlenih sirom Evrope.'),(5,'Arduino','Glavna 1','Subotica',450261,'Italija','32398067','EUR','42910','063723479','arduino@hotmail.com','','','','','Simona','Milic','simona@yahoo.com','064298786','069887766','','','','arduino.jpg','Firma koja pravi mikrokontrolere.'),(6,'BMW','Vuka Karadzica 1','Novi Sad',400107,'Nemacka','32198667','EUR','42919','063723479','bmw@hotmail.com','','','','','Rajko','Vulic','rajko@yahoo.com','064298906','','','','','BMW.jpg','Firma se bavi proizvodnjom automobila i rezervnih delova.'),(7,'RT-RK','Nikole Tesle 1','Kragujevac',550601,'Srbija','32197767','RSD','22919','063723000','rtrk@hotmail.com','rtrk1@hotmail.com','rtrk2@hotmail.com','rtrk3@hotmail.com','rtrk4@hotmail.com','Andjela','Saric','andjela@yahoo.com','064112233','063123456','064567890','062345678','060778899','RT-RK_logo.png','Veliki broj zaposlenih u Srbiji.'),(8,'Telenor','Alekse Nenadovica 3','Beograd',11000,'Srbija','1090876','RSD','33445','061234567','telenor@gmail.com','telenor1@gmail.com','telenor2@gmail.com','telenor3@gmail.com','telenor4@gmail.com','Jana','Nedic','jana@hotmail.com','066778899','069887732','','','','telenor.jpg','Jedan od najvecih mobilnih provajdera u zemlji.'),(9,'ViP','Njegoseva 3','Priboj',31330,'Srbija','1090899','RSD','30045','061234555','vip@gmail.com','','','','','Igor','Savic','igor@hotmail.com','066776599','066887737','','','','vip.jpg','Najisplativiji paketii najveci kvalitet telekomunikacionih usluga.'),(10,'Genius','Djure Jaksica 3','Novi Sad',21000,'Kina','1090889','CNY','30099','061234645','genius@gmail.com','','','','','Vuk','Tasic','vuk@hotmail.com','0661112299','','','','','genius.png','Firma koja proizvodi najrazlicitije elektronske uredjaje.'),(11,'Lenovo','Desanke Maksimovic 3','Valjevo',14000,'Kina','2233445','CNY','10987','061987654','lenovo@gmail.com','','','','','Sima','Tomasevic','sima@hotmail.com','0661112299','','','','','lenovo.png','Kompanija koja je za jako kratko vreme osvojila svetsko trziste racunarske opreme.'),(12,'Intel','Nadezde Petrovic 3','Kragujevac',34000,'SAD','2200998','USD','10980','0612233446','intel@gmail.com','','','','','Petar','Nikolic','petar@hotmail.com','063456789','','','','','intel.png','Vodeca kompanija u proizvoodnji i razvoju procesora.'),(13,'Texas Instruments','Diljska 3','Beograd',11000,'SAD','2299887','USD','11111','061223889','texas@gmail.com','','','','','Mia','Goranovic','mia@hotmail.com','063997744','','','','','ti.png','Americka kompanija za proizvodnju mikrokontrolera.'),(14,'Siemens','Zivojina Misica 5','Beograd',11000,'Nemacka','887799001','EUR','14325','069874433','siemens@gmail.com','','','','','Lara','Mirkovic','lara@gmail.com','069875544','','','','','siemens.png','Kompanija koja je jako dugo na svetskom trzistu elektronskih uredjaja.'),(15,'Tesla','Milutina Milankovica 5','Beograd',11000,'Srbija','887788001','RSD','14395','069874422','tesla@gmail.com','','','','','Mirko','Mirkovic','mirko@gmail.com','069870044','','','','','tesla.png','Mobilni telefoni koji se sastavljaju u Srbiji.'),(16,'Samsung','Milunke Savic 5','Beograd',11000,'Srbija','287788331','RSD','24399','066174422','samsung@gmail.com','','','','','Ivana','Ivkovic','ivana@gmail.com','069871114','','','','','samsung.png','Kompanija koja proizvodi sve od igle do lokomotive. Lider na svetskom trzistu.'),(17,'Sony ','Triglavska 5','Beograd',11000,'Japan','286688331','RSD','24399','0661234522','sony@gmail.com','','','','','Damir','Vujovic','damir@gmail.com','065237134','','','','','sony.png','Jedna od vodecih kompanija u proizvodnji racunarske opreme u svetu.'),(18,'Apple','Ostreljska 5','Beograd',11000,'SAD','281653331','USD','24311','0651234529','apple@gmail.com','','','','','Sanja','Mitric','sanja@gmail.com','065230004','','','','','apple.jpg','Jedna od najprofitabilnijih kompanija koje trenutno posluju.'),(19,'Havlett-Packard','Generala Aracica 5','Beograd',11000,'SAD','781658831','USD','64731','0621232229','hp@gmail.com','','','','','Nina','Trifkovic','nina@gmail.com','065239904','','','','','hp.jpg','Lider na svetskom trzistu racunara.'),(20,'Dell','Jurija Gagarina 5','Beograd',11000,'SAD','781659871','USD','64722','0621639829','dell@gmail.com','','','','','Iva','Radan','iva@gmail.com','065238404','','','','','dell.png','Komapnija koja ulaze mnogo u razvoj i kvalitet racunara i racunarske opreme.'),(21,'Komercijalna banka','Svetog Save 5','Beograd',11000,'Srbija','781685371','RSD','64704','0621677829','kombank@gmail.com','','','','','Ranko','Popovic','ranko@gmail.com','065778804','','','','','kb.jpg','Domaca banka sa najvecim brojem klijenata.'),(22,'Endava','Djure Danicica 3','Novi Sad',21000,'SAD','1090889','USD','31239','061230005','endava@gmail.com','','','','','Tomislav','Ivin','toma@hotmail.com','0669836745','','','','','endava.png','Komanija sa velikim brojem zaposlenih u Srbiji.'),(23,'NCR','Hilandarska 3','Novi Sad',21000,'SAD','10908014','USD','31238','061232675','ncr@gmail.com','','','','','Rada','Radic','rada@hotmail.com','0660986745','','','','','NCR_logo_green_block.png','Jedna od vodecih komanija na svetskom trzistu u oblasti razvoja softvera.'),(24,'Google','Glavna 3','Novi Sad',21000,'SAD','10908987','USD','31238','061232675','google@gmail.com','','','','','Jovan','Jovic','joca@hotmail.com','0660903445','','','','','google.png',NULL),(25,'Facebook','Vojvode Putnika 3','Novi Sad',21000,'SAD','10064987','USD','19278','061238775','facebook@gmail.com','','','','','Ljilja','Markovic','ljilja@hotmail.com','061093445','','','','','facebook.png',NULL),(26,'Youtube','Vojvode Putnika 9','Novi Sad',21000,'SAD','10064901','USD','19878','061288775','yt@gmail.com','','','','','Isidora','Miletic','isi@hotmail.com','061093409','','','','','youtube.png',NULL),(27,'Gorenje','Ilije Garasanina 12','Beograd',11000,'Slovenija','10061201','EUR','29778','061264375','gorenje@gmail.com','','','','','Milica','Ninkovic','milica@hotmail.com','061098765','','','','','gorenje.png','Lider na domacem trzistu u proizvodnji bele tehnike.'),(28,'Bosch','Spanskih boraca 6','Beograd',11000,'Nemacka','10061231','EUR','29723','061212375','bosch@gmail.com','','','','','Milica','Radojevic','milicaR@hotmail.com','061098965','','','','','bosch.jpg','Kompanija koja proizvodi najrazlicitije uredjaje.'),(29,'Twitter','Kralja Milana 6','Beograd',11000,'SAD','10061298','USD','29723','061212375','twitter@gmail.com','twitter1@gmail.com','','','','Vukan','Vukovic','vukan@hotmail.com','061924536','','','','','twitter.png',NULL),(30,'EPS','Masarikova 9','Beograd',11000,'Srbija','123987668','RSD','15478','0654321789','eps@gmail.com','','','','','Janko','Jakovljevic','janko@yahoo.com','065789432','','','','','logoEPSLogo_of_Elektroprivreda_Srbije.svg.png','Elektrodistribucija Srbije snabdeva skoro celu Srbiju elektricnomenergijom.');
/*!40000 ALTER TABLE `kompanija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novcani`
--

DROP TABLE IF EXISTS `novcani`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novcani` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kompanija` int(11) NOT NULL,
  `paket` int(11) NOT NULL,
  `datum` date NOT NULL,
  `status` int(11) NOT NULL,
  `poslato` tinyint(1) NOT NULL,
  `uplaceno` tinyint(1) NOT NULL,
  `datumUplate` date DEFAULT NULL,
  `istice` date NOT NULL,
  `komentar` mediumtext,
  `vrednost` decimal(8,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kljuc6_idx` (`kompanija`),
  KEY `kljuc7_idx` (`paket`),
  KEY `kljuc8_idx` (`status`),
  CONSTRAINT `kljuc6` FOREIGN KEY (`kompanija`) REFERENCES `kompanija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc7` FOREIGN KEY (`paket`) REFERENCES `paket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc8` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novcani`
--

LOCK TABLES `novcani` WRITE;
/*!40000 ALTER TABLE `novcani` DISABLE KEYS */;
INSERT INTO `novcani` VALUES (1,1,1,'2016-11-10',7,1,1,'2016-11-11','2018-11-10','',10000),(2,4,2,'2016-08-02',12,1,1,'2016-08-09','2018-08-02','',7500),(3,6,4,'2015-02-04',12,1,1,'2015-01-30','2016-02-04','',6000),(4,8,3,'2018-05-31',9,0,0,'2018-06-01','2020-05-31','',5000),(5,7,3,'2018-01-24',11,1,1,'2018-02-09','2020-01-24','Kasnjenje sa uplatom',5000),(6,2,1,'2017-10-30',12,1,1,'2017-10-30','2019-10-30','',10000),(7,9,3,'2016-04-05',12,1,1,'2016-04-05','2018-04-05','',5000),(9,3,2,'2016-08-25',12,1,1,'2016-09-01','2018-08-25','',7500),(10,10,5,'2017-09-15',12,1,1,'2017-09-15','2018-09-15','',700),(11,14,3,'2016-10-14',12,1,1,'2016-10-28','2018-10-14','',5000),(12,15,3,'2016-07-22',12,1,1,'2016-07-22','2018-07-22','',5000),(13,17,5,'2017-07-24',11,1,1,'2017-07-22','2018-07-24','',700),(14,18,2,'2016-08-10',12,1,1,'2016-08-10','2018-08-10','',7500),(15,20,3,'2016-09-06',12,1,1,'2016-08-10','2018-09-06','',5000),(16,21,5,'2017-11-02',11,1,1,'2017-11-03','2018-11-02','',700),(17,23,1,'2016-11-30',12,1,1,'2016-11-30','2018-11-30','',10000),(18,24,5,'2017-11-09',11,1,1,'2017-11-11','2018-11-09','',700),(19,25,3,'2016-11-28',12,1,1,'2017-11-30','2018-11-28','',5000),(20,26,3,'2016-11-25',12,1,1,'2017-11-30','2018-11-25','',5000),(21,29,3,'2016-07-16',12,1,1,'2016-07-28','2018-07-16','',5000);
/*!40000 ALTER TABLE `novcani` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `odgovoran`
--

DROP TABLE IF EXISTS `odgovoran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `odgovoran` (
  `username` varchar(45) NOT NULL,
  `kompanija` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `kljuc9_idx` (`username`),
  KEY `kljuc10_idx` (`kompanija`),
  CONSTRAINT `kljuc10` FOREIGN KEY (`kompanija`) REFERENCES `kompanija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kljuc9` FOREIGN KEY (`username`) REFERENCES `tabela` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odgovoran`
--

LOCK TABLES `odgovoran` WRITE;
/*!40000 ALTER TABLE `odgovoran` DISABLE KEYS */;
INSERT INTO `odgovoran` VALUES ('Tijana',1,1),('Tijana',2,2),('Tijana',3,3),('Tijana',4,4),('Tijana',5,5),('Tijana',6,6),('Tijana',7,7),('Stevan',8,8),('Stevan',9,9),('Stevan',10,10),('Stevan',11,11),('Stevan',12,12),('Stevan',13,13),('Stevan',14,14),('Stevan',15,15),('Stevan',16,16),('Stevan',17,17),('Stevan',18,18),('Stevan',19,19),('Stevan',20,20),('Stevan',21,21),('Tijana',22,22),('Tijana',23,23),('Tijana',24,24),('Tijana',25,25),('Tijana',26,26),('Tijana',27,27),('Tijana',28,28),('Tijana',29,29),('Tijana',30,30);
/*!40000 ALTER TABLE `odgovoran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oglas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(1024) NOT NULL,
  `opis` tinytext NOT NULL,
  `vrsta` tinyint(2) NOT NULL,
  `datum` varchar(45) NOT NULL,
  `kompanija` int(11) NOT NULL,
  `fajl` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kljuc11_idx` (`kompanija`),
  CONSTRAINT `kljuc11` FOREIGN KEY (`kompanija`) REFERENCES `kompanija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oglas`
--

LOCK TABLES `oglas` WRITE;
/*!40000 ALTER TABLE `oglas` DISABLE KEYS */;
INSERT INTO `oglas` VALUES (1,'Java junior developer','Rad sa serverskom stranom aplikacije',3,'2018-06-04 18:26:36.136',1,NULL),(2,'C++ developer','Potrebno dvogodisnje radno iskustvo',2,'2018-06-04 18:27:25.265',2,NULL),(3,'Prikupljanje i obrada podataka','Potrebno poznavanje alata za obradu podataka',2,'2018-06-07 18:55:26.85',26,NULL),(4,'Projektovanje ves masina','Potrebno dobro poznavanje elektronike',2,'2018-06-10 18:30:55.355',28,'oglasInzenjer ves masinaimage-060e2f66811f0d1ac5c4ca15d48b90fc4360ddaaea40a155ab25c23c7bf50926-V.jpg');
/*!40000 ALTER TABLE `oglas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paket`
--

DROP TABLE IF EXISTS `paket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `vrednost` int(11) NOT NULL,
  `trajanje` int(11) NOT NULL,
  `max` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paket`
--

LOCK TABLES `paket` WRITE;
/*!40000 ALTER TABLE `paket` DISABLE KEYS */;
INSERT INTO `paket` VALUES (1,'Zlatni partner',10000,2,5),(2,'Srebrni partner',7500,2,20),(3,'Bronzani partner',5000,2,50),(4,'Zvanicni partner',6000,1,5),(5,'Partner',700,1,100);
/*!40000 ALTER TABLE `paket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predavanje`
--

DROP TABLE IF EXISTS `predavanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predavanje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(1024) NOT NULL,
  `naslove` varchar(1024) DEFAULT NULL,
  `opis` mediumtext NOT NULL,
  `opise` mediumtext,
  `datum` varchar(45) NOT NULL,
  `sala` varchar(45) NOT NULL,
  `ime` varchar(256) NOT NULL,
  `biografija` mediumtext,
  `kompanija` int(11) NOT NULL,
  `fajl` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kljuc12_idx` (`kompanija`),
  CONSTRAINT `kljuc12` FOREIGN KEY (`kompanija`) REFERENCES `kompanija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predavanje`
--

LOCK TABLES `predavanje` WRITE;
/*!40000 ALTER TABLE `predavanje` DISABLE KEYS */;
INSERT INTO `predavanje` VALUES (1,'Postani Java developer','','Predavanje o najnovijim Java tehnologijama','','2018-07-18 12:40:00','65','Igor Jovanovic','',2,NULL),(2,'C# programiranje','C# programming','Predavanje o C# programskom jeziku','','2018-04-24 17:45:00','56','Ana Savic','Prefosor na MIT-u',23,NULL),(3,'Android programiranje','','Programiranje u Android okruzenju','','2018-03-06 12:00:00','313','Jana  Ljubinkovic','',23,NULL),(4,'Internet stvari','Internet of things','Najnoviji trendovi u programiranju uredjaja','','2018-04-25 09:00:00','56','Stanko Stankovic','',27,NULL),(5,'Pravljenje hes memorija','','Nove tehnike za hesiranje podataka','','2018-05-08 18:00:00','310','Nikola Nikolic','',19,NULL),(6,'Simulacija razgovora za posao','','Demonstracija razgovora za posao','','2018-02-07 17:00:00','56','Iva Ivkovic','',1,'predSimulacija razgovora za posao20170712_125739.jpg'),(7,'Cuvanje velike kolicine podataka','','Diskretna matematika koja stoji iza skladistenja i obrade velikekolicine podataka','','2018-02-02 17:00:00','56','Ivan Vuletic','',25,'');
/*!40000 ALTER TABLE `predavanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opis` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (7,'pripremljen'),(8,'poslat kompaniji'),(9,'potpisan od strane Fakulteta'),(10,'potpisan od strane kompanije'),(11,'potpisan od obe strane'),(12,'predat arhivi Fakulteta');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stavka` (
  `naziv` varchar(256) NOT NULL,
  `opis` mediumtext NOT NULL,
  PRIMARY KEY (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
INSERT INTO `stavka` VALUES ('baner','baner kompanije u zvanicnoj brosuri fakulteta'),('dogadjaji','mogucnost oglasavanja dogadjaja, takmicenja i drugih strucnih skupova'),('logo na panou','logo kompanije na reklamnom panou fakulteta'),('logo u brosuri','logo kompanije u brosuri fakulteta'),('predavanja','mogucnost odrzavanja strucnih predavanja');
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabela`
--

DROP TABLE IF EXISTS `tabela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabela` (
  `username` varchar(45) NOT NULL,
  `password` mediumtext NOT NULL,
  `ime` varchar(32) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `institucija` varchar(64) NOT NULL,
  `pol` int(11) NOT NULL,
  `datum` date NOT NULL,
  `odobren` int(11) NOT NULL,
  `tip` int(11) NOT NULL,
  `linkedin` varchar(256) DEFAULT NULL,
  `slika` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabela`
--

LOCK TABLES `tabela` WRITE;
/*!40000 ALTER TABLE `tabela` DISABLE KEYS */;
INSERT INTO `tabela` VALUES ('Aleksa','b508f52a36df36f3237ef21f8e43c3cd9338c485af3c841e38f67b600010842ca7786113ee2653b0c01efb8ce3eadbdca8893e65ece72a5dc0cebe4224779677','Aleksa','Negic','aleksa@hotmail.com','ETF',0,'1995-07-04',0,0,'https://www.linkedin/aleksa',NULL),('Danica','25426576d60fe996db3634fc38de32d9694a872c4e8009e7506707195dbf95415798fddf2d17b4fa549225e9ff90576a1a82d87202b88d3adab32bd92e936237','Danica','Mitrovic','danica@gmail.com','Ministarstvo prosvete',1,'1967-11-30',0,0,NULL,NULL),('Jelena','58fdb5c4a045deb216d6cacdafa5c2961faaffa8288f21cb93e4d555078bdc7e41d91e1a81a81ff5ff683497d55c54bb204ae77c7750e5781dd09a4540071527','Jelena','Milenkovic','jelena@gmail.com','Rektorat',1,'1988-09-17',1,1,NULL,NULL),('Ljubica','44d3840b985aa3c7e58a539ee07148096e895c5a8b4724fa149a0c8ff8eb0691ed9f3f916f7c7f6bbfc1af3e59f3c0784b9a67ff20305f164060d5d3f0702413','Ljubica','Matic','ljubica@gmail.com','ETF',1,'1959-06-06',0,0,'',NULL),('Matija','5628483d73abdc13f759b3deee724552cc2ddd1ede1e703c734f15b99e67053038f86484c8aa3ebb4ab2557114689fe7e7901369df71c8bc24ec1e9aa6c6a71c','Matija','Bjelogrlic','matija@gmail.com','FON',0,'1975-10-23',0,0,NULL,NULL),('Milica','a9456ab3df7f51946cbb3da9d9537b877ae9a25a1c7e92cc9bb3b6d36beb0057ccf586d72416fc1e28e29681d139fcf86252dfb35054500beda86a4b9c10a9cc','Milica','Vuckovic','milica@gmail.com','ETF',1,'1960-11-19',0,0,NULL,NULL),('Nevena','2a125377940ea6f61cd4b0390fd869af3dfd3dac25e42b57401d69c467d695150414694dd447b632081777454856c5c4bfa0d5f592ed97a2299f464a2c2c2da6','Nevena','Vukajlovic','nevena@gmail.com','ETF',1,'1984-10-09',1,2,NULL,NULL),('Stefan','69db5d5bb9b032829fb5acc4559d444344c389e2bc503ef0830de8830aaad29b16f37b0f35d84e2cd9430c0bf4f4bf28e1f8668f3c03ee0bc07df6cddeb137c7','Stefan','Bjelogrlic','stefan@gmail.com','FON',0,'1958-06-05',0,0,'https://www.linkedin/stefan','logoStefanvuk-kostic-02.jpg'),('Stevan','17487bb1842bb73c19518b63930d0a5f4a87b5c79eba1c397956e4d9de698610f0eb40404ce85d37a241500e2ec23314da173603eb0595647b1631cc8007bbe9','Stevan','Rakovic','stevan@gmail.com','ETF',0,'1979-11-24',1,0,NULL,NULL),('Tijana','f5bcdd42d9fac9706bcc158bac834bc60d35b42d08aea9b3bd6f35f6be6d49e4fe5e5bd0fa2b9790dd7735f75136b42b00cf33288e8e15c45b7d7d1d4b17ae25','Tijana','Kusljevic','tijana@gmail.com','ETF',1,'1993-09-26',1,0,NULL,NULL);
/*!40000 ALTER TABLE `tabela` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-19 16:44:32
