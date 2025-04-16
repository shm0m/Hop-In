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


    public String getDescriptionById(int idAttraction) {
        String description = "";
        String sql = "SELECT description FROM attraction WHERE id_attraction = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAttraction);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                description = rs.getString("description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }


}

