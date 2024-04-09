package View;

import Model.Docteur;

import javax.swing.*;
import java.awt.*;

public class Results {

        public static void viewResults(JScrollPane scrollPane, Docteur docteur){
            JPanel panel = new JPanel(new BorderLayout());

            panel.add(createLabel("Nom: ", docteur.getNom()), BorderLayout.NORTH);
            panel.add(createLabel("Prénom: ", docteur.getPrenom()), BorderLayout.WEST);
            panel.add(createLabel("Lieu: ", docteur.getLieu()), BorderLayout.EAST);
            panel.add(createLabel("Specialité: ", docteur.getSpecialite()), BorderLayout.CENTER);

            scrollPane.setViewportView(panel);
        }

        private static JLabel createLabel(String prefix, String text) {
            return new JLabel(prefix + text);
        }
}

