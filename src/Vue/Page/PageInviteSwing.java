package Vue.Page;

import Modele.Client;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class PageInviteSwing extends JFrame {

    public PageInviteSwing(JFrame previousFrame) {
        setTitle("Connexion en tant qu'invité");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(255, 248, 230),
                        getWidth(), getHeight(), new Color(255, 240, 245)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        ImageIcon logoIcon = new ImageIcon("assets/hop_in.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        JLabel title = new JLabel("Connexion en tant qu'invité");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(62, 15, 76));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JTextField emailField = new JTextField();
        styleField(emailField);
        addField(panel, gbc, "Email", emailField, 2);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(message, gbc);

        JButton btnConnect = createButton("Continuer", new Color(76, 215, 179));
        gbc.gridy = 4;
        panel.add(btnConnect, gbc);

        JButton btnRetour = createButton("Retour", new Color(249, 78, 139));
        gbc.gridy = 5;
        panel.add(btnRetour, gbc);

        btnConnect.addActionListener(e -> {
            String email = emailField.getText();
            if (email != null && !email.trim().isEmpty()) {
                Utilisateur invitedUser = new Client(0, "Invité", null, email, null, null);
                new PageReservationSwing(invitedUser);
                setVisible(false);
            } else {
                message.setText("Veuillez saisir une adresse email valide.");
            }
        });

        btnRetour.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int y) {
        JLabel label = new JLabel(labelText + " :");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(260, 34));
        panel.add(field, gbc);
    }

    private void styleField(JComponent field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(new Color(255, 240, 245));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(260, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(62, 15, 76));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }
}
