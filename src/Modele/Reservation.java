package Modele;

public class Reservation {
    private int id;
    private int idUtilisateur;
    private int idAttraction;
    private String dateReservation;
    private int nbPersonnes;
    private double reductionAppliquee;
    private double prixTotal;
    private String statut;

    public Reservation(int id, int idUtilisateur, int idAttraction, String dateReservation, int nbPersonnes, double reductionAppliquee, double prixTotal, String statut) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idAttraction = idAttraction;
        this.dateReservation = dateReservation;
        this.nbPersonnes = nbPersonnes;
        this.reductionAppliquee = reductionAppliquee;
        this.prixTotal = prixTotal;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public double getReductionAppliquee() {
        return reductionAppliquee;
    }

    public void setReductionAppliquee(double reductionAppliquee) {
        this.reductionAppliquee = reductionAppliquee;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
