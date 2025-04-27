package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Modele.Client;
import Modele.Utilisateur;
import Modele.Admin;

public class UtilisateurDAO {
    private GestionConnexion gerant;
    public UtilisateurDAO() {
        gerant=new GestionConnexion();
    }

    public int updateUtilisateur(Utilisateur u) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, date_naissance = ?, mot_de_passe = ? WHERE id = ?";
        try (Connection conn = gerant.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getDateNaissance());
            stmt.setString(5, u.getMotDePasse());
            stmt.setInt(6, u.getId());
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (-1);
    }

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
            ps.setDate(3, Date.valueOf(date));
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


    public int ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, date_naissance, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMotDePasse());
            stmt.setString(5, u.getDateNaissance());
            stmt.setString(6, u.getRole());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
