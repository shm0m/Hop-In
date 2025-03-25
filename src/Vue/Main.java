package Vue;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;

public class Main {
    public static void main(String[] args) {
        UtilisateurControleur controleur = new UtilisateurControleur();

        Utilisateur u = new Utilisateur(1, "Clément", "Toledano", "clement@mail.com", "123456789", "REGULIER", "CLIENT");
        controleur.ajouterUtilisateur(u);

        controleur.afficherTousLesUtilisateurs();
    }
}
