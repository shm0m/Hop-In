package Vue;

import Vue.Page.PageProfileSwing;
import Vue.Page.PageReservationSwing;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class NavBarUtil {
    public static JPanel createNavBar(JFrame frame, Utilisateur utilisateur) {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navBar.setOpaque(false);

        JButton btnProfil = new JButton("Mon Profil");
        JButton btnCalendrier = new JButton("Calendrier");
        JButton btnDeconnexion = new JButton("Déconnexion");

        styleButton(btnProfil, new Color(76, 215, 179));
        styleButton(btnCalendrier, new Color(255, 236, 110));
        styleButton(btnDeconnexion, new Color(249, 78, 139));

        btnProfil.addActionListener(e -> {
            frame.setVisible(false);
            new PageProfileSwing(utilisateur);
        });

        btnCalendrier.addActionListener(e -> {
            frame.setVisible(false);
            new PageReservationSwing(utilisateur);
        });

        btnDeconnexion.addActionListener(e -> {
            frame.dispose();
            new HopInGUI();
        });

        navBar.add(btnProfil);
        navBar.add(btnCalendrier);
        navBar.add(btnDeconnexion);

        return navBar;
    }

    private static void styleButton(JButton button, Color bgColor) {
        button.setPreferredSize(new Dimension(140, 35));
        button.setBackground(bgColor);
        button.setForeground(new Color(62, 15, 76));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
    }
}
