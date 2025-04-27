package Vue.Page.ModAdmin;

import DAO_Set.ReservationDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Map;

public class VueAffluenceAttraction extends JFrame {
    private final String attraction;
    private final LocalDate date;
    private final int id_attraction;

    public VueAffluenceAttraction(String attraction, int id_attraction, LocalDate date) {
        this.attraction = attraction;
        this.date = date;
        this.id_attraction = id_attraction;

        setTitle("Affluence - " + attraction);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Affluence - " + attraction + " (" + date + ")", SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setForeground(new Color(62, 15, 76));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titre, BorderLayout.NORTH);

        JPanel chartPanel = new ChartPanel(id_attraction, date);
        add(chartPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    class ChartPanel extends JPanel {
        private final Map<Integer, Integer> reservationsMap;
        private final Map<Integer, Time> creneauxDispo;

        public ChartPanel(int idAttraction, LocalDate date) {
            ReservationDAO reservationDAO = new ReservationDAO();
            this.reservationsMap = reservationDAO.getNbPersonnesParCreneau(idAttraction, date);
            this.creneauxDispo = reservationDAO.getHeuresCreneauxDepuisBase();
            setPreferredSize(new Dimension(800, 500));
            setBackground(new Color(255, 248, 230));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int padding = 50;
            int labelPadding = 40;
            int width = getWidth() - 2 * padding;
            int height = getHeight() - 2 * padding;

            int maxInscrits = reservationsMap.values().stream().mapToInt(Integer::intValue).max().orElse(10);
            maxInscrits = Math.max(maxInscrits, 5);

            int barWidth = width / reservationsMap.size() - 20;
            int x = padding + 10;

            for (Map.Entry<Integer, Integer> entry : reservationsMap.entrySet()) {
                int idCreneau = entry.getKey();
                int inscrits = entry.getValue();
                Time heureDebut = creneauxDispo.get(idCreneau);

                if (heureDebut == null) continue;

                int barHeight = (int) ((double) inscrits / maxInscrits * height);
                int y = getHeight() - padding - barHeight;

                g2.setColor(new Color(249, 78, 139));
                g2.fillRect(x, y, barWidth, barHeight);

                g2.setColor(Color.BLACK);
                g2.drawString(heureDebut.toString().substring(0,5), x + 5, getHeight() - padding + 15);

                g2.drawString(String.valueOf(inscrits), x + 10, y - 5);

                x += barWidth + 20;
            }

            g2.setColor(Color.BLACK);
            g2.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding); // Axe X
            g2.drawLine(padding, padding, padding, getHeight() - padding); // Axe Y
        }
    }
}
