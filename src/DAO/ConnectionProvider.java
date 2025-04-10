package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String URL = "jdbc:mysql://localhost:3306/hop_in";
    private static final String USER = "root";         // Ajuste selon ton environnement
    private static final String PASSWORD = ""; // Ajuste selon ton environnement

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
