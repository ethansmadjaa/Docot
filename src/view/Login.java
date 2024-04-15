package view;

import controller.LoginController;
import model.Docteur;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JPanel {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JFrame frame;

    private final LoginController controllerLogin;

    public Login(JFrame frame) {
        System.out.println("Filling Login Frame");
        controllerLogin = new LoginController();

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
        add(new JLabel("Email:"), c);

        // Create and add username field
        c.gridx = 1;
        c.gridy = 1;
        emailField = new JTextField(20);
        add(emailField, c);

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
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        add(loginButton, c);

    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                if (controllerLogin.login(username, password) == 1) {

                    Patient patient = new Patient(username, password);

                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    MainMenuPatient window = new MainMenuPatient(frame, patient);
                } else if (controllerLogin.login(username, password) == 2) {

                    Docteur docteur = new Docteur(username, password);

                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    MainMenuDocteur window = new MainMenuDocteur(frame, docteur);
                } else if (controllerLogin.login(username, password) == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Wrong username or password, Please try again",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException | ClassNotFoundException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}