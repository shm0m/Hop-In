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

        currentMonth = LocalDate.now().withDayOfMonth(1);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prevButton = new JButton("⬅");
        JButton nextButton = new JButton("➡");
        moisLabel = new JLabel("", SwingConstants.CENTER);
        moisLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(moisLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        add(calendarPanel, BorderLayout.CENTER);
        JPanel legendPanel = new JPanel();
        legendPanel.add(createLegend(Color.GREEN, "Haute saison"));
        legendPanel.add(createLegend(Color.CYAN, "Basse saison"));
        legendPanel.add(createLegend(new Color(186, 85, 211), "Nocturne Halloween"));
        legendPanel.add(createLegend(Color.ORANGE, "Sculpture Citrouille"));
        legendPanel.add(createLegend(Color.LIGHT_GRAY, "Normal"));
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

    private JPanel createLegend(Color color, String label) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel box = new JLabel("■");
        box.setForeground(color);
        JLabel txt = new JLabel(" " + label);
        p.add(box);
        p.add(txt);
        return p;
    }

    private void refreshCalendar() {
        calendarPanel.removeAll();
        moisLabel.setText(currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH) + " " + currentMonth.getYear());

        String[] jours = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (String jour : jours) {
            JLabel lbl = new JLabel(jour, SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
            btn.setBorderPainted(false);
            btn.setBackground(getColorForDate(date));
            btn.setToolTipText(date.toString() + " - " + getSaison(date));
            btn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "Date sélectionnée : " + date + "\nSaison : " + getSaison(date),
                    "Infos Réservation", JOptionPane.INFORMATION_MESSAGE));
            calendarPanel.add(btn);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private Color getColorForDate(LocalDate date) {
        String saison = getSaison(date);
        return switch (saison) {
            case "Haute saison" -> Color.GREEN;
            case "Basse saison" -> Color.CYAN;
            case "Nocturne Halloween" -> new Color(186, 85, 211); // violet
            case "Sculpture Citrouille" -> Color.ORANGE;
            default -> Color.LIGHT_GRAY;
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
