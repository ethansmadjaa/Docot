package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Docteur extends DaoImpl{

    private  int id;

    private  String nom;

    private  String prenom;

    private  String lieu;

    private  String specialite;

    private final String email;

    public Docteur(int id, String nom, String prenom,String specialite,  String email, String lieu) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.email = email;
        this.lieu = lieu;
    }
    public Docteur(String email, String password) throws SQLException, ClassNotFoundException {
        this.email=email;
        fetchDocteurFromDatabase(email, password);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getSpecialite(){
        return specialite;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return nom + " " + prenom + " " + lieu + " " + specialite;
    }

    private void fetchDocteurFromDatabase(String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT MedecinID, Nom, Prenom, Specialite, Lieu FROM Medecins WHERE Email = ? AND Motdepasse = ?";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    this.id = rs.getInt("MedecinID");
                    this.nom = rs.getString("Nom");
                    this.prenom = rs.getString("Prenom");
                    this.specialite = rs.getString("Specialite");
                    this.lieu = rs.getString("Lieu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
