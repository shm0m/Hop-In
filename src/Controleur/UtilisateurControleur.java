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

    public Utilisateur trouverParEmail(String email) {
        UtilisateurDAO dao = new UtilisateurDAO();
        return dao.trouverParEmail(email);
    }



    public void afficherTousLesUtilisateurs() {
        List<Utilisateur> liste = utilisateurDAO.getAll();
        for (Utilisateur u : liste) {
            System.out.println(u.getId() + " - " + u.getNom() + " " + u.getPrenom());
        }
    }
}
