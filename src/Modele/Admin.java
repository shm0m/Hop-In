package Modele;
import DAO.GestionConnexion;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAO.*;


import java.sql.*;

public class Admin extends Utilisateur {

    public Admin(int id, String nom, String prenom, String email, String motDePasse, String typeMembre, String role) {
        super(id,nom,  prenom, email,  motDePasse,  typeMembre,  role);
    }


    public static void changerAttraction(int idAtt, String nom,String description, float prix){
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
        sql=sql+" where id_Attraction="+idAtt+";";
        System.out.println(sql);
        GestionConnexion gerant=new GestionConnexion();
        try {
            Connection conn =gerant.getConnection();
            try (Statement stmt = conn.createStatement()) {
                int rs = stmt.executeUpdate(sql);
            }
        }  catch (SQLException e) {
           e.printStackTrace();
       }
    }
}
