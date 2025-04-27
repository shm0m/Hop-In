package DAO_Get;

import java.sql.*;

public class CreneauDAO {

    public String getHoraireById(int idCreneau) {
        String horaire = null;
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT heure FROM creneau WHERE id_creneau = ?")) {
            stmt.setInt(1, idCreneau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                horaire = rs.getString("heure");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horaire;
    }
}
