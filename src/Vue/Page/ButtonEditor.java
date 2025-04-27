package Vue.Page;

import Controleur.ReservationControleur;
import Modele.Utilisateur;

import javax.swing.*;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private JTable table;
    private boolean clicked;
    private Utilisateur utilisateur;
    private JFrame parentFrame;

    public ButtonEditor(JCheckBox checkBox, JTable table, Utilisateur utilisateur) {
        super(checkBox);
        this.table = table;
        this.utilisateur = utilisateur;
        this.parentFrame = (JFrame) SwingUtilities.getWindowAncestor(table);

        button = new JButton("Annuler");
        button.setOpaque(true);
        button.setBackground(new Color(249, 78, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        clicked = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (clicked) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                int idReservation = Integer.parseInt(table.getModel().getValueAt(modelRow, 6).toString());

                int confirm = JOptionPane.showConfirmDialog(button,
                        "Confirmer l'annulation de la réservation ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = new ReservationControleur().annulerReservation(idReservation);
                    if (success) {
                        JOptionPane.showMessageDialog(button, "Réservation annulée avec succès.");
                        refreshProfile();
                    } else {
                        JOptionPane.showMessageDialog(button, "Erreur lors de l'annulation.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        clicked = false;
        return "Annuler";
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    private void refreshProfile() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(table);
        if (parentFrame != null) {
            parentFrame.dispose();
            new PageProfileSwing(utilisateur);
        }
    }

}
