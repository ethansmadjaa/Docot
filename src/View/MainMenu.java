package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    private JTextField searchBar = new JTextField(20); // Create the search bar
    private JTextArea resultsArea = new JTextArea(5, 20); // Area for displaying results

    public MainMenu(JFrame frame) {
        frame.getContentPane().removeAll();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL; // Components expand to fill horizontal space

        // Title
        JLabel title = new JLabel("Doctolib", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 48));
        title.setForeground(Color.BLUE);
        c.gridx = 0; // Column
        c.gridy = 0; // Row
        c.weightx = 1; // Distribute space evenly across columns
        c.weighty = 0; // Title does not need extra vertical space
        c.gridwidth = GridBagConstraints.REMAINDER; // End row
        c.insets = new Insets(20, 0, 20, 0); // Top padding
        frame.add(title, c);

        // Search label
        JLabel label = new JLabel("Qu'est ce qu'il ne va pas aujourd'hui ?", SwingConstants.CENTER);
        label.setFont(new Font("Century Gothic", Font.BOLD, 24));
        c.gridy = 1; // Next row
        frame.add(label, c);

        // Search bar
        searchBar.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        c.gridy = 2; // Next row
        frame.add(searchBar, c);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        c.gridy = 3; // Next row
        c.insets = new Insets(10, 0, 10, 0); // Padding
        frame.add(searchButton, c);

        // Results area
        resultsArea.setEditable(false); // Make the results area non-editable
        JScrollPane scrollPane = new JScrollPane(resultsArea); // Add scrolling to results area
        c.gridy = 4; // Next row
        c.weighty = 1; // Give extra vertical space to results area
        c.fill = GridBagConstraints.BOTH; // Components expand to fill space in both directions
        frame.add(scrollPane, c); // Add scrollPane instead of resultsArea directly

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });


        frame.setLocationRelativeTo(null); // Center frame on screen
        frame.setVisible(true);
    }

    private void performSearch() {
        // Your search logic here
        // Use searchBar.getText() as input for search
        // Display results in resultsArea
    }
}
