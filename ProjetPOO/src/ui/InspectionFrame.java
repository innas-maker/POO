package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.*;
import model.ElementControle;

public class InspectionFrame extends JFrame {

    public CardLayout cardLayout;
    public JPanel container;
    /**
     *
     */
    public List<ElementControle> defautsSelectionnes;

    public InspectionFrame() {
        this.defautsSelectionnes = new ArrayList<>();
        setTitle("AutoCheck – Inspection en cours");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        JPanel accueil = new JPanel(new BorderLayout());

        accueil.add(createHeader(), BorderLayout.NORTH);
        accueil.add(createCenter(), BorderLayout.CENTER);

        container.add(accueil, "Accueil");

        container.add(new DocumentsPage(this), "Documents");
        container.add(new MoteurPage(this), "Moteur");
        container.add(new EclairagePage(this), "Eclairage");
        container.add(new FreinagePage(this), "Freinage");
        setContentPane(container);
        cardLayout.show(container, "Accueil");

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

        center.add(createCard("Documents Administratifs", "Plaques d'immatriculation, Certificat d'immatriculation, Assurance"));
        center.add(createCard("Moteur", "Fuites moteur, Support moteur, Courrois/Poulies"));
        center.add(createCard("Éclairage", "Feux de route, Clignotant, Feux stop"));
        center.add(createCard("Freinage", "Plaquettes, Disques, Liquide de frein"));

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

        card.addActionListener(e -> {
            switch (title) {
                case "Documents Administratifs" ->
                    afficherPage("Documents");
                case "Moteur" ->
                    afficherPage("Moteur");
                case "Éclairage" ->
                    afficherPage("Eclairage");
                case "Freinage" ->
                    afficherPage("Freinage");
            }
        });

        return card;
    }

    public void afficherPage(String page) {
        cardLayout.show(container, page);
    }

    public void ajouterDefaut(ElementControle defaut) {
        if (!defautsSelectionnes.contains(defaut)) {
            defautsSelectionnes.add(defaut);
        }
    }

    public void retirerDefaut(ElementControle defaut) {
        defautsSelectionnes.remove(defaut);
    }

    public List<ElementControle> getDefautsSelectionnes() {
        return defautsSelectionnes;
    }

}
