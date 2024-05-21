-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 18 mai 2024 à 01:06
-- Version du serveur : 5.7.40
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_cab`
--

-- --------------------------------------------------------

--
-- Structure de la table `cab_user`
--

DROP TABLE IF EXISTS `cab_user`;
CREATE TABLE IF NOT EXISTS `cab_user` (
  `USER_ID` int(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `privilage` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cab_user`
--

INSERT INTO `cab_user` (`USER_ID`, `username`, `password`, `email`, `privilage`) VALUES
(1, 'y.hadjiedj', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL, 'medecin');

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

DROP TABLE IF EXISTS `dossier`;
CREATE TABLE IF NOT EXISTS `dossier` (
  `DOSS_ID` int(100) NOT NULL AUTO_INCREMENT,
  `DOSS_NOM` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `DOSS_COMM` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FK_DOSS_PAT` int(11) NOT NULL,
  PRIMARY KEY (`DOSS_ID`),
  KEY `FK_DOSS_PAT` (`FK_DOSS_PAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fichier`
--

DROP TABLE IF EXISTS `fichier`;
CREATE TABLE IF NOT EXISTS `fichier` (
  `FICH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FICH_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FK_FICH_PAT` int(11) NOT NULL,
  PRIMARY KEY (`FICH_ID`),
  KEY `FK_FICH_PAT` (`FK_FICH_PAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lien_med_ord`
--

DROP TABLE IF EXISTS `lien_med_ord`;
CREATE TABLE IF NOT EXISTS `lien_med_ord` (
  `LIEN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIEN_DOSE` int(11) DEFAULT NULL,
  `LIEN_DUREE` int(11) DEFAULT NULL,
  `LIEN_ETAT` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LIEN_NB_DOSE` int(11) DEFAULT NULL,
  `LIEN_TEMPS` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FK_MED_LIEN` int(11) NOT NULL,
  `FK_ORD_LIEN` int(11) NOT NULL,
  PRIMARY KEY (`LIEN_ID`),
  KEY `MED_ID` (`FK_MED_LIEN`,`FK_ORD_LIEN`),
  KEY `ORD_ID` (`FK_ORD_LIEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `MED_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MED_NOM` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `MED_FORME` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `MED_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `MED_PRIX` int(11) DEFAULT NULL,
  PRIMARY KEY (`MED_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

DROP TABLE IF EXISTS `ordonnance`;
CREATE TABLE IF NOT EXISTS `ordonnance` (
  `ORD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORD_DC` date DEFAULT NULL,
  `ORD_CREE_DATE` date DEFAULT NULL,
  `FK_ORD_PAT` int(11) NOT NULL,
  PRIMARY KEY (`ORD_ID`),
  KEY `FK_ORD_PAT` (`FK_ORD_PAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `PAT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAT_NOM` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PAT_PRENOM` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PAT_DDN` date NOT NULL,
  `PAT_ADRESSE` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PAT_GENRE` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PAT_SANG` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PAT_CREE_DATE` date DEFAULT NULL,
  `FK_USER_PAT` int(11) NOT NULL,
  PRIMARY KEY (`PAT_ID`),
  KEY `FK_USER_PAT` (`FK_USER_PAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `RDV_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RDV_ETAT` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `RDV_DATE` date DEFAULT NULL,
  `RDV_HEURE` time DEFAULT NULL,
  `RDV_PRIORITE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `RDV_DESCR` varchar(999) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FK_RDV_PAT` int(11) NOT NULL,
  PRIMARY KEY (`RDV_ID`),
  KEY `FK_RDV_PAT` (`FK_RDV_PAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD CONSTRAINT `dossier_ibfk_1` FOREIGN KEY (`FK_DOSS_PAT`) REFERENCES `patient` (`PAT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD CONSTRAINT `fichier_ibfk_1` FOREIGN KEY (`FK_FICH_PAT`) REFERENCES `patient` (`PAT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `lien_med_ord`
--
ALTER TABLE `lien_med_ord`
  ADD CONSTRAINT `lien_med_ord_ibfk_1` FOREIGN KEY (`FK_MED_LIEN`) REFERENCES `medicament` (`MED_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lien_med_ord_ibfk_2` FOREIGN KEY (`FK_ORD_LIEN`) REFERENCES `ordonnance` (`ORD_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ordonnance`
--
ALTER TABLE `ordonnance`
  ADD CONSTRAINT `ordonnance_ibfk_1` FOREIGN KEY (`FK_ORD_PAT`) REFERENCES `patient` (`PAT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`FK_USER_PAT`) REFERENCES `cab_user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `rendez_vous_ibfk_1` FOREIGN KEY (`FK_RDV_PAT`) REFERENCES `patient` (`PAT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
