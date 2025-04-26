package Modele;

import java.sql.Date;
import java.time.OffsetDateTime;

public class Reservation {
    private int idReservation;
    private Integer idUtilisateur; // Peut être null pour un invité
    private String mailUt;
    private int idAttraction;
    private int idCreneau;
    private Date dateReservation;
    private int nbPersonnes;
    private String statut;

    public Reservation() { }

    public Reservation(Integer idUtilisateur, String mailUt, int idAttraction, Date dateReservation,
                       int idCreneau, int nbPersonnes, String statut) {
        this.idUtilisateur = idUtilisateur;
        this.mailUt = mailUt;
        this.idAttraction = idAttraction;
        this.dateReservation = dateReservation;
        this.idCreneau = idCreneau;
        this.nbPersonnes = nbPersonnes;
        this.statut = statut;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMailUt() {
        return mailUt;
    }

    public void setMailUt(String mailUt) {
        this.mailUt = mailUt;
    }

    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public int getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(int idCreneau) {
        this.idCreneau = idCreneau;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getId_attraction() {
        return idAttraction;
    }

    public OffsetDateTime getDate_reservation() {
        return dateReservation.toLocalDate().atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
    }

    public Integer getId_creneau() {
        return idCreneau;
    }

    public int getNb_personnes() {
        return nbPersonnes;
    }

}
