package view;
import controller.SearchController;
import model.Docteur;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class MainMenuPatient {

    private final JTextField searchBar = new JTextField(10);
    private final JTextArea resultsArea = new JTextArea(5, 20);
    private static final Font BIG_BOLD_CENTURY_FONT = new Font("Century Gothic", Font.BOLD, 48);
    private static final Font BOLD_CENTURY_FONT = new Font("Century Gothic", Font.BOLD, 24);
    private static final Font PLAIN_CENTURY_FONT = new Font("century gothic", Font.PLAIN, 18);
    private static final String DOCTOLIB = "Doctolib";


    public MainMenuPatient(JFrame frame, Patient patient) {
        frame.setTitle("Menu principal");

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout()); // Set the frame's layout to BorderLayout

        JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

        JPanel ongletRecherche = new JPanel(new GridBagLayout()); // Use GridBagLayout for the tabs
        onglets.addTab("Prise de rendez-vous", ongletRecherche);

        JPanel ongletPatient = new JPanel(new GridBagLayout()); // Use GridBagLayout for the tabs
        onglets.addTab("Mon compte", ongletPatient);

        ongletRecherche(ongletRecherche, patient); // Set up the first tab
        ongletPatient(ongletPatient, patient); // Set up the second tab

        frame.getContentPane().add(onglets, BorderLayout.CENTER); // Add the JTabbedPane to the center of the frame's content pane

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible
    }

    private void ongletRecherche(JPanel onglet, Patient p) {
        onglet.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        SearchController searchController = new SearchController();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = createLabel(DOCTOLIB, BIG_BOLD_CENTURY_FONT, constraints, 0, onglet);
        titleLabel.setForeground(Color.BLUE);
        String patientLabel = p.getNom() + " " + p.getPrenom() + ", qu'est ce qu'il ne va pas aujourd'hui ?";
        JLabel patientNameLabel = createLabel(patientLabel, BOLD_CENTURY_FONT, constraints, 1, onglet);

        constraints.weightx = 0;
        constraints.ipadx = 150;
        searchBar.setFont(PLAIN_CENTURY_FONT);
        searchBar.setSelectedTextColor(Color.WHITE);
        searchBar.setPreferredSize(new Dimension(50, 25));
        searchBar.setForeground(Color.BLUE);
        addComponentToPanel(searchBar, constraints, 2, onglet);

        JButton searchButton = new JButton("search");
        searchButton.setFont(PLAIN_CENTURY_FONT);
        addComponentToPanel(searchButton, constraints, 3, onglet);

        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        constraints.gridy = 4;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        onglet.add(scrollPane, constraints);
        searchButton.addActionListener(e -> onSearchButtonClick(searchController, scrollPane, p));
    }

    private JLabel createLabel(String text, Font font, GridBagConstraints constraints, int gridY, JPanel panel) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        addComponentToPanel(label, constraints, gridY, panel);
        return label;
    }

    private void addComponentToPanel(Component component, GridBagConstraints constraints, int gridY, JPanel panel) {
        constraints.gridy = gridY;
        panel.add(component, constraints);
    }

    private void onSearchButtonClick(SearchController searchController, JScrollPane scrollPane, Patient patient) {
        try {
            ArrayList<Docteur> docteurs = searchController.getSearchResults(searchBar.getText());
            resultsArea.setText("");
            ViewResults.viewDoctors(scrollPane, docteurs, patient);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ongletPatient(JPanel onglet, Patient p){
        onglet.setLayout(new BorderLayout());

        // Header panel for the introduction
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.white); // Set a light theme or choose a color that matches your design

        JLabel intro = new JLabel(
                "Bah alors , tu t'es trompé dans ton prénom ?",
                SwingConstants.CENTER);
        intro.setFont(new Font("Century Gothic", Font.PLAIN, 32));
        intro.setForeground(Color.blue);
        headerPanel.add(intro);

        // Content panel for doctor's information
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Single column grid with padding
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding around the grid

        JLabel labelName = new JLabel("Nom: " + p.getNom());
        JLabel labelPrenom = new JLabel("Prénom: " + p.getPrenom());
        JLabel labelEmail = new JLabel("Email: " + p.getEmail());
        // Setting a common font for labels
        Font labelFont = new Font("Century Gothic", Font.BOLD, 18);
        labelName.setFont(labelFont);
        labelPrenom.setFont(labelFont);
        labelEmail.setFont(labelFont);

        // Adding labels to the content panel
        contentPanel.add(labelName);
        contentPanel.add(labelPrenom);
        contentPanel.add(labelEmail);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton modifyButton = new JButton("Modifier mes informations");
        modifyButton.addActionListener(e -> {
            try {
                if(CreatePopupWindow(p)){
                    onglet.removeAll();
                    ongletPatient(onglet, p);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        buttonPanel.add(modifyButton);

        // Adding panels to the main panel
        onglet.add(headerPanel, BorderLayout.NORTH);
        onglet.add(contentPanel, BorderLayout.CENTER);
        onglet.add(buttonPanel, BorderLayout.SOUTH);

        onglet.revalidate();
        onglet.repaint();
    }

    private boolean CreatePopupWindow(Patient patient) throws SQLException {
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
        JTextField nameField = new JTextField(patient.getNom());
        JTextField prenomField = new JTextField(patient.getPrenom());
        JTextField emailField = new JTextField(patient.getEmail());

        // Adding labels and text fields to the panel
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        // Save button to submit changes
        JButton saveButton = new JButton("Enregistrer");

        saveButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                    modifyDialog,
                    "Confirmer la modification de vos informations" +
                            "nouvelles informations:" +
                            "nom: " + nameField.getText() +
                            "prénom: " + prenomField.getText() +
                            "email: " + emailField.getText(),

                    "Confirmation de la modification",
                    JOptionPane.YES_NO_OPTION);
            try {
                if (JOptionPane.YES_OPTION == option) {
                    if (patient.updateInfos(
                            nameField.getText(),
                            prenomField.getText(),
                            emailField.getText())
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