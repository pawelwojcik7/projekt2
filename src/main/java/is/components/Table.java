package is.components;

import is.model.Either;
import is.validator.implementation.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class Table extends JTable {

    public Table(TableModel dm) {
        super(dm);
        setUpTable();
    }

    public String validateData(){
        String errorMessage = "";
        int rowCount = this.getRowCount();
        int colCount = this.getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                ValidateTableCellRenderer renderer = (ValidateTableCellRenderer) this.getCellRenderer(row, col);
                String value = this.getValueAt(row, col).toString();
                Either<String, Boolean> validate = renderer.getValidator().validate(value);
                if (validate.isLeft()) {
                    errorMessage = errorMessage + "Wrong value at [" + (row + 1) + "][" + (col + 1) + "]. Message: " + validate.getLeft() + "\n";
                }
            }
        }
    }

    public List<List<String>> getTableContent() {
        List<List<String>> results = new ArrayList<>();
        int rowCount = this.getRowCount();
        int colCount = this.getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            List<String> rowList = new ArrayList<>();
            for (int col = 0; col < colCount; col++) {
                String value = this.getValueAt(row, col).toString();
                if (value == null) value = "";
                rowList.add(value);
            }
            results.add(rowList);
        }
        return results;
    }

    private void setUpTable() {
        getColumnModel().getColumn(0).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        getColumnModel().getColumn(1).setCellRenderer(new ValidateTableCellRenderer(new ProducerValidator()));
        getColumnModel().getColumn(2).setCellRenderer(new ValidateTableCellRenderer(new DiagonalValidator()));
        getColumnModel().getColumn(3).setCellRenderer(new ValidateTableCellRenderer(new ResolutionValidator()));
        getColumnModel().getColumn(4).setCellRenderer(new ValidateTableCellRenderer(new ScreenTypeValidator()));
        getColumnModel().getColumn(5).setCellRenderer(new ValidateTableCellRenderer(new IsTouchableValidator()));
        getColumnModel().getColumn(6).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        getColumnModel().getColumn(7).setCellRenderer(new ValidateTableCellRenderer(new NumberTableCellValidator()));
        getColumnModel().getColumn(8).setCellRenderer(new ValidateTableCellRenderer(new NumberTableCellValidator()));
        getColumnModel().getColumn(9).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        getColumnModel().getColumn(10).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        getColumnModel().getColumn(11).setCellRenderer(new ValidateTableCellRenderer(new DiskTypeValidator()));
        getColumnModel().getColumn(12).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        getColumnModel().getColumn(13).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        getColumnModel().getColumn(14).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        getColumnModel().getColumn(15).setCellRenderer(new ValidateTableCellRenderer(new OpticalDriveTypeValidator()));
        repaint();
    }
}
