package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class PageEnregistrementSwing extends JFrame {

    public PageEnregistrementSwing(JFrame previousFrame) {
        setTitle("Inscription - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Créer un compte Hop'In", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(new Color(255, 105, 180));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();
        JTextField dateField = new JTextField();

        styleField(nomField);
        styleField(prenomField);
        styleField(emailField);
        styleField(mdpField);
        styleField(dateField);

        addField(panel, gbc, "Nom", nomField, 1);
        addField(panel, gbc, "Prénom", prenomField, 2);
        addField(panel, gbc, "Email", emailField, 3);
        addField(panel, gbc, "Mot de passe", mdpField, 4);
        addField(panel, gbc, "Date de naissance (AAAA-MM-JJ)", dateField, 5);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(message, gbc);

        JButton btn = new JButton("S'enregistrer");
        styliseButton(btn, new Color(255, 105, 180));
        gbc.gridy = 8;
        panel.add(btn, gbc);

        JButton btnRetour = new JButton("⬅ Retour");
        styliseButton(btnRetour, Color.LIGHT_GRAY);
        gbc.gridy = 10;
        panel.add(btnRetour, gbc);

        btn.addActionListener(e -> {
            UtilisateurControleur controleur = new UtilisateurControleur();
            Utilisateur u = new Utilisateur(
                    0,
                    nomField.getText(),
                    prenomField.getText(),
                    emailField.getText(),
                    new String(mdpField.getPassword()),
                    dateField.getText(),
                    "CLIENT", // à adapter selon ton implémentation
                    "AUCUN"
            );
            controleur.ajouterUtilisateur(u);
            message.setForeground(new Color(0, 128, 0));
            message.setText("✅ Utilisateur enregistré avec succès !");
        });

        btnRetour.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        add(scroll);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        JLabel jLabel = new JLabel(label + " :");
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(jLabel, gbc);

        field.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void styleField(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styliseButton(JButton button, Color bg) {
        button.setPreferredSize(new Dimension(220, 40));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
