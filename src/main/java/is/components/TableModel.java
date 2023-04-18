package is.components;

import is.validator.models.Headers;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

    public TableModel() {
        super();
        setColumnHeaders();
    }

    private void setColumnHeaders() {
        Headers.sortedValues().forEach(e -> this.addColumn(e.getCode()));
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }

}
