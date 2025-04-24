package DAO;

import java.sql.*;

public class ReductionAppliqueeDAO {
    public void lierReductionPaiement(int idReduction, int idPaiement) {
        String sql = "INSERT INTO reduction_appliquee (id_reduction, id_paiement) VALUES (?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReduction);
            ps.setInt(2, idPaiement);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
