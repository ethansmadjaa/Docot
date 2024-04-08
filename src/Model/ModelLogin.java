package Model;

public class ModelLogin {

    String username;

    String password;

    public ModelLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean CheckCredentials(String username, String password) throws SQLException  {
        DaoImpl DAO = new DaoImpl
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

}