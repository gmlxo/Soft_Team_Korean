-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 21-11-24 03:05
-- 서버 버전: 10.4.20-MariaDB
-- PHP 버전: 7.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `teamdb`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `level_db`
--

CREATE TABLE `level_db` (
  `serve` int(11) NOT NULL,
  `main` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `level_db`
--

INSERT INTO `level_db` (`serve`, `main`) VALUES
(0, 0);

-- --------------------------------------------------------

--
-- 테이블 구조 `score`
--

CREATE TABLE `score` (
  `clickscore_DB` bigint(255) NOT NULL,
  `PerSecond_DB` int(11) NOT NULL,
  `click_DB` int(11) NOT NULL,
  `ScorePerSecond_DB` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `score`
--

INSERT INTO `score` (`clickscore_DB`, `PerSecond_DB`, `click_DB`, `ScorePerSecond_DB`) VALUES
(62, 0, 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
