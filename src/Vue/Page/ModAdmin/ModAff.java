package Vue.Page.ModAdmin;

import DAO_Set.AttractionDAO;
import DAO_Set.ReservationDAO;
import Modele.Attraction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class ModAff extends JFrame {
    private JPanel calendarPanel;
    private JLabel moisLabel;
    private LocalDate currentMonth;
    private JFrame previousFrame;

    public ModAff(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Affluence Hop'In");
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

        moisLabel = new JLabel("", SwingConstants.CENTER);
        moisLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JButton prev = new JButton("<");
        JButton next = new JButton(">");
        prev.setFont(new Font("Segoe UI", Font.BOLD, 18));
        next.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel monthPanel = new JPanel(new BorderLayout());
        monthPanel.setOpaque(false);
        monthPanel.add(prev, BorderLayout.WEST);
        monthPanel.add(moisLabel, BorderLayout.CENTER);
        monthPanel.add(next, BorderLayout.EAST);

        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        calendarPanel.setOpaque(false);
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        prev.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refreshCalendar();
        });

        next.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refreshCalendar();
        });

        refreshCalendar();

        JButton retour = new JButton("Retour");
        retour.setFont(new Font("Segoe UI", Font.BOLD, 16));
        retour.setBackground(new Color(249, 78, 139));
        retour.setForeground(Color.WHITE);
        retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retour.addActionListener(e -> {
            dispose();
            previousFrame.setVisible(true);
        });

        mainPanel.add(monthPanel, BorderLayout.NORTH);
        mainPanel.add(calendarPanel, BorderLayout.CENTER);
        mainPanel.add(retour, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
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
            btn.setBackground(new Color(224, 224, 224));

            btn.addActionListener(e -> afficherAffluence(date));

            calendarPanel.add(btn);
        }
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void afficherAffluence(LocalDate date) {
        ArrayList<Attraction> attractions = new AttractionDAO().getAtts();
        String[] attractionNames = new String[attractions.size()];

        for (int i = 0; i < attractions.size(); i++) {
            attractionNames[i] = attractions.get(i).toString();
        }

        String choix = (String) JOptionPane.showInputDialog(
                this,
                "Choisissez une attraction :",
                "Sélection de l'attraction",
                JOptionPane.QUESTION_MESSAGE,
                null,
                attractionNames,
                attractionNames[0]
        );

        if (choix != null) {
            Attraction selected = attractions.stream()
                    .filter(att -> att.toString().equals(choix))
                    .findFirst()
                    .orElse(null);

            if (selected != null) {
                dispose();
                new VueAffluenceAttraction(selected.toString(), selected.get_id_attraction(), date);
            }
        }
    }


}
