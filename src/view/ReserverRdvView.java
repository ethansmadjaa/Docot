package view;

import com.toedter.calendar.JCalendar;
import controller.RdvController;
import model.Docteur;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ReserverRdvView {

    private final Docteur docteur;
    private final Patient patient;
    private final RdvController rdvController;

    public ReserverRdvView(Docteur docteur, Patient patient){
        this.docteur = docteur;
        this.patient = patient;

        rdvController = new RdvController(docteur, patient);

        JDialog reserverFrame = new JDialog();
        reserverFrame.setTitle("Reserver un rdv avec Dr." + docteur.getNom());
        reserverFrame.setSize(800, 800);
        reserverFrame.setLocationRelativeTo(null);
        reserverFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        reserverFrame.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar(); // JCalendar to display a calendar
        JPanel timeSlotsPanel = new JPanel(new GridLayout(0, 2)); // One appointment per row

        reserverFrame.add(calendar, BorderLayout.NORTH); // Calendar at the top
        reserverFrame.add(new JScrollPane(timeSlotsPanel), BorderLayout.CENTER); // Scrollable timeSlotsPanel at the center

        // Add a property change listener to the calendar
        calendar.addPropertyChangeListener("calendar", event -> {
            java.util.Date selectedDate = calendar.getDate();
            try {
                updateTimeSlots(selectedDate, timeSlotsPanel); // Method to update the time slots
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        reserverFrame.setVisible(true);
    }

    private void updateTimeSlots(java.util.Date selectedDate, JPanel timeSlotsPanel) throws SQLException, ClassNotFoundException {
        JLabel Title = new JLabel(String.format(
                "Rendez-vous disponibles le " + selectedDate ));

        timeSlotsPanel.removeAll();

        LocalTime startTime = LocalTime.of(9, 0); // Début de la journée
        LocalTime endTime = LocalTime.of(18, 0); // Fin de la journée

        do{
            if (!isTimeSlotTaken(selectedDate)) {
                JButton timeSlotButton = new JButton(startTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                timeSlotButton.addActionListener(e -> {
                    // Handle reservation logic here
                });
                timeSlotsPanel.add(timeSlotButton);
            }
            startTime = startTime.plusMinutes(30); // Next time slot
        }while (startTime.isBefore(endTime));

            // Refresh and repaint the panel to display buttons
        timeSlotsPanel.revalidate();
        timeSlotsPanel.repaint();
    }

        private boolean isTimeSlotTaken(java.util.Date dateTime) throws SQLException, ClassNotFoundException {
        return  rdvController.checkTimeSlot(dateTime);
    }
}
