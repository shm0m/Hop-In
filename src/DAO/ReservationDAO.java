package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import Modele.Reservation;
import java.util.ArrayList;
import java.sql.Statement;



public class ReservationDAO {



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
            return new HashMap<>(); // ✅ Correction ici
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
            return new HashMap<>(); // ✅ Correction ici aussi
        }
    }

    public ArrayList<Reservation> getReservationsByClient(int idClient) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM Reservation WHERE id_utilisateur = ?";

            try (Connection conn = ConnectionProvider.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int clientId = rs.getInt("id_utilisateur");
                int idAttraction = rs.getInt("id_attraction");
                Date dateReservation = rs.getDate("date_reservation");
                String mailUt = rs.getString("mailUt");
                int nbPersonnes = rs.getInt("nb_personnes");
                int idCreneau = rs.getInt("id_creneau");
                String statut = rs.getString("statut");

                Reservation res = new Reservation(clientId, mailUt,idAttraction, dateReservation, idCreneau, nbPersonnes,  statut);

                reservations.add(res);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

}
