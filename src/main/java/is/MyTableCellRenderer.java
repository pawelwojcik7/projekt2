package is;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class MyTableCellRenderer extends DefaultTableCellRenderer {

    @Getter
    private final String regex;
    public MyTableCellRenderer(String regex) {
        this.regex = regex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == null || value.equals("")) {
            c.setBackground(Color.YELLOW);
        } else {
            if(!value.toString().matches(regex)) c.setBackground(Color.RED);
            else c.setBackground(table.getBackground());
        }

        return c;
    }
}
