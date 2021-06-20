package InterfazGráfica;

import javax.swing.*;

public interface Ventanas {
    String TITLE = "Story of Seasons 2D";

    static void fixComponents(JFrame frame, JPanel panel, String message) {
        JOptionPane.showMessageDialog(panel, message);
        frame.setContentPane(panel);
        frame.setTitle(TITLE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    static void fixComponents(JFrame frame, JPanel panel) {
        frame.add(panel);
        frame.setTitle(TITLE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    abstract void fixComponents(JFrame frame, JComponent component);
}