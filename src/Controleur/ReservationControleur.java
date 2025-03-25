package Controleur;

import DAO.ReservationDAO;
import Modele.Reservation;
import Modele.Utilisateur;

import java.time.LocalDate;
import java.util.Scanner;

public class ReservationControleur {

    private ReservationDAO reservationDAO = new ReservationDAO();

    public double calculerReduction(Utilisateur u) {
        int age = getAgeFromString(u.getDateNaissance());
        String type = u.getTypeMembre();

        if (age < 12) return 30.0;
        if (age >= 60) return 20.0;
        if ("REGULIER".equalsIgnoreCase(type)) return 10.0;
        return 0.0;
    }

    public int getAgeFromString(String dateNaissance) {
        LocalDate birth = LocalDate.parse(dateNaissance); // Format AAAA-MM-JJ
        return LocalDate.now().getYear() - birth.getYear();
    }

    public void effectuerReservation(Utilisateur u, int idAttraction, double prixUnitaire) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Combien de personnes ? ");
        int nb = scanner.nextInt();

        double reduction = calculerReduction(u);
        double total = nb * prixUnitaire * (1 - reduction / 100.0);
        String date = LocalDate.now().toString();

        Reservation r = new Reservation(
                0,
                u.getId(),
                idAttraction,
                date,
                nb,
                reduction,
                total,
                "CONFIRMEE"
        );

        reservationDAO.ajouterReservation(r);

        System.out.println("✅ Réservation enregistrée !");
        System.out.println("💸 Prix total : " + total + "€ avec " + reduction + "% de réduction");
    }
}
