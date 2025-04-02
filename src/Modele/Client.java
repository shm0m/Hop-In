package Modele;

import java.time.LocalDate;
import java.time.Period;

public class Client extends Utilisateur{
    private String dateNaissance;

    public Client(int id, String nom, String prenom, String email, String motDePasse,String dateNaissance){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
    }


    public int getAge() {
        LocalDate birth = LocalDate.parse(this.dateNaissance);
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    @Override
    public String getRole(){
        return("Client");
    }
}
