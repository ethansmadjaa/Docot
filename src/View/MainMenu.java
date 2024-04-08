package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {

    public MainMenu(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.setSize(1200, 800);

        // Create and add title
        JLabel title = new JLabel("Doctolib");
        title.setFont(new Font("Century Gothic", Font.BOLD, 48));
        title.setForeground(Color.BLUE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 20, 0);
        c.anchor = GridBagConstraints.CENTER;
        frame.add(title, c);

        frame.setLayout(new BorderLayout(10, 10));

        // Panel for form inputs
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Doctor's Name:"));
        JTextField doctorNameField = new JTextField();
        formPanel.add(doctorNameField);

        JButton searchButton = new JButton("Search");
        formPanel.add(searchButton);

        // Add formPanel to the north of the BorderLayout
        frame.add(formPanel, BorderLayout.NORTH);

        // Results area
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        frame.add(new JScrollPane(resultsArea), BorderLayout.CENTER);

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

    private void performSearch() {}


}
