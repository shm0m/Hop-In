package DAO;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modele.Attraction;

public class ModifAdminDAO {
    GestionConnexion gerant;

    public ModifAdminDAO(){
        gerant=new GestionConnexion();
    }

    public void changerAttraction(int idAtt, String nom,String description, float prix){
        String sql = "Update Attraction set ";
        if(!(nom.isEmpty())){
            sql=sql+"nom='"+nom+"' ";
        }
        if(!(description.isEmpty())){
            if(!(nom.isEmpty())){
                sql=sql+", ";
            }
            sql=sql+" description='"+description+"' ";
        }
        if(prix!=0.0){
            if(!(nom.isEmpty()) || !(description.isEmpty())){
                sql=sql+", ";
            }
            sql=sql+"prix="+prix;
        }
        sql=sql+" where id_Attraction="+idAtt+";" ;
        System.out.println(sql);

        try {
            Connection conn =gerant.getConnection();
            try (Statement stmt = conn.createStatement()) {
                int rs = stmt.executeUpdate(sql);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Attraction> getAtt(){
        String sql = "SELECT * FROM attraction";

        try {
            Connection conn =gerant.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery(sql);


            ArrayList<Attraction> attractions = new ArrayList();
            while (resultat.next()) {
                int id_attraction = resultat.getInt("id_attraction");
                String nom = resultat.getString("nom");
                String description = resultat.getString("description");
                float prix = resultat.getFloat("prix");
                int capacite_max = resultat.getInt("capacite_max");

                attractions.add(new Attraction(id_attraction, nom, description, prix, capacite_max));
            }
            return(attractions);



        }  catch (SQLException e) {
            e.printStackTrace();
        }

    return(new ArrayList<Attraction>());
    }
}
