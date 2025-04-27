package Modele;
import DAO_Set.AttractionDAO;

public class Admin extends Utilisateur{
    AttractionDAO modifieur;
    public Admin(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;

        this.modifieur=new AttractionDAO();
    }
    public Admin(Utilisateur u){
        this.nom=u.getNom();
        this.prenom = u.getPrenom();
        this.email = u.getEmail();
        this.motDePasse = u.getMotDePasse();

    }
    @Override
    public String getRole(){
        return("Admin");
    }

    public void changerAttraction(int idAtt, String nom,String description, float prix,int capaMax){
        this.modifieur.changerAttraction(idAtt, nom,description,prix,capaMax);
    }

}
