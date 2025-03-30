package Modele;

import java.time.LocalDate;
import java.time.Period;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String typeMembre;
    private String role;
    private String dateNaissance;

    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, String dateNaissance, String typeMembre, String role){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.typeMembre = typeMembre;
        this.role = role;
    }

    public Utilisateur(){

    }

    public int getAge() {
        LocalDate birth = LocalDate.parse(this.dateNaissance);
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTypeMembre() {
        return typeMembre;
    }
    public void setTypeMembre(String typeMembre) {
        this.typeMembre = typeMembre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
