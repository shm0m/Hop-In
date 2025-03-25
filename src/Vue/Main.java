package Vue;

import Controleur.UtilisateurControleur;
import Modele.Admin;
import Modele.Utilisateur;

import Modele.Admin.*;

public class Main {
    public static void main(String[] args) {
        UtilisateurControleur controleur = new UtilisateurControleur();
        Utilisateur u = new Utilisateur(1, "fdsfdsfsdfsdfsd", "Toledfdsfsdano", "machin@mail.com", "123456789", "REGULIER", "CLIENT");
        controleur.ajouterUtilisateur(u);
        controleur.afficherTousLesUtilisateurs();
        Admin.changerAttraction(1,"Grand15","Voilà cest mieux ?",58);
    }
}
