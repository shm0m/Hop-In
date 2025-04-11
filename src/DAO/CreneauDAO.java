package DAO;

import Modele.Creneau;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreneauDAO {
    public List<Creneau> findAllCreneaux() {
        List<Creneau> creneaux = new ArrayList<>();
        String sql = "SELECT id_creneau, heure FROM creneau ORDER BY heure";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Creneau c = new Creneau();
                c.setIdCreneau(rs.getInt("id_creneau"));
                Time heureDebut = rs.getTime("heure");
                c.setHeureDebut(heureDebut);
                c.setHeureFin(Time.valueOf(heureDebut.toLocalTime().plusHours(1)));
                creneaux.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des créneaux :");
            e.printStackTrace();
        }
        return creneaux;
    }

    public Map<Integer, Time> getHeuresCreneauxDepuisBase() {
        Map<Integer, Time> creneaux = new LinkedHashMap<>();
        String sql = "SELECT id_creneau, heure FROM creneau ORDER BY heure";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_creneau");
                Time heure = rs.getTime("heure");
                creneaux.put(id, heure);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des heures de créneaux :");
            e.printStackTrace();
        }

        return creneaux;
    }

}
