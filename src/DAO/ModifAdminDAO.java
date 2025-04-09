package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
