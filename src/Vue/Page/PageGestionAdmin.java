package Vue.Page;

import DAO.ModifAdminDAO;
import Vue.Page.ModAdmin.CreRed;
import Vue.Page.ModAdmin.ModAtt;
import Vue.Page.ModAdmin.ModCli;

import javax.swing.*;
import java.awt.*;

public class PageGestionAdmin extends JFrame {
    private JButton ajAttraction, modAttraction, ajReductions, modReduction, modCLi, quitter;
    private ModifAdminDAO modifieur;
    private JFrame previousFrame;

    public PageGestionAdmin(JFrame previousFrame) {
        super("Vue Administrateur");
        this.previousFrame = previousFrame;
        this.modifieur = new ModifAdminDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 248, 230), getWidth(), getHeight(), new Color(255, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ajAttraction = createButton("Ajouter Attraction", new Color(76, 215, 179));
        modAttraction = createButton("Modifier Attraction", new Color(255, 206, 86));
        ajReductions = createButton("Ajouter Reductions", new Color(100, 149, 237));
        modReduction = createButton("Modifier Reduction", new Color(186, 85, 211));
        modCLi = createButton("Modifier Client", new Color(255, 160, 122));
        quitter = createButton("Quitter", new Color(249, 78, 139));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ajAttraction, gbc);

        gbc.gridx = 1;
        panel.add(modAttraction, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(ajReductions, gbc);

        gbc.gridx = 1;
        panel.add(modReduction, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(modCLi, gbc);

        gbc.gridx = 1;
        panel.add(quitter, gbc);

        ajAttraction.addActionListener(e -> {
            new Vue.Page.ModAdmin.CreAtt(this);
            setVisible(false);
        });

        modAttraction.addActionListener(e -> {
            new ModAtt(this);
            setVisible(false);
        });
        ajReductions.addActionListener(e ->{
            new CreRed(this);
            this.setVisible(false);
        });

        modCLi.addActionListener(e -> {
            new ModCli(this);
            setVisible(false);
        });

        quitter.addActionListener(e -> {
            this.previousFrame.setVisible(true);
            setVisible(false);
        });


        setContentPane(panel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(220, 50));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 1, true));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        new PageGestionAdmin(new JFrame());
    }
}
