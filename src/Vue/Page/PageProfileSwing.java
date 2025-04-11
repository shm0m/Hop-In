package Vue.Page;

import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;

    public class PageProfileSwing extends JFrame {

        public PageProfileSwing(Utilisateur utilisateur) {
            setTitle("Page intermédiaire");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setOpaque(false);

            JLabel lblHeader = new JLabel("Bienvenue sur votre profile", SwingConstants.LEFT);
            lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
            headerPanel.add(lblHeader, BorderLayout.WEST);

            JButton btnProfile = new JButton("Profile");
            btnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnProfile.setPreferredSize(new Dimension(120, 40));

            JPanel profileBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            profileBtnPanel.setOpaque(false);
            profileBtnPanel.add(btnProfile);
            headerPanel.add(profileBtnPanel, BorderLayout.EAST);

            mainPanel.add(headerPanel, BorderLayout.NORTH);



        }
    }

