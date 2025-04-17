package Vue.Page;

import javax.swing.*;
import java.awt.*;

public class VerifAdmin extends JDialog {
    private int valide = 0;
    private JTextField champ;
    private JButton soumettre;
    private JButton annuler;
    private JLabel info;

    public VerifAdmin(Frame parent) {
        super(parent, "Vérification", true);
        setUndecorated(true);

        JPanel content = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 248, 230), getWidth(), getHeight(), new Color(255, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        content.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        info = new JLabel("Veuillez entrer le code admin pour continuer");
        info.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        info.setForeground(new Color(62, 15, 76));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        content.add(info, gbc);

        champ = new JTextField();
        champ.setPreferredSize(new Dimension(200, 30));
        champ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        champ.setBorder(BorderFactory.createLineBorder(new Color(199, 199, 199), 1, true));
        gbc.gridy = 1;
        content.add(champ, gbc);

        soumettre = new JButton("Soumettre");
        annuler = new JButton("Annuler");
        styleButton(soumettre, new Color(76, 215, 179));
        styleButton(annuler, new Color(249, 78, 139));

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        content.add(soumettre, gbc);
        gbc.gridx = 1;
        content.add(annuler, gbc);

        soumettre.addActionListener(e -> {
            if (champ.getText().equals("0000")) {
                valide = 1;
                setVisible(false);
            } else {
                info.setText("Code incorrect");
                info.setForeground(Color.RED);
            }
        });

        annuler.addActionListener(e -> {
            valide = -1;
            setVisible(false);
        });

        setContentPane(content);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(new Color(62, 15, 76));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
        button.setPreferredSize(new Dimension(110, 35));
    }

    public int getValide() {
        return this.valide;
    }

    public static void main(String[] args) {
        new VerifAdmin(null);
    }
}
