package controller;

import model.Docteur;
import model.RdvModel;
import model.RendezVous;
import model.Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    public ArrayList<Docteur> getSearchResults(String content) throws SQLException, ClassNotFoundException {

        Search search = new Search();

        List<Integer> results = search.searchResults(content);

        if (results.isEmpty()) {
            return null;
        } else {
            return search.searchDocteur(results);
        }
    }

    public ArrayList<RendezVous> getRendezVous(int id)  throws SQLException, ClassNotFoundException{

        RdvModel rdv = new RdvModel();

        ArrayList<RendezVous> rendezVous;

        rendezVous = rdv.searchRendezVousDocID(id);

        if (rendezVous.isEmpty()) {
            return null;
        }else{
            return rendezVous;
        }
    }
}