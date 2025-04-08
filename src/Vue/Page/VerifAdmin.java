package Vue.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerifAdmin extends JFrame {
    private Boolean valide= false;
    private JTextField champ;
    private JButton soumettre;
    private JLabel info;

    public VerifAdmin(){
        super("Vérification");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.champ = new JTextField("Code admin");
        this.info =  new JLabel("Veillez entrer le code admin pour continuer");
        this.soumettre=new JButton("Soumettre");
        this.add(info);
        this.add(champ );
        this.add(soumettre);

        soumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(champ.getText().compareTo("0000")==0){
                    valide=true;
                    setVisible(false);
                }
                else{
                    info.setText("Code incorrect");
                }
            }
        });
        setSize(250,150);
        setVisible(true);
    }
    public Boolean getValide(){
        System.out.println(this.valide);
        return(this.valide);

    }
    public static void  main(String[] args){
        new VerifAdmin();

    }
}
