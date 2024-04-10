package Controller;

import Model.Docteur;
import Model.RdvModel;
import Model.RendezVous;
import Model.Search;

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

        ArrayList<RendezVous> rendezVous = new ArrayList<>();

        rendezVous = rdv.searchRendezVous(id);

        if (rendezVous.isEmpty()) {
            return null;
        }else{
            return rendezVous;
        }
    }
}