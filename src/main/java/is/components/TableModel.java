package is.components;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

    public TableModel() {
        super();
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }

}
