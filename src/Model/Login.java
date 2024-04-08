package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends DaoImpl{

    public boolean CheckCredentials(String username, String password) throws SQLException, ClassNotFoundException {

        connect();

        String sqlInsert = "SELECT * FROM User WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rset = pstmt.executeQuery();

            // Check if the login exists
            if (rset.next()) {
                System.out.println("Logged in successfully");
            } else {
                System.out.println("Wrong login or password");
                disconnect();
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
            throw e;
        }
        disconnect();
        return true;
    }
}
