package is.components;

import is.validator.TableCellValidator;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ValidateTableCellRenderer extends DefaultTableCellRenderer {
    @Getter
    private final TableCellValidator validator;

    public ValidateTableCellRenderer(TableCellValidator validator) {
        this.validator = validator;
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //Pair<String, RecordType> value1 = (Pair<String, RecordType>) value;
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == null || value.equals("")) {
            c.setBackground(Color.YELLOW);
        } else {
            if (validator.validate((String) value).isLeft()) c.setBackground(Color.RED);
          else c.setBackground(table.getBackground());
        }
            //setText(value1.getLeft());
        return c;
    }


}
