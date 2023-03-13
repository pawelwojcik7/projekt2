package is.components;

import is.validator.ColumnValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ValidateTableCellRenderer extends DefaultTableCellRenderer {

    private final ColumnValidator validator;

    public ValidateTableCellRenderer(ColumnValidator validator) {
        this.validator = validator;
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == null || value.equals("")) {
            c.setBackground(Color.YELLOW);
        } else {
            if(validator.validate((String) value).isLeft()) c.setBackground(Color.RED);
            else c.setBackground(table.getBackground());
        }

        return c;
    }


}
