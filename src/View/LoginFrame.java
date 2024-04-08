package View;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login");
        setSize(1200, 800);
        setLocationRelativeTo(null);  // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and set the LoginPanel as the content pane
        LoginPanel loginPanel = new LoginPanel();
        setContentPane(loginPanel);

        setVisible(true);
    }

}
