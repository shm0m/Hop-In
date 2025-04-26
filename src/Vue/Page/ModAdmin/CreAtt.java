package Vue.Page.ModAdmin;

import DAO.AttractionDAO;
import DAO.ModifAdminDAO;
import Modele.Attraction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreAtt extends JFrame {
    private JLabel indicNom;
    private JTextField modicNom;
    private JLabel indicDescription;
    private JTextField modicDescription;
    private JLabel indicPrix;
    private JTextField modicPrix;
    private JLabel indicCapaciteMax;
    private JTextField modicCapaciteMax;
    private AttractionDAO modifieur;
    private JButton retour;
    private JButton ajouter;

    JFrame previousFrame;

    public CreAtt(JFrame previousFrame){
        super("Vue Administrateur");
        this.modifieur=new AttractionDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.previousFrame=previousFrame;

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


        modicNom=new JTextField("Nom de l'attraction");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        


        indicNom=new JLabel("Nom de l'attraction");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(indicNom, gbc);



        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(modicNom, gbc);

        indicDescription=new JLabel("Description: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(indicDescription, gbc);

        modicDescription=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;   
        panel.add(modicDescription, gbc);

        indicPrix=new JLabel("Prix");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(indicPrix, gbc);

        modicPrix=new JTextField("00.00");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;   
        panel.add(modicPrix, gbc);

        indicCapaciteMax=new JLabel("Capacité maximum");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(indicCapaciteMax, gbc);

        modicCapaciteMax=new JTextField("00");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4;   
        panel.add(modicCapaciteMax, gbc);

        ajouter = createButton("Ajouter", new Color(201, 222, 63));
        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 1;    
        panel.add(ajouter, gbc);

        retour =createButton("Retour", new Color(222, 63, 100));
        gbc.gridx = 1;
        gbc.gridy = 4;

        gbc.gridwidth = 1;    
        panel.add(retour, gbc);

        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previousFrame.setVisible(true);
                modifieur.ajouterAttraction(
                        new Attraction(0,
                            modicNom.getText(),
                            modicDescription.getText(),
                            Modele.Trans.toDouble(modicPrix.getText()),
                            Modele.Trans.toInt(modicCapaciteMax.getText())
                        )
                );
            }
        });
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previousFrame.setVisible(true);
            }
        });

        setContentPane(panel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(220, 50));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 1, true));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
