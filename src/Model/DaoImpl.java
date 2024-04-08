package Model;

import java.sql.*;

public class DaoImpl implements Dao {

    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;

    public DaoImpl(Connection conn, Statement stmt, ResultSet rset, ResultSetMetaData rsetMeta) {
        this.conn = conn;
        this.stmt = stmt;
        this.rset = rset;
        this.rsetMeta = rsetMeta;
    }

    public DaoImpl() {

    }

    @Override
    public void connect() throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        //création d'une connexion JDBC à la base
        conn = DriverManager.getConnection("Doctolib", "docteurECE", "Docteur");

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    @Override
    public void disconnect() throws SQLException {
        conn.close();
    }

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
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
            throw e;
        }
        disconnect();
        return true;
    }

}


