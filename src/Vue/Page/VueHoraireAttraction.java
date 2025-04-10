package Vue.Page;

import Modele.Utilisateur;
import Modele.Creneau;
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
    private final int PLACES_MAX = 25;
    private final String attraction;
    private final LocalDate date;
    private final int id_utilisateur;
    private final int id_attraction;

    public VueHoraireAttraction(String attraction, int id_attraction, Utilisateur utilisateur, LocalDate date) {
        this.attraction = attraction;
        this.date = date;
        this.id_attraction = id_attraction;
        this.id_utilisateur = utilisateur != null ? utilisateur.getId() : 0;

        setTitle("Réserver une attraction - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("📅 Réservation - " + date + " | " + attraction, SwingConstants.CENTER);
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

        for (Map.Entry<Integer, Time> entry : creneauxDispo.entrySet()) {
            int idCreneau = entry.getKey();
            Time heureDebut = entry.getValue();
            int heureFin = heureDebut.toLocalTime().plusHours(1).getHour();
            String label = heureDebut.toString().substring(0, 5) + " - " + heureFin + "h";

            int inscrits = reservationsMap.getOrDefault(idCreneau, 0);

            JPanel ligne = new JPanel(new BorderLayout());
            ligne.setPreferredSize(new Dimension(700, 50));
            ligne.setBackground(Color.WHITE);
            ligne.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

            JLabel heure = new JLabel("🕒 " + label, SwingConstants.LEFT);
            heure.setFont(new Font("Segoe UI", Font.BOLD, 14));
            heure.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JLabel statut = new JLabel(inscrits + "/" + PLACES_MAX + " inscrits", SwingConstants.RIGHT);
            statut.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            statut.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            statut.setForeground(inscrits >= PLACES_MAX ? Color.RED : new Color(50, 150, 50));

            ligne.add(heure, BorderLayout.WEST);
            ligne.add(statut, BorderLayout.EAST);
            ligne.setCursor(new Cursor(Cursor.HAND_CURSOR));

            ligne.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (inscrits < PLACES_MAX) {
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
                                "❌ Ce créneau est déjà complet !",
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

        JTextArea description = new JTextArea("📍 À propos de l’attraction \"" + attraction + "\"\n\n"
                + "🔹 Réservez un créneau horaire pour profiter de cette attraction dans les meilleures conditions.\n"
                + "🔹 Les créneaux sont limités en capacité (max " + PLACES_MAX + " personnes).\n"
                + "🔹 Une fois complet, le créneau devient indisponible.\n\n"
                + "🎢 Bon moment garanti à Hop'In !");
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
            getContentPane().removeAll();
            new VueHoraireAttraction(this.attraction, this.id_attraction, utilisateur, this.date);
            dispose();
        });
    }
}
