package Vue.Page;
import Modele.Admin;
import javax.swing.*;


public class PageGestionAdmin extends JFrame {
    Admin uti;
    JButton ajAttraction;
    JButton ModAttraction;
    JButton ajReductions;
    JButton ModReduction;
    JButton ModCLi;

    public PageGestionAdmin(Admin u){
        super("Vue Administrateur");
        this.uti=u;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        ajAttraction= new JButton("Ajouter Attraction");
        ModAttraction= new JButton("Modifier Attraction");
        ajReductions= new JButton("Ajouter Reductions");
        ModReduction= new JButton("Modifier Reduction");
        ModCLi= new JButton("Modifier Client");




    }
}
