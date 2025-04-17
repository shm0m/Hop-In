package Vue.Page;

import DAO.ReservationDAO;
import DAO.CreneauDAO;
import DAO.AttractionDAO;
import Modele.Reservation;
import Modele.Utilisateur;
import Controleur.UtilisateurControleur;
import Vue.NavBarUtil;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PageProfileSwing extends JFrame {

    public PageProfileSwing(Utilisateur utilisateur) {
        setTitle("Profil utilisateur");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel content = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0,
                        new Color(255, 248, 230),
                        getWidth(), getHeight(),
                        new Color(255, 240, 245)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        Color accentColor = new Color(76, 215, 179);
        Color labelColor = new Color(62, 15, 76);
        Color inputColor = new Color(255, 240, 245);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

        JPanel navBar = NavBarUtil.createNavBar(this, utilisateur);
        navBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(navBar);

        ImageIcon icon = new ImageIcon("assets/profil.png");
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(img));
        picLabel.setPreferredSize(new Dimension(80, 80));

        JLabel bienvenueLabel = new JLabel("Bienvenue, " + utilisateur.getPrenom() + " !");
        bienvenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        bienvenueLabel.setForeground(labelColor);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        header.add(picLabel);
        header.add(bienvenueLabel);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(header);

        JPanel formPanel = new RoundedPanel(20);
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 160));
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Nom", "Prénom", "Email", "Date de naissance", "Mot de passe", "ID"};
        JComponent[] champs = {
                new JTextField(utilisateur.getNom(), 22),
                new JTextField(utilisateur.getPrenom(), 22),
                new JTextField(utilisateur.getEmail(), 22),
                new JTextField(utilisateur.getDateNaissance(), 22),
                new JTextField(utilisateur.getMotDePasse(), 22),
                new JLabel(String.valueOf(utilisateur.getId()))
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel lbl = new JLabel(labels[i] + " :");
            lbl.setFont(labelFont);
            lbl.setForeground(labelColor);
            formPanel.add(lbl, gbc);

            gbc.gridx = 1;
            JComponent champ = champs[i];
            champ.setFont(fieldFont);
            if (champ instanceof JTextField) {
                champ.setBackground(inputColor);
                champ.setPreferredSize(new Dimension(260, 34));
                ((JTextField) champ).setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(220, 220, 220), 1, true),
                        new EmptyBorder(6, 10, 6, 10)
                ));
            }
            formPanel.add(champ, gbc);
        }

        JPanel formWrapper = new JPanel();
        formWrapper.setOpaque(false);
        formWrapper.add(formPanel);
        content.add(formWrapper);

        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(fieldFont);
        btnEnregistrer.setPreferredSize(new Dimension(160, 45));
        btnEnregistrer.setFocusPainted(false);
        btnEnregistrer.setBackground(accentColor);
        btnEnregistrer.setForeground(Color.WHITE);
        btnEnregistrer.setBorder(new LineBorder(accentColor, 1, true));
        btnEnregistrer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnregistrer.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnregistrer.setBackground(new Color(62, 15, 76));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnregistrer.setBackground(accentColor);
            }
        });

        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(btnEnregistrer);

        JLabel titreHistorique = new JLabel("Mes réservations");
        titreHistorique.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titreHistorique.setForeground(labelColor);
        titreHistorique.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreHistorique.setBorder(new EmptyBorder(20, 0, 10, 0));
        content.add(titreHistorique);

        String[] colonnes = {"Attraction", "Date", "Heure", "Nb Pers.", "Statut"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        ArrayList<Reservation> reservations = new ReservationDAO().getReservationsByClient(utilisateur.getId());

        for (Reservation res : reservations) {
            String nomAtt = new AttractionDAO().getNomAttractionById(res.getIdAttraction());
            String horaire = new CreneauDAO().getHoraireById(res.getIdCreneau());
            Object[] row = {
                    nomAtt,
                    res.getDateReservation(),
                    horaire,
                    res.getNbPersonnes(),
                    res.getStatut()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFont(fieldFont);
        table.setRowHeight(26);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowGrid(true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(235, 230, 255));
        table.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800, 140));
        scroll.setBorder(BorderFactory.createLineBorder(labelColor, 1, true));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(scroll);

        setContentPane(content);
        setVisible(true);

        JTextField tfNom = (JTextField) champs[0];
        JTextField tfPrenom = (JTextField) champs[1];
        JTextField tfEmail = (JTextField) champs[2];
        JTextField tfDateNaissance = (JTextField) champs[3];
        JTextField tfmdp = (JTextField) champs[4];

        btnEnregistrer.addActionListener(e -> {
            utilisateur.setNom(tfNom.getText());
            utilisateur.setPrenom(tfPrenom.getText());
            utilisateur.setEmail(tfEmail.getText());
            utilisateur.setDateNaissance(tfDateNaissance.getText());
            utilisateur.setMotDePasse(tfmdp.getText());
            new UtilisateurControleur().modifierUtilisateur(utilisateur);
            JOptionPane.showMessageDialog(this, "Profil mis à jour !");
        });
    }

    class RoundedPanel extends JPanel {
        private final int cornerRadius;

        public RoundedPanel(int radius) {
            this.cornerRadius = radius;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }
}
