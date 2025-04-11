package Vue.Page;
import Modele.Admin;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;


public class PageGestionAdmin extends JFrame {
    Admin uti;
    JButton ajAttraction;
    JButton modAttraction;
    JButton ajReductions;
    JButton modReduction;
    JButton modCLi;

    public PageGestionAdmin(Admin u){
        super("Vue Administrateur");
        this.uti=u;
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
        gbc.gridwidth = 1; // Ce bouton s'étend sur deux colonnes
        add(modCLi, gbc);

        setSize(500,500);
        setVisible(true);
    }
}
