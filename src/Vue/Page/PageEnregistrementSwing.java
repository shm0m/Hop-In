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

        addRadioGroup(panel, gbc, "Type de membre", new String[]{"AUCUN", "REGULIER", "SENIOR", "ENFANT"}, 6);
        ButtonGroup typeGroup = currentRadioGroup;

        // Radio buttons rôle
        addRadioGroup(panel, gbc, "Rôle", new String[]{"CLIENT", "ADMIN"}, 7);
        ButtonGroup roleGroup = currentRadioGroup;

        JButton btnEnregistrer = new JButton("S'enregistrer");
        styliseButton(btnEnregistrer, new Color(247, 143, 179));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(btnEnregistrer, gbc);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 9;
        panel.add(message, gbc);

        JButton btnRetour = new JButton("Retour");
        styliseButton(btnRetour, Color.LIGHT_GRAY);
        gbc.gridy = 10;
        panel.add(btnRetour, gbc);

        btnEnregistrer.addActionListener(e -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String mdp = new String(mdpField.getPassword());
            String date = dateField.getText();
            String typeMembre = getSelectedButtonText(typeGroup);
            String role = getSelectedButtonText(roleGroup);

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || date.isEmpty() || typeMembre == null || role == null) {
                message.setForeground(Color.RED);
                message.setText("Veuillez remplir tous les champs.");
                return;
            }

            UtilisateurControleur controleur = new UtilisateurControleur();
            if (controleur.trouverParEmail(email) != null) {
                message.setForeground(Color.RED);
                message.setText("Cet email est déjà utilisé.");
                return;
            }

            Utilisateur u = new Utilisateur(0, nom, prenom, email, mdp, date, typeMembre, role);
            controleur.ajouterUtilisateur(u);
            message.setForeground(new Color(0, 128, 0));
            message.setText("Compte créé avec succès !");
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

        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 30));
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

    private ButtonGroup currentRadioGroup;
    private void addRadioGroup(JPanel panel, GridBagConstraints gbc, String label, String[] options, int y) {
        JLabel jLabel = new JLabel(label + " :");
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(jLabel, gbc);

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPanel.setBackground(Color.WHITE);
        ButtonGroup group = new ButtonGroup();

        for (String option : options) {
            JRadioButton btn = new JRadioButton(option);
            btn.setBackground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            group.add(btn);
            optionPanel.add(btn);
        }

        currentRadioGroup = group;
        gbc.gridx = 1;
        panel.add(optionPanel, gbc);
    }

    private String getSelectedButtonText(ButtonGroup group) {
        for (AbstractButton button : java.util.Collections.list(group.getElements())) {
            if (button.isSelected()) return button.getText();
        }
        return null;
    }
}
