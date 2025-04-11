package Vue.Page;
import DAO.ModifAdminDAO;
import Modele.Admin;
import Modele.Utilisateur;
import Vue.Page.ModAdmin.ModAtt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;


public class PageGestionAdmin extends JFrame {
    private JButton ajAttraction;
    private JButton modAttraction;
    private JButton ajReductions;
    private JButton modReduction;
    private JButton modCLi;
    private ModifAdminDAO modifieur;
    private JButton quitter;
    private JFrame previousFrame;

    public PageGestionAdmin(JFrame previousFrame){

        super("Vue Administrateur");
        this.modifieur=new ModifAdminDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.previousFrame=previousFrame;

        ajAttraction= new JButton("Ajouter Attraction");
        modAttraction= new JButton("Modifier Attraction");
        ajReductions= new JButton("Ajouter Reductions");
        modReduction= new JButton("Modifier Reduction");
        modCLi= new JButton("Modifier Client");
        quitter=new JButton("Quitter");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(ajAttraction, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(modAttraction, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ajReductions, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(modReduction, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(modCLi, gbc);

        gbc.gridx =1;
        gbc.gridy = 2;
        add(quitter, gbc);


        ajAttraction.addActionListener(e -> {
            new Vue.Page.ModAdmin.CreAtt(this);
            setVisible(false);
        });

        modAttraction.addActionListener(e -> {
            new Vue.Page.ModAdmin.ModAtt(this);
            setVisible(false);
        });
        setSize(500,500);
        setVisible(true);

        quitter.addActionListener(e -> {
            this.previousFrame.setVisible(true);
            setVisible(false);
        });
    }
    public static void main(String args[]){
        new PageGestionAdmin(new JFrame());
    }
}
