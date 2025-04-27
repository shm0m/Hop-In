package Vue.Page.ModAdmin;

import DAO_Set.UtilisateurDAO;
import DAO_Get.UtilisateurchercherDAO;
import Modele.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ModCli extends JFrame {
    private JTextField mod_nom, mod_prenom, mod_email, mod_mot_de_passe, mod_date_naissance;
    private JComboBox<Client> men_choixCli;
    private JLabel indication;
    private UtilisateurDAO modifieur;
    private UtilisateurchercherDAO getter;
    private JFrame previousFrame;

    public ModCli(JFrame previousFrame) {
        super("Modification clients");
        this.previousFrame = previousFrame;
        this.modifieur = new UtilisateurDAO();
        this.getter = new UtilisateurchercherDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 248, 230), getWidth(), getHeight(), new Color(255, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBorder(new EmptyBorder(30, 50, 30, 50));
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ArrayList<Client> clients = getter.getClis();
        men_choixCli = new JComboBox<>(clients.toArray(new Client[0]));
        addField(panel, gbc, "Choisir un client :", men_choixCli, 0);

        mod_nom = new JTextField();
        addField(panel, gbc, "Nom :", mod_nom, 1);

        mod_prenom = new JTextField();
        addField(panel, gbc, "Prénom :", mod_prenom, 2);

        mod_email = new JTextField();
        addField(panel, gbc, "Email :", mod_email, 3);

        mod_mot_de_passe = new JTextField();
        addField(panel, gbc, "Mot de passe :", mod_mot_de_passe, 4);

        mod_date_naissance = new JTextField();
        addField(panel, gbc, "Date de naissance :", mod_date_naissance, 5);

        indication = new JLabel(" ");
        indication.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(indication, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);

        JButton actualiser = createButton("Actualiser", new Color(62, 15, 76));
        JButton enregistrer = createButton("Enregistrer", new Color(76, 215, 179));
        JButton quitter = createButton("Quitter", new Color(249, 78, 139));

        btnPanel.add(actualiser);
        btnPanel.add(enregistrer);
        btnPanel.add(quitter);

        gbc.gridy = 7;
        panel.add(btnPanel, gbc);

        actualiser.addActionListener(e -> {
            Client c = getCli(clients, men_choixCli.getSelectedItem().toString());
            mod_nom.setText(c.getNom());
            mod_prenom.setText(c.getPrenom());
            mod_mot_de_passe.setText(c.getMotDePasse());
            mod_email.setText(c.getEmail());
            mod_date_naissance.setText(c.getDateNaissance());
        });

        enregistrer.addActionListener(e -> {
            int id = getCli(clients, men_choixCli.getSelectedItem().toString()).getId();
            int resultat = modifieur.updateUtilisateur(new Client(
                    id,
                    mod_nom.getText(),
                    mod_prenom.getText(),
                    mod_email.getText(),
                    mod_mot_de_passe.getText(),
                    mod_date_naissance.getText()
            ));
            if (resultat == 1) {
                previousFrame.setVisible(true);
                setVisible(false);
            } else {
                indication.setText("Erreur lors de la modification");
            }
        });

        quitter.addActionListener(e -> {
            previousFrame.setVisible(true);
            setVisible(false);
        });

        setContentPane(panel);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(lbl, gbc);

        field.setPreferredSize(new Dimension(280, 30));
        if (field instanceof JTextField)
            ((JTextField) field).setBorder(BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true));
        gbc.gridx = 1;
        gbc.gridy = y;
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private Client getCli(ArrayList<Client> clients, String nom) {
        for (Client client : clients) {
            if (client.toString().equals(nom)) {
                return client;
            }
        }
        return new Client();
    }
}