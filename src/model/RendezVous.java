package model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVous {

    private final int rendezVousID;
    private final int patientID;
    private final int medecinID;
    private final LocalDate date;
    private final LocalTime Heure;
    private String Status;

    public RendezVous(
            int rendezVousID,
            int patientID,
            int medecinID,
            LocalDate dateEtHeure,
            LocalTime heure,
            String Status) {
        this.rendezVousID = rendezVousID;
        this.patientID = patientID;
        this.medecinID = medecinID;
        this.date = dateEtHeure;
        this.Heure = heure;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPatientInfo() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(patientID);
        return patient.toString();
    }

    public void setStatus(String status) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();

        //Changer le status dans l'objet
        this.Status = status;

        //Changer le status sur la bdd
        rdvModel.setRdvStatus(status, rendezVousID);

    }

    public LocalTime getHeure() {
        return Heure;
    }
}
