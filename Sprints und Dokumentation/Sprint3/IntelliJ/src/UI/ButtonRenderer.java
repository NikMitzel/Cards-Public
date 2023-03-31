package UI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
        setForeground(new Color(0x0000001, true));
        setBackground(new Color(0x0000001, true));
        setIcon(new ImageIcon("src/UI/img/loschen 1.png"));
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
