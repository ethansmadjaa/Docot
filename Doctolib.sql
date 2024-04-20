
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
                                                                                                       (4, 'Darrondeau', 'Jacques', 'Ophtalmologiste', 'jacques@darrondeau.fr', 'jacques', 'Clichy'),
                                                                                                       (5, 'Attias', 'Isaac', 'Dentiste', 'isaac@attias.fr', 'isaac', 'Courbevoie'),
                                                                                                       (6, 'Makovski', 'Daniel', 'Pédiatre', 'daniel@makovski.fr', 'daniel', 'Cergy'),
                                                                                                       (7, 'Rapin', 'Alexis', 'Psychiatre', 'alexis@rapin.fr', 'alexis', 'Deauville'),
                                                                                                       (8, 'Ferrer', 'Elsa', 'Infectiologue', 'elsa@ferrer.fr', 'elsa', 'Creteil'),
                                                                                                       (9, 'Guitare', 'Mohamed', 'Gériatre', 'mohamed@guitare.fr', 'mohamed', 'Arpajon'),
                                                                                                       (10, 'Gallion', 'Eric', 'Cancerologue', 'eric@gallion.fr', 'eric', 'Saint-Mandé'),
                                                                                                       (11, 'Secca', 'Michel', 'Neurologue', 'michel@secca.fr', 'michel', 'Saint-Tropez'),
                                                                                                       (12, 'Roccamcourt', 'michel', 'Podologue', 'michel@rocco.fr', 'michel', 'Nanterre'),
                                                                                                       (13, 'Dupont', 'Pierre', 'Gynecologue', 'pierre@dupont.fr', 'pierre', 'Palaiseau');


CREATE TABLE `Patients` (
                            `PatientID` int(11) NOT NULL,
                            `Nom` varchar(255) DEFAULT NULL,
                            `Prenom` varchar(255) DEFAULT NULL,
                            `Email` varchar(255) DEFAULT NULL,
                            `Motdepasse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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


ALTER TABLE `Medecins`
    ADD PRIMARY KEY (`MedecinID`);


ALTER TABLE `Patients`
    ADD PRIMARY KEY (`PatientID`);


ALTER TABLE `RendezVous`
    ADD PRIMARY KEY (`RendezVousID`),
    ADD KEY `PatientID` (`PatientID`),
    ADD KEY `MedecinID` (`MedecinID`);


ALTER TABLE `Medecins`
    MODIFY `MedecinID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;


ALTER TABLE `Patients`
    MODIFY `PatientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;


ALTER TABLE `RendezVous`
    MODIFY `RendezVousID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;


ALTER TABLE `RendezVous`
    ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `Patients` (`PatientID`),
    ADD CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`MedecinID`) REFERENCES `Medecins` (`MedecinID`);
COMMIT;

