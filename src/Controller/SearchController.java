package Controller;

import Model.Docteur;
import Model.Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    private final Search search;

    public SearchController() {
        this.search = new Search();
    }

    public ArrayList<Docteur> getSearchResults(String content) throws SQLException, ClassNotFoundException {

        List<Integer> results = search.searchResults(content);

        if (results.isEmpty()) {
            return null;
        } else {
            return search.searchDocteur(results);
        }
    }
}