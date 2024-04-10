package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends DaoImpl {
    public int checkCredentials(String username, String password) throws SQLException, ClassNotFoundException {
        connect();

        // Verify Medecins credentials
        if (verifyUserCredentials(username, password, "Medecins")) {
            disconnect();
            return 2;
        }

        // Verify Patients credentials
        if (verifyUserCredentials(username, password, "Patients")) {
            disconnect();
            return 1;
        }

        // Invalid credentials
        System.out.println("Invalid username or password");
        disconnect();
        return 0;
    }

    private boolean verifyUserCredentials(String username, String password, String tableName) throws SQLException {
        String sqlDoc = "SELECT * FROM " + tableName + " WHERE Email = ? AND Motdepasse = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlDoc)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rset = pstmt.executeQuery();

            if (rset.next()) {
                System.out.println("Logged in successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
            throw e;
        }

        // No such user found
        return false;
    }
}
