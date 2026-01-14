package ui;

import model.*;
import service.CatalogueParametres;
import util.*;

import javax.swing.*;
import java.awt.*;

public class FreinagePage extends JPanel {

    private InspectionFrame frame;

    public FreinagePage(InspectionFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(18, 18, 18));

        add(createLeftPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    // ================== PANEL GAUCHE ==================
    private JPanel createLeftPanel() {
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(280, 0));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(new Color(22, 22, 22));
        left.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel num = new JLabel("04");
        num.setFont(new Font("SansSerif", Font.BOLD, 48));
        num.setForeground(Color.WHITE);

        JLabel title = new JLabel("FREINAGE");
        title.setForeground(Color.LIGHT_GRAY);
        title.setFont(new Font("SansSerif", Font.BOLD, 12));

        JProgressBar conformite = new JProgressBar(0, 100);
        conformite.setValue(100);
        conformite.setStringPainted(true);
        conformite.setForeground(new Color(74, 222, 128));
        conformite.setBackground(Color.DARK_GRAY);

        left.add(num);
        left.add(Box.createVerticalStrut(10));
        left.add(title);
        left.add(Box.createVerticalStrut(30));
        left.add(new JLabel("Conformité"));
        left.add(Box.createVerticalStrut(5));
        left.add(conformite);

        return left;
    }

    // ================== PANEL CENTRAL ==================
    private JScrollPane createCenterPanel() {
        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBackground(new Color(18, 18, 18));
        list.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        CatalogueParametres.creerParametres().stream()
                .filter(p -> p.getCategorie() == CategorieControle.MOTEUR)
                .forEach(p -> {
                    JPanel paramPanel = createParametrePanel(p);

                    paramPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

                    list.add(paramPanel);
                    list.add(Box.createVerticalStrut(14));
                });

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        return scroll;
    }

    // ================== PARAMETRE CONTROLE ==================
    private JPanel createParametrePanel(ParametreControle parametre) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(25, 25, 25));
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JButton header = new JButton(parametre.getNom());
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(Color.WHITE);
        header.setFocusPainted(false);

        JPanel contenu = new JPanel();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        contenu.setBackground(new Color(22, 22, 22));

        contenu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(45, 45, 45), 1, true),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));

        contenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        contenu.setVisible(false);

        for (ElementControle d : parametre.getDefauts()) {
            contenu.add(createDefautCheckbox(d));
        }
        contenu.add(Box.createVerticalStrut(6));

        header.addActionListener(e -> {
            contenu.setVisible(!contenu.isVisible());
            wrapper.revalidate();
            wrapper.repaint();
        });

        wrapper.add(header, BorderLayout.NORTH);
        wrapper.add(contenu, BorderLayout.CENTER);

        return wrapper;
    }

    // ================== DEFAUT CHECKBOX ==================
    private JPanel createDefautCheckbox(ElementControle defaut) {

        JPanel row = new JPanel();
        row.setLayout(new BorderLayout());
        row.setBackground(new Color(22, 22, 22));
        row.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));

        JLabel label = new JLabel(defaut.getDescription());
        label.setForeground(new Color(200, 200, 200));
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JCheckBox box = new JCheckBox();
        box.setOpaque(false);
        box.setPreferredSize(new Dimension(20, 20));

        box.addActionListener(e -> {
            if (box.isSelected()) {
                frame.ajouterDefaut(defaut);
            } else {
                frame.retirerDefaut(defaut);
            }
        });

        row.add(label, BorderLayout.WEST);
        row.add(box, BorderLayout.EAST);

        return row;
    }

    // ================== PANEL BAS (SUIVANT) ==================
    private JPanel createBottomPanel() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bottom.setBackground(new Color(18, 18, 18));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton accueil = new JButton("Accueil");
        accueil.setBackground(new Color(60, 60, 60));
        accueil.setForeground(Color.WHITE);
        accueil.setFocusPainted(false);

        accueil.addActionListener(e -> frame.afficherPage("Accueil"));

        JButton suivant = new JButton("Verdict");
        suivant.setBackground(Color.WHITE);
        suivant.setForeground(Color.BLACK);
        suivant.setFocusPainted(false);

        suivant.addActionListener(e -> frame.afficherPage("Eclairage"));

        bottom.add(accueil);
        bottom.add(suivant);

        return bottom;
    }

}
