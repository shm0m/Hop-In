package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionConnexion {
    public GestionConnexion(){

    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hop_in", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
