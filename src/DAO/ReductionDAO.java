package DAO;

import Modele.Reduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ReductionDAO {
    private GestionConnexion gerant;
    public ReductionDAO() {
        gerant=new GestionConnexion();
    }

    public int ajouterReduction(Reduction reduction){
        String sql = "INSERT INTO reduction (prc_reduction,age_min,age_max,date_min,date_max,nom) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, reduction.getprcRed());
            if(reduction.getageMin()!=0){
                stmt.setInt(2, reduction.getageMin());
            }
            else{
                stmt.setNull(2, Types.INTEGER);
            }

            if(reduction.getageMax()!=0){
                stmt.setInt(3, reduction.getageMax());
            }
            else{
                stmt.setNull(3, Types.INTEGER);
            }

            if(reduction.getdateMin().compareTo("null")!=0){
                stmt.setString(4, reduction.getdateMin());
            }
            else{
                stmt.setNull(4, Types.DATE);
            }

            if(reduction.getdateMax().compareTo("null")!=0){
                stmt.setString(5, reduction.getdateMax());
            }
            else{
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, reduction.getNom());
            System.out.println(stmt);
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }



}


