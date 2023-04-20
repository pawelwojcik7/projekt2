package is.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyCellEditor extends DefaultCellEditor {

    private final JTable table;

    public MyCellEditor(JTable table) {
        super(new JTextField());
        this.table = table;
    }

    @Override
    public boolean stopCellEditing() {
        boolean result = super.stopCellEditing();
        int row = table.convertRowIndexToModel(table.getEditingRow()); // konwersja indeksu wiersza do indeksu modelu danych

        for (int i = 0; i < table.getColumnCount(); i++) { // iteracja po wszystkich kolumnach w wierszu
            TableCellRenderer renderer = table.getCellRenderer(row, i); // pobranie TableCellRenderer dla kolumny i
            Component component = renderer.getTableCellRendererComponent(table, null, false, false, row, i); // pobranie komponentu TableCellRenderer dla komórki
            component.setBackground(Color.GREEN); // ustawienie koloru tła komponentu na zielony
        }


        return result;
    }
}
