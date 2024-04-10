package View;
import Controller.SearchController;
import Model.Docteur;
import Model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class MainMenuPatient {

    private JTextField searchBar = new JTextField(20);
    private JTextArea resultsArea = new JTextArea(5, 20);

    public MainMenuPatient(JFrame frame, Patient patient) {
        Patient p = patient;

        SearchController searchController = new SearchController();

        frame.getContentPane().removeAll();

        frame.setLayout(new GridBagLayout());

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
        frame.add(title, c);

        // Search label
        JLabel label = new JLabel("Qu'est ce qu'il ne va pas aujourd'hui ?", SwingConstants.CENTER);
        label.setFont(new Font("Century Gothic", Font.BOLD, 24));
        c.gridy = 1;
        frame.add(label, c);

        // Search bar
        searchBar.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        c.gridy = 2;
        frame.add(searchBar, c);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        c.gridy = 3;
        c.insets = new Insets(10, 0, 10, 0);
        frame.add(searchButton, c);

        // Results area
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        c.gridy = 4;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        frame.add(scrollPane, c);

        searchButton.addActionListener(e -> onSearchButtonClick(searchController, scrollPane));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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