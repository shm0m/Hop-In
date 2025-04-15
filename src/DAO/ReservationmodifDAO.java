package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ReservationmodifDAO {
    public boolean reserve(int idUser, int idAttraction, int idCreneau) {
        String sql = "INSERT INTO reservation (id_utilisateur, id_attraction, date_reservation, id_creneau, nb_personnes, statut) " +
                "VALUES (?, ?, CURDATE(), ?, 1, 'CONFIRMEE')";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (idUser != 0) {
                ps.setInt(1, idUser);
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setInt(2, idAttraction);
            ps.setInt(3, idCreneau);

            int affectedRows = ps.executeUpdate();
            System.out.println("Nombre de lignes insérées : " + affectedRows);
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de la réservation simplifiée :");
            e.printStackTrace();
            return false;
        }
    }
}
