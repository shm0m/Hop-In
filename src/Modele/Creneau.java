package Modele;

import java.sql.Time;

public class Creneau {
    private int idCreneau;
    private Time heureDebut;
    private Time heureFin;

    public Creneau() { }

    public Creneau(int idCreneau, Time heureDebut, Time heureFin) {
        this.idCreneau = idCreneau;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public int getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(int idCreneau) {
        this.idCreneau = idCreneau;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "Creneau{" + "idCreneau=" + idCreneau + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }
}
