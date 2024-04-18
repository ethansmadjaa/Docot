package view;

import model.Docteur;


import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

@SuppressWarnings("ALL")
public class MainMenuDocteur {

    public MainMenuDocteur(JFrame frame, Docteur docteur) throws SQLException, ClassNotFoundException {

        initializeDoctorWindow(frame, docteur);

    }

    public static void initializeDoctorWindow(JFrame frame, Docteur docteur) throws SQLException, ClassNotFoundException {
        frame.setTitle("Menu principal");

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout()); // Set the frame's layout to BorderLayout

        JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

        JPanel ongletRecherche = new JPanel(new GridBagLayout()); // Use GridBagLayout for the tabs
        onglets.addTab("Prise de rendez-vous", ongletRecherche);

        JPanel ongletPatient = new JPanel(new GridBagLayout()); // Use GridBagLayout for the tabs
        onglets.addTab("Mon compte", ongletPatient);

        ongletRendezVous(ongletRecherche, docteur); // Set up the first tab
        ongletDocteur(ongletPatient, docteur); // Set up the second tab

        frame.getContentPane().add(onglets, BorderLayout.CENTER); // Add the JTabbedPane to the center of the frame's content pane

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible
    }

    public static void ongletRendezVous(JPanel panel, Docteur docteur) throws SQLException, ClassNotFoundException {

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Doctolib", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 48));
        title.setForeground(Color.BLUE);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 20, 0);
        panel.add(title, c);

        // Doctor's name
        JLabel Intro = new JLabel(
                "Hello Doc " +
                        docteur.getNom() +
                        ", pret a sauver des vies ?",
                SwingConstants.CENTER);
        Intro.setFont(new Font("Century Gothic", Font.PLAIN, 32));
        Intro.setForeground(Color.blue);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 20, 0);
        panel.add(Intro, c);

        // intro 2
        JLabel Intro2 = new JLabel(
                "Voici vos prochains Rendez-Vous:",
                SwingConstants.CENTER);
        Intro2.setFont(new Font("Century Gothic", Font.PLAIN, 26));
        Intro2.setForeground(Color.BLACK);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 20, 0);
        panel.add(Intro2, c);

        // Appointments area
        JTextArea RDVArea = new JTextArea(5, 20);
        RDVArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(RDVArea);
        c.gridy = 3;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, c);

        ViewResults.searchRdvDoc(scrollPane, docteur);

    }

    public static void ongletDocteur(JPanel panel, Docteur docteur) {
        panel.setLayout(new BorderLayout());

        // Header panel for the introduction
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.white); // Set a light theme or choose a color that matches your design

        JLabel intro = new JLabel(
                "Bah alors Doc " + docteur.getNom() + ", tu t'es trompé dans ton prénom ?",
                SwingConstants.CENTER);
        intro.setFont(new Font("Century Gothic", Font.PLAIN, 32));
        intro.setForeground(Color.blue);
        headerPanel.add(intro);

        // Content panel for doctor's information
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Single column grid with padding
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding around the grid

        JLabel labelName = new JLabel("Nom: " + docteur.getNom());
        JLabel labelPrenom = new JLabel("Prénom: " + docteur.getPrenom());
        JLabel labelSpeciality = new JLabel("Spécialité: " + docteur.getSpecialite());
        JLabel labelEmail = new JLabel("Email: " + docteur.getEmail());
        JLabel labelLieu = new JLabel("Lieu: " + docteur.getLieu());

        // Setting a common font for labels
        Font labelFont = new Font("Century Gothic", Font.BOLD, 18);
        labelName.setFont(labelFont);
        labelPrenom.setFont(labelFont);
        labelSpeciality.setFont(labelFont);
        labelEmail.setFont(labelFont);
        labelLieu.setFont(labelFont);

        // Adding labels to the content panel
        contentPanel.add(labelName);
        contentPanel.add(labelPrenom);
        contentPanel.add(labelSpeciality);
        contentPanel.add(labelEmail);
        contentPanel.add(labelLieu);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton modifyButton = new JButton("Modifier mes informations");
        modifyButton.addActionListener(e -> {
            try {
                if(CreatePopupWindow(docteur)){
                    panel.removeAll();
                    ongletDocteur(panel, docteur);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        buttonPanel.add(modifyButton);

        // Adding panels to the main panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }


    private static boolean CreatePopupWindow(Docteur docteur) throws SQLException {
        // Creating a dialog window for modification
        JDialog modifyDialog = new JDialog();
        modifyDialog.setTitle("Modifier mes informations");
        modifyDialog.setSize(400, 300);
        modifyDialog.setLocationRelativeTo(null);
        modifyDialog.setModal(true);
        modifyDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel to hold our form elements
        JPanel formPanel = new JPanel(new GridLayout(0, 2)); // 0 rows, 2 columns to auto-adjust the number of rows

        // Creating text fields pre-filled with doctor's information
        JTextField nameField = new JTextField(docteur.getNom());
        JTextField prenomField = new JTextField(docteur.getPrenom());
        JTextField specialityField = new JTextField(docteur.getSpecialite());
        JTextField emailField = new JTextField(docteur.getEmail());
        JTextField lieuField = new JTextField(docteur.getLieu());

        // Adding labels and text fields to the panel
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Spécialité:"));
        formPanel.add(specialityField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Lieu:"));
        formPanel.add(lieuField);

        // Save button to submit changes
        JButton saveButton = new JButton("Enregistrer");

        saveButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                    modifyDialog,
                    "Confirmer la modification de vos informations" +
                            "nouvelles informations:" +
                            "nom: " + nameField.getText() +
                            "prénom: " + prenomField.getText() +
                            "spécialité: " + specialityField.getText() +
                            "email: " + emailField.getText() +
                            "lieu: " + lieuField.getText(),

            "Confirmation de la modification",
                    JOptionPane.YES_NO_OPTION);
            try {
                if (JOptionPane.YES_OPTION == option) {
                    if (docteur.updateInfos(
                            nameField.getText(),
                            prenomField.getText(),
                            specialityField.getText(),
                            emailField.getText(),
                            lieuField.getText() )
                    ) {
                        JOptionPane.showMessageDialog(
                                modifyDialog,
                                "Modification réussie !",
                                "Confirmation",
                                JOptionPane.INFORMATION_MESSAGE);
                        modifyDialog.dispose();


                    }
                }
                modifyDialog.dispose();
            }catch(SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Adding the form panel and save button to the dialog's content pane
        modifyDialog.getContentPane().add(formPanel, BorderLayout.CENTER);
        modifyDialog.getContentPane().add(saveButton, BorderLayout.SOUTH);

        // Display the dialog
        modifyDialog.setVisible(true);
        return true;
    }

}
