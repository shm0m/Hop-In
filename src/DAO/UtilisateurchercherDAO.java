package DAO;

import Modele.Admin;
import Modele.Client;
import Modele.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurchercherDAO {
    private GestionConnexion gerant;
    public UtilisateurchercherDAO() {
        gerant=new GestionConnexion();
    }


    public Utilisateur trouverParEmailEtMotDePasse(String email, String mdp) {
        String sql = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ? ";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();
            System.out.println(stmt);
            if (rs.next()) {
                String role=rs.getString("role");
                if(role.equals("CLIENT")){
                    return new Client(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("mot_de_passe"),
                            rs.getString("date_naissance")
                    );
                }
                else{
                    return new Admin(
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("mot_de_passe")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur WHERE role='CLIENT'; ";
        try (Connection conn = this.gerant.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Utilisateur u = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("date_naissance")
                );
                utilisateurs.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }
}
