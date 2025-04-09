package Vue.Page;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerifAdmin extends JDialog {
    private int valide= 0;
    private JTextField champ;
    private JButton soumettre;
    private JButton annuler;
    private JLabel info;

    public VerifAdmin(Frame parent){
        super(parent,"Vérification",true);
        setLayout(new FlowLayout());

        this.champ = new JTextField("Code admin");
        this.info =  new JLabel("Veillez entrer le code admin pour continuer");
        this.soumettre=new JButton("Soumettre");
        this.annuler=new JButton("Annuler");
        this.add(info);
        this.add(champ );
        this.add(soumettre);
        this.add(annuler);

        soumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(champ.getText().compareTo("0000")==0){
                    valide=1;
                    setVisible(false);
                }
                else{
                    info.setText("Code incorrect");
                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valide=-1;
                setVisible(false);
            }
        });

        setSize(250,150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    public int getValide(){
        /*System.out.println(this.valide);*/
        return(this.valide);

    }
    public static void  main(String[] args){
        new VerifAdmin(null);

    }
}
