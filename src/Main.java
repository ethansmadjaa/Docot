import view.Login;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        System.out.println("Creating Login Frame");
        frame.setTitle("Login");
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and set the LoginPanel as the content panel
        Login login = new Login(frame);
        frame.setContentPane(login);

        frame.setVisible(true);

    }
}