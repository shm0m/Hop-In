package Vue.Page;

import DAO.AttractionDAO;
import Modele.Attraction;
import Modele.Utilisateur;
import Vue.NavBarUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class PageReservationSwing extends JFrame {
    private JPanel calendarPanel;
    private JLabel moisLabel;
    private LocalDate currentMonth;
    private Utilisateur utilisateur;

    private final Color COLOR_HAUTE = new Color(102, 204, 102);
    private final Color COLOR_BASSE = new Color(102, 178, 255);
    private final Color COLOR_NOCTURNE = new Color(186, 85, 211);
    private final Color COLOR_SCULPTURE = new Color(255, 165, 0);
    private final Color COLOR_NORMAL = new Color(224, 224, 224);

    public PageReservationSwing(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        setTitle("📅 Réservation Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0,
                        new Color(255, 248, 230),
                        getWidth(), getHeight(),
                        new Color(255, 240, 245)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        currentMonth = LocalDate.now().withDayOfMonth(1);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JPanel navBar = NavBarUtil.createNavBar(this, utilisateur);
        topPanel.add(navBar, BorderLayout.CENTER);

        moisLabel = new JLabel("", SwingConstants.CENTER);
        moisLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel monthPanel = new JPanel(new BorderLayout());
        monthPanel.setOpaque(false);
        JButton prev = new JButton("<");
        JButton next = new JButton(">");
        prev.setFont(new Font("Segoe UI", Font.BOLD, 18));
        next.setFont(new Font("Segoe UI", Font.BOLD, 18));
        monthPanel.add(prev, BorderLayout.WEST);
        monthPanel.add(moisLabel, BorderLayout.CENTER);
        monthPanel.add(next, BorderLayout.EAST);

        JPanel fullTop = new JPanel(new BorderLayout());
        fullTop.setOpaque(false);
        fullTop.add(navBar, BorderLayout.NORTH);
        fullTop.add(monthPanel, BorderLayout.SOUTH);

        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        calendarPanel.setOpaque(false);
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER));
        legend.setOpaque(false);
        legend.add(createLegend(COLOR_HAUTE, "Haute saison"));
        legend.add(createLegend(COLOR_BASSE, "Basse saison"));
        legend.add(createLegend(COLOR_NOCTURNE, "Nocturne Halloween"));
        legend.add(createLegend(COLOR_SCULPTURE, "Sculpture citrouille"));
        legend.add(createLegend(COLOR_NORMAL, "Normal"));

        prev.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refreshCalendar();
        });

        next.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refreshCalendar();
        });

        refreshCalendar();

        mainPanel.add(fullTop, BorderLayout.NORTH);
        mainPanel.add(calendarPanel, BorderLayout.CENTER);
        mainPanel.add(legend, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JPanel createLegend(Color c, String label) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setOpaque(false);
        JLabel box = new JLabel("■");
        box.setForeground(c);
        JLabel txt = new JLabel(" " + label);
        p.add(box);
        p.add(txt);
        return p;
    }

    private void refreshCalendar() {
        calendarPanel.removeAll();
        moisLabel.setText(currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH) + " " + currentMonth.getYear());

        String[] jours = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (String j : jours) {
            JLabel lbl = new JLabel(j, SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
            calendarPanel.add(lbl);
        }

        LocalDate first = currentMonth;
        int firstDay = first.getDayOfWeek().getValue();
        int total = first.lengthOfMonth();

        for (int i = 1; i < firstDay; i++) calendarPanel.add(new JLabel(""));

        for (int d = 1; d <= total; d++) {
            LocalDate date = currentMonth.withDayOfMonth(d);
            JButton btn = new JButton(String.valueOf(d));
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setBackground(getColorForDate(date));
            btn.setToolTipText(getSaison(date));

            btn.addActionListener(e -> {
                ArrayList<Attraction> attractions = new AttractionDAO().getAtts();
                String[] attractionsArr = AttToArray(attractions);
                String choix = (String) JOptionPane.showInputDialog(
                        this,
                        "Choisissez une attraction :",
                        "Attraction",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        attractionsArr,
                        attractionsArr[0]
                );

                if (choix != null) {
                    int idAttraction = getAttIdfromNom(attractions, choix);
                    int capaciteMax = new AttractionDAO().getCapaciteAttraction(idAttraction);
                    new VueHoraireAttraction(choix, idAttraction, utilisateur, date, capaciteMax);
                }
            });

            calendarPanel.add(btn);
        }
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private Color getColorForDate(LocalDate date) {
        return switch (getSaison(date)) {
            case "Haute saison" -> COLOR_HAUTE;
            case "Basse saison" -> COLOR_BASSE;
            case "Nocturne Halloween" -> COLOR_NOCTURNE;
            case "Sculpture citrouille" -> COLOR_SCULPTURE;
            default -> COLOR_NORMAL;
        };
    }

    private String getSaison(LocalDate d) {
        int day = d.getDayOfMonth(), month = d.getMonthValue();
        if (month == 10 && day >= 25) return "Nocturne Halloween";
        if (month == 10 && day >= 19 && day <= 24) return "Sculpture citrouille";
        if (month >= 4 && month <= 8) return "Haute saison";
        if (month == 9 || month == 10) return "Basse saison";
        return "Normal";
    }

    private int getAttIdfromNom(ArrayList<Attraction> attractions, String nom) {
        for (Attraction a : attractions) {
            if (a.toString().equals(nom)) {
                return a.get_id_attraction();
            }
        }
        return 0;
    }

    private String[] AttToArray(ArrayList<Attraction> atts) {
        String[] ret = new String[atts.size()];
        for (int i = 0; i < atts.size(); i++) {
            ret[i] = atts.get(i).toString();
        }
        return ret;
    }
}
