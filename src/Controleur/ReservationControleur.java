package Controleur;

import DAO.ReservationDAO;
import DAO.ReservationmodifDAO;


public class ReservationControleur {
    private ReservationmodifDAO reservationmodifDAO = new ReservationmodifDAO();
    public boolean reserver(int id, int idAttraction, int idCreneau) {
        System.out.println("iuhdfiudbhgfuiudfhssgiuidfhgf");
        return reservationmodifDAO.reserve(id, idAttraction, idCreneau);
    }
}
