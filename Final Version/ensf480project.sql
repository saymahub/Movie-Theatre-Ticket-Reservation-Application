-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2022 at 06:59 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ensf480project`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` int(10) NOT NULL,
  `title` varchar(200) NOT NULL,
  `duration` int(10) DEFAULT NULL,
  `released` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `title`, `duration`, `released`) VALUES
(1, 'Shrek', 92, 'true'),
(2, 'Smurfs', 91, 'true'),
(3, 'Bee Movie', 86, 'true'),
(4, 'Shark Tale', 98, 'true'),
(5, 'Avatar 2', 121, 'false'),
(6, 'The Super Mario Bros', 99, 'false'),
(7, 'Jaws', 102, 'true'),
(8, 'King Kong', 106, 'true');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `number` int(11) NOT NULL,
  `showtime id` int(11) NOT NULL,
  `available` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`number`, `showtime id`, `available`) VALUES
(1, 2, 'true'),
(2, 2, 'true'),
(3, 2, 'true'),
(4, 2, 'true'),
(5, 2, 'true'),
(6, 2, 'true'),
(7, 2, 'true'),
(8, 2, 'true'),
(9, 2, 'true'),
(10, 2, 'true'),
(11, 2, 'true'),
(12, 2, 'true'),
(13, 2, 'true'),
(14, 2, 'true'),
(15, 2, 'true'),
(16, 2, 'true'),
(17, 2, 'true'),
(18, 2, 'true'),
(19, 2, 'true'),
(20, 2, 'true'),
(1, 3, 'true'),
(2, 3, 'true'),
(3, 3, 'true'),
(4, 3, 'true'),
(5, 3, 'true'),
(6, 3, 'true'),
(7, 3, 'true'),
(8, 3, 'true'),
(9, 3, 'true'),
(10, 3, 'true'),
(11, 3, 'true'),
(12, 3, 'true'),
(13, 3, 'true'),
(14, 3, 'true'),
(15, 3, 'true'),
(16, 3, 'true'),
(17, 3, 'true'),
(18, 3, 'true'),
(19, 3, 'true'),
(20, 3, 'true'),
(1, 4, 'true'),
(2, 4, 'true'),
(3, 4, 'true'),
(4, 4, 'true'),
(5, 4, 'true'),
(6, 4, 'true'),
(7, 4, 'true'),
(8, 4, 'true'),
(9, 4, 'true'),
(10, 4, 'true'),
(11, 4, 'true'),
(12, 4, 'true'),
(13, 4, 'true'),
(14, 4, 'true'),
(15, 4, 'true'),
(16, 4, 'true'),
(17, 4, 'true'),
(18, 4, 'true'),
(19, 4, 'true'),
(20, 4, 'true'),
(1, 5, 'true'),
(2, 5, 'true'),
(3, 5, 'true'),
(4, 5, 'true'),
(5, 5, 'true'),
(6, 5, 'true'),
(7, 5, 'true'),
(8, 5, 'true'),
(9, 5, 'true'),
(10, 5, 'true'),
(11, 5, 'true'),
(12, 5, 'true'),
(13, 5, 'true'),
(14, 5, 'true'),
(15, 5, 'true'),
(16, 5, 'true'),
(17, 5, 'true'),
(18, 5, 'true'),
(19, 5, 'true'),
(20, 5, 'true'),
(1, 6, 'true'),
(2, 6, 'true'),
(3, 6, 'true'),
(4, 6, 'true'),
(5, 6, 'true'),
(6, 6, 'true'),
(7, 6, 'true'),
(8, 6, 'true'),
(9, 6, 'true'),
(10, 6, 'true'),
(11, 6, 'true'),
(12, 6, 'true'),
(13, 6, 'true'),
(14, 6, 'true'),
(15, 6, 'true'),
(16, 6, 'true'),
(17, 6, 'true'),
(18, 6, 'true'),
(19, 6, 'true'),
(20, 6, 'true'),
(1, 7, 'true'),
(2, 7, 'true'),
(3, 7, 'true'),
(4, 7, 'true'),
(5, 7, 'true'),
(6, 7, 'true'),
(7, 7, 'true'),
(8, 7, 'true'),
(9, 7, 'true'),
(10, 7, 'true'),
(11, 7, 'true'),
(12, 7, 'true'),
(13, 7, 'true'),
(14, 7, 'true'),
(15, 7, 'true'),
(16, 7, 'true'),
(17, 7, 'true'),
(18, 7, 'true'),
(19, 7, 'true'),
(20, 7, 'true'),
(1, 8, 'true'),
(2, 8, 'true'),
(3, 8, 'true'),
(4, 8, 'true'),
(5, 8, 'true'),
(6, 8, 'true'),
(7, 8, 'true'),
(8, 8, 'true'),
(9, 8, 'true'),
(10, 8, 'true'),
(11, 8, 'true'),
(12, 8, 'true'),
(13, 8, 'true'),
(14, 8, 'true'),
(15, 8, 'true'),
(16, 8, 'true'),
(17, 8, 'true'),
(18, 8, 'true'),
(19, 8, 'true'),
(20, 8, 'true'),
(1, 9, 'true'),
(2, 9, 'true'),
(3, 9, 'true'),
(4, 9, 'true'),
(5, 9, 'true'),
(6, 9, 'true'),
(7, 9, 'true'),
(8, 9, 'true'),
(9, 9, 'true'),
(10, 9, 'true'),
(11, 9, 'true'),
(12, 9, 'true'),
(13, 9, 'true'),
(14, 9, 'true'),
(15, 9, 'true'),
(16, 9, 'true'),
(17, 9, 'true'),
(18, 9, 'true'),
(19, 9, 'true'),
(20, 9, 'true'),
(1, 10, 'true'),
(2, 10, 'true'),
(3, 10, 'true'),
(4, 10, 'true'),
(5, 10, 'true'),
(6, 10, 'true'),
(7, 10, 'true'),
(8, 10, 'true'),
(9, 10, 'true'),
(10, 10, 'true'),
(11, 10, 'true'),
(12, 10, 'true'),
(13, 10, 'true'),
(14, 10, 'true'),
(15, 10, 'true'),
(16, 10, 'true'),
(17, 10, 'true'),
(18, 10, 'true'),
(19, 10, 'true'),
(20, 10, 'true'),
(1, 11, 'true'),
(2, 11, 'true'),
(3, 11, 'true'),
(4, 11, 'true'),
(5, 11, 'true'),
(6, 11, 'true'),
(7, 11, 'true'),
(8, 11, 'true'),
(9, 11, 'true'),
(10, 11, 'true'),
(1, 12, 'true'),
(2, 12, 'true'),
(3, 12, 'true'),
(4, 12, 'true'),
(5, 12, 'true'),
(6, 12, 'true'),
(7, 12, 'true'),
(8, 12, 'true'),
(9, 12, 'true'),
(10, 12, 'true'),
(1, 13, 'true'),
(2, 13, 'true'),
(3, 13, 'true'),
(4, 13, 'true'),
(5, 13, 'true'),
(6, 13, 'true'),
(7, 13, 'true'),
(8, 13, 'true'),
(9, 13, 'true'),
(10, 13, 'true'),
(1, 14, 'true'),
(2, 14, 'true'),
(3, 14, 'true'),
(4, 14, 'true'),
(5, 14, 'true'),
(6, 14, 'true'),
(7, 14, 'true'),
(8, 14, 'true'),
(9, 14, 'true'),
(10, 14, 'true'),
(1, 15, 'true'),
(2, 15, 'true'),
(3, 15, 'true'),
(4, 15, 'true'),
(5, 15, 'true'),
(6, 15, 'true'),
(7, 15, 'true'),
(8, 15, 'true'),
(9, 15, 'true'),
(10, 15, 'true'),
(1, 16, 'true'),
(2, 16, 'true'),
(3, 16, 'true'),
(4, 16, 'true'),
(5, 16, 'true'),
(6, 16, 'true'),
(7, 16, 'true'),
(8, 16, 'true'),
(9, 16, 'true'),
(10, 16, 'true'),
(1, 17, 'true'),
(2, 17, 'true'),
(3, 17, 'true'),
(4, 17, 'true'),
(5, 17, 'true'),
(6, 17, 'true'),
(7, 17, 'true'),
(8, 17, 'true'),
(9, 17, 'true'),
(10, 17, 'true'),
(1, 18, 'true'),
(2, 18, 'true'),
(3, 18, 'true'),
(4, 18, 'true'),
(5, 18, 'true'),
(6, 18, 'true'),
(7, 18, 'true'),
(8, 18, 'true'),
(9, 18, 'true'),
(10, 18, 'true'),
(1, 19, 'true'),
(2, 19, 'true'),
(3, 19, 'true'),
(4, 19, 'true'),
(5, 19, 'true'),
(6, 19, 'true'),
(7, 19, 'true'),
(8, 19, 'true'),
(9, 19, 'true'),
(10, 19, 'true'),
(1, 20, 'true'),
(2, 20, 'true'),
(3, 20, 'true'),
(4, 20, 'true'),
(5, 20, 'true'),
(6, 20, 'true'),
(7, 20, 'true'),
(8, 20, 'true'),
(9, 20, 'true'),
(10, 20, 'true'),
(1, 21, 'true'),
(2, 21, 'true'),
(3, 21, 'true'),
(4, 21, 'true'),
(5, 21, 'true'),
(6, 21, 'true'),
(7, 21, 'true'),
(8, 21, 'true'),
(9, 21, 'true'),
(10, 21, 'true'),
(1, 22, 'true'),
(2, 22, 'true'),
(3, 22, 'true'),
(4, 22, 'true'),
(5, 22, 'true'),
(6, 22, 'true'),
(7, 22, 'true'),
(8, 22, 'true'),
(9, 22, 'true'),
(10, 22, 'true'),
(1, 23, 'true'),
(2, 23, 'true'),
(3, 23, 'true'),
(4, 23, 'true'),
(5, 23, 'true'),
(6, 23, 'true'),
(7, 23, 'true'),
(8, 23, 'true'),
(9, 23, 'true'),
(10, 23, 'true'),
(1, 24, 'true'),
(2, 24, 'true'),
(3, 24, 'true'),
(4, 24, 'true'),
(5, 24, 'true'),
(6, 24, 'true'),
(7, 24, 'true'),
(8, 24, 'true'),
(9, 24, 'true'),
(10, 24, 'true'),
(1, 25, 'true'),
(2, 25, 'true'),
(3, 25, 'true'),
(4, 25, 'true'),
(5, 25, 'true'),
(6, 25, 'true'),
(7, 25, 'true'),
(8, 25, 'true'),
(9, 25, 'true'),
(10, 25, 'true'),
(1, 26, 'true'),
(2, 26, 'true'),
(3, 26, 'true'),
(4, 26, 'true'),
(5, 26, 'true'),
(6, 26, 'true'),
(7, 26, 'true'),
(8, 26, 'true'),
(9, 26, 'true'),
(10, 26, 'true');

-- --------------------------------------------------------

--
-- Table structure for table `showtimes`
--

CREATE TABLE `showtimes` (
  `id` int(10) NOT NULL,
  `movie id` int(11) NOT NULL,
  `month` varchar(200) NOT NULL,
  `day` varchar(200) NOT NULL,
  `time` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `showtimes`
--

INSERT INTO `showtimes` (`id`, `movie id`, `month`, `day`, `time`) VALUES
(2, 1, 'December', '6th', '5:00pm'),
(3, 1, 'December', '6th', '8:00pm'),
(4, 1, 'December', '8th', '6:00pm'),
(5, 1, 'December', '9th', '6:30pm'),
(6, 1, 'December', '9th', '4:00pm'),
(7, 1, 'December', '11th', '6:30pm'),
(8, 2, 'December', '6th', '5:45pm'),
(9, 2, 'December', '7th', '8:30pm'),
(10, 2, 'December', '8th', '12:30pm'),
(11, 2, 'December', '8th', '7:00pm'),
(12, 2, 'December', '10th', '2:00pm'),
(13, 2, 'December', '13th', '6:30pm'),
(14, 3, 'December', '8th', '2:30pm'),
(15, 3, 'December', '26th', '8:00pm'),
(16, 3, 'December', '29th', '6:00pm'),
(17, 3, 'January', '2nd', '6:30pm'),
(18, 4, 'December', '9th', '6:30pm'),
(19, 4, 'December', '10th', '2:00pm'),
(20, 4, 'December', '11th', '3:30pm'),
(21, 4, 'December', '15th', '4:45pm'),
(22, 4, 'January', '3rd', '7:00pm'),
(23, 5, 'December', '10th', '4:30pm'),
(24, 5, 'December', '14th', '3:00pm'),
(25, 5, 'December', '15th', '9:30pm'),
(26, 5, 'December', '17th', '5:45pm'),
(27, 5, 'January', '4th', '3:00pm'),
(28, 6, 'March', '9th', '6:30pm'),
(29, 6, 'March', '10th', '2:00pm'),
(30, 6, 'March', '13th', '3:30pm'),
(31, 7, 'December', '9th', '1:30pm'),
(32, 7, 'December', '13th', '2:45pm'),
(33, 8, 'January', '5th', '11:00am'),
(34, 8, 'January', '6th', '11:45am');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `card` varchar(200) DEFAULT NULL,
  `cardtype` varchar(200) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `card`, `cardtype`, `password`, `address`) VALUES
(1, 'Kolby Lalonde', 'kolby.lalonde@ucalgary.ca', '1111111111111111', 'credit', 'Software', 'university drive'),
(3, 'Carlos', 'Carlos@gmail.com', '2222222222222222', 'debit', 'Software1', 'university drive'),
(4, 'Jana', 'Jana@ucalgary.ca', '3333333333333333', 'credit', 'Software1', 'university drive'),
(5, 'Sayma', 'Sayma@ucalgary.ca', '4444444444444444', 'credit', 'Software1', 'university drive'),
(6, 'Harry', 'Harry@gmail.com', '1234123412341234', 'debit', 'Code', 'university drive'),
(7, 'John', 'John@gmail.com', '1234123412341234', 'credit', 'Code', 'university drive'),
(8, 'joe', 'hello', '1234123412341234', 'credit', 'hi', 'university drive'),
(9, 'Dan', 'Dan23@gmail.com', '1234123412341234', 'credit', 'Code1', 'university drive'),
(10, 'you', 'hgd', '1234123412341234', 'credit', 'rt', 'university drive'),
(11, 'james', 'james', '1234123412341234', 'debit', 'code1', 'university drive'),
(12, 'g', 'g', '1234123412341234', 'credit', 'g', 'university drive');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `showtimes`
--
ALTER TABLE `showtimes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `showtimes`
--
ALTER TABLE `showtimes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
