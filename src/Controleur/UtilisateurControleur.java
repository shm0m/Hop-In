package Controleur;

import DAO_Set.UtilisateurDAO;
import Modele.Utilisateur;
import DAO_Get.UtilisateurchercherDAO;
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
        UtilisateurDAO dao = new UtilisateurDAO();
        dao.updateUtilisateur(utilisateur);
    }

}