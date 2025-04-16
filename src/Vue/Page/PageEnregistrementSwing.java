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

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Créer un compte Hop'In",SwingConstants.CENTER );
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

        // Boutons radio Type de membre
        JLabel membreLabel = new JLabel("Type de membre :");
        membreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(membreLabel, gbc);

        JPanel membrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup groupeMembre = new ButtonGroup();
        String[] membres = {"AUCUN", "REGULIER", "SENIOR", "ENFANT"};
        for (String m : membres) {
            JRadioButton rb = new JRadioButton(m);
            rb.setActionCommand(m);
            rb.setBackground(Color.WHITE);
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            groupeMembre.add(rb);
            membrePanel.add(rb);
        }
        gbc.gridx = 1;
        panel.add(membrePanel, gbc);

        // Boutons radio Rôle
        JLabel roleLabel = new JLabel("Rôle :");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(roleLabel, gbc);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup groupeRole = new ButtonGroup();
        String[] roles = {"CLIENT", "ADMIN"};
        java.util.List<JRadioButton> boutonRoles;

        for (String r : roles) {
            JRadioButton rb = new JRadioButton(r);
            rb.setActionCommand(r);
            rb.setBackground(Color.WHITE);
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            groupeRole.add(rb);
            rolePanel.add(rb);
        }
        gbc.gridx = 1;
        panel.add(rolePanel, gbc);

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
            String typeMembre = (groupeMembre.getSelection() != null) ? groupeMembre.getSelection().getActionCommand() : null;
            String role = (groupeRole.getSelection() != null) ? groupeRole.getSelection().getActionCommand() : null;

            if (!dateField.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                message.setForeground(Color.RED);
                message.setText("Le format de la date doit être xxxx-xx-xx, avec seulement des chiffres.");
                return;
            }

            if (typeMembre == null || role == null) {
                message.setForeground(Color.RED);
                message.setText("Veuillez sélectionner un type de membre et un rôle.");
                return;
            }
            if (role=="ADMIN"){
                System.out.println("Avant");
                VerifAdmin verif=new VerifAdmin(null);
                System.out.println("Après");
                /*do{ }while(verif.getValide()==0);*/
                if(verif.getValide()==1){
                    Utilisateur u=new Admin(
                        nomField.getText(),
                        prenomField.getText(),
                        emailField.getText(),
                        new String(mdpField.getPassword())
                    );

                    UtilisateurControleur controleur = new UtilisateurControleur();
                    controleur.ajouterUtilisateur(u);
                    message.setForeground(new Color(0, 128, 0));
                    message.setText("Utilisateur enregistré avec succès !");
                }
                else{
                    previousFrame.setVisible(true);
                    dispose();
                }
            }else {
                Utilisateur u = new Client(
                        0,
                        nomField.getText(),
                        prenomField.getText(),
                        emailField.getText(),
                        new String(mdpField.getPassword()),
                        dateField.getText()
                );
                UtilisateurControleur controleur = new UtilisateurControleur();
                controleur.ajouterUtilisateur(u);
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
