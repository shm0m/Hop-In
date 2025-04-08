package DAO;

import java.sql.*;

public class AttractionDAO {
    private final Connection conn;

    public AttractionDAO() {
        this.conn = new GestionConnexion().getConnection();
    }

    public int trouverIdParNom(String nom) {
        String sql = "SELECT id_attraction FROM attraction WHERE nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id_attraction");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getPrixParNom(String nom) {
        String sql = "SELECT prix FROM attraction WHERE nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble("prix");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCapaciteMaxParNom(String nom) {
        String sql = "SELECT capacite_max FROM attraction WHERE nom = ?";
        try (Connection conn = new GestionConnexion().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("capacite_max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
