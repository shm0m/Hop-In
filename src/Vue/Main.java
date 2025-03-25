package Vue;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UtilisateurControleur controleur = new UtilisateurControleur();

        System.out.println("=== Création d'un nouvel utilisateur ===");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        System.out.print("Type de membre (AUCUN / REGULIER / SENIOR / ENFANT) : ");
        String typeMembre = scanner.nextLine().toUpperCase();

        System.out.print("Rôle (CLIENT / ADMIN) : ");
        String role = scanner.nextLine().toUpperCase();

        Utilisateur u = new Utilisateur(0, nom, prenom, email, motDePasse, typeMembre, role);
        controleur.ajouterUtilisateur(u);

        System.out.println("\nUtilisateur ajouté avec succès ! 🎉");
        System.out.println("\n📋 Liste des utilisateurs enregistrés :");
        controleur.afficherTousLesUtilisateurs();

        scanner.close();
    }
}
