package Modele;

public class Attraction {
    private int id_attraction;
    private String nom;
    private String description;
    private double prix;
    private int capacite_max;

    public Attraction(int id_attraction,String nom, String description,double prix,int capacite_max){
        this.id_attraction=id_attraction;
        this.nom=nom;
        this.description=description;
        this.prix=prix;
        this.capacite_max=capacite_max;
    }
    @Override
    public String toString(){
        return(this.nom);
    }

    public int get_id_attraction(){
        return(this.id_attraction);
    }
    public String get_id_attraction_Str(){
        return(this.id_attraction+"");
    }
    public String get_nom(){
        return(this.nom);
    }
    public String get_description(){
        return(this.description);
    }
    public double get_prix(){
        return(this.prix);
    }
    public int get_capacite_max(){
        return(this.capacite_max);
    }
    public String get_prix_Str(){
        return(this.prix+"");
    }
    public String get_capacite_max_Str(){
        return(this.capacite_max+"");
    }

}
