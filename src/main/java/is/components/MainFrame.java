package is.components;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import is.database.Connector;
import is.format.xml.*;
import is.model.*;
import is.validator.implementation.*;
import is.model.ComputerInfo;
import is.validator.models.Headers;
import is.validator.models.RecordType;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static is.validator.models.RecordType.NEW;

public class MainFrame extends JFrame {
    private final JTextArea errorField;
    private List<Pair<ComputerInfo, RecordType>> list;
    private final TableModel tableModel;
    private final JTable table;
    private Boolean isDataLoaded = false;
    private Connector dataBaseConnector;

    public MainFrame() {
        super("Projekt 2");
        setSize(1000, 1000);

        list = new ArrayList<>();
        errorField = new JTextArea();
        tableModel = new TableModel();
        table = new JTable(tableModel);

        JButton readDataTxt = new JButton("Wczytaj dane TXT");
        JButton saveDataTxt = new JButton("Zapisz dane do TXT");
        JButton readDataXml = new JButton("Wczytaj dane XML");
        JButton savaDataXml = new JButton("Zapisz dane do XML");
        JButton readDataFromDatabaseButton = new JButton("Wczytaj z bazy");
        JButton saveDataToDataBase = new JButton("Zapisz do bazy");
        JPanel mainPanel = new JPanel(new GridLayout(1, 5));
        JScrollPane content = new JScrollPane(table);

        try{
            dataBaseConnector = new Connector();
            if(!dataBaseConnector.isTableExist("laptops")){
                dataBaseConnector.createLaptopsTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            readDataFromDatabaseButton.setEnabled(false);
            saveDataToDataBase.setEnabled(false);
        }

        readDataTxt.addActionListener(e -> {
            try {
                readData();
                isDataLoaded = true;
            } catch (CsvValidationException | IOException ex) {
                errorField.setText(ex.getMessage());
                clearErrorField();
            }
        });

        saveDataTxt.addActionListener(e -> {
            try {
                saveData();
            } catch (IOException ex) {
                errorField.setText(ex.getMessage());
                clearErrorField();
            }
        });

        readDataXml.addActionListener(e -> readXml());
        savaDataXml.addActionListener(e -> saveXml());

        mainPanel.add(readDataTxt);
        mainPanel.add(saveDataTxt);
        mainPanel.add(readDataXml);
        mainPanel.add(savaDataXml);
        mainPanel.add(readDataFromDatabaseButton);
        mainPanel.add(saveDataToDataBase);
        mainPanel.add(errorField);

        setColumnHeaders();
        setColumnRenderer();


        add(content, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void saveXml() {
        if (!isDataLoaded) {
            errorField.setText("Dane nie zostały wczytane");
            clearErrorField();
            return;
        }
        if (validateData()) {
            List<List<String>> results = getTableContent();
           List<XMLInputFormat> XMLInputFormatList = results.stream().map(e ->
              new XMLInputFormat(
                        e.get(0),
                        e.get(1),
                        new Screen(
                                e.get(5),
                                e.get(2),
                                e.get(3),
                                e.get(4)
                        ),
                        new Processor(
                                e.get(6),
                                e.get(7),
                                e.get(8)
                        ),
                        e.get(9),
                        new Disc(
                                e.get(11),
                                e.get(10)
                        ),
                        new GraphicCard(
                                e.get(12),
                                e.get(13)
                        ),
                        e.get(14),
                        e.get(15)
                )
            ).collect(Collectors.toList());

            LaptopsXML LaptopsXML = new LaptopsXML(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), XMLInputFormatList);
            try {

                JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                String fileName = "laptops.xml";
                FileOutputStream outputStream = new FileOutputStream(fileName);
                marshaller.marshal(LaptopsXML, outputStream);
                outputStream.close();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void saveData() throws IOException {
        if (!isDataLoaded) {
            errorField.setText("Dane nie zostały wczytane");
            clearErrorField();
            return;
        }
        if (validateData()) {
            List<List<String>> results = getTableContent();
            String zipFileName = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) + ".zip";
            String fileName = "katalog.txt";

            PrintWriter writer = new PrintWriter(fileName);
            results.forEach(e -> {
                String res = "";
                for (int i = 1; i < e.size(); i++) {
                    res = res + e.get(i) + ";";
                }
                writer.println(res);
            });

            writer.close();

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(fileName);
            zos.putNextEntry(ze);

            FileInputStream in = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();
            zos.close();
            fos.close();

            errorField.setText("Pomyślnie zapisano do pliku: " + zipFileName);
            clearErrorField();

        }


    }

    private List<List<String>> getTableContent() {
        List<List<String>> results = new ArrayList<>();
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            List<String> rowList = new ArrayList<>();
            for (int col = 0; col < colCount; col++) {
                String value = table.getValueAt(row, col).toString();
                if (value == null) value = "";
                rowList.add(value);
            }
            results.add(rowList);
        }
        return results;
    }

    private Boolean validateData() {
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();
        String error = "";
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                ValidateTableCellRenderer renderer = (ValidateTableCellRenderer) table.getCellRenderer(row, col);
                String value = table.getValueAt(row, col).toString();
                Either<String, Boolean> validate = renderer.getValidator().validate(value);
                if (validate.isLeft()) {
                    error = error + "Wrong value at [" + (row + 1) + "][" + (col + 1) + "]. Message: " + validate.getLeft() + "\n";
                }
            }
        }
        if (!error.equals("")) {
            errorField.setText(error);
            clearErrorField();
            return false;
        } else return true;
    }

    private void clearErrorField() {
        Timer timer = new Timer(5000, e -> {
            errorField.setText("");
            errorField.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void readData() throws IOException, CsvValidationException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
        String fileName = "katalog.txt";
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(csvParser).build();
        String[] line;
        list = new ArrayList<>();
        int i = 1;
        while ((line = reader.readNext()) != null) {
            String diagonal;
            if (line[1] == null || line[1].equals("")) diagonal = line[1];
            else diagonal = line[1] + "\"";
            list.add(new Pair<>(new ComputerInfo(
                    Integer.toString(i),
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
            ), NEW));
            i++;
        }
        reader.close();
        setTable();
    }

    private void setColumnHeaders() {
        Headers.sortedValues().forEach(e -> tableModel.addColumn(e.getCode()));
        repaint();
    }

    private void setColumnRenderer() {
        table.getColumnModel().getColumn(0).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        table.getColumnModel().getColumn(1).setCellRenderer(new ValidateTableCellRenderer(new ProducerValidator()));
        table.getColumnModel().getColumn(2).setCellRenderer(new ValidateTableCellRenderer(new DiagonalValidator()));
        table.getColumnModel().getColumn(3).setCellRenderer(new ValidateTableCellRenderer(new ResolutionValidator()));
        table.getColumnModel().getColumn(4).setCellRenderer(new ValidateTableCellRenderer(new ScreenTypeValidator()));
        table.getColumnModel().getColumn(5).setCellRenderer(new ValidateTableCellRenderer(new IsTouchableValidator()));
        table.getColumnModel().getColumn(6).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        table.getColumnModel().getColumn(7).setCellRenderer(new ValidateTableCellRenderer(new NumberTableCellValidator()));
        table.getColumnModel().getColumn(8).setCellRenderer(new ValidateTableCellRenderer(new NumberTableCellValidator()));
        table.getColumnModel().getColumn(9).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        table.getColumnModel().getColumn(10).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        table.getColumnModel().getColumn(11).setCellRenderer(new ValidateTableCellRenderer(new DiskTypeValidator()));
        table.getColumnModel().getColumn(12).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        table.getColumnModel().getColumn(13).setCellRenderer(new ValidateTableCellRenderer(new GbTableCellValidator()));
        table.getColumnModel().getColumn(14).setCellRenderer(new ValidateTableCellRenderer(new DefaultValidator()));
        table.getColumnModel().getColumn(15).setCellRenderer(new ValidateTableCellRenderer(new OpticalDriveTypeValidator()));
        repaint();
    }

    private void setTable() {
        tableModel.setRowCount(0);
        repaint();
        list.forEach(info -> tableModel.addRow(info.getLeft().toStringArray()));
        repaint();
    }

    private void readXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("katalog.xml");
            LaptopsXML LaptopsXML = (LaptopsXML) jaxbUnmarshaller.unmarshal(xmlFile);
            list = new ArrayList<>();
            list = LaptopsXML.getXMLInputFormats().stream().map(XMLInputFormat::toComputerInfo).map(e -> new Pair<ComputerInfo, RecordType>(e, NEW)).collect(Collectors.toList());
            setTable();
            isDataLoaded = true;
        } catch (JAXBException e) {
            errorField.setText(e.getMessage());
            clearErrorField();
        }

    }


}
