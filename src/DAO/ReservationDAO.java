package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReservationDAO {

    public boolean reserve(int idUser, int idAttraction, int idCreneau) {
        String sql = "INSERT INTO reservation (id_utilisateur, id_attraction, date_reservation, id_creneau, nb_personnes, statut) "
                + "VALUES (?, ?, CURDATE(), ?, 1, 'CONFIRMEE')";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Si l'utilisateur n'est pas identifié (par exemple, invité),
            // on passe 0 ou une valeur équivalente pour indiquer NULL.
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

    public Map<Integer, Integer> getNbPersonnesParCreneau(int idAttraction, LocalDate date) {

        String sql = "SELECT id_creneau, SUM(nb_personnes) AS total_personnes " +
                "FROM reservation " +
                "WHERE id_attraction = ? AND date_reservation = ? " +
                "GROUP BY id_creneau";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAttraction);
            ps.setDate(2, java.sql.Date.valueOf(date));
            var rs = ps.executeQuery();
            Map<Integer, Integer> resultMap = new HashMap<>();
            while (rs.next()) {
                resultMap.put(rs.getInt("id_creneau"), rs.getInt("total_personnes"));
            }
            return resultMap;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nombre de personnes par créneau:");
            e.printStackTrace();
            return null;
        }

    }

    public Map<Integer, Time> getHeuresCreneauxDepuisBase() {
        String sql = "SELECT id_creneau, heure FROM creneau";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            Map<Integer, Time> resultMap = new HashMap<>();
            while (rs.next()) {
                resultMap.put(rs.getInt("id_creneau"), rs.getTime("heure"));
            }
            return resultMap;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des heures de créneaux:");
            e.printStackTrace();
            return null;
        }
    }
}
