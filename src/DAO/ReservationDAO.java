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
        Map<Integer, Integer> map = new HashMap<>();
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT id_creneau, SUM(nb_personnes) AS total " +
                             "FROM reservation " +
                             "WHERE id_attraction = ? AND date_reservation = ? AND statut = 'CONFIRMÉE' " +
                             "GROUP BY id_creneau"
             )) {
            ps.setInt(1, idAttraction);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("id_creneau"), rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean annulerReservation(int idReservation) {
        System.out.println("Suppression reservation ID : " + idReservation);
        try (Connection conn = ConnectionProvider.getConnection()) {
            String query = "UPDATE reservation SET statut = 'ANNULEE' WHERE id_reservation = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idReservation);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Nombre de lignes affectées : " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Exception SQL rencontrée !");
            e.printStackTrace();
            return false;
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
            return new HashMap<>();
        }
    }

    public ArrayList<Reservation> getReservationsByClient(int idClient) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservation WHERE id_utilisateur = ?";

            try (Connection conn = ConnectionProvider.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int idReservation = rs.getInt("id_reservation");
                    int clientId = rs.getInt("id_utilisateur");
                    int idAttraction = rs.getInt("id_attraction");
                    Date dateReservation = rs.getDate("date_reservation");
                    String mailUt = rs.getString("mailUt");
                    int nbPersonnes = rs.getInt("nb_personnes");
                    int idCreneau = rs.getInt("id_creneau");
                    String statut = rs.getString("statut");

                    Reservation res = new Reservation(clientId, mailUt, idAttraction, dateReservation, idCreneau, nbPersonnes, statut);
                    res.setIdReservation(idReservation);

                    reservations.add(res);
                }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
    public static int getDernierIdReservation() {
        String sql = "SELECT MAX(id_reservation) FROM reservation";
        try (Connection conn = ConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
