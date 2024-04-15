package model;

import java.sql.SQLException; // Exception levée en cas de driver non trouvé de la base de données


public interface Dao {

    // Etablir la connexion avec la base de données
    void connect() throws SQLException, ClassNotFoundException ;

    // Fermer la connexion à la base de données
    void disconnect() throws SQLException ;


}