-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 09, 2024 at 06:11 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ProjetJava`
--

-- --------------------------------------------------------

--
-- Table structure for table `CreneauDisponible`
--

CREATE TABLE `CreneauDisponible` (
  `creneau_id` int(11) NOT NULL,
  `doc_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `debut` time NOT NULL,
  `fin` time NOT NULL,
  `statut` enum('disponible','reserve') DEFAULT 'disponible'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `CreneauDisponible`
--

INSERT INTO `CreneauDisponible` (`creneau_id`, `doc_id`, `date`, `debut`, `fin`, `statut`) VALUES
(17, 2, '2024-04-12', '11:30:00', '12:30:00', 'reserve'),
(18, 2, '2024-04-13', '08:30:00', '09:30:00', 'disponible'),
(19, 2, '2024-05-01', '10:00:00', '11:00:00', 'disponible'),
(20, 2, '2024-04-18', '11:30:00', '12:30:00', 'disponible'),
(21, 2, '2024-04-23', '11:00:00', '12:00:00', 'disponible'),
(22, 6, '2024-05-01', '09:30:00', '10:30:00', 'reserve'),
(23, 6, '2024-04-24', '10:00:00', '11:00:00', 'disponible'),
(24, 6, '2024-05-04', '09:30:00', '10:30:00', 'disponible'),
(25, 6, '2024-04-21', '10:00:00', '11:00:00', 'disponible'),
(26, 6, '2024-05-08', '11:00:00', '12:00:00', 'disponible'),
(27, 10, '2024-04-10', '09:00:00', '10:00:00', 'reserve'),
(28, 10, '2024-04-10', '09:30:00', '10:30:00', 'disponible'),
(29, 10, '2024-04-15', '11:00:00', '12:00:00', 'disponible'),
(30, 10, '2024-04-29', '08:00:00', '09:00:00', 'disponible'),
(31, 10, '2024-04-19', '09:00:00', '10:00:00', 'disponible'),
(32, 14, '2024-05-05', '11:00:00', '12:00:00', 'reserve'),
(33, 14, '2024-04-20', '10:30:00', '11:30:00', 'disponible'),
(34, 14, '2024-04-24', '11:00:00', '12:00:00', 'disponible'),
(35, 14, '2024-05-09', '11:30:00', '12:30:00', 'disponible'),
(36, 14, '2024-04-22', '08:30:00', '09:30:00', 'disponible'),
(37, 18, '2024-04-18', '08:30:00', '09:30:00', 'reserve'),
(38, 18, '2024-04-11', '08:00:00', '09:00:00', 'disponible'),
(39, 18, '2024-04-10', '09:30:00', '10:30:00', 'disponible'),
(40, 18, '2024-04-11', '09:00:00', '10:00:00', 'disponible'),
(41, 18, '2024-04-11', '09:30:00', '10:30:00', 'disponible');

-- --------------------------------------------------------

--
-- Table structure for table `Docteur`
--

CREATE TABLE `Docteur` (
  `doc_id` int(11) NOT NULL,
  `utilisateur_id` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Docteur`
--

INSERT INTO `Docteur` (`doc_id`, `utilisateur_id`, `nom`, `prenom`, `specialite`, `lieu`) VALUES
(1, 2, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(2, 6, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(3, 10, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(4, 14, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(5, 18, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(6, 22, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(7, 26, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(8, 30, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(9, 34, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(10, 38, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(11, 2, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(12, 6, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(13, 10, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(14, 14, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(15, 18, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(16, 22, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(17, 26, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(18, 30, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(19, 34, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu'),
(20, 38, 'NomDocteur', 'Prenom', 'Generaliste', 'Lieu');

-- --------------------------------------------------------

--
-- Table structure for table `Employe`
--

CREATE TABLE `Employe` (
  `employe_id` int(11) NOT NULL,
  `utilisateur_id` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Employe`
--

INSERT INTO `Employe` (`employe_id`, `utilisateur_id`, `nom`, `prenom`) VALUES
(1, 3, 'NomEmploye', 'Prenom'),
(2, 7, 'NomEmploye', 'Prenom'),
(3, 11, 'NomEmploye', 'Prenom'),
(4, 15, 'NomEmploye', 'Prenom'),
(5, 19, 'NomEmploye', 'Prenom'),
(6, 23, 'NomEmploye', 'Prenom'),
(7, 27, 'NomEmploye', 'Prenom'),
(8, 31, 'NomEmploye', 'Prenom'),
(9, 35, 'NomEmploye', 'Prenom'),
(10, 39, 'NomEmploye', 'Prenom'),
(11, 3, 'NomEmploye', 'Prenom'),
(12, 7, 'NomEmploye', 'Prenom'),
(13, 11, 'NomEmploye', 'Prenom'),
(14, 15, 'NomEmploye', 'Prenom'),
(15, 19, 'NomEmploye', 'Prenom'),
(16, 23, 'NomEmploye', 'Prenom'),
(17, 27, 'NomEmploye', 'Prenom'),
(18, 31, 'NomEmploye', 'Prenom'),
(19, 35, 'NomEmploye', 'Prenom'),
(20, 39, 'NomEmploye', 'Prenom');

-- --------------------------------------------------------

--
-- Table structure for table `HistoriqueRendezVous`
--

CREATE TABLE `HistoriqueRendezVous` (
  `historique_id` int(11) NOT NULL,
  `rdv_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doc_id` int(11) DEFAULT NULL,
  `statut_final` enum('effectue','annule','no_show') DEFAULT NULL,
  `commentaire` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Patient`
--

CREATE TABLE `Patient` (
  `patient_id` int(11) NOT NULL,
  `utilisateur_id` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Patient`
--

INSERT INTO `Patient` (`patient_id`, `utilisateur_id`, `nom`, `prenom`, `age`) VALUES
(1, 1, 'NomPatient', 'Prenom', 30),
(2, 5, 'NomPatient', 'Prenom', 30),
(3, 9, 'NomPatient', 'Prenom', 30),
(4, 13, 'NomPatient', 'Prenom', 30),
(5, 17, 'NomPatient', 'Prenom', 30),
(6, 21, 'NomPatient', 'Prenom', 30),
(7, 25, 'NomPatient', 'Prenom', 30),
(8, 29, 'NomPatient', 'Prenom', 30),
(9, 33, 'NomPatient', 'Prenom', 30),
(10, 37, 'NomPatient', 'Prenom', 30),
(11, 1, 'NomPatient', 'Prenom', 30),
(12, 5, 'NomPatient', 'Prenom', 30),
(13, 9, 'NomPatient', 'Prenom', 30),
(14, 13, 'NomPatient', 'Prenom', 30),
(15, 17, 'NomPatient', 'Prenom', 30),
(16, 21, 'NomPatient', 'Prenom', 30),
(17, 25, 'NomPatient', 'Prenom', 30),
(18, 29, 'NomPatient', 'Prenom', 30),
(19, 33, 'NomPatient', 'Prenom', 30),
(20, 37, 'NomPatient', 'Prenom', 30);

-- --------------------------------------------------------

--
-- Table structure for table `RendezVous`
--

CREATE TABLE `RendezVous` (
  `rdv_id` int(11) NOT NULL,
  `creneau_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doc_id` int(11) DEFAULT NULL,
  `statut` enum('confirme','annule','en_attente') DEFAULT 'en_attente',
  `date_prise_rdv` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `RendezVous`
--

INSERT INTO `RendezVous` (`rdv_id`, `creneau_id`, `patient_id`, `doc_id`, `statut`, `date_prise_rdv`) VALUES
(1, 17, 1, 2, 'confirme', '2024-04-09 18:09:41'),
(2, 22, 5, 6, 'confirme', '2024-04-09 18:09:41'),
(3, 27, 9, 10, 'confirme', '2024-04-09 18:09:41'),
(4, 32, 13, 14, 'confirme', '2024-04-09 18:09:41'),
(5, 37, 17, 18, 'confirme', '2024-04-09 18:09:41');

-- --------------------------------------------------------

--
-- Table structure for table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `utilisateur_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` enum('patient','docteur','employe','administrateur') NOT NULL,
  `date_inscription` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`utilisateur_id`, `email`, `mot_de_passe`, `role`, `date_inscription`) VALUES
(1, 'patient0@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(2, 'docteur0@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(3, 'employe0@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(4, 'administrateur0@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(5, 'patient1@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(6, 'docteur1@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(7, 'employe1@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(8, 'administrateur1@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(9, 'patient2@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(10, 'docteur2@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(11, 'employe2@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(12, 'administrateur2@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(13, 'patient3@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(14, 'docteur3@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(15, 'employe3@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(16, 'administrateur3@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(17, 'patient4@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(18, 'docteur4@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(19, 'employe4@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(20, 'administrateur4@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(21, 'patient5@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(22, 'docteur5@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(23, 'employe5@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(24, 'administrateur5@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(25, 'patient6@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(26, 'docteur6@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(27, 'employe6@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(28, 'administrateur6@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(29, 'patient7@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(30, 'docteur7@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(31, 'employe7@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(32, 'administrateur7@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(33, 'patient8@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(34, 'docteur8@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(35, 'employe8@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(36, 'administrateur8@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59'),
(37, 'patient9@exemple.com', 'motdepassehash', 'patient', '2024-04-09 18:04:59'),
(38, 'docteur9@exemple.com', 'motdepassehash', 'docteur', '2024-04-09 18:04:59'),
(39, 'employe9@exemple.com', 'motdepassehash', 'employe', '2024-04-09 18:04:59'),
(40, 'administrateur9@exemple.com', 'motdepassehash', 'administrateur', '2024-04-09 18:04:59');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CreneauDisponible`
--
ALTER TABLE `CreneauDisponible`
  ADD PRIMARY KEY (`creneau_id`),
  ADD KEY `doc_id` (`doc_id`);

--
-- Indexes for table `Docteur`
--
ALTER TABLE `Docteur`
  ADD PRIMARY KEY (`doc_id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`);

--
-- Indexes for table `Employe`
--
ALTER TABLE `Employe`
  ADD PRIMARY KEY (`employe_id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`);

--
-- Indexes for table `HistoriqueRendezVous`
--
ALTER TABLE `HistoriqueRendezVous`
  ADD PRIMARY KEY (`historique_id`),
  ADD KEY `rdv_id` (`rdv_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doc_id` (`doc_id`);

--
-- Indexes for table `Patient`
--
ALTER TABLE `Patient`
  ADD PRIMARY KEY (`patient_id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`);

--
-- Indexes for table `RendezVous`
--
ALTER TABLE `RendezVous`
  ADD PRIMARY KEY (`rdv_id`),
  ADD KEY `creneau_id` (`creneau_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doc_id` (`doc_id`);

--
-- Indexes for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`utilisateur_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `CreneauDisponible`
--
ALTER TABLE `CreneauDisponible`
  MODIFY `creneau_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `Docteur`
--
ALTER TABLE `Docteur`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `Employe`
--
ALTER TABLE `Employe`
  MODIFY `employe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `HistoriqueRendezVous`
--
ALTER TABLE `HistoriqueRendezVous`
  MODIFY `historique_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Patient`
--
ALTER TABLE `Patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `RendezVous`
--
ALTER TABLE `RendezVous`
  MODIFY `rdv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `CreneauDisponible`
--
ALTER TABLE `CreneauDisponible`
  ADD CONSTRAINT `creneaudisponible_ibfk_1` FOREIGN KEY (`doc_id`) REFERENCES `Docteur` (`doc_id`);

--
-- Constraints for table `Docteur`
--
ALTER TABLE `Docteur`
  ADD CONSTRAINT `docteur_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);

--
-- Constraints for table `Employe`
--
ALTER TABLE `Employe`
  ADD CONSTRAINT `employe_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);

--
-- Constraints for table `HistoriqueRendezVous`
--
ALTER TABLE `HistoriqueRendezVous`
  ADD CONSTRAINT `historiquerendezvous_ibfk_1` FOREIGN KEY (`rdv_id`) REFERENCES `RendezVous` (`rdv_id`),
  ADD CONSTRAINT `historiquerendezvous_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `Patient` (`patient_id`),
  ADD CONSTRAINT `historiquerendezvous_ibfk_3` FOREIGN KEY (`doc_id`) REFERENCES `Docteur` (`doc_id`);

--
-- Constraints for table `Patient`
--
ALTER TABLE `Patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);

--
-- Constraints for table `RendezVous`
--
ALTER TABLE `RendezVous`
  ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`creneau_id`) REFERENCES `CreneauDisponible` (`creneau_id`),
  ADD CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `Patient` (`patient_id`),
  ADD CONSTRAINT `rendezvous_ibfk_3` FOREIGN KEY (`doc_id`) REFERENCES `Docteur` (`doc_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
