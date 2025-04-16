package Vue.Page;

import Controleur.UtilisateurControleur;
import Modele.Admin;
import Modele.Utilisateur;
import Modele.Client;

import javax.swing.*;
import java.awt.*;

public class Pagepayement extends JFrame{

        public Pagepayement(JFrame previousFrame) {
            setTitle("Paiement - Carte de Crédit");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            JLabel headerLabel = new JLabel("Veuillez saisir vos informations de carte de crédit", SwingConstants.CENTER);
            headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            mainPanel.add(headerLabel, BorderLayout.NORTH);

            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblCardHolder = new JLabel("Nom du titulaire :");
            lblCardHolder.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(lblCardHolder, gbc);
            gbc.gridx = 1;
            JTextField tfCardHolder = new JTextField(20);
            tfCardHolder.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(tfCardHolder, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel lblCardNumber = new JLabel("Numéro de carte de crédit :");
            lblCardNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(lblCardNumber, gbc);
            gbc.gridx = 1;
            JTextField tfCardNumber = new JTextField(20);
            tfCardNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(tfCardNumber, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            JLabel lblExpiration = new JLabel("Date d'expiration (MM/YY) :");
            lblExpiration.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(lblExpiration, gbc);
            gbc.gridx = 1;
            JTextField tfExpiration = new JTextField(10);
            tfExpiration.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(tfExpiration, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            JLabel lblCVV = new JLabel("CVV :");
            lblCVV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(lblCVV, gbc);
            gbc.gridx = 1;
            JTextField tfCVV = new JTextField(5);
            tfCVV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(tfCVV, gbc);

            mainPanel.add(contentPanel, BorderLayout.CENTER);

            add(mainPanel);
            setVisible(true);

        }
}
