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

        JButton createAccountButton = new JButton("Creer un compte");
        createAccountButton.addActionListener(e->{
                createAnAccount(frame);
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(createAccountButton, c);
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

    public void createAnAccount(JFrame frame) {
        // Create a dialog window
        JDialog dialog = new JDialog(frame, "Créer un compte", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(frame);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Créer un nouveau compte");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Create labels
        JLabel nameLabel = new JLabel("Nom:");
        JLabel firstNameLabel = new JLabel("Prénom:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Mot de Passe:");

        // Create text fields
        JTextField nameTextField = new JTextField(20);
        JTextField firstNameTextField = new JTextField(20);
        JTextField emailTextField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Add components to the panel
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);
        contentPanel.add(firstNameLabel);
        contentPanel.add(firstNameTextField);
        contentPanel.add(emailLabel);
        contentPanel.add(emailTextField);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);
        contentPanel.add(new JLabel("")); // Placeholder for grid alignment

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createAccountButton = new JButton("Créer");

        createAccountButton.addActionListener(e ->{
               createPatientAccount(
                   dialog,
                   nameTextField.getText(),
                   firstNameTextField.getText(),
                   emailField.getText(),
                   new String(passwordField.getPassword()));
        }
        );
        buttonPanel.add(createAccountButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding panels to the dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show the dialog
        dialog.setVisible(true);
    }

    private void createPatientAccount(JDialog dialog, String nom, String prenom, String email, String motDePasse) {
        try {
            Patient patient = new Patient(nom, prenom, email, motDePasse);

            if(patient.getId() != 0){
                dialog.setVisible(false);
                dialog.dispose();
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                MainMenuPatient window = new MainMenuPatient(frame, patient);

            };
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog,
                    "Unable to create account. Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}