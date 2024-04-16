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
        String sql = "SELECT PatientID, Nom, Prenom FROM Patients WHERE PatientID = ? ";

        connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    this.Email = rs.getString("Email");
                    this.Nom = rs.getString("Nom");
                    this.Prenom = rs.getString("Prenom");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public String toString(){
        return ("nom: " + Nom + ", Prenom: " + Prenom + ", Email: " + Email);
    }

}
