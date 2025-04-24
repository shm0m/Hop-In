package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class PageConnexionSwing extends JFrame {

    public PageConnexionSwing(JFrame previousFrame) {
        setTitle("Connexion - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel wrapper = new JPanel(new GridBagLayout()) {
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
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Connexion");
        title.setFont(new Font("Segoe UI", Font.BOLD, 38));
        title.setForeground(new Color(62, 15, 76));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        wrapper.add(title, gbc);
        gbc.gridwidth = 1;

        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();
        styleField(emailField);
        styleField(mdpField);

        addField(wrapper, gbc, "Email", emailField, 1);
        addField(wrapper, gbc, "Mot de passe", mdpField, 2);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        wrapper.add(message, gbc);

        JButton btnConnexion = createButton("Se connecter", new Color(76, 215, 179));
        gbc.gridy = 4;
        wrapper.add(btnConnexion, gbc);

        JButton btnRetour = createButton("Retour", new Color(249, 78, 139));
        gbc.gridy = 5;
        wrapper.add(btnRetour, gbc);

        btnConnexion.addActionListener(e -> {
            String email = emailField.getText();
            String mdp = new String(mdpField.getPassword());
            Utilisateur u = new UtilisateurControleur().trouverParEmailEtMotDePasse(email, mdp);
            if (u != null) {
                dispose();
                System.out.println(u.getRole());
                if (u.getRole().equals("Admin")) {
                    new PageGestionAdmin(this);
                } else {
                    new PageProfileSwing(u);
                    setVisible(false);
                }
            } else {
                message.setForeground(Color.RED);
                message.setText("Email ou mot de passe incorrect.");
            }
        });

        btnRetour.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        add(wrapper);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int y) {
        JLabel label = new JLabel(labelText + " :");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 35));
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 45));
        button.setBackground(bgColor);
        button.setForeground(new Color(62, 15, 76));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
        return button;
    }

    private void styleField(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
    }
}
