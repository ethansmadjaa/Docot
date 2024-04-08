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
    private JFrame frame;

    private ControllerLogin controllerLogin;

    public LoginPanel(JFrame frame) {
        System.out.println("Filling Login Frame");
        controllerLogin = new ControllerLogin();

        this.frame = frame;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create and add title
        JLabel title = new JLabel("Doctolib");
        title.setFont(new Font("Century Gothic", Font.BOLD, 48));
        title.setForeground(Color.BLUE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 20, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(title, c);

        // Reset c.gridwidth to 1 for subsequent components
        c.gridwidth = 1;

        // Improve component alignment and spacing
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        // Create and add username label
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Username:"), c);

        // Create and add username field
        c.gridx = 1;
        c.gridy = 1;
        usernameField = new JTextField(20);
        add(usernameField, c);

        // Reset X position for next component
        c.gridx = 0;

        // Create and add password label
        c.gridy = 2;
        add(new JLabel("Password:"), c);

        // Create and add password field
        c.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, c);

        // Create and add login button
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
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
                if (controllerLogin.login(username, password)) {
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    MainMenu window = new MainMenu(frame);
                };

            } catch (SQLException | ClassNotFoundException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}