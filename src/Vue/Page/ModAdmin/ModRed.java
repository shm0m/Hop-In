package Vue.Page.ModAdmin;

import DAO.ReductionChercherDAO;
import DAO.ReductionDAO;
import Modele.Client;
import Modele.Reduction;
import Modele.Trans;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ModRed extends JFrame{
    private JLabel indNom;
    private JComboBox<Reduction> choixRed;
    private JLabel indPrc;
    private JTextField ajPrc;
    private JLabel indAgeMin;
    private JTextField ajAgeMin;
    private JLabel indAgeMax;
    private JTextField ajAgeMax;
    private JLabel indDateMin;
    private JTextField ajDateMin;
    private JLabel indDateMax;
    private JTextField ajDateMax;

    private ReductionDAO modifieur;
    private ReductionChercherDAO getter;

    private JLabel output;

    public ModRed(JFrame previousFrame){
        super("Ajout d'une réduction");
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modifieur=new ReductionDAO();
        getter=new ReductionChercherDAO();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes

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

        indNom=new JLabel("Nom");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(indNom, gbc);

        ArrayList<Reduction> reductions = getter.getReducs();
        choixRed = new JComboBox<>(reductions.toArray(new Reduction[0]));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(choixRed, gbc);

        indPrc=new JLabel("Pourcentage de réduction");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(indPrc, gbc);
        ajPrc= new JTextField("00");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajPrc, gbc);

        indAgeMin=new JLabel("Age minimum");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(indAgeMin, gbc);
        ajAgeMin= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajAgeMin, gbc);

        indAgeMax=new JLabel("Age maximum ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(indAgeMax, gbc);
        ajAgeMax= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajAgeMax, gbc);

        indAgeMax=new JLabel("Age maximal ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(indAgeMax, gbc);
        ajAgeMax= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajAgeMax, gbc);

        indDateMin=new JLabel("Date de début (aaaa-mm-jj)");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(indDateMin, gbc);
        ajDateMin= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajDateMin, gbc);

        indDateMax=new JLabel("Date de fin (aaaa-mm-jj)");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(indDateMax, gbc);
        ajDateMax= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        panel.add(ajDateMax, gbc);

        output=new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(output, gbc);


        gbc.gridy=8;
        JButton actualiser = createButton("Actualiser", new Color(13, 255, 211));
        JButton quitter = createButton("Quitter", new Color(249, 78, 139));
        JButton enregQuitter=createButton("Enregistrer", new Color(169, 249, 78));
        JButton supprimer=createButton("Supprimer", new Color(238, 49, 49));

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(actualiser, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        panel.add(enregQuitter, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        panel.add(quitter, gbc);
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        panel.add(supprimer, gbc);

        quitter.addActionListener(e->{
            previousFrame.setVisible(true);
            setVisible(false);
        });
        supprimer.addActionListener(e->{
            int resultat =modifieur.supprimerReduc(Trans.toInt(choixRed.getSelectedItem().toString()));
            if(resultat==1){
                previousFrame.setVisible(true);
                setVisible(false);
            }
            else{
                output.setText("Ouups ! Somesing ouant oueong");
            }
        });
        actualiser.addActionListener(e->{
            Reduction reduction=getRed(reductions,Trans.toInt( choixRed.getSelectedItem().toString()));
            ajPrc.setText(reduction.getprcRed()+"");
            ajAgeMin.setText(reduction.getageMin()+"");
            ajAgeMax.setText(reduction.getageMax()+"");
            ajDateMin.setText(reduction.getdateMin());
            ajDateMax.setText(reduction.getdateMax());
        });
        enregQuitter.addActionListener(e->{
            int resultat =modifieur.updateReduc(new Reduction(
                    Trans.toInt(choixRed.getSelectedItem().toString()),
                    Trans.toDouble(ajPrc.getText()),
                    Trans.toInt(ajAgeMin.getText()),
                    Trans.toInt(ajAgeMax.getText()),
                    ajDateMin.getText(),
                    ajDateMax.getText(),
                    "sans_importance"
            ));
            if(resultat==1){
                previousFrame.setVisible(true);
                setVisible(false);
            }
            else{
                output.setText("Ouups ! Somesing ouant oueong");
            }
        });


        setContentPane(panel);
        setVisible(true);
    }


    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private Reduction getRed(ArrayList<Reduction> reductions, int id) {
        for (Reduction reduction: reductions) {
            if (reduction.getid()==id) {
                return reduction;
            }
        }
        return new Reduction();
    }
}

