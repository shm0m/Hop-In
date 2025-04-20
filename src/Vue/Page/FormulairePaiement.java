package Vue.Page;

import Controleur.ReservationControleur;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

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
            if (numeroCarte.getText().isEmpty() || dateExpiration.getText().isEmpty() || cvc.getText().isEmpty() || nomTitulaire.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }


            boolean ok = new ReservationControleur().reserver(id_utilisateur, id_attraction, id_creneau, date);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Réservation confirmée pour " + label + "\nAttraction : " + attraction, "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parent.dispose();
                new VueHoraireAttraction(attraction, id_attraction, null, date, new DAO.AttractionDAO().getCapaciteAttraction(id_attraction));
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(btnValider, BorderLayout.SOUTH);
        setVisible(true);
    }
}
