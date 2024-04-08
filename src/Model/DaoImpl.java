package Model;

import java.sql.*;

public class DaoImpl implements Dao {

    public Connection conn;
    public Statement stmt;
    public ResultSet rset;
    public ResultSetMetaData rsetMeta;

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



}


