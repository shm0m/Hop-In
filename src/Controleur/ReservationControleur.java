package Controleur;

import DAO.ReservationDAO;


public class ReservationControleur {
    private ReservationDAO reservationDAO = new ReservationDAO();
    public boolean reserver(int idUser, int idAttraction, int idCreneau) {
        return reservationDAO.reserve(idUser, idAttraction, idCreneau);
    }
}
