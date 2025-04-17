package Vue;

import javax.swing.*;
import java.awt.*;

public class HopInGUI extends JFrame {

    public HopInGUI() {
        setTitle("Hop'In - Accueil");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 248, 230), getWidth(), getHeight(), new Color(255, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Bienvenue sur Hop'In");
        label.setFont(new Font("Segoe UI", Font.BOLD, 40));
        label.setForeground(new Color(62, 15, 76));
        gbc.gridy = 0;
        panel.add(label, gbc);

        JButton btnConnexion = createButton("Se connecter", new Color(76, 215, 179));
        gbc.gridy = 1;
        panel.add(btnConnexion, gbc);

        JButton btnInscription = createButton("S'enregistrer", new Color(249, 78, 139));
        gbc.gridy = 2;
        panel.add(btnInscription, gbc);

        JButton btnInvite = createButton("Se connecter invité", new Color(255, 236, 110));
        gbc.gridy = 3;
        panel.add(btnInvite, gbc);

        btnConnexion.addActionListener(e -> {
            new Vue.Page.PageConnexionSwing(this);
            setVisible(false);
        });

        btnInscription.addActionListener(e -> {
            new Vue.Page.PageEnregistrementSwing(this);
            setVisible(false);
        });

        btnInvite.addActionListener(e -> {
            new Vue.Page.PageInviteSwing(this);
            setVisible(false);
        });

        add(panel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 50));
        button.setBackground(bgColor);
        button.setForeground(new Color(62, 15, 76));
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HopInGUI::new);
    }
}
