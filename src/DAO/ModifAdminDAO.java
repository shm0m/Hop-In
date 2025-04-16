package DAO;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import Modele.Attraction;
import Modele.Utilisateur;

public class ModifAdminDAO {
    GestionConnexion gerant;

    public ModifAdminDAO(){
        gerant=new GestionConnexion();
    }

    public void changerAttraction(int idAtt, String nom,String description, double prix,int capaMax){
        String sql = "Update attraction set ";
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
        if(capaMax!=0){
            if(!(nom.isEmpty()) || !(description.isEmpty())|| !(prix!=0.0)){
                sql=sql+", ";
            }
            sql=sql+"capacite_max="+capaMax;
        }
        sql=sql+" where id_attraction="+idAtt+";" ;
        System.out.println(sql);

        try {
            Connection conn =gerant.getConnection();
            System.out.println(sql);
            try (Statement stmt = conn.createStatement()) {
                int rs = stmt.executeUpdate(sql);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delAtt(int idAtt){
        String sql = "DELETE FROM attraction WHERE id_attraction="+idAtt+";";
        try {
            Connection conn =gerant.getConnection();
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void ajouterAttraction(Attraction a) {
        String sql = "INSERT INTO attraction (nom, description, prix, capacite_max) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.get_nom());
            stmt.setString(2, a.get_description());
            stmt.setString(3, a.get_prix_Str());
            stmt.setString(4, a.get_capacite_max_Str());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public ArrayList<Attraction> getAtts(){
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

