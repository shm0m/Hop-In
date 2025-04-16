package Vue.Page.ModAdmin;

import DAO.ModifAdminDAO;
import Modele.Attraction;

import javax.swing.*;
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
    private ModifAdminDAO modifieur;

    public CreRed(JFrame previousFrame){
        super("Ajout d'une réduction");
        this.modifieur=new ModifAdminDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes

        /*ind=new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(ind, gbc);
        mod= new JTextField("");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(mod, gbc);*/


    }
}
