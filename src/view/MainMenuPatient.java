package view;
import controller.SearchController;
import model.Docteur;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
public class MainMenuPatient {

    private static final JTextField searchBar = new JTextField();
    private static final JTextArea resultsArea = new JTextArea(5, 20);
    private static final Font BIG_BOLD_CENTURY_FONT = new Font("Century Gothic", Font.BOLD, 48);
    private static final Font BOLD_CENTURY_FONT = new Font("Century Gothic", Font.BOLD, 24);
    private static final Font PLAIN_CENTURY_FONT = new Font("century gothic", Font.PLAIN, 18);
    private static final String DOCTOLIB = "Doctolib";


    public MainMenuPatient(JFrame frame, Patient patient) throws SQLException, ClassNotFoundException {
        initializePatientWindow(frame, patient);
    }

    public static void initializePatientWindow(JFrame frame, Patient patient) throws SQLException, ClassNotFoundException {
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

    private static void ongletRecherche(JPanel onglet, Patient p) {
        onglet.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        SearchController searchController = new SearchController();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = createLabel(DOCTOLIB, BIG_BOLD_CENTURY_FONT, constraints, 0, onglet);
        titleLabel.setForeground(Color.BLUE);
        String patientLabel = p.getNom() + " " + p.getPrenom() + ", qu'est ce qu'il ne va pas aujourd'hui ?";
        JLabel patientNameLabel = createLabel(patientLabel, BOLD_CENTURY_FONT, constraints, 1, onglet);
        addComponentToPanel(patientNameLabel, constraints, 1, onglet);

        searchBar.setText("Entrez le nom d'un medecin, sa spécialité, ou son lieu de travail");

        searchBar.setForeground(Color.GRAY);



        // Add Focus Listener
        searchBar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Entrez le nom d'un medecin, sa spécialité, ou son lieu de travail")) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setForeground(Color.GRAY);
                    searchBar.setText("Entrez le nom d'un medecin, sa spécialité, ou son lieu de travail");
                }
            }
        });


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

    private static JLabel createLabel(String text, Font font, GridBagConstraints constraints, int gridY, JPanel panel) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        addComponentToPanel(label, constraints, gridY, panel);
        return label;
    }

    private static void addComponentToPanel(Component component, GridBagConstraints constraints, int gridY, JPanel panel) {
        constraints.gridy = gridY;
        panel.add(component, constraints);
    }

    private static void onSearchButtonClick(SearchController searchController, JScrollPane scrollPane, Patient patient) {
        try {
            ArrayList<Docteur> docteurs = searchController.getSearchResults(searchBar.getText());
            resultsArea.setText("");
            ViewResults.viewDoctors(scrollPane, docteurs, patient);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void ongletPatient(JPanel mainPanel, Patient patient) throws SQLException, ClassNotFoundException {
        mainPanel.setLayout(new BorderLayout());
        Font headerFont = new Font("Century Gothic", Font.PLAIN, 32);
        Font labelFont = new Font("Century Gothic", Font.BOLD, 18);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
        headerPanel.setBackground(Color.white);
        JLabel introLabel = new JLabel("Profil de " + patient.getPrenom() + " " + patient.getNom(), SwingConstants.CENTER);
        introLabel.setFont(headerFont);
        introLabel.setForeground(Color.blue);
        headerPanel.add(introLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Patient Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        infoPanel.add(createPatientInfoLabel("Nom: ", patient.getNom(), labelFont));
        infoPanel.add(createPatientInfoLabel("Prénom: ", patient.getPrenom(), labelFont));
        infoPanel.add(createPatientInfoLabel("Email: ", patient.getEmail(), labelFont));

        // Modify Info Button
        JButton modifyButton = new JButton("Modifier mes informations");
        modifyButton.addActionListener(e -> {
            try {
                if (createPopupWindow(patient)) {
                    mainPanel.removeAll();
                    ongletPatient(mainPanel, patient); // Refresh the patient tab
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        infoPanel.add(modifyButton);

        // Appointments Area
        JTextArea appointmentsArea = new JTextArea(5, 20);
        appointmentsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentsArea);
        // Assuming viewResults.searchRdvPat method is defined elsewhere and fills the appointmentsArea
        ViewResults.searchRdvPat(scrollPane, patient);

        // Adding all panels to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        // Add the scrollPane for appointments before the button panel
        mainPanel.add(scrollPane, BorderLayout.EAST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private static JLabel createPatientInfoLabel(String prefixText, String patientInfo, Font font) {
        JLabel label = new JLabel(prefixText + patientInfo);
        label.setFont(font);
        return label;
    }

    private static boolean createPopupWindow(Patient patient) {
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


