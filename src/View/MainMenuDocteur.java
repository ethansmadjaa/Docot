package View;

import Model.Docteur;


import javax.swing.*;
import java.awt.*;

public class MainMenuDocteur {

    private JTextArea RDVArea = new JTextArea(5, 20);

    public MainMenuDocteur(JFrame frame, Docteur docteur) {

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
        frame.add(Intro, c);

        // Appointments area
        RDVArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(RDVArea);
        c.gridy = 3;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        frame.add(scrollPane, c);

        ViewResults.searchRdv(frame, docteur.getId());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
