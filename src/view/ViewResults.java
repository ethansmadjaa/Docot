package view;

import javax.swing.*;
import java.awt.*;

import controller.RdvController;
import model.Docteur;
import model.Patient;
import model.RdvModel;
import model.RendezVous;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewResults {

    public static void viewDoctors(JScrollPane scrollPane, ArrayList<Docteur> docteurs, Patient patient){
        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Docteur docteur : docteurs) {

            JPanel card = new JPanel();
            card.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            JButton button = new JButton("Reserver un Rendez-vous avec le Dr. " + docteur.getNom());

            RdvController rdvController = new RdvController(docteur, patient);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rdvController.reserverDocteur();
                }
            });

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            card.add(createLabel("Nom: ", docteur.getNom()), gbc);
            card.add(createLabel("Prénom: ", docteur.getPrenom()), gbc);
            card.add(createLabel("Lieu: ", docteur.getLieu()), gbc);
            card.add(createLabel("Spécialité: ", docteur.getSpecialite()), gbc);
            card.add(createLabel("Email: ", docteur.getEmail()), gbc);
            card.add(button, gbc);

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(Color.BLUE)
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

    public static void searchRdvDoc(JScrollPane scrollPane, Docteur docteur) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        ArrayList<RendezVous> listRdv = rdvModel.searchRendezVousDocID(docteur.getId());

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

            card.add(createLabel("Date: ", rdv.getDate().toString()), gbc);
            card.add(createLabel("Heure: ", rdv.getHeure().toString()), gbc);
            card.add(createLabel("Status: ", rdv.getStatus()), gbc);
            card.add(createLabel("Infos du patient:\n ", rdv.getPatientInfo()), gbc);
            card.add(createLabel("Lieu: ", docteur.getLieu()), gbc);

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(Color.BLACK)
            ));

            Patient patient = new Patient(rdv.getPatientId());

            JButton cancelButton = new JButton("Annuler le Rendez-Vous avec le Mr. " + patient.getNom());

            cancelButton.addActionListener(e -> {
                int userChoice = JOptionPane.showConfirmDialog(
                        cards,
                        "Confirmer l'annulation du RDV avec Mr. "
                                + patient.getNom()
                                + "\n pour le "
                                + rdv.getDate() + ".",
                        "Annulation d'un RDV",
                        JOptionPane.YES_NO_OPTION);

                if (userChoice == JOptionPane.YES_OPTION) {
                    try {
                        if (RdvController.cancelRdv(rdv.getRendezVousID())) {
                            JOptionPane.showMessageDialog(
                                    cards,
                                    "Rendez vous annulé avec Succès !",
                                    "Confirmation",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            card.add(cancelButton, gbc);

            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            wrapper.add(card);
            cards.add(wrapper);
        }

        scrollPane.setViewportView(cards);
        scrollPane.getViewport().setBackground(Color.WHITE);
    }

    public static void searchRdvPat(JScrollPane scrollPane, Patient patient) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        ArrayList<RendezVous> listRdv = rdvModel.searchRendezVousPatID(patient.getId());

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

            card.add(createLabel("Date: ", rdv.getDate().toString()), gbc);
            card.add(createLabel("Heure: ", rdv.getHeure().toString()), gbc);
            card.add(createLabel("Status: ", rdv.getStatus()), gbc);
            card.add(createLabel("Infos du medecin:\n ", rdv.getDoctorInfo()), gbc);
            card.add(createLabel("Lieu: ", rdv.getLieu()), gbc);

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(Color.BLACK)
            ));

            Docteur docteur = new Docteur(rdv.getDocId());

            JButton cancelButton = new JButton("Annuler le Rendez-Vous avec le Dr. " + docteur.getNom());

            cancelButton.addActionListener(e -> {
                int userChoice = JOptionPane.showConfirmDialog(
                        cards,
                        "Confirmer l'annulation du RDV avec le Dr. "
                                + docteur.getNom()
                                + "\n pour le "
                                + rdv.getDate() + ".",
                        "Annulation d'un RDV",
                        JOptionPane.YES_NO_OPTION);

                if (userChoice == JOptionPane.YES_OPTION) {
                    try {
                        if (RdvController.cancelRdv(rdv.getRendezVousID())) {
                            JOptionPane.showMessageDialog(
                                    cards,
                                    "Rendez vous annulé avec Succès !",
                                    "Confirmation",
                                    JOptionPane.INFORMATION_MESSAGE);

                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);

                    }
                }

            });

            card.add(cancelButton, gbc);
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            wrapper.add(card);
            cards.add(wrapper);
        }

        scrollPane.setViewportView(cards);
        scrollPane.getViewport().setBackground(Color.WHITE);

    }
}