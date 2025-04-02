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

        JLabel titre = new JLabel("Réservation pour le " + date + " | " + attraction, SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titre, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBackground(new Color(245, 245, 245));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        List<String> creneaux = getCreneauxSelonSaison(date);
        Map<String, Integer> reservations = getReservationsSimulees(creneaux);

        for (String creneau : creneaux) {
            int inscrits = reservations.getOrDefault(creneau, 0);

            JPanel bloc = new JPanel(new BorderLayout());
            bloc.setMaximumSize(new Dimension(400, 70));
            bloc.setBackground(inscrits >= PLACES_MAX ? new Color(255, 100, 100) : new Color(204, 229, 255));
            bloc.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

            JLabel heure = new JLabel("🕒 " + creneau, SwingConstants.CENTER);
            heure.setFont(new Font("Segoe UI", Font.BOLD, 16));

            JLabel info = new JLabel(inscrits + "/" + PLACES_MAX + " inscrits", SwingConstants.CENTER);
            info.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            bloc.add(heure, BorderLayout.CENTER);
            bloc.add(info, BorderLayout.SOUTH);
            bloc.setCursor(new Cursor(Cursor.HAND_CURSOR));

            bloc.addMouseListener(new java.awt.event.MouseAdapter() {
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

        JPanel panelDroite = new JPanel();
        panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
        panelDroite.setPreferredSize(new Dimension(400, 0));
        panelDroite.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        panelDroite.setBackground(Color.WHITE);


        JTextArea description = new JTextArea("Bienvenue dans l'univers de l'attraction \"" + attraction + "\" !\n\n" +
                "Plongez dans une expérience immersive avec des créneaux adaptés à tous les âges. " +
                "Réservez votre passage dès maintenant et profitez d’un moment inoubliable au parc Hop’In.");
        description.setEditable(false);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setBackground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDroite.add(Box.createVerticalStrut(20));
        panelDroite.add(description);

        mainPanel.add(panelDroite, BorderLayout.EAST);

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
            for (int h = 10; h < 19; h++) {
                creneaux.add(h + "h - " + (h + 1) + "h");
            }
        } else if (m == 9 || m == 10) {
            for (int h = 10; h < 18; h++) {
                creneaux.add(h + "h - " + (h + 1) + "h");
            }
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
