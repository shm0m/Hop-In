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

    public PageGestionAdmin(){

        super("Vue Administrateur");
        this.modifieur=new ModifAdminDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        ajAttraction= new JButton("Ajouter Attraction");
        modAttraction= new JButton("Modifier Attraction");
        ajReductions= new JButton("Ajouter Reductions");
        modReduction= new JButton("Modifier Reduction");
        modCLi= new JButton("Modifier Client");

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

        modAttraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModAtt();

            }
        });

        setSize(500,500);
        setVisible(true);
    }
    public static void main(String args[]){
        new PageGestionAdmin();
    }
}
