package Model;

import java.sql.*;

public class Patient extends DaoImpl {

    private int id;
    private String Nom;
    private String Prenom;
    private String Email;

    public Patient(String email, String password) throws SQLException, ClassNotFoundException {
        this.Email = email;
        fetchPatientInfoFromDB(email, password);
    }

    private void fetchPatientInfoFromDB(String email, String password) throws SQLException, ClassNotFoundException {
        String patientInfo = "";
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

    public String getNom() {
        return Nom;
    }
    public String getPrenom() {
        return Prenom;
    }

}
