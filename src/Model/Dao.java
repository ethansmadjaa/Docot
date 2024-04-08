import java.sql.SQLException; // Exception levée en cas de driver non trouvé de la base de données
import java.util.ArrayList;

public interface Dao {

    // Etablir la connexion avec la base de données
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase)
            throws SQLException, ClassNotFoundException ;

    // Fermer la connexion à la base de données
    public void disconnect() throws SQLException ;


}