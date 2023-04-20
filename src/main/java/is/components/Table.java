package is.components;

import is.model.Either;
import is.validator.implementation.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class Table extends JTable {

    public Table(TableModel dm) {
        super(dm);
        setUpTableValidators();
        setUpTableCellEditor();
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = super.getCellEditor(row, column);
        if (editor instanceof DefaultCellEditor) {
            ((DefaultCellEditor) editor).setClickCountToStart(1);
        }
        return editor;
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer renderer = super.getCellRenderer(row, column);
        if (renderer instanceof DefaultTableCellRenderer) {
            ((DefaultTableCellRenderer) renderer).setOpaque(true);
        }
        return renderer;
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
        boolean result = super.editCellAt(row, column, e);
        if (result) {
            SwingUtilities.invokeLater(() -> {
                TableCellEditor editor = getCellEditor();
                if (editor instanceof DefaultCellEditor) {
                    ((DefaultCellEditor) editor).getComponent().requestFocusInWindow();
                }
            });
        }
        return result;
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        super.editingStopped(e);
        int row = getSelectedRow();
        for (int i = 0; i < getColumnCount(); i++) {
            TableCellRenderer renderer = getCellRenderer(row, i);
            Component component = prepareRenderer(renderer, row, i);
            component.setBackground(Color.GREEN);
        }
    }

    public String validateData(){
        StringBuilder errorMessage = new StringBuilder();
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColumnCount(); col++) {
                ValidateTableCellRenderer renderer = (ValidateTableCellRenderer) this.getCellRenderer(row, col);
                String value = this.getValueAt(row, col).toString();
                Either<String, Boolean> validate = renderer.getValidator().validate(value);
                if (validate.isLeft()) {
                    errorMessage.append("Wrong value at [").append(row + 1).append("][").append(col + 1).append("]. Message: ").append(validate.getLeft()).append("\n");
                }
            }
        }
        return errorMessage.toString();
    }

    public List<List<String>> getTableContent() {
        List<List<String>> results = new ArrayList<>();
        for (int row = 0; row < getRowCount(); row++) {
            List<String> rowList = new ArrayList<>();
            for (int col = 0; col < getColumnCount(); col++) {
                String value = this.getValueAt(row, col).toString();
                if (value == null) value = "";
                rowList.add(value);
            }
            results.add(rowList);
        }
        return results;
    }

    private void setUpTableValidators() {
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

    private void setUpTableCellEditor() {
        getColumnModel().getColumn(0).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(1).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(2).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(3).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(4).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(5).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(6).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(7).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(8).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(9).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(10).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(11).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(12).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(13).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(14).setCellEditor(new MyCellEditor(this));
        getColumnModel().getColumn(15).setCellEditor(new MyCellEditor(this));
        repaint();
    }
}
