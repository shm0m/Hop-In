package Vue.Page;

import Controleur.ReservationControleur;
import DAO.*;
import Modele.Client;
import Modele.Reduction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class FormulairePaiement extends JDialog {
    public FormulairePaiement(JFrame parent, int id_utilisateur, int id_attraction, int id_creneau, LocalDate date, String label, String attraction) {
        super(parent, "Paiement", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField numeroCarte = new JTextField();
        JTextField dateExpiration = new JTextField();
        JTextField cvc = new JTextField();
        JTextField nomTitulaire = new JTextField();

        formPanel.add(new JLabel("Numéro de carte :"));
        formPanel.add(numeroCarte);
        formPanel.add(new JLabel("Date d’expiration :"));
        formPanel.add(dateExpiration);
        formPanel.add(new JLabel("CVC :"));
        formPanel.add(cvc);
        formPanel.add(new JLabel("Nom du titulaire :"));
        formPanel.add(nomTitulaire);

        JButton btnValider = new JButton("Valider et réserver");
        btnValider.setBackground(new Color(76, 175, 80));
        btnValider.setForeground(Color.WHITE);

        btnValider.addActionListener(e -> {
            if (numeroCarte.getText().isEmpty() || dateExpiration.getText().isEmpty()
                    || cvc.getText().isEmpty() || nomTitulaire.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 1. Récupération de l'utilisateur
            UtilisateurchercherDAO userDAO = new UtilisateurchercherDAO();
            System.out.println("ID utilisateur transmis : " + id_utilisateur);
            Client client = userDAO.trouverParId(id_utilisateur);
            if (client == null) {
                JOptionPane.showMessageDialog(this, "Utilisateur introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Calcul de l'âge
            LocalDate naissance = LocalDate.parse(client.getDateNaissance());
            int age = Period.between(naissance, LocalDate.now()).getYears();
            System.out.println("Âge de l'utilisateur : " + age);

            // 3. Récupération réduction
            ReductionChercherDAO redDAO = new ReductionChercherDAO();
            ArrayList<Reduction> allReductions = redDAO.getReducs();
            System.out.println("Réductions disponibles en base :");
            for (Reduction r : allReductions) {
                System.out.println("- " + r.getNom() + " (âge min : " + r.getageMin() + ", âge max : " + r.getageMax() + ")");
            }

            Reduction reduction = redDAO.getReductionApplicable(age);
            if (reduction != null) {
                System.out.println("Réduction applicable trouvée : " + reduction.getNom());
            } else {
                System.out.println("Aucune réduction applicable.");
            }

            // 4. Application réduction
            AttractionDAO attractionDAO = new AttractionDAO();
            float prixBase = attractionDAO.getPrixById(id_attraction);
            float prixFinal = prixBase;
            String messageReduc = "Aucune réduction appliquée.";

            if (reduction != null) {
                prixFinal = prixBase * (1 - (float) reduction.getprcRed() / 100);
                messageReduc = "Réduction appliquée : " + reduction.getNom() + " (-" + reduction.getprcRed() + "%)";
            }

            System.out.println("Prix original : " + prixBase + " €, Prix final après réduction : " + prixFinal);

            // 5. Affichage du montant final
            JOptionPane.showMessageDialog(this,
                    "Prix original : " + prixBase + " €\n" +
                            messageReduc + "\nTotal à payer : " + prixFinal + " €");

            // 6. Enregistrement réservation
            boolean ok = new ReservationControleur().reserver(id_utilisateur, id_attraction, id_creneau, date);

            if (ok) {
                // 7. Paiement
                int idReservation = ReservationDAO.getDernierIdReservation();
                int idPaiement = new PaiementDAO().enregistrerPaiement(idReservation, prixFinal);

                // 8. Lier réduction si applicable
                if (reduction != null && idPaiement != -1) {
                    new ReductionAppliqueeDAO().lierReductionPaiement(reduction.getid(), idPaiement);
                }

                // 9. Affichage succès
                JOptionPane.showMessageDialog(this, "Réservation confirmée pour " + label + "\nAttraction : " + attraction,
                        "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parent.dispose();
                new VueHoraireAttraction(attraction, id_attraction, null, date,
                        attractionDAO.getCapaciteAttraction(id_attraction));
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        add(formPanel, BorderLayout.CENTER);
        add(btnValider, BorderLayout.SOUTH);
        setVisible(true);
    }
}
