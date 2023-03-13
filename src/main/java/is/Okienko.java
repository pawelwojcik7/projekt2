package is;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Okienko extends JFrame {

    private final static String onlyLettersRegex = "^[a-zA-Z]+$";
    private final static String diagonalRegex = "^[0-9]+\"$";

    private final static String resolutionRegex = "\\d+x\\d+";

    private final static String onlyNumbersRegex = "^[0-9]+$";

    private final static String gbRegexRegex = "^[0-9]+GB$";

    private final List<String> headers = new ArrayList<>(Arrays.asList(
            "Producent",
            "Wielkość matrycy",
            "Rozdzielczość",
            "Typ matrycy",
            "Czy dotykowa",
            "Procesor",
            "Liczba rdzeni fizycznych",
            "Taktowanie",
            "RAM",
            "Pojemność dysku",
            "Typ dysku",
            "Karta graficzna",
            "Pamięć karty graficznej",
            "System operacyjny",
            "Napęd optyczny"
    ));

    private final JTextArea errorField;

    private List<ComputerInfo> list;

    private final DefaultTableModel model;
   private final JTable table;
    public Okienko(){
        super("Projekt 2");
        setSize(1000, 1000);

        list = new ArrayList<>();
        errorField = new JTextArea();
        model = new DefaultTableModel();
        table = new JTable(model);

        JButton readData = new JButton("Wczytaj dane");
        JButton saveData = new JButton("Zapisz dane");
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        JScrollPane content = new JScrollPane(table);

        readData.addActionListener(e -> {
            try {
                readData();
            } catch (CsvValidationException | IOException ex) {
                errorField.setText(ex.getMessage());
                clearErrorField();
            }
        });

        saveData.addActionListener(e -> {
            try {
                saveData();
            } catch (IOException ex) {
                errorField.setText(ex.getMessage());
                clearErrorField();
            }
        });

        mainPanel.add(readData);
        mainPanel.add(saveData);
        mainPanel.add(errorField);

        setColumnHeaders();


        add(content, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void saveData() throws IOException {
        if(validateData()){
            List<List<String>> results = new ArrayList<>();
            int rowCount = table.getRowCount();
            int colCount = table.getColumnCount();
            for (int row = 0; row < rowCount; row++) {
                List<String> rowList = new ArrayList<>();
                for (int col = 0; col < colCount; col++) {
                    String value = table.getValueAt(row, col).toString();
                    if(value == null) value = "";
                    rowList.add(value);
                }
                results.add(rowList);
            }
            String fileName = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"))+".csv";
            File file = new File(fileName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            results.forEach(e -> {
                try {
                    String res = "";
                    for (String s : e) {
                        res = res + s + ";";
                    }
                    writer.write(res +"\n");
                } catch (IOException ex) {
                    errorField.setText(ex.getMessage());
                    clearErrorField();
                }
            });
            errorField.setText("Pomyślnie zapisano do pliku: "+fileName);
            clearErrorField();
            writer.close();
        }



    }

    private Boolean validateData(){
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();
        String error = "";
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                TableCellRenderer renderer =  table.getCellRenderer(row, col);
                Component c = renderer.getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col);
                Color cellBackgroundColor = c.getBackground();
                if(cellBackgroundColor == Color.red){
                    MyTableCellRenderer a =  (MyTableCellRenderer)renderer;
                    String regex = a.getRegex();
                    int r = row+1;
                    int co = col + 1;
                    error = error + "Wrong format!  row:  "+r+", column: "+co+".  Required format:  " +regex+ "\n";
                }
            }
        }
        if(!error.equals("")){
        errorField.setText(error);
        clearErrorField();
        return false;
        }
        else return true;
    }

    private void clearErrorField(){
        Timer timer = new Timer(5000, e -> {
            errorField.setText("");
            errorField.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void readData() throws IOException, CsvValidationException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
        String fileName = "katalog.csv";
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(csvParser).build();
        String[] line;
        list = new ArrayList<>();
        while ((line = reader.readNext()) != null) {
            String diagonal;
            if(line[1]==null || line[1].equals("")) diagonal = line[1];
            else diagonal = line[1]+"\"";
            list.add(new ComputerInfo(
                    line[0],
                    diagonal,
                    line[2],
                    line[3],
                    line[4],
                    line[5],
                    line[6],
                    line[7],
                    line[8],
                    line[9],
                    line[10],
                    line[11],
                    line[12],
                    line[13],
                    line[14]
            ));
        }
        reader.close();

        list.forEach(info -> System.out.println(info.toString()));
        setTable();
    }

    private void setColumnHeaders(){
            headers.forEach(model::addColumn);
            table.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRenderer(onlyLettersRegex));
            table.getColumnModel().getColumn(1).setCellRenderer(new MyTableCellRenderer(diagonalRegex));
            table.getColumnModel().getColumn(2).setCellRenderer(new MyTableCellRenderer(resolutionRegex));

        table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer(onlyLettersRegex));
        table.getColumnModel().getColumn(4).setCellRenderer(new MyTableCellRenderer(onlyLettersRegex));
        table.getColumnModel().getColumn(5).setCellRenderer(new EmptyTableCellRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new MyTableCellRenderer(onlyNumbersRegex));
        table.getColumnModel().getColumn(7).setCellRenderer(new MyTableCellRenderer(onlyNumbersRegex));
        table.getColumnModel().getColumn(8).setCellRenderer(new MyTableCellRenderer(gbRegexRegex));
        table.getColumnModel().getColumn(9).setCellRenderer(new MyTableCellRenderer(gbRegexRegex));
        table.getColumnModel().getColumn(10).setCellRenderer(new MyTableCellRenderer(onlyLettersRegex));
        table.getColumnModel().getColumn(11).setCellRenderer(new EmptyTableCellRenderer());
        table.getColumnModel().getColumn(12).setCellRenderer(new MyTableCellRenderer(gbRegexRegex));
        table.getColumnModel().getColumn(13).setCellRenderer(new EmptyTableCellRenderer());
        table.getColumnModel().getColumn(14).setCellRenderer(new EmptyTableCellRenderer());
            repaint();
    }
    private void setTable() {
        model.setRowCount(0);
        repaint();
     list.forEach(info -> model.addRow(info.toStringArray()));
        repaint();
    }



}
