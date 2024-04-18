package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class RdvModel extends DaoImpl{

    public boolean cancelRdv(int rdvId) throws RuntimeException, SQLException, ClassNotFoundException {
        String query = "DELETE FROM RendezVous WHERE RendezVousID = ?";

        connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, rdvId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to cancel RendezVous.", e);
        }

    }

    public ArrayList<RendezVous> searchRendezVousDocID(int id) throws SQLException, ClassNotFoundException {
        String query = "select * from RendezVous where MedecinID= ? AND Date >= NOW() ORDER BY Record";

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
                        rset.getDate("Date").toLocalDate(),
                        rset.getTime("Heure").toLocalTime(),
                        rset.getString("status"),
                        rset.getString("lieu"));
                rendezVous.add(rdv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        disconnect();
        return rendezVous;
    }

    public ArrayList<RendezVous> searchRendezVousPatID(int id) throws SQLException, ClassNotFoundException {
        String query = "select * from RendezVous where PatientID= ? AND Date >= NOW() ORDER BY Record";

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
                        rset.getDate("Date").toLocalDate(),
                        rset.getTime("Heure").toLocalTime(),
                        rset.getString("status"),
                        rset.getString("lieu"));
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

    public ArrayList<RendezVous> searchRdvDocIdPatId(int docId, int patId) throws SQLException, ClassNotFoundException {
        String query =
                "select * from RendezVous where " +
                "MedecinID= ? AND PatientID = ? AND Date >= NOW() " +
                "ORDER BY RendezVous.Record";

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
                        rset.getDate("Date").toLocalDate(),
                        rset.getTime("Heure").toLocalTime(),
                        rset.getString("status"),
                        rset.getString("lieu"));
                rendezVous.add(rdv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        disconnect();
        return rendezVous;
    }

    public boolean reserverRdv(Date date, LocalTime time, int docId, int patId)throws SQLException, ClassNotFoundException {
        String query = "Insert INTO RendezVous (PatientId, MedecinID, Date, Heure, lieu, Status) VALUES (?,?,?,?,?,?)";
        connect();

        Docteur docteur = new Docteur(docId);

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patId);
            pstmt.setInt(2, docId);
            pstmt.setDate(3, date);
            pstmt.setTime(4, Time.valueOf(time));
            pstmt.setString(5, docteur.getLieu());
            pstmt.setString(6, "Reservé");
            pstmt.executeUpdate();
            System.out.println("reservation effectuée.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("reservation échouée.");
            return false;
        }
    }
}