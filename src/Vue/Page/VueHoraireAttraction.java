package Vue.Page;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class VueHoraireAttraction extends JFrame {
    private final int PLACES_MAX = 25;
    private final String attraction;
    private final LocalDate date;

    public VueHoraireAttraction(String attraction, LocalDate date) {
        this.attraction = attraction;
        this.date = date;

        setTitle("Réserver une attraction - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bandeau titre
        JLabel titre = new JLabel("📅 Réservation - " + date + " | " + attraction, SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setOpaque(true);
        titre.setBackground(new Color(240, 240, 240));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titre, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tableau de créneaux (style Pronote)
        JPanel planningPanel = new JPanel();
        planningPanel.setLayout(new GridLayout(0, 1, 5, 5));
        planningPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 30));
        planningPanel.setBackground(new Color(250, 250, 250));

        List<String> creneaux = getCreneauxSelonSaison(date);
        Map<String, Integer> reservations = getReservationsSimulees(creneaux);

        for (String creneau : creneaux) {
            int inscrits = reservations.getOrDefault(creneau, 0);

            JPanel ligne = new JPanel(new BorderLayout());
            ligne.setPreferredSize(new Dimension(700, 50));
            ligne.setBackground(Color.WHITE);
            ligne.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

            JLabel heure = new JLabel("🕒 " + creneau, SwingConstants.LEFT);
            heure.setFont(new Font("Segoe UI", Font.BOLD, 14));
            heure.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JLabel statut = new JLabel(inscrits + "/" + PLACES_MAX + " inscrits", SwingConstants.RIGHT);
            statut.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            statut.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            statut.setForeground(inscrits >= PLACES_MAX ? Color.RED : new Color(50, 150, 50));

            ligne.add(heure, BorderLayout.WEST);
            ligne.add(statut, BorderLayout.EAST);

            ligne.setCursor(new Cursor(Cursor.HAND_CURSOR));

            ligne.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (inscrits < PLACES_MAX) {
                        JOptionPane.showMessageDialog(null,
                                "Réservation confirmée pour " + creneau + "\nAttraction : " + attraction,
                                "Créneau sélectionné", JOptionPane.INFORMATION_MESSAGE);
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

        // Bloc description à droite
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

    private List<String> getCreneauxSelonSaison(LocalDate date) {
        List<String> creneaux = new ArrayList<>();
        int m = date.getMonthValue();
        int d = date.getDayOfMonth();

        if (m == 10 && d >= 25) {
            creneaux.addAll(Arrays.asList("21h - 22h", "22h - 23h", "23h - 00h", "00h - 01h"));
        } else if (m == 10 && d >= 19 && d <= 24) {
            creneaux.addAll(Arrays.asList("14h - 15h", "15h - 16h"));
        } else if (m >= 4 && m <= 8) {
            for (int h = 10; h < 19; h++) creneaux.add(h + "h - " + (h + 1) + "h");
        } else if (m == 9 || m == 10) {
            for (int h = 10; h < 18; h++) creneaux.add(h + "h - " + (h + 1) + "h");
        }
        return creneaux;
    }

    private Map<String, Integer> getReservationsSimulees(List<String> creneaux) {
        Map<String, Integer> map = new HashMap<>();
        Random rand = new Random();
        for (String creneau : creneaux) {
            map.put(creneau, rand.nextInt(PLACES_MAX + 1));
        }
        return map;
    }
}
