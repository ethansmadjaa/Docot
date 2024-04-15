package controller;

import model.Login;

import java.sql.SQLException;

public class LoginController {
    public int login(String username, String password) throws SQLException, ClassNotFoundException {
        System.out.println("Logging in with username: " + username + " and password: " + password);
        Login login = new Login();
        if (login.checkCredentials(username, password) == 1) {
            System.out.println("Login successful as a patient");
            return 1;
        } else if (login.checkCredentials(username, password) == 2) {
            System.out.println("Login successful as a doctor");
            return 2;
        }
        else {
            System.out.println("Login failed");
            return 0;
        }
    }
}