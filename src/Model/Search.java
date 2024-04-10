package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Search extends DaoImpl {

    private static final String SQL_SEARCH_DOCTEUR =
            "SELECT MedecinID FROM Medecins WHERE Nom LIKE ? OR Lieu LIKE ? OR specialite LIKE ?";

    private static final String SQL_SEARCH_DOCTEUR_BY_ID =
            "SELECT Nom, Prenom, Specialite, Email, Lieu FROM Medecins WHERE MedecinID = ?";


    public List<Integer> searchResults(String content) throws SQLException, ClassNotFoundException {

        List<Integer> results = new ArrayList<>();

        connect();

        String searchQuery = "%" + content + "%";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SEARCH_DOCTEUR)) {

            pstmt.setString(1, searchQuery);
            pstmt.setString(2, searchQuery);
            pstmt.setString(3, searchQuery);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                results.add(rset.getInt("docID"));
            }

            if (results.isEmpty()) {
                System.out.println("No results found");
                results.add(0);
            }
            else {
                System.out.println("Results:");
            }

        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
            throw e;
        }
        finally {
            disconnect();
        }

        return results;
    }


    //This is used to create Docteur objects after they found it in the results
    public ArrayList<Docteur> searchDocteur(List<Integer> idList) throws SQLException, ClassNotFoundException {

        connect();

        ArrayList<Docteur> docteurs = new ArrayList<>();

        for (Integer id : idList) {
            Docteur docteur = null;
            try (PreparedStatement pstmt = conn.prepareStatement(SQL_SEARCH_DOCTEUR_BY_ID)) {
                pstmt.setInt(1, id);
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    docteur = new Docteur(
                            rset.getString("nom"),
                            rset.getString("prenom"),
                            rset.getString("specialite"),
                            rset.getString("Email"),
                            rset.getString("Lieu")
                            );
                }

            } catch (SQLException e) {
                System.out.println("Database access error:");
                e.printStackTrace();
                throw e;
            }
            if (docteur != null) {
                docteurs.add(docteur);
            }
        }
        disconnect();
        return docteurs;
    }
}

