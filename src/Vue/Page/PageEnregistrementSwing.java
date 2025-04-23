package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;
import Modele.Client;
import Modele.Admin;

import javax.swing.*;
import java.awt.*;

public class PageEnregistrementSwing extends JFrame {

    public PageEnregistrementSwing(JFrame previousFrame) {
        setTitle("Inscription - Hop'In");
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
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Créer un compte Hop'In", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(62, 15, 76));
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

        /*JLabel membreLabel = new JLabel("Type de membre :");
        membreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        membreLabel.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(membreLabel, gbc);

        JPanel membrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        membrePanel.setOpaque(false);
        ButtonGroup groupeMembre = new ButtonGroup();
        String[] membres = {"AUCUN", "REGULIER", "SENIOR", "ENFANT"};
        for (String m : membres) {
            JRadioButton rb = new JRadioButton(m);
            rb.setActionCommand(m);
            rb.setBackground(new Color(255, 248, 230));
            rb.setForeground(new Color(62, 15, 76));
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            rb.setFocusPainted(false);
            rb.setOpaque(true);
            groupeMembre.add(rb);
            membrePanel.add(rb);
        }
        gbc.gridx = 1;
        panel.add(membrePanel, gbc);*/

        JLabel roleLabel = new JLabel("Rôle :");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleLabel.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(roleLabel, gbc);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePanel.setOpaque(false);
        ButtonGroup groupeRole = new ButtonGroup();
        String[] roles = {"CLIENT", "ADMIN"};
        for (String r : roles) {
            JRadioButton rb = new JRadioButton(r);
            rb.setActionCommand(r);
            rb.setBackground(new Color(255, 248, 230));
            rb.setForeground(new Color(62, 15, 76));
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            rb.setFocusPainted(false);
            rb.setOpaque(true);
            groupeRole.add(rb);
            rolePanel.add(rb);
        }
        gbc.gridx = 1;
        panel.add(rolePanel, gbc);

        JButton btn = createButton("S'enregistrer", new Color(76, 215, 179));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(btn, gbc);

        JLabel message = new JLabel(" ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        message.setForeground(Color.RED);
        gbc.gridy = 9;
        panel.add(message, gbc);

        JButton btnRetour = createButton("Retour", new Color(249, 78, 139));
        gbc.gridy = 10;
        panel.add(btnRetour, gbc);

        btn.addActionListener(e -> {
            /*String typeMembre = (groupeMembre.getSelection() != null) ? groupeMembre.getSelection().getActionCommand() : null;*/
            String role = (groupeRole.getSelection() != null) ? groupeRole.getSelection().getActionCommand() : null;

            if (!dateField.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                message.setForeground(Color.RED);
                message.setText("Le format de la date doit être xxxx-xx-xx, avec seulement des chiffres.");
                return;
            }

            /*if (typeMembre == null || role == null) {
                message.setForeground(Color.RED);
                message.setText("Veuillez sélectionner un type de membre et un rôle.");
                return;
            }*/

            if (role.equals("ADMIN")) {
                VerifAdmin verif = new VerifAdmin(null);
                if (verif.getValide() == 1) {
                    Utilisateur u = new Admin(
                            nomField.getText(),
                            prenomField.getText(),
                            emailField.getText(),
                            new String(mdpField.getPassword())
                    );
                    new UtilisateurControleur().ajouterUtilisateur(u);
                    message.setForeground(new Color(0, 128, 0));
                    message.setText("Utilisateur enregistré avec succès !");
                } else {
                    previousFrame.setVisible(true);
                    dispose();
                }
            } else {
                Utilisateur u = new Client(
                        0,
                        nomField.getText(),
                        prenomField.getText(),
                        emailField.getText(),
                        new String(mdpField.getPassword()),
                        dateField.getText()
                );
                new UtilisateurControleur().ajouterUtilisateur(u);
                message.setForeground(new Color(0, 128, 0));
                message.setText("Utilisateur enregistré avec succès !");
            }
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
        jLabel.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(jLabel, gbc);

        field.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void styleField(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
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
}
