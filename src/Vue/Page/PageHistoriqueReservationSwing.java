package Vue.Page;

import Modele.Reservation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PageHistoriqueReservationSwing extends JFrame {

    public PageHistoriqueReservationSwing(ArrayList<Reservation> reservations, JFrame previousFrame) {
        setTitle("Historique des réservations");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour revenir à la fenêtre précédente

        String[] colonnes = {"Client ID", "Attraction ID", "Date de Réservation", "Nb Personnes", "ID Creneau", "Statut"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);

        for (Reservation res : reservations) {

            Object[] rowData = {
                    res.getIdUtilisateur(),    // client ID
                    res.getIdAttraction(),      // ID de l'attraction
                    res.getDateReservation(),   // Date de réservation (peut être un java.sql.Date ou String)
                    res.getNbPersonnes(),       // Nombre de personnes
                    res.getIdCreneau(),         // ID du créneau (si vous l'avez défini)
                    res.getStatut()             // Statut de la réservation
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bouton Retour
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnRetour.setPreferredSize(new Dimension(120, 40));
        btnRetour.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
