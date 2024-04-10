package Model;

import java.sql.Date;

public class RendezVous {

    private int rendezVousID;
    private int patientID;
    private int medecinID;
    private Date dateEtHeure;
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
}
