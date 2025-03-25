package DAO;

import Modele.Reservation;

import java.sql.*;

public class ReservationDAO {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hop_in", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ajouterReservation(Reservation r) {
        String sql = "INSERT INTO Reservation (id, id_attraction, date_reservation, nb_personnes, reduction_appliquee, prix_total, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdUtilisateur());
            stmt.setInt(2, r.getIdAttraction());
            stmt.setString(3, r.getDateReservation());
            stmt.setInt(4, r.getNbPersonnes());
            stmt.setDouble(5, r.getReductionAppliquee());
            stmt.setDouble(6, r.getPrixTotal());
            stmt.setString(7, r.getStatut());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
