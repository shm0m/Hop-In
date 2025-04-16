package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class ReservationmodifDAO {
    public boolean reserve(int idUser, int idAttraction, int idCreneau, LocalDate date) {
        String sql = "INSERT INTO reservation (id_utilisateur, id_attraction, date_reservation, id_creneau, nb_personnes, statut) " +
                "VALUES (?, ?, ?, ?, 1, 'CONFIRMEE')";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (idUser != 0) {
                ps.setInt(1, idUser);
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setInt(2, idAttraction);
            ps.setDate(3, Date.valueOf(date)); // <-- date passée ici
            ps.setInt(4, idCreneau);

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
