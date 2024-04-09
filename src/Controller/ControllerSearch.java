package Controller;

import Model.Docteur;
import Model.Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerSearch {
    private Search search;

    public ControllerSearch(String content) throws SQLException, ClassNotFoundException {

        List<Integer> results = new ArrayList<>();

        results = search.searchResults(content);
        if (results.size() > 0) {
            ArrayList<Docteur> docteurs = search.searchDocteur(results);
        }

    }
}