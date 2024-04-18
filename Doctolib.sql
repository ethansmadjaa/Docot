
CREATE TABLE `Medecins` (
                            `MedecinID` int(11) NOT NULL,
                            `Nom` varchar(255) DEFAULT NULL,
                            `Prenom` varchar(255) DEFAULT NULL,
                            `Specialite` varchar(255) DEFAULT NULL,
                            `Email` varchar(255) DEFAULT NULL,
                            `Motdepasse` varchar(255) DEFAULT NULL,
                            `Lieu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `Medecins` (`MedecinID`, `Nom`, `Prenom`, `Specialite`, `Email`, `Motdepasse`, `Lieu`) VALUES
   (1, 'Cohen', 'Henry', 'Généraliste', 'henry@cohen.fr', 'henry', 'Levallois'),
   (2, 'Segado', 'Jean-pierre', 'Pédiatre', 'jp@segado.fr', 'segado', 'Creteil'),
   (3, 'Smadja', 'Didier', 'Osthéopathe', 'didier@smadja.biz', 'didier', 'Courbevoie'),
   (4, 'Darrondeau', 'Jacques', 'Ophtalmologiste', 'jacques@darrondeau.fr', 'jacques', 'Clichy');


CREATE TABLE `Patients` (
                            `PatientID` int(11) NOT NULL,
                            `Nom` varchar(255) DEFAULT NULL,
                            `Prenom` varchar(255) DEFAULT NULL,
                            `Email` varchar(255) DEFAULT NULL,
                            `Motdepasse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Patients` (`PatientID`, `Nom`, `Prenom`, `Email`, `Motdepasse`) VALUES
    (1, 'Smadja', 'Ethan', 'ethan@smadja.biz', 'ethan'),
    (2, 'Dray', 'Roxane', 'roxane@dray.fr', 'roxane'),
    (3, 'DRAY', 'ETHAN', 'ethan.28@hotmail.fr', 'Ethan123');

-- --------------------------------------------------------

CREATE TABLE `RendezVous` (
                              `RendezVousID` int(11) NOT NULL,
                              `PatientID` int(11) DEFAULT NULL,
                              `MedecinID` int(11) DEFAULT NULL,
                              `Date` date DEFAULT NULL,
                              `Heure` time DEFAULT NULL,
                              `Lieu` varchar(255) DEFAULT NULL,
                              `Status` varchar(10) DEFAULT NULL,
                              `Record` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `RendezVous` (`RendezVousID`, `PatientID`, `MedecinID`, `Date`, `Heure`, `Lieu`, `Status`, `Record`) VALUES
     (2, 1, 1, '2024-04-17', '11:00:00', NULL, 'Reservé', '2024-04-16 12:49:41'),
     (4, 1, 1, '2024-04-17', '11:30:00', NULL, 'Reservé', '2024-04-16 13:28:28'),
     (5, 1, 1, '2024-04-17', '16:30:00', NULL, 'Reservé', '2024-04-16 13:28:34'),
     (7, 1, 1, '2024-04-17', '15:00:00', NULL, 'Reservé', '2024-04-16 13:28:43'),
     (8, 1, 2, '2024-04-17', '11:30:00', NULL, 'Reservé', '2024-04-16 13:28:49'),
     (12, 1, 2, '2024-04-25', '12:00:00', 'Creteil', 'Reservé', '2024-04-18 14:59:33'),
     (14, 2, 1, '2024-04-19', '09:00:00', 'Levallois', 'Reservé', '2024-04-18 16:19:54'),
     (15, 2, 3, '2024-04-19', '09:30:00', 'Courbevoie', 'Reservé', '2024-04-18 16:23:40');


ALTER TABLE `Medecins`
    ADD PRIMARY KEY (`MedecinID`);


ALTER TABLE `Patients`
    ADD PRIMARY KEY (`PatientID`);

ALTER TABLE `RendezVous`
    ADD PRIMARY KEY (`RendezVousID`),
    ADD KEY `PatientID` (`PatientID`),
    ADD KEY `MedecinID` (`MedecinID`);

ALTER TABLE `Medecins`
    MODIFY `MedecinID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `Patients`
    MODIFY `PatientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `RendezVous`
    MODIFY `RendezVousID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;


ALTER TABLE `RendezVous`
    ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `Patients` (`PatientID`),
    ADD CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`MedecinID`) REFERENCES `Medecins` (`MedecinID`);
COMMIT;
