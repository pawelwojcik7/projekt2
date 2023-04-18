package is.components;

import is.validator.implementation.*;

import javax.swing.*;

public class Table extends JTable {

    public Table() {
        super();
        setUpTable();
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
