package Vue.Page;

import DAO.AttractionDAO;
import DAO.ModifAdminDAO;
import Modele.Utilisateur;
import Controleur.ReservationControleur;
import DAO.ReservationDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Map;

public class VueHoraireAttraction extends JFrame {
    private final int capaciteMax;
    private final String attraction;
    private final LocalDate date;
    private final int id_utilisateur;
    private final int id_attraction;

    public VueHoraireAttraction(String attraction, int id_attraction, Utilisateur utilisateur, LocalDate date, int capaciteMax) {
        this.capaciteMax = capaciteMax;
        this.attraction = attraction;
        this.date = date;
        this.id_attraction = id_attraction;
        this.id_utilisateur = utilisateur != null ? utilisateur.getId() : 0;

        String descriptionFromDB = new ModifAdminDAO().getDescriptionById(id_attraction);
        if (descriptionFromDB == null || descriptionFromDB.isBlank()) {
            descriptionFromDB = "Pas de description disponible pour cette attraction.";
        }


        setTitle("Réserver une attraction - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel(" Réservation - " + date + " | " + attraction, SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setOpaque(true);
        titre.setBackground(new Color(240, 240, 240));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titre, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel planningPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        planningPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 30));
        planningPanel.setBackground(new Color(250, 250, 250));

        ReservationDAO reservationDAO = new ReservationDAO();
        Map<Integer, Time> creneauxDispo = reservationDAO.getHeuresCreneauxDepuisBase();
        Map<Integer, Integer> reservationsMap = reservationDAO.getNbPersonnesParCreneau(id_attraction, date);

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.setOpaque(false);  // Pour conserver le fond transparent

        JButton btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnRetour.setPreferredSize(new Dimension(120, 40));

        btnRetour.addActionListener(e -> {
            setVisible(false);
        });

        JButton btnPaiement = new JButton("Passer au paiement");
        btnPaiement.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnPaiement.setPreferredSize(new Dimension(180, 40));

        btnPaiement.addActionListener(e -> {
            new Pagepayement(this);
            setVisible(false);
        });

        bottomRightPanel.add(btnRetour);
        bottomRightPanel.add(btnPaiement);

        add(bottomRightPanel, BorderLayout.SOUTH);

        for (Map.Entry<Integer, Time> entry : creneauxDispo.entrySet()) {
            int idCreneau = entry.getKey();
            Time heureDebut = entry.getValue();
            int heureFin = heureDebut.toLocalTime().plusHours(1).getHour();
            String label = heureDebut.toString().substring(0, 5) + " - " + heureFin + "h";

            int inscrits = reservationsMap.getOrDefault(idCreneau, 0); // 💥 ne plantera plus


            JPanel ligne = new JPanel(new BorderLayout());
            ligne.setPreferredSize(new Dimension(700, 50));
            ligne.setBackground(Color.WHITE);
            ligne.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

            JLabel heure = new JLabel(" " + label, SwingConstants.LEFT);
            heure.setFont(new Font("Segoe UI", Font.BOLD, 14));
            heure.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JLabel statut = new JLabel(inscrits + "/" + capaciteMax + " inscrits", SwingConstants.RIGHT);
            statut.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            statut.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            statut.setForeground(inscrits >= capaciteMax ? Color.RED : new Color(50, 150, 50));

            ligne.add(heure, BorderLayout.WEST);
            ligne.add(statut, BorderLayout.EAST);
            ligne.setCursor(new Cursor(Cursor.HAND_CURSOR));

            ligne.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (inscrits < capaciteMax) {
                        ReservationControleur controleur = new ReservationControleur();
                        boolean ok = controleur.reserver(id_utilisateur, id_attraction, idCreneau);
                        if (ok) {
                            JOptionPane.showMessageDialog(null,
                                    "Réservation confirmée pour " + label + "\nAttraction : " + attraction,
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Erreur lors de la réservation.",
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        rechargerAffichage(utilisateur);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ce créneau est déjà complet !",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            planningPanel.add(ligne);
        }

        JScrollPane scrollPane = new JScrollPane(planningPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setPreferredSize(new Dimension(400, getHeight()));
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBackground(Color.WHITE);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JTextArea description = new JTextArea("À propos de l’attraction \"" + attraction + "\"\n\n"
                + descriptionFromDB + "\n\n"
                + "Les créneaux sont limités en capacité (max " + capaciteMax + " personnes).\n"
                + "Une fois complet, le créneau devient indisponible.\n\n"
                + "Bon moment garanti à Hop'In !");

        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setEditable(false);
        description.setBackground(Color.WHITE);

        descriptionPanel.add(description);
        mainPanel.add(descriptionPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void rechargerAffichage(Utilisateur utilisateur) {
        SwingUtilities.invokeLater(() -> {
            dispose();
            int capaciteMax = new AttractionDAO().getCapaciteAttraction(id_attraction);
            new VueHoraireAttraction(attraction, id_attraction, utilisateur, date, capaciteMax);
        });
    }


}
