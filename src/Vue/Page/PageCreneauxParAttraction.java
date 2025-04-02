package Vue.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PageCreneauxParAttraction extends JFrame {

    private JComboBox<String> attractionCombo;
    private JPanel creneauxPanel;
    private Map<String, Integer> inscriptionsMock = new HashMap<>();

    public PageCreneauxParAttraction(LocalDate date) {
        setTitle("Réserver une attraction - Hop'In");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Réservation pour le " + date.toString(), SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Choisissez une attraction :"));
        attractionCombo = new JComboBox<>(new String[]{
                "Labyrinthe du Maïs",
                "Chasse à l'œuf",
                "Laser-Tag",
                "Spectacle Pirate",
                "Sculpture Citrouille"
        });
        topPanel.add(attractionCombo);
        JButton btnAfficher = new JButton("Afficher les créneaux");
        topPanel.add(btnAfficher);
        add(topPanel, BorderLayout.CENTER);
        creneauxPanel = new JPanel();
        creneauxPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scroll = new JScrollPane(creneauxPanel);
        scroll.setBorder(BorderFactory.createTitledBorder("Créneaux disponibles"));
        add(scroll, BorderLayout.SOUTH);

        btnAfficher.addActionListener(this::afficherCreneaux);

        setVisible(true);
    }

    private void afficherCreneaux(ActionEvent e) {
        creneauxPanel.removeAll();
        String attraction = (String) attractionCombo.getSelectedItem();

        for (int heure = 10; heure < 19; heure++) {
            String creneau = heure + "h - " + (heure + 1) + "h";
            String key = attraction + "_" + creneau;
            int dejaInscrits = inscriptionsMock.getOrDefault(key, (int) (Math.random() * 20));

            JButton bouton = new JButton(creneau + " (" + dejaInscrits + "/25)");
            bouton.setBackground(dejaInscrits >= 25 ? Color.RED : new Color(173, 216, 230));
            bouton.setEnabled(dejaInscrits < 25);
            bouton.addActionListener(ev -> {
                JOptionPane.showMessageDialog(this,
                        "Vous avez réservé : " + creneau + " pour " + attraction,
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                inscriptionsMock.put(key, dejaInscrits + 1);
                afficherCreneaux(null);
            });

            creneauxPanel.add(bouton);
        }

        creneauxPanel.revalidate();
        creneauxPanel.repaint();
    }
}
