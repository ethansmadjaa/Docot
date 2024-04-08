package View;

import Controller.ControllerLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private ControllerLogin controllerLogin;

    public LoginPanel() {
        controllerLogin = new ControllerLogin();

        setLayout(new GridLayout(3, 2));

        // Create and add username label and field
        add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        add(usernameField);

        // Create and add password label and field
        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        // Create and add login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        add(loginButton);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                controllerLogin.login(username, password);
            } catch (SQLException | ClassNotFoundException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}