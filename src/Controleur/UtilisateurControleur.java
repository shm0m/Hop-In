package Controleur;

import DAO.UtilisateurDAO;
import Modele.Utilisateur;
import java.util.List;

public class UtilisateurControleur {
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public void ajouterUtilisateur(Utilisateur u) {
        utilisateurDAO.ajouterUtilisateur(u);
    }

    public Utilisateur trouverParEmailEtMotDePasse(String email, String mdp) {
        return utilisateurDAO.trouverParEmailEtMotDePasse(email, mdp);


    }
    public void modifierUtilisateur(Utilisateur utilisateur) {
        // Créez une instance du DAO et appelez la méthode update
        UtilisateurDAO dao = new UtilisateurDAO();
        dao.updateUtilisateur(utilisateur);
    }

}
