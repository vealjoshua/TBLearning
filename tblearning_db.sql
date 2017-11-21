-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 04, 2017 at 07:11 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tblearning_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `internal_id` int(11) NOT NULL,
  `sso_id` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`internal_id`, `sso_id`) VALUES
(4, 'agiujn'),
(5, 'asdjbjhv'),
(6, 'jbtend'),
(1, 'jkv2c9'),
(7, 'johnnyap'),
(2, 'pfl'),
(3, 'wenieh');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_code` char(16) DEFAULT NULL,
  `course_title` char(64) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `semester` char(16) DEFAULT NULL,
  `date_course_was_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `instructor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_code`, `course_title`, `year`, `semester`, `date_course_was_created`, `instructor_id`) VALUES
(1, 'cs4012', 'JavaWebEnterprise', 2017, 'Fall', '2017-10-26 04:09:33', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `course_instructor`
--

CREATE TABLE `course_instructor` (
  `relationship_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `internal_id` int(11) NOT NULL,
  `sso_id` char(16) NOT NULL,
  `department` char(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`internal_id`, `sso_id`, `department`) VALUES
(1, 'jkv2c9', 'cmpsci'),
(2, 'lf28j', 'meat'),
(3, 'pfl', 'meat'),
(4, 'jkv2c', 'cmpsci'),
(5, 'wenieh', 'cmpsci'),
(6, 'agiujn', 'cmpsci'),
(7, 'asdjbjhv', 'cmpsci'),
(8, 'jbtend', 'hard knocks');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `content` char(255) NOT NULL,
  `options` int(11) DEFAULT NULL,
  `option_a` char(64) NOT NULL,
  `option_b` char(64) NOT NULL,
  `option_c` char(64) DEFAULT NULL,
  `option_d` char(64) DEFAULT NULL,
  `option_e` char(64) DEFAULT NULL,
  `option_f` char(64) DEFAULT NULL,
  `answer` char(64) NOT NULL,
  `date_question_was_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `instructor_id` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `content`, `options`, `option_a`, `option_b`, `option_c`, `option_d`, `option_e`, `option_f`, `answer`, `date_question_was_created`, `instructor_id`) VALUES
(1, '', NULL, '', '', NULL, NULL, NULL, NULL, '', '2017-11-04 05:42:46', '');

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `quiz_id` int(11) NOT NULL,
  `course_id` char(32) NOT NULL,
  `quiz_number` int(11) NOT NULL,
  `time_limit` time NOT NULL DEFAULT '00:00:00',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_quiz_was_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `instructor_id` char(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `internal_id` int(11) NOT NULL,
  `sso_id` char(16) NOT NULL,
  `student_number` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`internal_id`, `sso_id`, `student_number`) VALUES
(1, 'jkv2c', 18114709);

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `topic_id` int(11) NOT NULL,
  `topic_title` char(64) NOT NULL,
  `course_id` char(16) NOT NULL,
  `date_topic_was_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `instructor_id` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `internal_id` int(11) NOT NULL,
  `sso_id` char(16) NOT NULL,
  `password` char(32) NOT NULL,
  `first_name` char(16) NOT NULL,
  `last_name` char(16) NOT NULL,
  `admin` char(3) NOT NULL DEFAULT 'No',
  `instructor` char(3) NOT NULL DEFAULT 'No',
  `student` char(3) NOT NULL DEFAULT 'No',
  `login_attempts` int(1) NOT NULL DEFAULT '0',
  `date_account_was_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`internal_id`, `sso_id`, `password`, `first_name`, `last_name`, `admin`, `instructor`, `student`, `login_attempts`, `date_account_was_created`) VALUES
(1, 'jkv2c9', 'password', 'Joshua', 'Veal-Briscoe', 'yes', 'yes', 'no', 0, '2017-10-19 23:17:31'),
(5, 'lf28j', '', 'Jake', 'Steak', 'yes', 'yes', 'no', 0, '2017-10-25 02:12:25'),
(6, 'pfl', '', 'pleasant', 'pheasant', 'yes', 'yes', 'no', 0, '2017-10-25 02:20:51'),
(7, 'student', 'password', 'joshua', 'veal', 'yes', 'yes', 'no', 0, '2017-10-25 21:48:46'),
(8, 'wenieh', '', 'wenjie', 'he', 'yes', 'yes', 'no', 0, '2017-10-25 22:30:17'),
(9, 'agiujn', '', 'johsua', 'veal', 'yes', 'yes', 'no', 0, '2017-10-25 22:49:53'),
(10, 'asdjbjhv', '', 'Josh', 'Veal', 'yes', 'yes', 'no', 0, '2017-11-02 00:00:25'),
(11, 'jbtend', '', 'jack', 'black', 'yes', 'yes', 'no', 0, '2017-11-03 03:36:39'),
(12, 'johnnyap', '7URNLYK82NFPSVEX', 'johnny', 'appleseed', 'No', 'No', 'No', 0, '2017-11-04 04:36:01'),
(13, 'bobbyd', 'WA0FAXVJDELYKIHTT', 'bob', 'dylan', 'No', 'No', 'No', 0, '2017-11-04 04:41:50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`internal_id`),
  ADD UNIQUE KEY `sso_id` (`sso_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`),
  ADD UNIQUE KEY `course_code` (`course_code`);

--
-- Indexes for table `course_instructor`
--
ALTER TABLE `course_instructor`
  ADD PRIMARY KEY (`relationship_id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`internal_id`),
  ADD UNIQUE KEY `sso_id` (`sso_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`quiz_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`internal_id`),
  ADD UNIQUE KEY `sso_id` (`sso_id`),
  ADD UNIQUE KEY `student_number` (`student_number`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`topic_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`internal_id`),
  ADD UNIQUE KEY `sso_id` (`sso_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `internal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `course_instructor`
--
ALTER TABLE `course_instructor`
  MODIFY `relationship_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `internal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `quiz_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `internal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `topic_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `internal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
