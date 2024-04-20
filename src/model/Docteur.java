package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Docteur extends DaoImpl{

    private  int id;

    private  String nom;

    private  String prenom;

    private  String lieu;

    private  String specialite;

    private String email;

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
        fetchDocteurFromDatabaseEmail(email, password);
    }


    public Docteur(int id) throws SQLException, ClassNotFoundException {
        this.id = id;
        fetchDocteurFromDatabaseId(id);
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String toString(){

        return "Nom: " +
                nom +
                ", Prenom: " +
                prenom +
                ", Specialté: " +
                specialite +
                ", Email: " +
                email;
    }

    private void fetchDocteurFromDatabaseEmail(String email, String password) throws SQLException, ClassNotFoundException {
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

    private void fetchDocteurFromDatabaseId(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Email, Nom, Prenom, Specialite, Lieu FROM Medecins WHERE MedecinID = ?";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    this.nom = rs.getString("Nom");
                    this.prenom = rs.getString("Prenom");
                    this.specialite = rs.getString("Specialite");
                    this.email = rs.getString("Email");
                    this.lieu = rs.getString("Lieu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateInfos(
            String nom,
            String prenom,
            String specialite,
            String email,
            String lieu)
            throws SQLException, ClassNotFoundException {

        String UPDATE_MEDECINS_QUERY =
                "UPDATE Medecins " +
                        "SET Nom = ?, " +
                        "Prenom = ?, " +
                        "Specialite = ?, " +
                        "Email = ?, " +
                        "Lieu = ? " +
                        "WHERE MedecinID = ?;";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_MEDECINS_QUERY)) {
            addQueryParameters(stmt, nom, prenom, specialite, email, lieu, this.id);
            stmt.executeUpdate();
            disconnect();
            stmt.close();
            setEmail(email);
            setLieu(lieu);
            setNom(nom);
            setPrenom(prenom);
            setSpecialite(specialite);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addQueryParameters(PreparedStatement stmt, String nom, String prenom,
                                    String specialite, String email, String lieu, int id) throws SQLException {
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, specialite);
        stmt.setString(4, email);
        stmt.setString(5, lieu);
        stmt.setInt(6, id);
    }
}
