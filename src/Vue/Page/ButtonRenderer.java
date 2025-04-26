package Vue.Page;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
        setFocusPainted(false);
        setBackground(new Color(249, 78, 139));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBorder(BorderFactory.createLineBorder(new Color(62, 15, 76), 2, true));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText((value == null) ? "Annuler" : value.toString());

        if (isSelected) {
            setBackground(new Color(62, 15, 76));
            setForeground(Color.WHITE);
        } else {
            setBackground(new Color(249, 78, 139));
            setForeground(Color.WHITE);
        }

        return this;
    }
}
