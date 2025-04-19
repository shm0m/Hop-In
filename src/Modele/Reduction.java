package Modele;

public class Reduction {
    private int id;
    private double prcRed;
    private int ageMin;
    private int ageMax;
    private String dateMin;
    private String dateMax;
    private String nom;

    public Reduction(int id, double prcRed,int ageMin,int ageMax,String dateMin,String dateMax,String nom) {
        this.id = id;
        this.prcRed = prcRed;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
        this.nom=nom;
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

}
