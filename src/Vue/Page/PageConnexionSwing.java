package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class PageConnexionSwing extends JFrame {

    public PageConnexionSwing(JFrame previousFrame) {
        setTitle("Connexion - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Connexion à Hop'In", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(new Color(255, 105, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();

        styleField(emailField);
        styleField(mdpField);

        addField(panel, gbc, "Email", emailField, 1);
        addField(panel, gbc, "Mot de passe", mdpField, 2);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(message, gbc);

        JButton btnConnexion = new JButton("Se connecter");
        styliseButton(btnConnexion, new Color(126, 214, 223));
        gbc.gridy = 4;
        panel.add(btnConnexion, gbc);

        JButton btnRetour = new JButton("Retour");
        styliseButton(btnRetour, Color.LIGHT_GRAY);
        gbc.gridy = 5;
        panel.add(btnRetour, gbc);

        btnConnexion.addActionListener(e -> {
            String email = emailField.getText();
            String mdp = new String(mdpField.getPassword());
            Utilisateur u = new UtilisateurControleur().trouverParEmailEtMotDePasse(email, mdp);

            if (u != null) {
                message.setForeground(new Color(0, 128, 0));
                message.setText("Bienvenue " + u.getPrenom() + " !");
            } else {
                message.setForeground(Color.RED);
                message.setText("Email ou mot de passe incorrect.");
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
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 30));
        panel.add(field, gbc);
    }

    private void styliseButton(JButton button, Color bg) {
        button.setPreferredSize(new Dimension(220, 40));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleField(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
