package ui;

import javax.swing.*;
import java.awt.*;

public class InspectionFrame extends JFrame {

    public InspectionFrame() {
        setTitle("AutoCheck – Inspection en cours");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
        add(createSidebar(), BorderLayout.EAST);
    }

    // ================= HEADER =================
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        header.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Accueil Visite Technique");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel subtitle = new JLabel(
            "Sélectionnez une catégorie pour accéder aux points de contrôle."
        );
        subtitle.setForeground(Color.DARK_GRAY);

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setOpaque(false);
        text.add(title);
        text.add(Box.createVerticalStrut(5));
        text.add(subtitle);

        header.add(text, BorderLayout.WEST);

        return header;
    }

    // ================= CENTER =================
    private JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(2, 2, 25, 25));
        center.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        center.setBackground(Color.WHITE);

        center.add(createCard("Documents Administratifs", "Carte grise, Plaques, VIN"));
        center.add(createCard("Moteur", "Niveaux, Fuites, Échappement"));
        center.add(createCard("Éclairage", "Feux, Signalisation"));
        center.add(createCard("Freinage", "Disques, Plaquettes, Liquide"));

        return center;
    }

    private JButton createCard(String title, String desc) {
        JButton card = new JButton();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(30, 30, 30));
        card.setForeground(Color.WHITE);
        card.setFocusPainted(false);

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 18));
        t.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        JLabel d = new JLabel(desc);
        d.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        d.setForeground(Color.LIGHT_GRAY);

        card.add(t, BorderLayout.NORTH);
        card.add(d, BorderLayout.CENTER);

        card.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Ouverture : " + title)
        );

        return card;
    }

    // ================= SIDEBAR =================
    private JPanel createSidebar() {
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(300, 0));
        side.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Synthèse de l’inspection");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        side.add(title);
        side.add(Box.createVerticalStrut(20));

        side.add(createStatus("Documents"));
        side.add(createStatus("Moteur"));
        side.add(createStatus("Éclairage"));
        side.add(createStatus("Freinage"));

        side.add(Box.createVerticalGlue());

        JButton finish = new JButton("Finaliser l’inspection");
        finish.setBackground(new Color(20, 20, 20));
        finish.setForeground(Color.WHITE);

        side.add(finish);

        return side;
    }

    private JPanel createStatus(String name) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        row.add(new JLabel(name), BorderLayout.WEST);
        row.add(new JLabel("À faire"), BorderLayout.EAST);

        return row;
    }
}
