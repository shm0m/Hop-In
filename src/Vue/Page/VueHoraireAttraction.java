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

        JLabel titre = new JLabel("Réservation - " + date + " | " + attraction, SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titre, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Partie gauche : créneaux
        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBackground(Color.WHITE);
        panelGauche.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 20));

        List<String> creneaux = getCreneauxSelonSaison(date);
        Map<String, Integer> reservations = getReservationsSimulees(creneaux);

        for (String creneau : creneaux) {
            int inscrits = reservations.getOrDefault(creneau, 0);

            JPanel bloc = new JPanel(new BorderLayout());
            bloc.setMaximumSize(new Dimension(500, 50));
            bloc.setBackground(Color.WHITE);
            bloc.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(8, 16, 8, 16)
            ));

            JLabel heureLabel = new JLabel(creneau);
            heureLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            bloc.add(heureLabel, BorderLayout.WEST);

            JLabel dispoLabel = new JLabel(inscrits + "/" + PLACES_MAX + " inscrits");
            dispoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            dispoLabel.setForeground(inscrits >= PLACES_MAX ? Color.RED : new Color(34, 139, 34));
            bloc.add(dispoLabel, BorderLayout.EAST);

            bloc.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bloc.setBackground(new Color(245, 250, 255));

            bloc.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    bloc.setBackground(new Color(230, 244, 255));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    bloc.setBackground(new Color(245, 250, 255));
                }

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (inscrits < PLACES_MAX) {
                        JOptionPane.showMessageDialog(null,
                                "Réservation confirmée pour " + creneau + "\nAttraction : " + attraction,
                                "Créneau sélectionné", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ce créneau est déjà complet !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            panelGauche.add(bloc);
            panelGauche.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollGauche = new JScrollPane(panelGauche);
        scrollGauche.setBorder(null);
        scrollGauche.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollGauche, BorderLayout.CENTER);

        // Partie droite : description
        JPanel panelDroite = new JPanel();
        panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
        panelDroite.setPreferredSize(new Dimension(400, 0));
        panelDroite.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelDroite.setBackground(Color.WHITE);

        JTextArea description = new JTextArea("""
                À propos de l'attraction \"""" + attraction + """

                Réservez un créneau horaire pour profiter de cette attraction dans les meilleures conditions.
                Les créneaux sont limités en capacité (max 25 personnes).
                Une fois complet, le créneau devient indisponible.

                Bon moment garanti à Hop'In !
                """);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setEditable(false);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setBackground(Color.WHITE);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelDroite.add(description);
        mainPanel.add(panelDroite, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private List<String> getCreneauxSelonSaison(LocalDate date) {
        List<String> creneaux = new ArrayList<>();
        int m = date.getMonthValue(), d = date.getDayOfMonth();

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
