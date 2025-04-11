package Modele;

public class Attraction {
    public int id_attraction;
    public String nom;
    public String description;
    public float prix;
    public int capacite_max;

    public Attraction(int id_attraction,String nom, String description,float prix,int capacite_max){
        this.id_attraction=id_attraction;
        this.nom=nom;
        this.description=description;
        this.prix=prix;
        this.capacite_max=capacite_max;
    }
}
