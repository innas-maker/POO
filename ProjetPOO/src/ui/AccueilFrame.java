package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import model.*;
import service.*;
import javax.swing.JOptionPane;

public class AccueilFrame extends JFrame {

    private JTextField plaqueField;
    private JComboBox<String> typeBox;
    private JTextField modeleField;
    private JTextField anneeField;

    public AccueilFrame() {
        setTitle("AutoCheck – Visite Technique Automobile");
        setSize(1250, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Image bg = loadImage("/images/desert.jpg");
        BackgroundPanel root = new BackgroundPanel(bg);
        setContentPane(root);

        root.setLayout(new BorderLayout());

        root.add(createNavbar(), BorderLayout.NORTH);
        root.add(createCenter(), BorderLayout.CENTER);
        root.add(createFooter(), BorderLayout.SOUTH);
    }

    // ================= NAVBAR =================
    private JPanel createNavbar() {
        GlassPanel nav = new GlassPanel(25);
        nav.setLayout(new BorderLayout());
        nav.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel(" AUTOCHECK");
        logo.setFont(new Font("Oswald", Font.BOLD, 18));

        

        nav.add(logo, BorderLayout.WEST);

        return nav;
    }

    // ================= CENTER =================
    private JPanel createCenter() {
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        gbc.fill = GridBagConstraints.BOTH;

        JPanel cards = new JPanel(new GridLayout(1, 2, 30, 0));
        cards.setOpaque(false);

        cards.add(createLeftCard());
        cards.add(createRightCard());

        center.add(cards, gbc);
        return center;
    }

    // ================= LEFT CARD =================
    private JPanel createLeftCard() {
        GlassPanel card = new GlassPanel(35);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel step = new JLabel("01");
        step.setFont(new Font("Oswald", Font.PLAIN, 50));

        JLabel title = new JLabel("<html>VISITE TECHNIQUE<br>AUTOMOBILE</html>");
        title.setFont(new Font("Oswald", Font.BOLD, 26));

        JLabel desc = new JLabel(
                "<html>Plateforme de gestion pour inspections techniques certifiées.<br>"
                + "Assurez la conformité et la sécurité en quelques clics.</html>"
        );

        JLabel status = new JLabel("● Système Opérationnel");
        status.setForeground(new Color(0, 150, 0));

        card.add(step);
        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(Box.createVerticalStrut(15));
        card.add(desc);
        card.add(Box.createVerticalGlue());
        card.add(status);

        return card;
    }

    // ================= RIGHT CARD =================
    private JPanel createRightCard() {
        GlassPanel card = new GlassPanel(35);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Nouvelle Entrée");
        title.setFont(new Font("Oswald", Font.BOLD, 20));

        JLabel subtitle = new JLabel("Veuillez saisir les informations du véhicule.");

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));
        card.add(createForm());
        card.add(Box.createVerticalStrut(20));
        card.add(createActions());

        return card;
    }

    // ================= FORM =================
    private JPanel createForm() {
        JPanel form = new JPanel(new GridLayout(4, 2, 15, 15));
        form.setOpaque(false);

        plaqueField = new RoundedTextField();
        typeBox = new JComboBox<>(new String[]{"Véhicule Léger", "Utilitaire", "Poids Lourd", "Moto"});
        modeleField = new RoundedTextField();
        anneeField = new RoundedTextField();

        form.add(new JLabel("Plaque d’immatriculation"));
        form.add(plaqueField);

        form.add(new JLabel("Type de véhicule"));
        form.add(typeBox);

        form.add(new JLabel("Modèle"));
        form.add(modeleField);

        form.add(new JLabel("Année"));
        form.add(anneeField);

        return form;
    }

    // ================= ACTIONS =================
    private JPanel createActions() {
    JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
    actions.setOpaque(false);

    RoundedButton nouvelle = new RoundedButton("Nouvelle Inspection");
    nouvelle.setBackground(new Color(20, 20, 20));
    nouvelle.setForeground(Color.WHITE);
    nouvelle.setPreferredSize(new Dimension(220, 45));

   
    nouvelle.addActionListener(e -> {
        try {
            String plaque = plaqueField.getText();
            String type = (String) typeBox.getSelectedItem();
            String modele = modeleField.getText();
            int annee = Integer.parseInt(anneeField.getText());

            Vehicule vehicule = new Vehicule();
            vehicule.setPlaque(plaque);
            vehicule.setType(type);
            vehicule.setModele(modele);
            vehicule.setAnnee(annee);

            ControleTechnique controle = new ControleTechnique();
            controle.setVehicule(vehicule);

            
            new InspectionFrame().setVisible(true);

           
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Année invalide",
                "Erreur",
                JOptionPane.ERROR_MESSAGE
            );
        }
    });

    RoundedButton quitter = new RoundedButton("Quitter");
    quitter.setBackground(new Color(180, 180, 180));
    quitter.setForeground(Color.BLACK);
    quitter.setPreferredSize(new Dimension(120, 45));
    quitter.addActionListener(e -> System.exit(0));

    actions.add(nouvelle);
    actions.add(quitter);

    return actions;
}


    // ================= FOOTER =================
    private JPanel createFooter() {
        GlassPanel footer = new GlassPanel(25);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));

       

        return footer;
    }

    // ================= UTIL =================
    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        return url == null ? null : new ImageIcon(url).getImage();
    }

    // =================================================
    // ================= CLASSES INTERNES ==============
    // =================================================
    private static class BackgroundPanel extends JPanel {

        private final Image bg;

        public BackgroundPanel(Image bg) {
            this.bg = bg;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private static class GlassPanel extends JPanel {

        private final int radius;

        public GlassPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 140));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(new Color(255, 255, 255, 200));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            super.paintComponent(g);
        }
    }

    private static class RoundedTextField extends JTextField {

        public RoundedTextField() {
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
        }
    }

    private static class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFont(getFont().deriveFont(Font.BOLD));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
        }
    };

}
