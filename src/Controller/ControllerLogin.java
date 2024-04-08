package Controller;

import Model.Login;

import java.sql.SQLException;

public class ControllerLogin {
    public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        System.out.println("Logging in with username: " + username + " and password: " + password);
        Login login = new Login();
        if (login.CheckCredentials(username, password)) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Login unsuccessful");
            return false;
        }
    }
}