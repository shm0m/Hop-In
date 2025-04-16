package Vue.Page;

import DAO.AttractionDAO;
import DAO.ModifAdminDAO;
import Modele.Attraction;
import Modele.Utilisateur;
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
    private Utilisateur utilisateur; // ✅ utilisateur connecté

    private final Color COLOR_HAUTE = new Color(102, 204, 102);
    private final Color COLOR_BASSE = new Color(102, 178, 255);
    private final Color COLOR_NOCTURNE = new Color(186, 85, 211);
    private final Color COLOR_SCULPTURE = new Color(255, 165, 0);
    private final Color COLOR_NORMAL = new Color(224, 224, 224);

    // ✅ Constructeur avec utilisateur
    public PageReservationSwing(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        setTitle("📅 Réservation Hop'In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        currentMonth = LocalDate.now().withDayOfMonth(1);

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prev = new JButton("⬅");
        JButton next = new JButton("➡");

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(prev);



        JButton btnMesEvenements = new JButton("Mes informations");
        btnMesEvenements.setPreferredSize(new Dimension(150, 40));
        btnMesEvenements.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        btnMesEvenements.setBackground(new Color(60, 179, 113));
        btnMesEvenements.setForeground(Color.WHITE);
        btnMesEvenements.setFocusPainted(false);
        btnMesEvenements.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(btnMesEvenements);



        btnMesEvenements.addActionListener(e -> {
            setVisible(false);
        });



        moisLabel = new JLabel("", SwingConstants.CENTER);
        moisLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(moisLabel, BorderLayout.CENTER);
        topPanel.add(next, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);


        topPanel.add(prev, BorderLayout.WEST);
        topPanel.add(moisLabel, BorderLayout.CENTER);
        topPanel.add(next, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(calendarPanel, BorderLayout.CENTER);

        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER));
        legend.add(createLegend(COLOR_HAUTE, "Haute saison"));
        legend.add(createLegend(COLOR_BASSE, "Basse saison"));
        legend.add(createLegend(COLOR_NOCTURNE, "Nocturne Halloween"));
        legend.add(createLegend(COLOR_SCULPTURE, "Sculpture citrouille"));
        legend.add(createLegend(COLOR_NORMAL, "Normal"));
        add(legend, BorderLayout.SOUTH);

        prev.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refreshCalendar();
        });

        next.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refreshCalendar();
        });

        refreshCalendar();
        setVisible(true);
    }

    private JPanel createLegend(Color c, String label) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
                /*String[] attractions = {"Laser Game", "Exploration", "Sculpture Citrouille", "Nocturne Halloween"};*/
                ArrayList<Attraction> attractions = new ModifAdminDAO().getAtts();
                String[] attractionsArr=AttToArray(attractions);
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
                    int idAttraction = getAttIdfromNom(attractions,choix) ;
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

    private int getAttractionId(String nom) {
        return switch (nom) {
            case "Laser Game" -> 1;
            case "Exploration" -> 2;
            case "Sculpture Citrouille" -> 3;
            case "Nocturne Halloween" -> 4;
            default -> 1;
        };
    }
    private int getAttIdfromNom(ArrayList<Attraction> attractions, String  nom){
        for(Attraction a: attractions  ){
            if(a.toString().compareTo(nom)==0){
                return(a.get_id_attraction());
            }
        }
        return(0);
    }

    private String[] AttToArray(ArrayList<Attraction> atts){
        String[] ret=new String[atts.size()];
        for (int i = 0; i < atts.size(); i++) {
            ret[i]=atts.get(i).toString();

        }
        return(ret);
    }


}
