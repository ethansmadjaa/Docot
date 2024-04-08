package View;

import javax.swing.*;
import java.awt.event.*;

public class MainMenu {
    private JFrame frame;


    public MainMenu(JFrame frame) {
        this.frame = frame;

        frame.getContentPane().removeAll();

        JButton button1 = new JButton("Button 1");
        button1.setBounds(50, 100, 117, 29);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                function1();
            }
        });
        frame.getContentPane().add(button1);

        JButton button2 = new JButton("Button 2");
        button2.setBounds(200, 100, 117, 29);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                function2();
            }
        });
        frame.getContentPane().add(button2);

        frame.setVisible(true);
    }

    private void function1() {
        System.out.println("Button 1 pressed");
        // Your code for functionality of button 1
    }

    private void function2() {
        System.out.println("Button 2 pressed");
        // Your code for functionality of button 2
    }
}