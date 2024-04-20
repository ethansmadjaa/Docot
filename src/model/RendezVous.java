package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVous {

    private final int rendezVousID;
    private final int patientID;
    private final int medecinID;
    private final LocalDate date;
    private final LocalTime Heure;
    private String Status;
    private final String lieu;

    public RendezVous(
            int rendezVousID,
            int patientID,
            int medecinID,
            LocalDate dateEtHeure,
            LocalTime heure,
            String Status,
            String lieu) {
        this.rendezVousID = rendezVousID;
        this.patientID = patientID;
        this.medecinID = medecinID;
        this.date = dateEtHeure;
        this.Heure = heure;
        this.Status = Status;
        this.lieu = lieu;
    }

    public String getStatus() {
        return Status;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public String getPatientInfo() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(patientID);
        return patient.toString();
    }

    public String getDoctorInfo() throws SQLException, ClassNotFoundException {
        Docteur docteur = new Docteur(medecinID);
        return docteur.toString();
    }

    public LocalTime getHeure() {
        return Heure;
    }

    public int getDocId() {
        return medecinID;
    }

    public int getPatientId() {
        return patientID;
    }

    public int getRendezVousID() {
        return rendezVousID;
    }
}
