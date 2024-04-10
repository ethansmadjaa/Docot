package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RdvModel extends DaoImpl{

    public ArrayList<RendezVous> searchRendezVous(int id) throws SQLException, ClassNotFoundException {
        String query = "select * from RendezVous where RendezVousID= ? AND DateEtHeure > NOW()";

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
        }
        disconnect();
        return rendezVous;


    }

}
/**
        Mettre a jour le status du rdv en passé

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

