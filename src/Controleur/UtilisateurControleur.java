package Controleur;

import DAO.UtilisateurDAO;
import Modele.Utilisateur;
import java.util.List;
import DAO.UtilisateurchercherDAO;
public class UtilisateurControleur {
    private UtilisateurchercherDAO utilisateurDAO = new UtilisateurchercherDAO();
    private UtilisateurDAO utilisateur = new UtilisateurDAO();

    public int ajouterUtilisateur(Utilisateur u) {
       return( utilisateur.ajouterUtilisateur(u));
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