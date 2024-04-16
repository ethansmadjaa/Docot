package controller;

import model.Docteur;
import model.Patient;
import model.RdvModel;
import model.RendezVous;
import view.ReserverRdvView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class RdvController {
    private final Docteur docteur;
    private final Patient patient;

    public RdvController(Docteur docteur, Patient patient) {
        this.docteur = docteur;
        this.patient = patient;
    }

    public void reserverrdv(){
        ReserverRdvView rdvView = new ReserverRdvView(docteur, patient);
    }

    public boolean checkTimeSlot(Date date) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        ArrayList<RendezVous> rdvList = rdvModel.searchRdvdocIdPatId(docteur.getId(), patient.getId());
        for(RendezVous rdv : rdvList){
            return !rdv.getDateEtHeure().equals(date);
        }
        return false;
    }
}
