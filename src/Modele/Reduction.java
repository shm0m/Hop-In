package Modele;

public class Reduction {
    private int id;
    private double prcRed;
    private int ageMin;
    private int ageMax;
    private String dateMin;
    private String dateMax;
    private String nom;
    private int nb_visites_min;

    public Reduction(int id, double prcRed,int ageMin,int ageMax,String dateMin,String dateMax,String nom,int nb_visites_min) {
        this.id = id;
        this.prcRed = prcRed;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
        this.nom=nom;

        this.nb_visites_min=nb_visites_min;
    }
    public Reduction() {
        this.id = 0;
        this.prcRed = 0;
        this.ageMin = 0;
        this.ageMax = 0;
        this.dateMin = "err";
        this.dateMax = "err";
        this.nom="err";
        nb_visites_min=0;
    }
    public int getid(){
        return(this.id);
    }
    public double getprcRed(){
        return(this.prcRed);
    }
    public int getageMin(){
        return(this.ageMin);
    }
    public int getageMax(){
        return(this.ageMax);
    }
    public String  getdateMin(){
        return(this.dateMin);
    }
    public String  getdateMax(){
        return(this.dateMax);
    }
    public String getNom(){
        return(this.nom);
    }
    public int getMinVis(){
        return(this.nb_visites_min);
    }

    @Override
    public String toString(){
        return(id+": "+this.nom);
    }

}
