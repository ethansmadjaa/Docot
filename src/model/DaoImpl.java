package model;

import java.sql.*;

public class DaoImpl implements Dao {

    public Connection conn;
    public Statement stmt;
    public ResultSet rset;


    public DaoImpl() {

    }
    @Override
    public void connect() throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.cj.jdbc.Driver");


        // creation of a JDBC connection
        conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/Doctolib",
                "docteurECE",
                "Docteur");

        // creation of a Statement
        stmt = conn.createStatement();

        System.out.println("Connected to database");
    }


    @Override
    public void disconnect() throws SQLException {
        conn.close();
    }



}


