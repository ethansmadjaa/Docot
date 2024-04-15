package view;

import javax.swing.*;
import java.awt.*;
import model.Docteur;
import model.RdvModel;
import model.RendezVous;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewResults {

    public static void viewDoctors(JScrollPane scrollPane, ArrayList<Docteur> doctors){
        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Docteur doctor : doctors) {

            JPanel card = new JPanel();
            card.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            card.add(createLabel("Nom: ", doctor.getNom()), gbc);
            card.add(createLabel("Prénom: ", doctor.getPrenom()), gbc);
            card.add(createLabel("Lieu: ", doctor.getLieu()), gbc);
            card.add(createLabel("Spécialité: ", doctor.getSpecialite()), gbc);
            card.add(createLabel("Email: ", doctor.getEmail()), gbc);

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(Color.BLACK)
            ));

            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            wrapper.add(card);
            cards.add(wrapper);
        }

        scrollPane.setViewportView(cards);
        scrollPane.getViewport().setBackground(Color.WHITE);
    }



    private static JLabel createLabel(String prefix, String text) {
        return new JLabel(prefix + text);
    }

    public static void searchRdv(JScrollPane scrollPane, Docteur docteur) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        ArrayList<RendezVous> listRdv = rdvModel.searchRendezVous(docteur.getId());

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (RendezVous rdv : listRdv) {

            JPanel card = new JPanel();
            card.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            card.add(createLabel("Date et Heure: ", rdv.getDateEtHeure().toString()), gbc);
            card.add(createLabel("Status: ", rdv.getStatus()), gbc);
            card.add(createLabel("Infos du patient:\n ", rdv.getPatientInfo()), gbc);
            card.add(createLabel("Lieu: ", docteur.getLieu()), gbc);

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(Color.BLACK)
            ));

            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            wrapper.add(card);
            cards.add(wrapper);
        }

        scrollPane.setViewportView(cards);
        scrollPane.getViewport().setBackground(Color.WHITE);
    }
}