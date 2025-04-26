package DAO;

import Modele.Client;
import Modele.Reduction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ReductionChercherDAO {
    private GestionConnexion gerant;
    public ReductionChercherDAO() {
        gerant=new GestionConnexion();
    }

    public ArrayList<Reduction> getReducs() {
        String sql = "select * from reduction;";

        try {
            Connection conn = gerant.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery(sql);


            ArrayList<Reduction> reductions = new ArrayList();
            while (resultat.next()) {
                int id = resultat.getInt("id_reduction");
                double prc_reduction=resultat.getFloat("prc_reduction");
                int age_min= resultat.getInt("age_min");
                int age_max= resultat.getInt("age_max");
                String date_min=resultat.getString("date_min");
                String date_max=resultat.getString("date_max");
                String nom=resultat.getString("nom");
                int nb_visites_min=resultat.getInt("nb_visites_min");

                reductions.add(new Reduction(id,prc_reduction,age_min,age_max,date_min,date_max,nom,nb_visites_min));
            }
            return (reductions);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(new ArrayList<Reduction>());
    }

    public Reduction getReductionApplicable(int age,int nbResa) {
        LocalDate dateAjd= LocalDate.now();


        ArrayList<Reduction> reductionsApplicables=new ArrayList<>();
        for (Reduction r : getReducs()) {
            boolean nbResavalide=true;
            boolean dateMaxValide=true;
            boolean dateMinValide=true;
            if(!(r.getdateMax()==null)){
                dateMaxValide=dateAjd.isBefore(LocalDate.parse(r.getdateMax())) ;
            }
            if(!(r.getdateMin()==null)){
                dateMinValide=dateAjd.isAfter(LocalDate.parse(r.getdateMin()));
            }
            if(r.getMinVis()!=0){
                nbResavalide=nbResa>r.getMinVis();
            }

            boolean minValide = (r.getageMin() == 0 || age >= r.getageMin());
            boolean maxValide = (r.getageMax() == 0 || age <= r.getageMax());
            if (minValide && maxValide && dateMinValide && dateMaxValide && nbResavalide) {
                reductionsApplicables.add(r);
            }
        }
        if (reductionsApplicables.isEmpty()){
            return null;
        }
        Reduction renvoi=reductionsApplicables.get(0);
        for(Reduction r: reductionsApplicables){
            if (r.getprcRed()>renvoi.getprcRed()){
                renvoi=r;
            }
        }
        return(renvoi);
    }


}
