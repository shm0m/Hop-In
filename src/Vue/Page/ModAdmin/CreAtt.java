package Vue.Page.ModAdmin;

import DAO.AttractionDAO;
import DAO.ModifAdminDAO;
import Modele.Attraction;

import javax.swing.*;
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

        modicNom=new JTextField("Nom de l'attraction");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes


        indicNom=new JLabel("Nom de l'attraction");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(indicNom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(modicNom, gbc);

        indicDescription=new JLabel("Description: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(indicDescription, gbc);

        modicDescription=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(modicDescription, gbc);

        indicPrix=new JLabel("Prix");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(indicPrix, gbc);

        modicPrix=new JTextField("00.00");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(modicPrix, gbc);

        indicCapaciteMax=new JLabel("Capacité maximum");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(indicCapaciteMax, gbc);

        modicCapaciteMax=new JTextField("00");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(modicCapaciteMax, gbc);

        ajouter =new JButton("Ajouter l'attraction");
        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 1; // Limiter à une seule colonne
        add(ajouter, gbc);

        retour =new JButton("Retour");
        gbc.gridx = 1;
        gbc.gridy = 4;

        gbc.gridwidth = 1; // Limiter à une seule colonne
        add(retour, gbc);

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

        setSize(1000,500);
        setVisible(true);

    }
}
