package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import model.*;
import util.NiveauDefaillance;
import util.CategorieControle;
import service.AnalyseurControle;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// ✅ Change de JFrame à JPanel
public class VerdictPage extends JPanel {

    private InspectionFrame parentFrame;
    private Vehicule vehicule;
    private List<ElementControle> defautsSelectionnes;
    private NiveauDefaillance verdictGlobal;
    private Map<CategorieControle, Integer> statsParCategorie;

    // ✅ Constructeur corrigé
    public VerdictPage(InspectionFrame frame) {
        this.parentFrame = frame;
        this.vehicule = frame.getVehicule();
        this.defautsSelectionnes = frame.getDefautsSelectionnes();

        // Calculer le verdict global
        AnalyseurControle analyseur = new AnalyseurControle() {
            @Override
            public int genererVerdict(float pourcentage) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public float genererPourcentage(List<ElementControle> elements, NiveauDefaillance niveau) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int CompterElements(List<ElementControle> elements) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        this.verdictGlobal = analyseur.genererVerdict(defautsSelectionnes);

        // Calculer les statistiques par catégorie
        this.statsParCategorie = calculerStatistiques();

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        Image bg = loadImage("/images/desert.jpg");
        BackgroundPanel root = new BackgroundPanel(bg);
        root.setLayout(new BorderLayout());

        root.add(createNavbar(), BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(createMainContent());
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        root.add(scroll, BorderLayout.CENTER);

        add(root, BorderLayout.CENTER);
    }

    // =================== CALCUL DES STATISTIQUES ===================
    private Map<CategorieControle, Integer> calculerStatistiques() {
        Map<CategorieControle, Integer> stats = new HashMap<>();

        // Initialiser toutes les catégories à 0
        stats.put(CategorieControle.DOCUMENTS_ADMINISTRATIFS, 0);
        stats.put(CategorieControle.MOTEUR, 0);
        stats.put(CategorieControle.ECLAIRAGE, 0);
        stats.put(CategorieControle.FREINAGE, 0);

        // Compter les défauts par catégorie
        for (ElementControle defaut : defautsSelectionnes) {
            CategorieControle cat = defaut.getParametre().getCategorie();
            stats.put(cat, stats.get(cat) + 1);
        }

        return stats;
    }

    // =================== NAVBAR ===================
    private JPanel createNavbar() {
        GlassPanel nav = new GlassPanel(25, new Color(255, 255, 255, 51));
        nav.setLayout(new BorderLayout());
        nav.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoPanel.setOpaque(false);

        JLabel icon = new JLabel("🚗");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        icon.setForeground(Color.WHITE);

        JLabel logo = new JLabel("Auto");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(Color.WHITE);

        JLabel logoLight = new JLabel("Inspect");
        logoLight.setFont(new Font("Arial", Font.PLAIN, 20));
        logoLight.setForeground(new Color(255, 255, 255, 204));

        logoPanel.add(icon);
        logoPanel.add(logo);
        logoPanel.add(logoLight);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        statusPanel.setOpaque(false);

        JPanel statusBadge = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        statusBadge.setBackground(new Color(255, 255, 255, 51));
        statusBadge.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, new Color(255, 255, 255, 76)),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        JLabel statusDot = new JLabel("●");
        statusDot.setForeground(new Color(74, 222, 128));
        statusDot.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel statusText = new JLabel("Inspection Terminée");
        statusText.setForeground(Color.WHITE);
        statusText.setFont(new Font("Arial", Font.PLAIN, 13));

        statusBadge.add(statusDot);
        statusBadge.add(statusText);
        statusPanel.add(statusBadge);

        nav.add(logoPanel, BorderLayout.WEST);
        nav.add(statusPanel, BorderLayout.EAST);

        return nav;
    }

    // =================== MAIN CONTENT ===================
    private JPanel createMainContent() {
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        main.add(createHeader());
        main.add(Box.createVerticalStrut(30));
        main.add(createVerdictCard());

        return main;
    }

    // =================== HEADER ===================
    private JPanel createHeader() {
        GlassPanel header = new GlassPanel(25, new Color(255, 255, 255, 204));
        header.setLayout(new BorderLayout());
header.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel subtitle = new JLabel("RAPPORT DE FIN DE MISSION");
        subtitle.setFont(new Font("Arial", Font.BOLD, 11));
        subtitle.setForeground(new Color(107, 114, 128));

        JLabel title = new JLabel("Verdict Final");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(17, 24, 39));

        JLabel desc = new JLabel("<html>Analyse globale de l'état du véhicule après vérification<br>de tous les points de contrôle.</html>");
        desc.setFont(new Font("Arial", Font.PLAIN, 13));
        desc.setForeground(new Color(75, 85, 99));

        left.add(subtitle);
        left.add(Box.createVerticalStrut(8));
        left.add(title);
        left.add(Box.createVerticalStrut(12));
        left.add(desc);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        right.setOpaque(false);

        String vehiculeInfo = vehicule.getType() + " " + vehicule.getModele();
        right.add(createInfoBadge("VÉHICULE", vehiculeInfo));
        right.add(createInfoBadge("IMMATRICULATION", vehicule.getPlaque()));

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    private JPanel createInfoBadge(String label, String value) {
        JPanel badge = new JPanel();
        badge.setLayout(new BoxLayout(badge, BoxLayout.Y_AXIS));
        badge.setBackground(new Color(243, 244, 246, 128));
        badge.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(16, new Color(229, 231, 235, 128)),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Arial", Font.PLAIN, 10));
        labelComp.setForeground(new Color(107, 114, 128));
        labelComp.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.BOLD, 14));
        valueComp.setForeground(new Color(17, 24, 39));
        valueComp.setAlignmentX(Component.CENTER_ALIGNMENT);

        badge.add(labelComp);
        badge.add(Box.createVerticalStrut(4));
        badge.add(valueComp);

        return badge;
    }

    // =================== VERDICT CARD ===================
    private JPanel createVerdictCard() {
        LiquidGlassPanel card = new LiquidGlassPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        card.add(createVerdictIcon());
        card.add(Box.createVerticalStrut(30));
        card.add(createVerdictTitle());
        card.add(Box.createVerticalStrut(15));
        card.add(createVerdictDescription());
        card.add(Box.createVerticalStrut(40));
        card.add(createStatistics());
        card.add(Box.createVerticalStrut(40));
        card.add(createRulesBox());
        card.add(Box.createVerticalStrut(40));
        card.add(createActions());
card.setMaximumSize(new Dimension(1100, Integer.MAX_VALUE));

        return card;
    }

    private JPanel createVerdictIcon() {
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int size = 90;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                Color iconColor = getVerdictColor();

                // Outer glow
                g2.setColor(new Color(iconColor.getRed(), iconColor.getGreen(), iconColor.getBlue(), 51));
                g2.fillOval(x - 10, y - 10, size + 20, size + 20);

                // Background circle
                g2.setColor(new Color(iconColor.getRed(), iconColor.getGreen(), iconColor.getBlue(), 26));
                g2.fillOval(x, y, size, size);

                // Border
                g2.setStroke(new BasicStroke(4));
                g2.setColor(new Color(iconColor.getRed(), iconColor.getGreen(), iconColor.getBlue(), 128));
                g2.drawOval(x, y, size, size);

                // Icon
                g2.setColor(iconColor);
                g2.setFont(new Font("Arial", Font.BOLD, 70));
                String symbol = getVerdictSymbol();
                FontMetrics fm = g2.getFontMetrics();
                int textX = x + (size - fm.stringWidth(symbol)) / 2;
                int textY = y + ((size - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(symbol, textX, textY);
            }
        };
        iconPanel.setOpaque(false);
        iconPanel.setPreferredSize(new Dimension(800, 105));
        iconPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        return iconPanel;
    }

    private JLabel createVerdictTitle() {
        String titleText = getVerdictText();
        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Arial", Font.BOLD, 44));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private JLabel createVerdictDescription() {
        String descText = getVerdictDescription();
        JLabel desc = new JLabel("<html><div style='text-align: center; width: 700px;'>" + descText + "</div></html>");
        desc.setFont(new Font("Arial", Font.PLAIN, 16));
        desc.setForeground(new Color(209, 213, 219));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        desc.setMaximumSize(new Dimension(800, 100));
        return desc;
    }

    // =================== STATISTICS ===================
    private JPanel createStatistics() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setOpaque(false);
        statsPanel.setMaximumSize(new Dimension(1000, 100));

        statsPanel.add(createStatCard("Documents", CategorieControle.DOCUMENTS_ADMINISTRATIFS));
        statsPanel.add(createStatCard("Moteur", CategorieControle.MOTEUR));
        statsPanel.add(createStatCard("Éclairage", CategorieControle.ECLAIRAGE));
        statsPanel.add(createStatCard("Freinage", CategorieControle.FREINAGE));

        return statsPanel;
    }

    private JPanel createStatCard(String category, CategorieControle cat) {
        int count = statsParCategorie.get(cat);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(255, 255, 255, 13));
        card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(16, new Color(255, 255, 255, 26)),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel categoryLabel = new JLabel(category.toUpperCase());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        categoryLabel.setForeground(new Color(156, 163, 175));
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        countPanel.setOpaque(false);

        Color countColor = count > 0 ? getVerdictColor() : new Color(74, 222, 128);
        JLabel countLabel = new JLabel(String.valueOf(count));
        countLabel.setFont(new Font("Arial", Font.BOLD, 28));
        countLabel.setForeground(countColor);

        JLabel unitLabel = new JLabel(count > 0 ? "défaut" + (count > 1 ? "s" : "") : "défaut");
        unitLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        unitLabel.setForeground(new Color(107, 114, 128));

        countPanel.add(countLabel);
        countPanel.add(unitLabel);

        card.add(categoryLabel);
        card.add(countPanel);

        return card;
    }

    // =================== RULES BOX ===================
    private JPanel createRulesBox() {
        JPanel rulesBox = new JPanel();
        rulesBox.setLayout(new BoxLayout(rulesBox, BoxLayout.Y_AXIS));
        rulesBox.setBackground(new Color(0, 0, 0, 76));
        rulesBox.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(16, new Color(255, 255, 255, 13)),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        rulesBox.setMaximumSize(new Dimension(1000, 200));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setOpaque(false);

        JLabel infoIcon = new JLabel("ℹ");
        infoIcon.setFont(new Font("Arial", Font.BOLD, 18));
        infoIcon.setForeground(new Color(96, 165, 250));

        JLabel titleLabel = new JLabel("RÈGLES DE VALIDATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(infoIcon);
        titlePanel.add(titleLabel);

        rulesBox.add(titlePanel);
        rulesBox.add(Box.createVerticalStrut(15));
        rulesBox.add(createRuleLine("●", new Color(239, 68, 68), "Critique :", "Immobilisation immédiate. Contre-visite obligatoire après réparation immédiate."));
        rulesBox.add(Box.createVerticalStrut(10));
        rulesBox.add(createRuleLine("●", new Color(234, 179, 8), "Majeur :", "Circulation autorisée pendant 2 mois. Contre-visite obligatoire après réparation."));
        rulesBox.add(Box.createVerticalStrut(10));
        rulesBox.add(createRuleLine("●", new Color(34, 197, 94), "Mineur :", "Circulation autorisée. Réparations conseillées sans obligation de contre-visite."));

        return rulesBox;
    }

    private JPanel createRuleLine(String bullet, Color bulletColor, String bold, String text) {
        JPanel line = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        line.setOpaque(false);

        JLabel bulletLabel = new JLabel(bullet);
        bulletLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bulletLabel.setForeground(bulletColor);

        JLabel ruleLabel = new JLabel("<html><b>" + bold + "</b> " + text + "</html>");
        ruleLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        ruleLabel.setForeground(new Color(156, 163, 175));

        line.add(bulletLabel);
        line.add(ruleLabel);

        return line;
    }

    // =================== ACTIONS ===================
    private JPanel createActions() {
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        actions.setOpaque(false);
        actions.setMaximumSize(new Dimension(800, 60));

        RoundedButton rapport = new RoundedButton("VOIR LE RAPPORT COMPLET", new Color(17, 24, 39));
        rapport.setForeground(Color.WHITE);
        rapport.setPreferredSize(new Dimension(280, 50));
        rapport.addActionListener(e -> genererRapport());

        RoundedButton nouvelle = new RoundedButton("NOUVELLE VISITE", new Color(255, 255, 255, 26));
        nouvelle.setForeground(Color.WHITE);
        nouvelle.setPreferredSize(new Dimension(220, 50));
        nouvelle.addActionListener(e -> {
            // ✅ Retour à l'accueil de l'inspection
            parentFrame.afficherPage("Accueil");
        });

        actions.add(rapport);
        actions.add(nouvelle);

        return actions;
    }

    private void genererRapport() {
        StringBuilder rapport = new StringBuilder();
        rapport.append("=== RAPPORT D'INSPECTION ===\n\n");
        rapport.append("Véhicule: ").append(vehicule.getType()).append(" ").append(vehicule.getModele()).append("\n");
        rapport.append("Immatriculation: ").append(vehicule.getPlaque()).append("\n");
        rapport.append("Année: ").append(vehicule.getAnnee()).append("\n\n");
        rapport.append("Verdict global: ").append(getVerdictText()).append("\n\n");
        rapport.append("Défauts détectés:\n");

        if (defautsSelectionnes.isEmpty()) {
            rapport.append("Aucun défaut détecté.\n");
        } else {
            for (ElementControle defaut : defautsSelectionnes) {
                rapport.append("- ").append(defaut.getDescription());
                rapport.append(" (").append(defaut.getNiveau()).append(")\n");
            }
        }

        JTextArea textArea = new JTextArea(rapport.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(
                SwingUtilities.getWindowAncestor(this),
                scrollPane,
                "Rapport Complet",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =================== VERDICT LOGIC ===================
    private Color getVerdictColor() {
        switch (verdictGlobal) {
            case CRITIQUE:
                return new Color(239, 68, 68);
            case MAJEUR:
                return new Color(234, 179, 8);
            case MINEUR:
                return new Color(34, 197, 94);
            default:
                return new Color(74, 222, 128);
        }
    }

    private String getVerdictSymbol() {
        switch (verdictGlobal) {
            case CRITIQUE:
                return "✕";
            case MAJEUR:
                return "⚠";
            case MINEUR:
                return "!";
            default:
                return "✓";
        }
    }

    private String getVerdictText() {
        switch (verdictGlobal) {
            case CRITIQUE:
                return "Critique - Immobilisation";
            case MAJEUR:
                return "Majeur - Visite à revoir";
            case MINEUR:
                return "Mineur - À surveiller";
            default:
                return "Conforme - Visite validée";
        }
    }

    private String getVerdictDescription() {
        switch (verdictGlobal) {
            case CRITIQUE:
                return "Le véhicule présente au moins une défaillance critique. Immobilisation immédiate obligatoire. Contre-visite requise après réparation immédiate.";
            case MAJEUR:
                return "Le véhicule présente au moins une défaillance majeure. Une contre-visite est requise dans un délai de 2 mois après réparation des points signalés.";
            case MINEUR:
                return "Le véhicule présente des défaillances mineures. Réparations conseillées mais aucune contre-visite n'est requise.";
            default:
                return "Le véhicule est conforme à tous les points de contrôle. Aucune anomalie détectée. Prochaine visite technique dans 2 ans.";
        }
    }

    // =================== UTIL ===================
    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        return url == null ? null : new ImageIcon(url).getImage();
    }

    // =================== CLASSES INTERNES ===================
    private static class BackgroundPanel extends JPanel {

        private final Image bg;

        public BackgroundPanel(Image bg) {
            this.bg = bg;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(0, 0, 0, 76),
                        0, getHeight(), new Color(0, 0, 0, 102)
                );
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }

    private static class GlassPanel extends JPanel {

        private final int radius;
        private final Color bgColor;

        public GlassPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    private static class LiquidGlassPanel extends JPanel {

        public LiquidGlassPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(20, 20, 30, 115));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.setColor(new Color(255, 255, 255, 32));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            super.paintComponent(g);
        }
    }

    private static class RoundedBorder implements Border {

        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private static class RoundedButton extends JButton {

        private final Color bgColor;

        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.bgColor = bgColor;
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFont(new Font("Arial", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(),
                        Math.max(bgColor.getAlpha() - 30, 0)));
            } else if (getModel().isRollover()) {
                g2.setColor(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(),
                        Math.min(bgColor.getAlpha() + 30, 255)));
            } else {
                g2.setColor(bgColor);
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            super.paintComponent(g);
        }
    }
}
