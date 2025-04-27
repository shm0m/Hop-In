package DAO_Set;

import DAO_Get.GestionConnexion;
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
        String sql = "INSERT INTO reduction (prc_reduction,age_min,age_max,date_min,date_max,nom,nb_visites_min) VALUES (?, ?, ? , ? , ? , ? , ?)";
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

            if(reduction.getdateMin().compareTo("null")!=0  && reduction.getdateMin().compareTo("")!=0){
                stmt.setString(4, reduction.getdateMin());
            }
            else{
                stmt.setNull(4, Types.DATE);
            }

            if(reduction.getdateMax().compareTo("null")!=0 &&  reduction.getdateMin().compareTo("")!=0){
                stmt.setString(5, reduction.getdateMax());
            }
            else{
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, reduction.getNom());

            if(reduction.getMinVis()!=0){
                stmt.setInt(7, reduction.getMinVis());
            }
            else{
                stmt.setNull(7, Types.INTEGER);
            }
            System.out.println(stmt);
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }

    //wString sql = "UPDATE utilisateur age_min= ?, age_min = ?, prc_reduction= ?,date_min = ?, date_max = ? WHERE id = ?";

    public int updateReduc(Reduction reduction){
        String sql = "UPDATE reduction set age_min= ?, age_max = ?, prc_reduction= ?,date_min = ?, date_max = ?, nb_visites_min= ? WHERE id_reduction = ?";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if(reduction.getageMin()!=0){
                stmt.setInt(1, reduction.getageMin());
            }
            else{
                stmt.setNull(1, Types.INTEGER);
            }

            if(reduction.getageMax()!=0){
                stmt.setInt(2, reduction.getageMax());
            }
            else{
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setDouble(3,reduction.getprcRed());

            if(reduction.getdateMin().compareTo("null")!=0 && reduction.getdateMin().compareTo("")!=0){
                stmt.setString(4, reduction.getdateMin());
            }
            else{
                stmt.setNull(4, Types.DATE);
            }

            if(reduction.getdateMax().compareTo("null")!=0 && reduction.getdateMin().compareTo("")!=0){
                stmt.setString(5, reduction.getdateMax());
            }
            else{
                stmt.setNull(5, Types.DATE);
            }
            if(reduction.getMinVis()!=0){
                stmt.setInt(6, reduction.getMinVis());
            }
            else{
                stmt.setNull(6, Types.INTEGER);
            }

            stmt.setInt(7,reduction.getid());
            System.out.println(stmt);
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }
    public int supprimerReduc(int id){
        String sql = "delete from reduction WHERE id_reduction = ?";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            System.out.println(stmt);
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }



}


