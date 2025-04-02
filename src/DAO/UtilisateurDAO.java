package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modele.Utilisateur;

public class UtilisateurDAO {
    private GestionConnexion gerant;
    public UtilisateurDAO() {
        gerant=new GestionConnexion();
    }

    public void ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, mot_de_passe, date_naissance, type_membre, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMotDePasse());
            stmt.setString(5, u.getDateNaissance());
            stmt.setString(6, u.getTypeMembre());
            stmt.setString(7, u.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur trouverParEmailEtMotDePasse(String email, String mdp) {
        String sql = "SELECT * FROM Utilisateur WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("date_naissance"),
                        rs.getString("type_membre"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
