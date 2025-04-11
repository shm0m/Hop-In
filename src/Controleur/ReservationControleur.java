package Controleur;

import DAO.ReservationDAO;


public class ReservationControleur {
    private ReservationDAO reservationDAO = new ReservationDAO();
    public boolean reserver(int id, int idAttraction, int idCreneau) {
        System.out.println("iuhdfiudbhgfuiudfhssgiuidfhgf");
        return reservationDAO.reserve(id, idAttraction, idCreneau);
    }
}
