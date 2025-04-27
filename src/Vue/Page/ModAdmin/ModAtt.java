package Vue.Page.ModAdmin;

import DAO_Set.AttractionDAO;
import Modele.Attraction;
import Reporting.AffichageStats;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ModAtt extends JFrame {
    private JTextField modicDescription, modicPrix, modicCapaciteMax;
    private JLabel indicQuelId, affichAff;
    private JComboBox<Attraction> men_attractions;
    private AttractionDAO modifieur;
    private JFrame previousFrame;
    private JLabel stats;
    private AffichageStats affichageStats;

    public ModAtt(JFrame previousFrame) {
        super("Modifier Attraction");
        this.previousFrame = previousFrame;
        this.modifieur = new AttractionDAO();
        this.affichageStats=new AffichageStats();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        ArrayList<Attraction> attractions = modifieur.getAtts();

        JPanel panel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 248, 230), getWidth(), getHeight(), new Color(255, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBorder(new EmptyBorder(30, 50, 30, 50));
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        men_attractions = new JComboBox<>(attractions.toArray(new Attraction[0]));
        addField(panel, gbc, "Choisir une attraction :", men_attractions, 0);

        modicDescription = new JTextField();
        addField(panel, gbc, "Description :", modicDescription, 1);

        modicPrix = new JTextField();
        addField(panel, gbc, "Prix :", modicPrix, 2);

        modicCapaciteMax = new JTextField();
        addField(panel, gbc, "Capacité max :", modicCapaciteMax, 3);

        indicQuelId = new JLabel("0");
        addField(panel, gbc, "ID de l'attraction :", indicQuelId, 4);

        affichAff = new JLabel();
        addField(panel, gbc, "Affluence :", affichAff, 5);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);

        JButton afficherProp = createButton("Afficher", new Color(62, 15, 76));
        JButton modifier = createButton("Modifier & Quitter", new Color(76, 215, 179));
        JButton supprimer = createButton("Supprimer", new Color(249, 78, 139));
        JButton quitter = createButton("Quitter", new Color(239, 53, 53));

        btnPanel.add(afficherProp);
        btnPanel.add(modifier);
        btnPanel.add(supprimer);
        btnPanel.add(quitter);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);

        gbc.gridy=7;
        stats=new JLabel("Actualisez pour voir les infos");
        ;
        panel.add(stats,gbc);

        afficherProp.addActionListener(e -> {
            Attraction a = getAtt(men_attractions.getSelectedItem().toString(), attractions);
            modicDescription.setText(a.get_description());
            modicPrix.setText(a.get_prix_Str());
            modicCapaciteMax.setText(a.get_capacite_max_Str());
            indicQuelId.setText(a.get_id_attraction_Str());
            affichAff.setText(modifieur.getAffluence(a.get_id_attraction_Str()));
            stats.setText(affichageStats.getStatFromId(a.get_id_attraction()));
            System.out.println(affichageStats.getStatFromId(a.get_id_attraction()));
        });

        modifier.addActionListener(e -> {
            setVisible(false);
            previousFrame.setVisible(true);
            modifieur.changerAttraction(
                    Modele.Trans.toInt(indicQuelId.getText()),
                    men_attractions.getSelectedItem().toString(),
                    modicDescription.getText(),
                    Modele.Trans.toDouble(modicPrix.getText()),
                    Modele.Trans.toInt(modicCapaciteMax.getText())
            );
        });

        supprimer.addActionListener(e -> {
            setVisible(false);
            previousFrame.setVisible(true);
            modifieur.delAtt(getAtt(men_attractions.getSelectedItem().toString(), attractions).get_id_attraction());
        });
        quitter.addActionListener(e -> {
            previousFrame.setVisible(true);
            setVisible(false);
        });

        setContentPane(panel);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(new Color(62, 15, 76));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(lbl, gbc);

        field.setPreferredSize(new Dimension(280, 30));
        if (field instanceof JTextField)
            ((JTextField) field).setBorder(BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true));
        gbc.gridx = 1;
        gbc.gridy = y;
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(100, 40));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private Attraction getAtt(String nom, ArrayList<Attraction> attractions) {
        for (Attraction a : attractions) {
            if (a.get_nom().equals(nom)) {
                return a;
            }
        }
        return new Attraction(0, "ERREUR", "ERREUR", 0, 0);
    }
}
