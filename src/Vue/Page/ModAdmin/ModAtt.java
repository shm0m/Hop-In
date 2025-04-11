package Vue.Page.ModAdmin;

import DAO.ModifAdminDAO;
import Modele.Attraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModAtt extends JFrame {
    private JLabel indicNom;
    private JComboBox men_attractions;
    private JLabel indicDescription;
    private JTextField modicDescription;
    private JLabel indicPrix;
    private JTextField modicPrix;
    private JLabel indicCapaciteMax;
    private JTextField modicCapaciteMax;
    private ModifAdminDAO modifieur;
    private JLabel indicId;
    private JLabel indicQuelId;

    private JButton afficherProp;
    private JButton modifier;


    public ModAtt(){
        super("Vue Administrateur");
        this.modifieur=new ModifAdminDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ArrayList<Attraction> attractions = modifieur.getAtt();
        men_attractions = new JComboBox<>(attractions.toArray(new Attraction[0]));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes
        indicNom=new JLabel("Choisir attraction à modifier");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(indicNom, gbc);


        men_attractions = new JComboBox<>(attractions.toArray(new Attraction[0]));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(men_attractions, gbc);

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

        indicId=new JLabel("Id de l'attraction");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(indicId, gbc);

        indicQuelId=new JLabel("0");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(indicQuelId, gbc);

        afficherProp=new JButton("Afficher les propriétés actuelles");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(afficherProp, gbc);

        modifier =new JButton("Appliquer les changements");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(modifier, gbc);

        afficherProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attraction a=getAtt(men_attractions.getSelectedItem().toString(),attractions);
                modicCapaciteMax.setText(a.get_capacite_max_Str());
                modicDescription.setText(a.get_description());
                modicPrix.setText(a.get_prix_Str());
                indicQuelId.setText(a.get_id_attraction_Str());
                modicDescription.getText();
            }
        });

        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifieur.changerAttraction(Modele.Trans.toInt(
                        indicQuelId.getText()),
                        men_attractions.getSelectedItem().toString(),
                        modicDescription.getText(),
                        Modele.Trans.toDouble(modicPrix.getText()),
                        Modele.Trans.toInt(modicCapaciteMax.getText())
                        );
            }
        });

        setSize(1000,500);
        setVisible(true);

    }
    private Attraction getAtt(String nom,ArrayList<Attraction> attractions){
        for(Attraction a: attractions ){
            if(a.get_nom().compareTo(nom)==0){
                return(a);
            }
        }
        return(new Attraction(0,"ERREUR","ERREUR", 0,0));
    }

}
