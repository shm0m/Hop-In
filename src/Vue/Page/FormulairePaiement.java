package Vue.Page;

import Controleur.ReservationControleur;
import DAO.*;
import Modele.Client;
import Modele.Reduction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

public class FormulairePaiement extends JDialog {
    public FormulairePaiement(JFrame parent, Integer id_utilisateur, int id_attraction, int id_creneau, LocalDate date, String label, String attraction) {
        super(parent, "Paiement", true);
        setSize(480, 480);
        setLocationRelativeTo(parent);

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
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titre = new JLabel("Informations de Paiement");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titre, gbc);

        gbc.gridwidth = 1;

        JTextField numeroCarte = new JTextField();
        JTextField dateExpiration = new JTextField();
        JTextField cvc = new JTextField();
        JTextField nomTitulaire = new JTextField();
        JTextField mailInviteField = new JTextField(); // Champ pour l'email invité

        addField(panel, gbc, "Numéro de carte :", numeroCarte, 1);
        addField(panel, gbc, "Date d'expiration :", dateExpiration, 2);
        addField(panel, gbc, "CVC :", cvc, 3);
        addField(panel, gbc, "Nom du titulaire :", nomTitulaire, 4);

        final Integer finalIdUtilisateur = id_utilisateur;
        boolean isInvited = finalIdUtilisateur == null || finalIdUtilisateur == -1;

        int ligneSuivante = 5;

        if (isInvited) {
            addField(panel, gbc, "Email :", mailInviteField, ligneSuivante);
            ligneSuivante++;
        }

        JButton btnValider = new JButton("Valider et Réserver");
        btnValider.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnValider.setBackground(new Color(76, 215, 179));
        btnValider.setForeground(Color.WHITE);
        btnValider.setFocusPainted(false);
        btnValider.setPreferredSize(new Dimension(200, 45));
        btnValider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnValider.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
        gbc.gridx = 0;
        gbc.gridy = ligneSuivante;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnValider, gbc);

        btnValider.addActionListener(e -> {
            if (numeroCarte.getText().isEmpty() || dateExpiration.getText().isEmpty()
                    || cvc.getText().isEmpty() || nomTitulaire.getText().isEmpty()
                    || (isInvited && mailInviteField.getText().isEmpty())) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isInvited) {
                String mailInvite = mailInviteField.getText();
                if (!isValidEmail(mailInvite)) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un email valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            UtilisateurchercherDAO userDAO = new UtilisateurchercherDAO();
            Client client = null;
            if (!isInvited) client = userDAO.trouverParId(finalIdUtilisateur);

            int age = 30;
            if (client != null && client.getDateNaissance() != null) {
                LocalDate naissance = LocalDate.parse(client.getDateNaissance());
                age = Period.between(naissance, LocalDate.now()).getYears();
            }


            Reduction reduction = null;
            if (!isInvited) {
                ReductionChercherDAO redDAO = new ReductionChercherDAO();
                reduction = redDAO.getReductionApplicable(age, userDAO.nbReservations(client.getId()));
            }

            AttractionDAO attractionDAO = new AttractionDAO();
            float prixBase = attractionDAO.getPrixById(id_attraction);
            float prixFinal = prixBase;
            if (reduction != null && !isInvited) {
                prixFinal = prixBase * (1 - (float) reduction.getprcRed() / 100);
            }

            JOptionPane.showMessageDialog(this,
                    "Prix original : " + prixBase + " €\n" +
                            (reduction != null ? "Réduction appliquée : " + reduction.getNom() + " (-" + reduction.getprcRed() + "%)\n" : "Aucune réduction appliquée.\n") +
                            "Total à payer : " + prixFinal + " €");

            boolean ok;
            if (!isInvited) {
                ok = new ReservationControleur().reserver(finalIdUtilisateur, id_attraction, id_creneau, date);
            } else {
                String mailInvite = mailInviteField.getText();
                ok = new ReservationControleur().reserverInvite(mailInvite, id_attraction, id_creneau, date);
            }

            if (ok) {
                int idReservation = ReservationDAO.getDernierIdReservation();
                int idPaiement = new PaiementDAO().enregistrerPaiement(idReservation, prixFinal);
                if (reduction != null && idPaiement != -1) {
                    new ReductionAppliqueeDAO().lierReductionPaiement(reduction.getid(), idPaiement);
                }
                JOptionPane.showMessageDialog(this, "Réservation confirmée pour " + label + "\nAttraction : " + attraction,
                        "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parent.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        setContentPane(panel);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setPreferredSize(new Dimension(220, 30));
        field.setBackground(new Color(255, 245, 250));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(field, gbc);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
