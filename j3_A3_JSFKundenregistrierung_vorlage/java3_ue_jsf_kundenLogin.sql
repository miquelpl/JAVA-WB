-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 14. Jul 2015 um 15:24
-- Server Version: 5.5.36
-- PHP-Version: 5.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `java3`
--
CREATE DATABASE IF NOT EXISTS `java3` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `java3`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunde`
--

CREATE TABLE IF NOT EXISTS `kunde` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nachname` varchar(50) CHARACTER SET utf8 NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 NOT NULL,
  `passwort` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Daten für Tabelle `kunde`
--

INSERT INTO `kunde` (`id`, `vorname`, `nachname`, `username`, `passwort`, `email`) VALUES
(1, 'Max', 'Muster', 'max', '123', 'max@web.de'),
(2, 'Josef', 'Meier', 'josef', '123435', 'neier@web.de'),
(3, 'Franz', 'Schusbert', 'franz', '123', 'franz@we.de'),
(4, 'Ilse', 'Werner', 'ilse', '111', 'werner@we.de'),
(5, 'Felix', 'Hasa', 'felo', '2222', 'erer@wew.de'),
(9, 'Josef', 'Meier', 'hans', '3333', 'wurst@wew.de'),
(10, 'michael', 'schirmer', 'test', 'test', 'micha.schirmer@gmail.com'),
(11, 'test', 'test', 'test', 'test', 'micha.schirmer@gmail.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `passwort` varchar(50) NOT NULL,
  `vorname` varchar(50) NOT NULL,
  `nachname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `username`, `passwort`, `vorname`, `nachname`, `email`) VALUES
(2, 'anton', '1234', 'Antonio', 'Schuster', 'schuster@test.de'),
(3, 'max', '123', 'Max', 'Meier', 'meier@we.de'),
(12, 'petra', '12345', 'Petra', 'Meier', 'meierq@we.de'),
(13, 'petra', '12345', 'Petra', 'Meier', 'meierq@we.de'),
(14, 'petra', '12345', 'Petra', 'Meier', 'meierq@we.de'),
(15, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com'),
(16, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com'),
(17, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com'),
(18, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com'),
(19, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com'),
(20, 'maxe', '123', 'Max', 'Schultze', 'yxaq@ewb.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
