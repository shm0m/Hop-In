package Reporting;

import Modele.Reservation;

import javax.swing.*;
import java.util.ArrayList;

public class AffichageStats {

    public AffichageStats(){}
    public String getStatFromId(int idAttraction){
        ArrayList<String> crenaux=(new DAO_Get.ReservationDAO()).getAfflience(idAttraction);
        if(crenaux.size()==0){
            return("Pas de réservation pour cette attraction");
        }
        String cPrec=crenaux.get(0);
        String resultat="<html>"+cPrec+": ";
        for(String c: crenaux){
            if(c.equals(cPrec)){
                resultat=resultat+"#";
            }
            else{
                cPrec=c;
                resultat+=" <br> "+c+": #";
            }
        }
        return(resultat+"</html>");
    }
}
