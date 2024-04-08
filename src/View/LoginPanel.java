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

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Create and add username label and field
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Username:"), c);

        c.gridx = 1;
        usernameField = new JTextField(20);
        add(usernameField, c);

        // Create and add password label and field
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Password:"), c);

        c.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, c);

        // Create and add login button
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        add(loginButton, c);
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