package view;

import com.toedter.calendar.JCalendar;
import controller.RdvController;
import model.Docteur;
import model.Patient;
import model.RendezVous;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReserveRdvView {

    private final Docteur docteur;
    private final Patient patient;
    private final RdvController rdvController;
    private final JDialog reserverDialog;

    public ReserveRdvView(Docteur docteur, Patient patient) {
        this.docteur = docteur;
        this.patient = patient;

        rdvController = new RdvController(docteur, patient);

        reserverDialog = new JDialog();
        reserverDialog.setTitle("Reserver un rdv avec Dr." + docteur.getNom());
        reserverDialog.setSize(800, 800);
        reserverDialog.setLocationRelativeTo(null);
        reserverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        reserverDialog.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar(); // JCalendar to display a calendar
        JPanel timeSlotsPanel = new JPanel(new GridLayout(0, 2)); // Two appointments per row

        reserverDialog.add(calendar, BorderLayout.NORTH); // Calendar at the top
        reserverDialog.add(new JScrollPane(timeSlotsPanel), BorderLayout.CENTER); // Scrollable timeSlotsPanel at the center

        // Add a property change listener to the calendar
        calendar.addPropertyChangeListener("calendar", event -> {
            java.util.Date utilDate = calendar.getDate();
            java.sql.Date selectedDate = new java.sql.Date(utilDate.getTime());
            LocalDateTime now = LocalDateTime.now();
            if (selectedDate.before(java.sql.Timestamp.valueOf(now))) {
                JOptionPane.showMessageDialog(reserverDialog,
                        "La date sélectionnée est antérieure " +
                        "à la date d'aujourd'hui," +
                        " veuillez choisir une autre date.", "Date invalide", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    updateTimeSlots(selectedDate, timeSlotsPanel); // Method to update the time slots
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace(); // Consider displaying an error dialog instead
                }
            }
        });

        reserverDialog.setVisible(true);
    }

    private JLabel createTitleLabel(Date selectedDate) {
        JLabel title = new JLabel(String.format("Rendez-vous disponibles le %1$td %1$tB %1$tY", selectedDate));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 18));
        title.setForeground(Color.BLUE);
        return title;
    }

    private JSeparator createSeparator() {
        return new JSeparator();
    }

    private void updateTimeSlots(Date selectedDate, JPanel timeSlotsPanel) throws SQLException, ClassNotFoundException {
        // Clear the timeSlotsPanel and set up a title label
        timeSlotsPanel.removeAll();
        JLabel title = createTitleLabel(selectedDate);
        timeSlotsPanel.add(title);

        JSeparator separator = createSeparator();
        timeSlotsPanel.add(separator);

        // Creat time slot buttons
        createTimeSlotButtons(selectedDate, timeSlotsPanel);

        // Refresh and repaint the panel to display buttons
        timeSlotsPanel.revalidate();
        timeSlotsPanel.repaint();
    }

    private void createTimeSlotButtons(Date selectedDate, JPanel timeSlotsPanel) throws SQLException, ClassNotFoundException {
        LocalTime startTime = LocalTime.of(9, 0); // Start of working hours
        LocalTime endTime = LocalTime.of(18, 0); // End of working hours
        ArrayList<RendezVous> rdv = getRendezVousByDocteur();
        do{
             if (!isTimeSlotTaken(rdv, startTime, selectedDate.toLocalDate())) {
                JButton timeSlotButton = new JButton(String.valueOf(startTime));
                 LocalTime finalStartTime = startTime;
                 timeSlotButton.addActionListener(e -> {
                     try {
                         confirmReservation(selectedDate, finalStartTime);
                     } catch (SQLException | ClassNotFoundException ex) {
                         throw new RuntimeException(ex);
                     }
                 });
                timeSlotsPanel.add(timeSlotButton);
            }
            startTime = startTime.plusMinutes(30); // Increment to the next time slot
        }while (startTime.isBefore(endTime));
    }

    private void confirmReservation(Date selectedDate, LocalTime time) throws SQLException, ClassNotFoundException {
        int option = JOptionPane.showConfirmDialog(
                reserverDialog,
                "Confirmer la réservation\nPour le "
                        + selectedDate + " à " + time
                        + " avec le docteur " + docteur.getNom() + " ?",
                "Confirmation du RDV",
                JOptionPane.YES_NO_OPTION);

        if(JOptionPane.YES_OPTION == option){
            if(rdvController.reserverRdv(selectedDate, time, docteur.getId(), patient.getId())){
                JOptionPane.showMessageDialog(
                        reserverDialog,
                        "Réservation réussie !",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);

                reserverDialog.dispose();

            }
        }
    }

    private ArrayList<RendezVous> getRendezVousByDocteur() throws SQLException, ClassNotFoundException {
        return rdvController.getDocteurRdv();
    }

    private boolean isTimeSlotTaken(ArrayList<RendezVous> appointments, LocalTime timeSlot, LocalDate date) {
        for (RendezVous appointment : appointments) {
            System.out.println(appointment.getDate()+ " " + appointment.getHeure() + " " + timeSlot);
            if (appointment.getDate().equals(date) && appointment.getHeure().equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }
}

