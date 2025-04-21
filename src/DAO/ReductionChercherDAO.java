package DAO;

import Modele.Client;
import Modele.Reduction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

                reductions.add(new Reduction(id,prc_reduction,age_min,age_max,date_min,date_max,nom));
            }
            return (reductions);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(new ArrayList<Reduction>());
    }

}
