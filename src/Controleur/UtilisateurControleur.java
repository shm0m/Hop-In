package Controleur;

import DAO.UtilisateurDAO;
import Modele.Utilisateur;
import java.util.List;

public class UtilisateurControleur {
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public void ajouterUtilisateur(Utilisateur u) {
        utilisateurDAO.ajouterUtilisateur(u);
    }

    public void afficherTousLesUtilisateurs() {
        List<Utilisateur> liste = utilisateurDAO.getAll();
        for (Utilisateur u : liste) {
            System.out.println(u.getId() + " - " + u.getNom() + " " + u.getPrenom());
        }
    }
}
