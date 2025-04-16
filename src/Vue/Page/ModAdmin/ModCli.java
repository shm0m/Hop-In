package Vue.Page.ModAdmin;

import DAO.ModifAdminDAO;
import DAO.UtilisateurDAO;
import DAO.UtilisateurchercherDAO;
import Modele.Attraction;
import Modele.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModCli extends JFrame {
    private JLabel  indic_nom;
    private JTextField mod_nom;
    private JLabel  indic_prenom;
    private JTextField mod_prenom;
    private JLabel  indic_mot_de_passe;
    private JTextField mod_mot_de_passe;
    private JLabel  indic_email;
    private JTextField mod_email;
    private JLabel  indic_date_naissance;
    private JTextField mod_date_naissance;
    private JLabel  indic_choixCli;
    private JComboBox men_choixCli;
    private JLabel indication;

    private JButton actualiser;
    private JButton enregistrer;
    private JButton quitter;
    private UtilisateurDAO modifieur;
    private UtilisateurchercherDAO getter;
        private JFrame previousFrame;


    public ModCli(JFrame previousFrame) {
        super("Modification clients");
        this.modifieur = new UtilisateurDAO();
        this.getter =new UtilisateurchercherDAO();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        this.previousFrame=previousFrame;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        // Ajouter les boutons avec des contraintes
        indic_choixCli=new JLabel("Choisir client à modifier");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(indic_choixCli, gbc);

        ArrayList<Client> clients=getter.getClis();
        men_choixCli = new JComboBox<>(clients.toArray(new Client[0]));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ce bouton s'étend sur deux colonnes
        add(men_choixCli, gbc);


        mod_nom=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        add(mod_nom, gbc);

        indic_nom=new JLabel("nom");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(indic_nom, gbc);


        mod_prenom=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        add(mod_prenom, gbc);

        indic_prenom=new JLabel("prenom");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(indic_prenom, gbc);


        mod_email=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        add(mod_email, gbc);

        indic_email=new JLabel("email");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(indic_email, gbc);


        mod_mot_de_passe=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        add(mod_mot_de_passe, gbc);

        indic_mot_de_passe=new JLabel("mot_de_passe");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(indic_mot_de_passe, gbc);


        mod_date_naissance=new JTextField("Champ vide                                                                                          ");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        add(mod_date_naissance, gbc);

        indic_date_naissance=new JLabel("Date de naissance");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(indic_date_naissance, gbc);

        indication=new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(indication, gbc);

        actualiser=new JButton("actualiser");
        gbc.gridx=0;
        gbc.gridy=7;
        gbc.gridwidth = 1;
        add(actualiser,gbc);

        enregistrer=new JButton("enregistrer");
        gbc.gridx=1;
        gbc.gridy=7;
        gbc.gridwidth = 1;
        add(enregistrer,gbc);

        quitter=new JButton("quitter");
        gbc.gridx=2;
        gbc.gridy=7;
        gbc.gridwidth = 1;
        add(quitter,gbc);

        actualiser.addActionListener(e->{
            Client c=getCli(clients,men_choixCli.getSelectedItem().toString());
            mod_nom.setText(c.getNom());
            mod_prenom.setText(c.getPrenom());
            mod_mot_de_passe.setText(c.getMotDePasse());
            mod_email.setText(c.getEmail());
            mod_date_naissance.setText(c.getDateNaissance());
        });

        enregistrer.addActionListener(e->{
            int id=getCli(clients,men_choixCli.getSelectedItem().toString()).getId();
            int resultat=modifieur.updateUtilisateur(new Client(
                    id,
                    mod_nom.getText(),
                    mod_prenom.getText(),
                    mod_email.getText(),
                    mod_mot_de_passe.getText(),
                    mod_date_naissance.getText()
            ));
            if(resultat==1){
                previousFrame.setVisible(true);
                setVisible(false);

            }
            else{
                indication.setText("Problème lors de la modification "+resultat);
            }
        });

        quitter.addActionListener(e->{
            previousFrame.setVisible(true);
            setVisible(false);
        });


        setSize(1000,500);
        setVisible(true);
    }

    private Client getCli(ArrayList<Client> clients,String nom){
        for(Client client: clients){
            if(client.toString().compareTo(nom)==0){
                return(client);
            }
        }
        return(new Client());
    }




}
