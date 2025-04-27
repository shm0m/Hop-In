package Controleur;

import DAO.ReservationDAO;

import java.time.LocalDate;

public class ReservationControleur {
    public boolean reserver(int idUser, int idAttraction, int idCreneau, LocalDate date) {
        ReservationDAO dao = new ReservationDAO();
        return dao.reserve(idUser, idAttraction, idCreneau, date);
    }


    public boolean annulerReservation(int idReservation) {
        System.out.println("[DEBUG] Controleur - Tentative d'annulation ID : " + idReservation);
        return new ReservationDAO().annulerReservation(idReservation);
    }

    public boolean reserverInvite(String mailInvite, int idAttraction, int idCreneau, LocalDate date) {
        ReservationDAO dao = new ReservationDAO();
        return dao.reserveInvite(mailInvite, idAttraction, idCreneau, date);


    }
}
