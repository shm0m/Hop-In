package DAO;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttractionDAO {
    public int getCapaciteAttraction(int idAttraction) {
        int capacite = 0;
        String sql = "SELECT capacite_max FROM attraction WHERE id_attraction = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAttraction);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                capacite = rs.getInt("capacite_max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capacite;
    }

}
