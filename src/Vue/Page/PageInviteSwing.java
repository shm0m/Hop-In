package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Admin;
import Modele.Utilisateur;
import Modele.Client;


import javax.swing.*;
import java.awt.*;

public class PageInviteSwing extends JFrame {

    public PageInviteSwing(JFrame previousFrame) {
        setTitle("Connexion en tant qu'invité");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Connexion en tant qu'invité");
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        title.setForeground(new Color(126, 214, 223));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JTextField emailField = new JTextField();
        addField(panel, gbc, "Email", emailField, 1);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(message, gbc);

        JButton btnConnect = new JButton(" continuer ");
        btnConnect.setPreferredSize(new Dimension(250, 45));
        btnConnect.setBackground(new Color(126, 214, 223));
        btnConnect.setForeground(Color.WHITE);
        btnConnect.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConnect.setFocusPainted(false);
        btnConnect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 3;
        panel.add(btnConnect, gbc);

        JButton btnRetour = new JButton("Retour");
        btnRetour.setPreferredSize(new Dimension(250, 45));
        btnRetour.setBackground(Color.LIGHT_GRAY);
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRetour.setFocusPainted(false);
        btnRetour.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
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
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 30));
        panel.add(field, gbc);
    }

}
