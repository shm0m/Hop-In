package Modele;
import DAO.GestionConnexion;
import DAO.ModifAdminDAO;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAO.*;
import DAO.ModifAdminDAO.*;


import java.sql.*;

public class Admin extends Utilisateur{
    ModifAdminDAO modifieur;
    public Admin(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;

        this.modifieur=new ModifAdminDAO();
    }
    @Override
    public String getRole(){
        return("Admin");
    }

    public void changerAttraction(int idAtt, String nom,String description, float prix){
        this.modifieur.changerAttraction(idAtt, nom,description,prix);
    }

}
