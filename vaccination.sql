-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 09 Ιαν 2023 στις 10:46:18
-- Έκδοση διακομιστή: 10.4.27-MariaDB
-- Έκδοση PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `vaccination`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `appointments`
--

CREATE TABLE `appointments` (
  `id` bigint(20) NOT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `timeslot_id` bigint(20) DEFAULT NULL,
  `citizen_id` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `citizens`
--

CREATE TABLE `citizens` (
  `id` bigint(64) NOT NULL,
  `amka` varchar(32) NOT NULL,
  `afm` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `times_canceled` int(4) NOT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `citizens`
--

INSERT INTO `citizens` (`id`, `amka`, `afm`, `name`, `surname`, `email`, `times_canceled`, `password`) VALUES
(1, '31253546', '32546142', 'Dimitris', 'Koumandrakis', 'dimitrios729@gmail.com', 1, '');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `doctors`
--

CREATE TABLE `doctors` (
  `id` bigint(64) NOT NULL,
  `amka` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `doctors`
--

INSERT INTO `doctors` (`id`, `amka`, `name`, `surname`) VALUES
(1, '5151955', 'DR', 'Strange');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `timeslots`
--

CREATE TABLE `timeslots` (
  `id` int(11) NOT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `day` int(2) NOT NULL,
  `starting_hour` int(2) NOT NULL,
  `starting_minute` int(2) NOT NULL,
  `finish_hour` int(2) NOT NULL,
  `finish_minute` int(2) NOT NULL,
  `doctor_id` bigint(64) NOT NULL,
  `is_available` tinyint(1) NOT NULL,
  `date` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `timeslots`
--

INSERT INTO `timeslots` (`id`, `year`, `month`, `day`, `starting_hour`, `starting_minute`, `finish_hour`, `finish_minute`, `doctor_id`, `is_available`, `date`) VALUES
(22, 2023, 1, 6, 22, 42, 0, 0, 1, 1, '2023-01-06'),
(23, 2023, 1, 7, 22, 59, 0, 0, 1, 1, '2023-01-07'),
(24, 2023, 1, 6, 23, 4, 0, 0, 1, 1, '2023-01-06');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `citizens`
--
ALTER TABLE `citizens`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `timeslots`
--
ALTER TABLE `timeslots`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT για πίνακα `citizens`
--
ALTER TABLE `citizens`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT για πίνακα `doctors`
--
ALTER TABLE `doctors`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT για πίνακα `timeslots`
--
ALTER TABLE `timeslots`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
