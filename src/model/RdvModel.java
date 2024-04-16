package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RdvModel extends DaoImpl{

    public ArrayList<RendezVous> searchRendezVousDocID(int id) throws SQLException, ClassNotFoundException {
        String query = "select * from RendezVous where MedecinID= ? AND DateEtHeure > NOW() ORDER BY RendezVous.DateEtHeure";

        ArrayList<RendezVous> rendezVous = new ArrayList<>();

        connect();

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                RendezVous rdv = new RendezVous(
                        rset.getInt("RendezVousID"),
                        rset.getInt("PatientID"),
                        rset.getInt("MedecinID"),
                        rset.getDate("dateEtHeure"),
                        rset.getString("status"));
                rendezVous.add(rdv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        disconnect();
        return rendezVous;
    }

    public void setRdvStatus(String status, int RendezVousID) throws SQLException, ClassNotFoundException {
        String query = "UPDATE RendezVous SET Status = ? WHERE rendezVousID = ? ";

        connect();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, status);
            pstmt.setInt(2, RendezVousID);

            rset = pstmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        disconnect();
    }

    public ArrayList<RendezVous> searchRdvdocIdPatId(int docId, int patId) throws SQLException, ClassNotFoundException {
        String query =
                "select * from RendezVous where " +
                "MedecinID= ? AND PatientID = ? AND DateEtHeure > NOW() " +
                "ORDER BY RendezVous.DateEtHeure";

        ArrayList<RendezVous> rendezVous = new ArrayList<>();

        connect();

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, docId);
            pstmt.setInt(2, patId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                RendezVous rdv = new RendezVous(
                        rset.getInt("RendezVousID"),
                        rset.getInt("PatientID"),
                        rset.getInt("MedecinID"),
                        rset.getDate("dateEtHeure"),
                        rset.getString("status"));
                rendezVous.add(rdv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        disconnect();
        return rendezVous;
    }
}
/**
 *
 * Utiliser un jseparator
 *
        Mettre à jour le status du rdv en passé

 UPDATE RendezVous SET Status = 'Passé' WHERE DateEtHeure < NOW();

                Inserer un nouveau RDV

 INSERT INTO RendezVous (PatientID, MedecinID, DateEtHeure, Raison) VALUES (?, ?, ?, ?);

                Voir les rdv pris par medecin ID

 SELECT * FROM RendezVous
 JOIN Patients ON RendezVous.PatientID = Patients.PatientID
 JOIN Medecins ON RendezVous.MedecinID = Medecins.MedecinID
 WHERE RendezVous.MedecinID = ? AND RendezVous.DateEtHeure > NOW()
 ORDER BY RendezVous.DateEtHeure;

 pour Voir les rdv dispo, faire cette requete et afficher les horraires qui ne sont pas dans ce resultat

 SELECT DateEtHeure FROM RendezVous WHERE MedecinID = ? AND DateEtHeure > NOW() ORDER BY DateEtHeure;

 */

