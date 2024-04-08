package Controller;

import Model.DaoImpl;

import java.sql.SQLException;

public class ControllerLogin {
    public void login(String username, String password) throws SQLException, ClassNotFoundException {
        System.out.println("Logging in with username: " + username + " and password: " + password);
        DaoImpl dao = new DaoImpl();
        if( !dao.CheckCredentials(username, password) ){System.out.println("Login unsuccessful");}
        else{System.out.println("Login successful");}
    }
}