package Vue.Page;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class PageReservationSwing extends JFrame {

    private JPanel calendarPanel;
    private JLabel moisLabel;
    private LocalDate currentMonth;

    public PageReservationSwing() {
        setTitle("Réservations - Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(250, 250, 255));

        currentMonth = LocalDate.now().withDayOfMonth(1);
        setLayout(new BorderLayout(20, 20));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 255));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JButton prevButton = new JButton("⬅ Mois précédent");
        JButton nextButton = new JButton("Mois suivant ➡");
        styliseNavButton(prevButton);
        styliseNavButton(nextButton);

        moisLabel = new JLabel("", SwingConstants.CENTER);
        moisLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(moisLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel(new GridLayout(0, 7, 10, 10));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        calendarPanel.setBackground(Color.WHITE);
        add(calendarPanel, BorderLayout.CENTER);

        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        legendPanel.setBackground(new Color(245, 245, 245));
        legendPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        legendPanel.add(createLegend(new Color(144, 238, 144), "Haute saison"));         // vert pastel
        legendPanel.add(createLegend(new Color(173, 216, 230), "Basse saison"));         // bleu ciel doux
        legendPanel.add(createLegend(new Color(186, 85, 211), "Nocturne Halloween"));    // violet profond
        legendPanel.add(createLegend(new Color(255, 200, 120), "Sculpture Citrouille")); // orange doux
        legendPanel.add(createLegend(new Color(220, 220, 220), "Normal"));               // gris clair

        add(legendPanel, BorderLayout.SOUTH);
        prevButton.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refreshCalendar();
        });

        nextButton.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refreshCalendar();
        });

        refreshCalendar();
        setVisible(true);
    }

    private void styliseNavButton(JButton btn) {
        btn.setBackground(new Color(200, 230, 255));
        btn.setForeground(Color.DARK_GRAY);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 200, 240), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JPanel createLegend(Color color, String label) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(new Color(245, 245, 245));
        JLabel box = new JLabel("■");
        box.setForeground(color);
        JLabel txt = new JLabel(" " + label);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.add(box);
        p.add(txt);
        return p;
    }

    private void refreshCalendar() {
        calendarPanel.removeAll();
        moisLabel.setText(currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH).toUpperCase() + " " + currentMonth.getYear());

        String[] jours = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (String jour : jours) {
            JLabel lbl = new JLabel(jour, SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lbl.setOpaque(true);
            lbl.setBackground(new Color(230, 240, 255));
            lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            calendarPanel.add(lbl);
        }

        LocalDate firstDay = currentMonth;
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();
        int daysInMonth = firstDay.lengthOfMonth();

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentMonth.withDayOfMonth(day);
            JButton btn = new JButton(String.valueOf(day));
            btn.setOpaque(true);
            btn.setBackground(getColorForDate(date));
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setToolTipText(date.toString() + " - " + getSaison(date));

            btn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "Date sélectionnée : " + date +
                            "\n Saison : " + getSaison(date),
                    "Infos Réservation", JOptionPane.INFORMATION_MESSAGE));

            calendarPanel.add(btn);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private Color getColorForDate(LocalDate date) {
        return switch (getSaison(date)) {
            case "Haute saison" -> new Color(144, 238, 144);
            case "Basse saison" -> new Color(173, 216, 230);
            case "Nocturne Halloween" -> new Color(186, 85, 211);
            case "Sculpture Citrouille" -> new Color(255, 200, 120);
            default -> new Color(220, 220, 220);
        };
    }

    private String getSaison(LocalDate date) {
        int d = date.getDayOfMonth();
        int m = date.getMonthValue();
        if (m == 10 && d >= 25) return "Nocturne Halloween";
        if (m == 10 && d >= 19 && d <= 24) return "Sculpture Citrouille";
        if (m >= 4 && m <= 8) return "Haute saison";
        if (m == 10 || m == 9) return "Basse saison";
        return "Normal";
    }
}
