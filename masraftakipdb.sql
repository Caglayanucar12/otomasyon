-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2021 at 10:02 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `masraftakipdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `islem`
--

CREATE TABLE `islem` (
  `tur` varchar(10) NOT NULL,
  `user` varchar(30) NOT NULL,
  `urun_adi` varchar(30) NOT NULL,
  `fiyat` int(11) NOT NULL,
  `adet` int(11) NOT NULL,
  `toplam_fiyat` int(11) NOT NULL,
  `islem_tarihi` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `islem`
--

INSERT INTO `islem` (`tur`, `user`, `urun_adi`, `fiyat`, `adet`, `toplam_fiyat`, `islem_tarihi`) VALUES
('Al', 'deneme', 'Lale', 25, 2, 50, '2021-09-05 at 17:57:07 EET'),
('Al', 'deneme', 'Papatya', 15, 13, 195, '2021-09-05 at 17:59:35 EET');

-- --------------------------------------------------------

--
-- Table structure for table `login_logs`
--

CREATE TABLE `login_logs` (
  `username` varchar(30) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_logs`
--

INSERT INTO `login_logs` (`username`, `date`) VALUES
('deneme', '2021-09-04 at 23:34:48 EET'),
('admin', '2021-09-04 at 23:35:12 EET'),
('admin', '2021-09-04 at 23:39:16 EET'),
('admin', '2021-09-04 at 23:44:30 EET'),
('admin', '2021-09-04 at 23:47:32 EET'),
('admin', '2021-09-04 at 23:50:20 EET'),
('admin', '2021-09-04 at 23:51:14 EET'),
('admin', '2021-09-04 at 23:58:41 EET'),
('admin', '2021-09-04 at 23:59:48 EET'),
('admin', '2021-09-05 at 00:08:27 EET'),
('admin', '2021-09-05 at 17:01:01 EET'),
('deneme', '2021-09-05 at 17:01:31 EET'),
('deneme', '2021-09-05 at 17:21:03 EET'),
('deneme', '2021-09-05 at 17:21:57 EET'),
('deneme', '2021-09-05 at 17:22:27 EET'),
('deneme', '2021-09-05 at 17:24:00 EET'),
('deneme', '2021-09-05 at 17:25:03 EET'),
('deneme', '2021-09-05 at 17:27:53 EET'),
('deneme', '2021-09-05 at 17:48:00 EET'),
('deneme', '2021-09-05 at 17:53:26 EET'),
('deneme', '2021-09-05 at 17:57:01 EET'),
('deneme', '2021-09-05 at 17:59:28 EET'),
('deneme', '2021-09-05 at 18:22:29 EET'),
('deneme', '2021-09-05 at 18:23:35 EET'),
('deneme', '2021-09-05 at 18:29:09 EET'),
('deneme', '2021-09-05 at 18:45:12 EET'),
('deneme', '2021-09-05 at 18:46:02 EET'),
('test', '2021-09-05 at 18:47:30 EET'),
('deneme', '2021-09-05 at 19:20:46 EET'),
('test', '2021-09-05 at 19:20:59 EET'),
('deneme', '2021-09-05 at 21:25:57 EET'),
('deneme', '2021-09-05 at 21:26:15 EET'),
('admin', '2021-09-05 at 21:27:29 EET'),
('admin', '2021-09-05 at 21:34:35 EET'),
('admin', '2021-09-05 at 21:36:02 EET'),
('admin', '2021-09-05 at 21:38:24 EET'),
('admin', '2021-09-05 at 21:39:21 EET'),
('admin', '2021-09-05 at 21:41:18 EET'),
('admin', '2021-09-05 at 21:41:36 EET'),
('admin', '2021-09-05 at 21:49:03 EET'),
('admin', '2021-09-05 at 21:52:27 EET'),
('admin', '2021-09-05 at 21:56:52 EET'),
('admin', '2021-09-05 at 22:04:13 EET'),
('admin', '2021-09-05 at 22:06:27 EET'),
('admin', '2021-09-05 at 22:08:50 EET'),
('admin', '2021-09-05 at 22:09:37 EET'),
('admin', '2021-09-05 at 22:18:38 EET'),
('admin', '2021-09-05 at 22:19:32 EET'),
('admin', '2021-09-05 at 22:19:49 EET'),
('admin', '2021-09-05 at 22:22:24 EET'),
('admin', '2021-09-05 at 22:36:14 EET'),
('admin', '2021-09-05 at 22:37:02 EET'),
('admin', '2021-09-05 at 22:37:51 EET'),
('admin', '2021-09-05 at 22:38:43 EET'),
('admin', '2021-09-05 at 22:41:30 EET'),
('admin', '2021-09-05 at 22:45:43 EET');

-- --------------------------------------------------------

--
-- Table structure for table `personel_islem`
--

CREATE TABLE `personel_islem` (
  `username` varchar(30) NOT NULL,
  `aciklama` varchar(50) NOT NULL,
  `fiyat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `personel_islem`
--

INSERT INTO `personel_islem` (`username`, `aciklama`, `fiyat`) VALUES
('deneme', 'Öğle Yemeği', 19),
('test', 'Kahvaltı', 29);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `urun_adi` varchar(30) NOT NULL,
  `fiyat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `urun_adi`, `fiyat`) VALUES
(1, 'Papatya', 15),
(3, 'Lale', 25);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `maas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `maas`) VALUES
('admin', 'admin', 0),
('deneme', '123', 700),
('test', '123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
