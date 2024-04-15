package model;

import java.sql.Date;
import java.sql.SQLException;

public class RendezVous {

    private final int rendezVousID;
    private final int patientID;
    private final int medecinID;
    private final Date dateEtHeure;
    private String Status;

    public RendezVous(
            int rendezVousID,
            int patientID,
            int medecinID,
            Date dateEtHeure,
            String Status) {
        this.rendezVousID = rendezVousID;
        this.patientID = patientID;
        this.medecinID = medecinID;
        this.dateEtHeure = dateEtHeure;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public Date getDateEtHeure() {
        return dateEtHeure;
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
}
