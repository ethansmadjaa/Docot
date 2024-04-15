package View;
import Controller.SearchController;
import Model.Docteur;
import Model.Patient;

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
        searchButton.addActionListener(e -> onSearchButtonClick(searchController, scrollPane));
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

    private void ongletPatient(JPanel onglet, Patient p){

    }

    private void onSearchButtonClick(SearchController searchController, JScrollPane scrollPane) {
        try {
            ArrayList<Docteur> docteurs = searchController.getSearchResults(searchBar.getText());
            resultsArea.setText("");
            for (Docteur docteur : docteurs) {
                ViewResults.viewDoctors(scrollPane, docteur);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}