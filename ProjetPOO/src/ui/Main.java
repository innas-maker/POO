package ui;

import ui.AccueilFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AccueilFrame().setVisible(true);
        });
    }
}
