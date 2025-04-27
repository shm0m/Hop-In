package Vue.Page.ModAdmin;

import DAO_Set.ReductionDAO;
import Modele.Reduction;
import Modele.Trans;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreRed extends JFrame {
    private JLabel indNom;
    private JTextField ajNom;
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
    private JLabel indMinVis;
    private JTextField ajMinVis;

    private ReductionDAO modifieur;

    private JLabel output;

    public CreRed(JFrame previousFrame){
        super("Ajout d'une réduction");
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modifieur=new ReductionDAO();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
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
        ajNom= new JTextField("Nom ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;     
       panel.add(ajNom, gbc);

        indPrc=new JLabel("Pourcentage de réduction");
        gbc.gridx = 0;
        gbc.gridy = 1;
       panel.add(indPrc, gbc);
        ajPrc= new JTextField("00");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;     
       panel.add(ajPrc, gbc);

        indAgeMin=new JLabel("Age minimum");
        gbc.gridx = 0;
        gbc.gridy = 2;
       panel.add(indAgeMin, gbc);
        ajAgeMin= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;     
       panel.add(ajAgeMin, gbc);

        indAgeMax=new JLabel("Age maximum ");
        gbc.gridx = 0;
        gbc.gridy = 3;
       panel.add(indAgeMax, gbc);
        ajAgeMax= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4;     
       panel.add(ajAgeMax, gbc);

        indDateMin=new JLabel("Date de début (aaaa-mm-jj)");
        gbc.gridx = 0;
        gbc.gridy = 5;
       panel.add(indDateMin, gbc);
        ajDateMin= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4;     
       panel.add(ajDateMin, gbc);

        indDateMax=new JLabel("Date de fin (aaaa-mm-jj)");
        gbc.gridx = 0;
        gbc.gridy = 6;
       panel.add(indDateMax, gbc);
        ajDateMax= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 4;     
       panel.add(ajDateMax, gbc);

        indMinVis=new JLabel("Nombre minimum de réservations");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(indMinVis, gbc);
        ajMinVis= new JTextField("null");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 4;     
        panel.add(ajMinVis, gbc);

        output=new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 8;
       panel.add(output, gbc);

        JButton ajouter = createButton("Ajouter", new Color(62, 15, 76));
        JButton quitter = createButton("Quitter", new Color(249, 78, 139));

        gbc.gridy=9;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(ajouter, gbc);


        gbc.gridx = 1;
        gbc.gridwidth = 1;
        panel.add(quitter, gbc);

        quitter.addActionListener(e->{
            previousFrame.setVisible(true);
            setVisible(false);
        });
        ajouter.addActionListener(e->{
            int resultat=modifieur.ajouterReduction(new Reduction(
                    0,
                    Trans.toDouble(ajPrc.getText()),
                    Trans.toInt(ajAgeMin.getText()),
                    Trans.toInt(ajAgeMax.getText()),
                    ajDateMin.getText(),
                    ajDateMax.getText(),
                    ajNom.getText(),
                    Trans.toInt(ajMinVis.getText())
            ));
            if(resultat==1){
                previousFrame.setVisible(true);
                setVisible(false);
            }
            else{
                output.setText("Erreur lors de l'insertion à la bdd");
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
}
