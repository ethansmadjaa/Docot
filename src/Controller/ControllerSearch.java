package Controller;

import Model.Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerSearch {
    private Search search;

    public ControllerSearch(String content) throws SQLException, ClassNotFoundException {

        List<Integer> results = new ArrayList<>();

        results = search.search(content);

        for (Integer result : results) {

        }


    }
}