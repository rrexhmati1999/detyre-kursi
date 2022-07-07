-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 18, 2021 at 08:00 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `CommentID` int(11) NOT NULL,
  `PersonName` varchar(25) DEFAULT NULL,
  `Course` varchar(20) DEFAULT NULL,
  `CommentText` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`CommentID`, `PersonName`, `Course`, `CommentText`) VALUES
(1, 'alban', 'history', 'test test'),
(2, 'ambra', 'tst', 'test'),
(3, 'besa', 'tstkt', 'test nr 3'),
(4, 'ronaldo', 'Software Engineering', 'test test test');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `CourseID` int(11) NOT NULL,
  `CourseName` varchar(35) DEFAULT NULL,
  `Program` varchar(35) NOT NULL,
  `Instructor` varchar(35) DEFAULT NULL,
  `Major` varchar(35) DEFAULT NULL,
  `Schedule` varchar(35) DEFAULT NULL,
  `Location` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`CourseID`, `CourseName`, `Program`, `Instructor`, `Major`, `Schedule`, `Location`) VALUES
(1, 'Networking', 'Major', 'Genti Daci', 'Computer Engineering', 'Mon 16:00 - 18:00', '3FE'),
(2, 'Software Engineering', 'Major', 'Elinda Meçe', 'Computer Engineering', 'Tue 12:00-15:00', '201A'),
(3, 'Project Management', 'Minor', 'Aleksander Biberaj', 'Computer Engineering', 'Wednesday 17:00-19:00', '212AA');

-- --------------------------------------------------------

--
-- Table structure for table `course_count`
--

CREATE TABLE `course_count` (
  `CourseNo` int(11) NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `enrollcourses`
--

CREATE TABLE `enrollcourses` (
  `CourseID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `IssueDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enrollcourses`
--

INSERT INTO `enrollcourses` (`CourseID`, `UserID`, `IssueDate`, `ReturnDate`) VALUES
(1, 2, '2022-06-02', '2022-06-10'),
(1, 1, '2022-08-18', '2022-09-02'),
(3, 1, '2022-08-18', '2022-09-02');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `friendshipID` int(11) NOT NULL,
  `LoginID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`friendshipID`, `LoginID`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `LoginID` int(11) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FullName` varchar(35) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Age` int(11) NOT NULL,
  `MajorN` varchar(50) NOT NULL,
  `RegDate` datetime NOT NULL,
  `friendshipID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`LoginID`, `UserName`, `Password`, `FullName`, `Email`, `Age`, `MajorN`, `RegDate`, `friendshipID`) VALUES
(1, 'alban', 'alban', 'alban zyle', 'alban@gmail.com', 22, 'Computer Engineering', '2022-06-01 19:58:33', 2),
(2, 'ambra', 'ambra', 'ambra zajsi', 'ambra@gmail.com', 21, 'Computer Engineering', '2022-05-04 21:36:47', 1),
(3, 'besa', 'besa', 'besa salimusaj', 'besa@gmail.com', 23, 'Computer Engineering', '2022-06-04 22:17:18', 1);
(4, 'ronaldo', 'ronaldo', 'ronaldo rexhmati', 'ronaldo@gmail.com', 23, 'Computer Engineering', '2022-06-04 23:17:18', 1);


--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`CommentID`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`CourseID`);

--
-- Indexes for table `course_count`
--
ALTER TABLE `course_count`
  ADD KEY `CourseCountID` (`UserID`);

--
-- Indexes for table `enrollcourses`
--
ALTER TABLE `enrollcourses`
  ADD KEY `UserID` (`UserID`),
  ADD KEY `CourseID` (`CourseID`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`friendshipID`),
  ADD KEY `logID` (`LoginID`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`LoginID`),
  ADD KEY `friendshipID` (`friendshipID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `CourseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course_count`
--
ALTER TABLE `course_count`
  ADD CONSTRAINT `CourseCountID` FOREIGN KEY (`UserID`) REFERENCES `enrollcourses` (`UserID`);

--
-- Constraints for table `enrollcourses`
--
ALTER TABLE `enrollcourses`
  ADD CONSTRAINT `CourseID` FOREIGN KEY (`CourseID`) REFERENCES `courses` (`CourseID`),
  ADD CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `login` (`LoginID`);

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `logID` FOREIGN KEY (`LoginID`) REFERENCES `login` (`LoginID`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `friendshipID` FOREIGN KEY (`friendshipID`) REFERENCES `friends` (`friendshipID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
