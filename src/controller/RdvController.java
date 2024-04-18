package controller;

import model.Docteur;
import model.Patient;
import model.RdvModel;
import model.RendezVous;
import view.ReserveRdvView;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;


public class RdvController {
    private final Docteur docteur;
    private final Patient patient;

    public RdvController(Docteur docteur, Patient patient) {
        this.docteur = docteur;
        this.patient = patient;
    }

    public static boolean cancelRdv(int rdvId) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();

        if(rdvModel.cancelRdv(rdvId)){
            return true;
        }else{
            throw new RuntimeException("Failed to cancel appointment");
        }

    }

    public void reserverDocteur(){
        ReserveRdvView rdvView = new ReserveRdvView(docteur, patient);
    }

    public boolean reserverRdv(java.sql.Date date, LocalTime time, int docId, int patId) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        return rdvModel.reserverRdv(date, time, docId, patId);
    }

    public ArrayList<RendezVous> getDocteurRdv() throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        return rdvModel.searchRdvDocIdPatId(docteur.getId(), patient.getId());
    }
}
