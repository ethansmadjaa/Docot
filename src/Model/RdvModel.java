package Model;

public class RdvModel{
}
/**                 Trouver un rdv

SELECT * FROM RendezVous WHERE Status = 'Libre' AND DateEtHeure > NOW() AND MedcinID = ?;

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

