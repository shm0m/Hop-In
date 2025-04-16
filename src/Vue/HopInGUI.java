package Vue;
import javax.swing.*;
import java.awt.*;

public class HopInGUI extends JFrame {

    public HopInGUI() {
        setTitle("Hop'In - Accueil");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Bienvenue sur Hop'In");
        label.setFont(new Font("Verdana", Font.BOLD, 30));
        label.setForeground(new Color(255, 105, 180));
        gbc.gridy = 0;
        panel.add(label, gbc);
        
         JButton btnInvite = createButton("Se connecter invité", new Color(100, 149, 237));
        gbc.gridy = 3;
        panel.add(btnInvite, gbc);

        JButton btnConnexion = createButton("Se connecter", new Color(126, 214, 223));
        gbc.gridy = 1;
        panel.add(btnConnexion, gbc);

        JButton btnInscription = createButton("S'enregistrer", new Color(247, 143, 179));
        gbc.gridy = 2;
        panel.add(btnInscription, gbc);

        btnConnexion.addActionListener(e -> {
            new Vue.Page.PageConnexionSwing(this);
            setVisible(false);
        });

        btnInscription.addActionListener(e -> {
            new Vue.Page.PageEnregistrementSwing(this);
            setVisible(false);
        });

        btnInvite.addActionListener(e -> {
            new Vue.Page.PageInviteSwing(this);
            setVisible(false); 
        });

        add(panel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(220, 45));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HopInGUI::new);
    }
}
