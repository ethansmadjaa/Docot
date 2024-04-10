
CREATE TABLE Patients (
      PatientID INT PRIMARY KEY,
      Nom VARCHAR(255),
      Prenom VARCHAR(255),
      Email VARCHAR(255),
      Motdepasse VARCHAR(255)
);

CREATE TABLE Medecins (
      MedecinID INT PRIMARY KEY,
      Nom VARCHAR(255),
      Prenom VARCHAR(255),
      Specialite VARCHAR(255),
      Email VARCHAR(255),
      Motdepasse VARCHAR(255)
);


CREATE TABLE RendezVous (
    RendezVousID INT PRIMARY KEY,
    PatientID INT,
    MedecinID INT,
    DateEtHeure DATETIME,
    Status VARCHAR(10) CHECK (Status IN ('Libre', 'Occupé', 'Passé')),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (MedecinID) REFERENCES Medecins(MedecinID)
);


