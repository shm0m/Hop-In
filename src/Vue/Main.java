package Vue;

import Controleur.UtilisateurControleur;
import Modele.Utilisateur;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UtilisateurControleur controleur = new UtilisateurControleur();

        System.out.println("=== Bienvenue sur Hop'In ===");
        System.out.println("1. Se connecter");
        System.out.println("2. S'enregistrer");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.println("\n--- Connexion ---");
            System.out.print("Email : ");
            String email = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String mdp = scanner.nextLine();

            Utilisateur utilisateur = controleur.trouverParEmailEtMotDePasse(email, mdp);
            if (utilisateur != null) {
                System.out.println("Connexion réussie, bienvenue " + utilisateur.getPrenom() + " !");
            } else {
                System.out.println("Identifiants incorrects.");
            }

        } else if (choix == 2) {
            System.out.println("\n--- Enregistrement ---");

            System.out.print("Nom : ");
            String nom = scanner.nextLine();

            System.out.print("Prénom : ");
            String prenom = scanner.nextLine();

            System.out.print("Email : ");
            String email = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();

            System.out.print("Date de naissance (AAAA-MM-JJ) : ");
            String dateNaissance = scanner.nextLine();

            System.out.print("Type de membre (AUCUN / REGULIER / SENIOR / ENFANT) : ");
            String typeMembre = scanner.nextLine().toUpperCase();

            System.out.print("Rôle (CLIENT / ADMIN) : ");
            String role = scanner.nextLine().toUpperCase();

            Utilisateur u = new Utilisateur(0, nom, prenom, email, motDePasse, dateNaissance, typeMembre, role);
            controleur.ajouterUtilisateur(u);

            System.out.println("✅ Utilisateur enregistré !");
        }
        scanner.close();
    }
}
