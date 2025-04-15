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
            System.out.println(stmt);
            return(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(-1);
    }


    public void ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, date_naissance, type_membre, role) VALUES (?, ?, ?, ?, CURDATE(), ?, ?)";
        try (Connection conn = this.gerant.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMotDePasse());
            /*Date*/
            stmt.setString(5, "aucun");
            stmt.setString(6, u.getRole());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Client> getClis() {
        String sql = "select * from utilisateur where role=\"CLIENT\";";

        try {
            Connection conn = gerant.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery(sql);


            ArrayList<Client> clients = new ArrayList();
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String mot_de_passe = resultat.getString("mot_de_passe");
                String date_naissance = resultat.getString("date_naissance");

                clients.add(new Client(id, nom, prenom, email, mot_de_passe, date_naissance));
            }
            return (clients);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(new ArrayList<Client>());
    }
}
