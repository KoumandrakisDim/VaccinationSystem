-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 11 Φεβ 2023 στις 20:12:47
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

--
-- Άδειασμα δεδομένων του πίνακα `appointments`
--

INSERT INTO `appointments` (`id`, `doctor_id`, `timeslot_id`, `citizen_id`, `date`) VALUES
(128, 1, 23, 2, '2023-01-07');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `citizens`
--

CREATE TABLE `citizens` (
  `id` bigint(64) NOT NULL,
  `amka` varchar(32) NOT NULL,
  `afm` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `surname` varchar(32) DEFAULT NULL,
  `email` varchar(32) NOT NULL,
  `times_canceled` int(4) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `is_vaccinated` tinyint(1) DEFAULT NULL,
  `vaccination_expiration_date` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `citizens`
--

INSERT INTO `citizens` (`id`, `amka`, `afm`, `name`, `surname`, `email`, `times_canceled`, `password`, `is_vaccinated`, `vaccination_expiration_date`) VALUES
(1, '2', '32546142', 'Dimitris', 'Koumandrakis', 'dimitrios729@gmail.com', 1, '123', 1, '2023-08-14'),
(2, '3', '6343252', 'Giannias', 'Papadopoulos', 'papadopoulos@yahoo.com', 1, '1', NULL, '0');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `doctors`
--

CREATE TABLE `doctors` (
  `id` bigint(64) NOT NULL,
  `amka` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `doctors`
--

INSERT INTO `doctors` (`id`, `amka`, `name`, `surname`, `password`) VALUES
(1, '1', 'DR', 'Strange', '321');

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
(23, 2023, 1, 7, 22, 59, 0, 0, 1, 0, '2023-01-07'),
(25, 2023, 2, 14, 11, 2, 0, 0, 1, 1, '2023-02-14'),
(27, 2023, 2, 22, 18, 21, 0, 0, 1, 0, '2023-02-22'),
(29, 2023, 2, 25, 18, 21, 0, 0, 1, 1, '2023-02-25'),
(30, 2023, 3, 4, 19, 4, 0, 0, 1, 1, '2023-03-04');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `vaccination`
--

CREATE TABLE `vaccination` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `citizen_id` bigint(20) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK52t133p8yxjom7qg9ef2tgnt7` (`citizen_id`),
  ADD KEY `FKmujeo4tymoo98cmf7uj3vsv76` (`doctor_id`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7n2y5a0e7t9us9nkaih4ew94` (`doctor_id`);

--
-- Ευρετήρια για πίνακα `vaccination`
--
ALTER TABLE `vaccination`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4ysnile0the7v61n3imfey4eu` (`citizen_id`),
  ADD KEY `FKaaho5d1j6uw49xch7688i7g1j` (`doctor_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT για πίνακα `citizens`
--
ALTER TABLE `citizens`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT για πίνακα `doctors`
--
ALTER TABLE `doctors`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT για πίνακα `timeslots`
--
ALTER TABLE `timeslots`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT για πίνακα `vaccination`
--
ALTER TABLE `vaccination`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK52t133p8yxjom7qg9ef2tgnt7` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `FKmujeo4tymoo98cmf7uj3vsv76` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`);

--
-- Περιορισμοί για πίνακα `timeslots`
--
ALTER TABLE `timeslots`
  ADD CONSTRAINT `FK7n2y5a0e7t9us9nkaih4ew94` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`);

--
-- Περιορισμοί για πίνακα `vaccination`
--
ALTER TABLE `vaccination`
  ADD CONSTRAINT `FK4ysnile0the7v61n3imfey4eu` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `FKaaho5d1j6uw49xch7688i7g1j` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
