package Vue.Page;

import Controleur.ReservationControleur;
import DAO.AttractionDAO;
import DAO.ReservationDAO;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Map;

public class PageCreneauxParAttraction extends JFrame {

    private JComboBox<String> attractionCombo;
    private JPanel creneauxPanel;
    private LocalDate date;
    private Utilisateur utilisateur;

    public PageCreneauxParAttraction(LocalDate date, Utilisateur utilisateur) {
        this.date = date;
        this.utilisateur = utilisateur;

        setTitle("Réserver une attraction - Hop'In");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Réservation pour le " + date.toString(), SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Choisissez une attraction :"));
        attractionCombo = new JComboBox<>(new String[]{
                "Labyrinthe du Maïs",
                "Chasse à l'œuf",
                "Laser-Tag",
                "Spectacle Pirate",
                "Sculpture Citrouille"
        });
        topPanel.add(attractionCombo);
        JButton btnAfficher = new JButton("Afficher les créneaux");
        topPanel.add(btnAfficher);
        add(topPanel, BorderLayout.NORTH);

        creneauxPanel = new JPanel();
        creneauxPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scroll = new JScrollPane(creneauxPanel);
        scroll.setBorder(BorderFactory.createTitledBorder("Créneaux disponibles"));
        add(scroll, BorderLayout.CENTER);

        btnAfficher.addActionListener(this::afficherCreneaux);
        setVisible(true);
    }

    private int getAttractionId(String attractionName) {
        return switch (attractionName) {
            case "Labyrinthe du Maïs" -> 1;
            case "Chasse à l'œuf" -> 2;
            case "Laser-Tag" -> 3;
            case "Spectacle Pirate" -> 4;
            case "Sculpture Citrouille" -> 5;
            default -> 1;
        };
    }

    private void afficherCreneaux(ActionEvent e) {
        creneauxPanel.removeAll();
        String attractionName = (String) attractionCombo.getSelectedItem();
        int idAttraction = getAttractionId(attractionName);

        AttractionDAO attractionDAO = new AttractionDAO();
        int capacite_max = attractionDAO.getCapaciteAttraction(idAttraction);
        if (capacite_max <= 0) capacite_max = 25;

        ReservationDAO reservationDAO = new ReservationDAO();
        Map<Integer, Integer> reservationsMap = reservationDAO.getNbPersonnesParCreneau(idAttraction, date);

        for (int heure = 10; heure < 19; heure++) {
            int idCreneau = heure - 9;
            String creneauLabel = heure + "h - " + (heure + 1) + "h";
            int dejaInscrits = reservationsMap.getOrDefault(idCreneau, 0);

            JButton bouton = new JButton(creneauLabel + " (" + dejaInscrits + "/" + capacite_max + ")");
            bouton.setBackground(dejaInscrits >= capacite_max ? Color.RED : new Color(173, 216, 230));
            bouton.setEnabled(dejaInscrits < capacite_max);

            int finalCapacite_max = capacite_max;
            bouton.addActionListener(ev -> {
                String input = JOptionPane.showInputDialog(this,
                        "Combien de places voulez-vous réserver pour : " + creneauLabel,
                        "1");
                if (input == null) return;

                int nbPlaces;
                try {
                    nbPlaces = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Entrée invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (dejaInscrits + nbPlaces > finalCapacite_max) {
                    JOptionPane.showMessageDialog(this,
                            "Pas assez de places disponibles pour ce créneau !",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ReservationControleur controleur = new ReservationControleur();
                int idUtilisateur = utilisateur != null ? utilisateur.getId() : 0;

                boolean ok = true;
                for (int i = 0; i < nbPlaces; i++) {
                    ok = ok && controleur.reserver(idUtilisateur, idAttraction, idCreneau);
                }

                if (ok) {
                    JOptionPane.showMessageDialog(this,
                            "Réservation confirmée pour " + creneauLabel + " (" + nbPlaces + " places)",
                            "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erreur lors de la réservation.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                afficherCreneaux(null);
            });
            creneauxPanel.add(bouton);
        }
        creneauxPanel.revalidate();
        creneauxPanel.repaint();
    }
}
