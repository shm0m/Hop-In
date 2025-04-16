package DAO;

import java.sql.*;
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

    public void updateUtilisateur(Utilisateur u) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, date_naissance = ?, mot_de_passe = ? WHERE id = ?";
        try (Connection conn = gerant.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getDateNaissance());
            stmt.setString(5, u.getMotDePasse());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, date_naissance, type_membre, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMotDePasse());
            stmt.setString(5, u.getDateNaissance());
            stmt.setString(6,"AUCUN");
            stmt.setString(7, u.getRole());

            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }

}
