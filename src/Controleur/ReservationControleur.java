package Controleur;

import DAO.ReservationDAO;
import DAO.ReservationmodifDAO;

import java.time.LocalDate;

public class ReservationControleur {
    public boolean reserver(int idUser, int idAttraction, int idCreneau, LocalDate date) {
        ReservationmodifDAO dao = new ReservationmodifDAO();
        return dao.reserve(idUser, idAttraction, idCreneau, date);
    }

    public boolean annulerReservation(int idReservation) {
        System.out.println("[DEBUG] Controleur - Tentative d'annulation ID : " + idReservation);
        return new ReservationDAO().annulerReservation(idReservation);
    }

}
