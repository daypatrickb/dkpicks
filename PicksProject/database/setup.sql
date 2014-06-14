--
-- 1. Create user "picks".
--
create user 'picks' identified by 'picks';

--
-- 2. create database
--
create database `picks`;


--
-- 3. select database
--
use 'picks';


--
-- Table structure for table `t_player`
--

DROP TABLE IF EXISTS `t_player`;
CREATE TABLE `t_player` (
  `id_player` int(11) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(45) DEFAULT NULL,
  `id_team` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_player`),
  UNIQUE KEY `id_player_UNIQUE` (`id_player`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `t_player`
--

LOCK TABLES `t_player` WRITE;
/*!40000 ALTER TABLE `t_player` DISABLE KEYS */;
INSERT INTO `t_player` VALUES 
(1,'Dylan',18,1),
(2,'Pat',18,2),
(3,'Jeff',18,3),
(4,'Dave',20,4),
(5,'Dennis',6,5),
(6,'Justin',17,6),
(7,'Mexisnow',-1,NULL);
/*!40000 ALTER TABLE `t_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `google_id` char(21) DEFAULT NULL,
  `id_player` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES 
(1,'Dylan','118273317212870330010',1),
(2,'Pat','104760251617423522347',2),
(3,'Jeff','103851251872932007865',3),
(4,'Dave','113587464232141390323',4),
(5,'Dennis','109064731349904213414',5),
(6,'Justin','105630898613663508672',6);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

