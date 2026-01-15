package ui;

import ui.*;
import javax.swing.SwingUtilities;
import model.Vehicule;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new AccueilFrame().setVisible(true);
        });
    }
}
