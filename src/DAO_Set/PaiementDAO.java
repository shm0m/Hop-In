package DAO_Set;

import DAO_Get.ConnectionProvider;

import java.sql.*;

public class PaiementDAO {
    public int enregistrerPaiement(int idReservation, float montant) {
        String sql = "INSERT INTO paiement (id_reservation, methode, montant, statut) VALUES (?, 'CARTE', ?, 'SUCCES')";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idReservation);
            ps.setFloat(2, montant);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
