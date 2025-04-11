package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Admin;
import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class PageConnexionSwing extends JFrame {

    public PageConnexionSwing(JFrame previousFrame) {
        setTitle("Connexion - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Connexion");
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        title.setForeground(new Color(126, 214, 223));
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

        JButton btnConnexion = new JButton("Se connecter");
        styliseButton(btnConnexion, new Color(126, 214, 223));
        gbc.gridy = 4;
        wrapper.add(btnConnexion, gbc);

        JButton btnRetour = new JButton("Retour");
        styliseButton(btnRetour, Color.LIGHT_GRAY);
        gbc.gridy = 5;
        wrapper.add(btnRetour, gbc);

        btnConnexion.addActionListener(e -> {
            String email = emailField.getText();
            String mdp = new String(mdpField.getPassword());
            Utilisateur u = new UtilisateurControleur().trouverParEmailEtMotDePasse(email, mdp);
            if (u != null) {
                dispose();
                if (u.getRole().compareTo("Admin")==0) {
                    new PageGestionAdmin();
                }
                else {
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
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 30));
        panel.add(field, gbc);
    }

    private void styliseButton(JButton button, Color bg) {
        button.setPreferredSize(new Dimension(250, 45));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleField(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
