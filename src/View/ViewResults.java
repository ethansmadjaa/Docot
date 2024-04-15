package View;

import javax.swing.*;
import java.awt.*;
import Model.Docteur;
import Model.RdvModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewResults {

    private static int doctorIndex = 0;

    public static void viewDoctors(JScrollPane scrollPane, ArrayList<Docteur> doctors){
        JPanel cards = new JPanel(new CardLayout());

        for (Docteur doctor : doctors) {
            JPanel card = new JPanel(new BorderLayout());
            card.add(createLabel("Nom: ", doctor.getNom()), BorderLayout.NORTH);
            card.add(createLabel("Prénom: ", doctor.getPrenom()), BorderLayout.WEST);
            card.add(createLabel("Lieu: ", doctor.getLieu()), BorderLayout.CENTER);
            card.add(createLabel("Specialité: ", doctor.getSpecialite()), BorderLayout.EAST);
            card.add(createLabel("Email: ", doctor.getEmail()), BorderLayout.SOUTH);

            cards.add(card, "card" + doctorIndex);
            doctorIndex++;
        }

        scrollPane.setViewportView(cards);
    }

    public static void showCard(JScrollPane scrollPane, int index){
        JPanel cards = (JPanel) scrollPane.getViewport().getView();
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "card" + index);
    }

    private static JLabel createLabel(String prefix, String text) {
        return new JLabel(prefix + text);
    }

    public static void searchRdv(JFrame frame, int id) throws SQLException, ClassNotFoundException {
        RdvModel rdvModel = new RdvModel();
        rdvModel.searchRendezVous(id);
    }
}