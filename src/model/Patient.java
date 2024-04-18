package model;

import java.sql.*;

public class Patient extends DaoImpl {

    private int id;
    private String Nom;
    private String Prenom;
    private String Email;

    public Patient(String email, String password) throws SQLException, ClassNotFoundException {
        this.Email = email;
        fetchPatientInfoFromEmail(email, password);
    }

    public Patient(int patientID) throws SQLException, ClassNotFoundException {
        this.id = patientID;
        fetchPatientInfoFromID(patientID);
    }

    public Patient(String nom, String prenom, String email, String password) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Email = email;
        try {
            insertPatientIntoDb(nom, prenom, email, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void fetchPatientInfoFromEmail(String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT PatientID, Nom, Prenom FROM Patients WHERE Email = ? AND Motdepasse = ?";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    this.id = rs.getInt("PatientID");
                    this.Nom = rs.getString("Nom");
                    this.Prenom = rs.getString("Prenom");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchPatientInfoFromID(int patientID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Email, Nom, Prenom FROM Patients WHERE PatientID = ? ";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    this.Nom = rs.getString("Nom");
                    this.Prenom = rs.getString("Prenom");
                    this.Email = rs.getString("Email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertPatientIntoDb(String nom, String prenom, String email, String password) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Patients (Nom, Prenom, Email, MotDePasse) VALUES (?, ?, ?, ?)";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fetchPatientInfoFromEmail(email, password);
    }


    public String getNom() {
        return Nom;
    }
    public String getPrenom() {
        return Prenom;
    }
    public int getId() {
        return id;
    }
    public String getEmail() { return Email; }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }

    public void setEmail(String email) {
        this.Email=email;
    }

    public String toString(){
        return ("Nom: " + Nom + " , Prenom: " + Prenom + ",  Email: " + Email);
    }


    public boolean updateInfos(
            String nom,
            String prenom,
            String email)
            throws SQLException, ClassNotFoundException {

        String UPDATE_MEDECINS_QUERY =
                "UPDATE Patients " +
                        "SET Nom = ?, " +
                        "Prenom = ?, " +
                        "Email = ? " +
                        "WHERE PatientID = ?;";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_MEDECINS_QUERY)) {
            addQueryParameters(stmt, nom, prenom,  email, this.id);
            stmt.executeUpdate();
            disconnect();
            stmt.close();
            setEmail(email);
            setNom(nom);
            setPrenom(prenom);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // New helper method
    private void addQueryParameters(PreparedStatement stmt, String nom, String prenom,
                                     String email, int id) throws SQLException {
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, email);
        stmt.setInt(4, id);
    }
}


