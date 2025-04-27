package DAO_Get;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReservationDAO {

    private GestionConnexion gerant;
    public ReservationDAO() {
        gerant=new GestionConnexion();
    }

    public ArrayList<String> getAfflience(int idAtt)    {
        ArrayList<String> dates=new ArrayList<String>();
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT date_reservation from reservation where id_attraction=? order by date_reservation;"
             )) {
            ps.setInt(1, idAtt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("date_reservation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }
}
