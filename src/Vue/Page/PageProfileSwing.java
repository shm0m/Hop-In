package Vue.Page;

import Modele.Utilisateur;
import Vue.HopInGUI;
import javax.swing.*;
import java.awt.*;
import Controleur.UtilisateurControleur;
import javax.swing.*;
import java.awt.*;

    public class PageProfileSwing extends JFrame {

        private JTextField tfID;
        private JTextField tfTypeMembre;
        private JTextField tfNom;
        private JTextField tfPrenom;
        private JTextField tfEmail;
        private JTextField tfDateNaissance;


        public PageProfileSwing(Utilisateur utilisateur) {
            setTitle("Page intermédiaire");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setOpaque(false);

            JLabel lblHeader = new JLabel("Bienvenue sur votre profil", SwingConstants.CENTER);
            lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
            headerPanel.add(lblHeader, BorderLayout.CENTER);
            mainPanel.add(headerPanel, BorderLayout.NORTH);

            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setBackground(new Color(245, 245, 245));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblNom = new JLabel("Nom :");
            lblNom.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblNom, gbc);

            gbc.gridx = 1;
            JTextField tfNom = new JTextField(utilisateur.getNom());
            tfNom.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tfNom.setColumns(15);
            contentPanel.add(tfNom, gbc);


            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel lblPrenom = new JLabel("Prénom :");
            lblPrenom.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblPrenom, gbc);

            gbc.gridx = 1;
            JTextField tfPrenom = new JTextField(utilisateur.getPrenom());
            tfPrenom.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tfPrenom.setColumns(15);
            contentPanel.add(tfPrenom, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            JLabel lblEmail = new JLabel("Email :");
            lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblEmail, gbc);

            gbc.gridx = 1;
            JTextField tfEmail = new JTextField(utilisateur.getEmail());
            tfEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tfEmail.setColumns(15);
            contentPanel.add(tfEmail, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            JLabel lblDateNaissance = new JLabel("Date de naissance :");
            lblDateNaissance.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblDateNaissance, gbc);

            gbc.gridx = 1;
            JTextField tfDateNaissance = new JTextField(utilisateur.getDateNaissance());
            tfDateNaissance.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tfDateNaissance.setColumns(15);
            contentPanel.add(tfDateNaissance, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            JLabel lblMdp = new JLabel("mot de passe:");
            lblMdp.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblMdp, gbc);

            gbc.gridx = 1;
            JTextField tfmdp = new JTextField(utilisateur.getMotDePasse());
            tfmdp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tfmdp.setColumns(15);
            contentPanel.add(tfmdp, gbc);

            JLabel valMdp = new JLabel(utilisateur.getMotDePasse());
            valMdp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(valMdp, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            JLabel lblId = new JLabel("ID :");
            lblId.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblId, gbc);

            gbc.gridx = 1;
            JLabel valId = new JLabel(String.valueOf(utilisateur.getId()));
            valId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(valId, gbc);

// quand on aura un getteur pour le type member /*
        /*    gbc.gridx = 0;
            gbc.gridy = 6;
            JLabel lblTypeMembre = new JLabel("Type de membre :");
            lblTypeMembre.setFont(new Font("Segoe UI", Font.BOLD, 16));
            contentPanel.add(lblTypeMembre, gbc);

            gbc.gridx = 1;
            JLabel valTypeMembre = new JLabel(utilisateur.get type membre ());
            valTypeMembre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            contentPanel.add(valTypeMembre, gbc);
*/




                mainPanel.add(contentPanel, BorderLayout.CENTER);

            JButton btnProfile = new JButton(" historique ");
            btnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnProfile.setPreferredSize(new Dimension(120, 40));

            JPanel profileBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            profileBtnPanel.setOpaque(false);
            profileBtnPanel.add(btnProfile);
            headerPanel.add(profileBtnPanel, BorderLayout.EAST);

            mainPanel.add(headerPanel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));



            bottomPanel.setOpaque(false);
            JButton btnEnregistrer = new JButton("Enregistrer");
            btnEnregistrer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnEnregistrer.setPreferredSize(new Dimension(120, 40));
            bottomPanel.add(btnEnregistrer);

            bottomPanel.setOpaque(false);
            JButton btnCalendar = new JButton("Calendrier");
            btnCalendar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnCalendar.setPreferredSize(new Dimension(150, 40));
            bottomPanel.add(btnCalendar);

            JButton btnRetour = new JButton("Retour");
            btnRetour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnRetour.setPreferredSize(new Dimension(120, 40));
            bottomPanel.add(btnRetour);

            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            add(mainPanel);
            setVisible(true);

            btnEnregistrer.addActionListener(e -> {

                   utilisateur.setNom(tfNom.getText());
                 utilisateur.setPrenom(tfPrenom.getText());
                utilisateur.setEmail(tfEmail.getText());
                utilisateur.setDateNaissance(tfDateNaissance.getText());
                utilisateur.setMotDePasse(tfmdp.getText());

                  UtilisateurControleur controleur = new UtilisateurControleur();

                 controleur.modifierUtilisateur(utilisateur);

                 JOptionPane.showMessageDialog(this, "Profil mis à jour avec succès !");
            });

            btnRetour.addActionListener(e -> {
                new Vue.HopInGUI();
                dispose();
            });




            add(mainPanel);
            setVisible(true);


            btnProfile.addActionListener(e -> {

            });
            btnCalendar.addActionListener(e -> {
                new PageReservationSwing(utilisateur);
                setVisible(false);
            });

        }
    }

