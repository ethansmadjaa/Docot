DROP DATABASE Doctolib;

CREATE DATABASE `Doctolib`;


CREATE TABLE Patients (
      PatientID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
      Nom VARCHAR(255),
      Prenom VARCHAR(255),
      Email VARCHAR(255),
      Motdepasse VARCHAR(255)
);

CREATE TABLE Medecins (
      MedecinID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
      Nom VARCHAR(255),
      Prenom VARCHAR(255),
      Specialite VARCHAR(255),
      Email VARCHAR(255),
      Motdepasse VARCHAR(255),
    Lieu VARCHAR(255)
);


CREATE TABLE RendezVous (
    RendezVousID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    PatientID INT,
    MedecinID INT,
    DateEtHeure DATETIME,
    Status VARCHAR(10) CHECK (Status IN ('Libre', 'Occupé', 'Passé')),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (MedecinID) REFERENCES Medecins(MedecinID)
);
