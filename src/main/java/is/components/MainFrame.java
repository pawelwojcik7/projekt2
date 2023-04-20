package is.components;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import is.database.Connector;
import is.exception.SaveDataException;
import is.format.xml.LaptopsXML;
import is.format.xml.XMLInputFormat;
import is.menager.XMLInputFormatManager;
import is.model.ComputerInfo;
import is.model.Pair;
import is.utils.AppUtils;
import is.validator.models.RecordType;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
    private final TableModel tableModel;
    private final Table table;
    private final JScrollPane content;
    private final MainPanel mainPanel;

    private Connector dataBaseConnector;

    private final XMLInputFormatManager xmlInputFormatManager;
    private List<Pair<ComputerInfo, RecordType>> mainList;
    private Boolean isDataLoaded = false;

    public MainFrame() {
        super("Projekt 2");

        mainList = new ArrayList<>();
        errorField = new JTextArea();
        tableModel = new TableModel();
        table = new Table(tableModel);
        content = new JScrollPane(table);
        mainPanel = new MainPanel();

        xmlInputFormatManager = new XMLInputFormatManager();

        try {
            dataBaseConnector = new Connector();
            if (!dataBaseConnector.isTableExist("laptops")) {
                dataBaseConnector.createLaptopsTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            mainPanel.getReadDataFromDatabaseButton().setEnabled(false);
            mainPanel.getSaveDataToDataBase().setEnabled(false);
        }

        mainPanel.getReadDataTxt().addActionListener(e -> {
            try {
                readData();
                isDataLoaded = true;
            } catch (CsvValidationException | IOException exception) {
                mainPanel.setCommunicate(exception.getMessage());
            }
        });

        mainPanel.getSaveDataTxt().addActionListener(e -> {
            try {
                saveData();
            } catch (IOException ex) {
                errorField.setText(ex.getMessage());
                clearErrorField();
            }
        });

        readDataXml.addActionListener(e -> readXml());
        savaDataXml.addActionListener(e -> saveXml());


    }

    private void init() {
        setSize(1000, 1000);
        add(content, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    private void saveXml() {
        if (!isDataLoaded) {
            mainPanel.setCommunicate("Dane nie zostały wczytane");
        } else if (validateData()) {
            try {
                xmlInputFormatManager.saveRecords(mainList.stream().map(Pair::getLeft).map(ComputerInfo::toXMLInputFormat).collect(Collectors.toList()));
            } catch (SaveDataException e) {
                mainPanel.setCommunicate(e.getMessage());
            }
        }
    }

    private void saveData() throws IOException {
        if (!isDataLoaded) {
            mainPanel.setCommunicate("Dane nie zostały wczytane");
        } else if (validateData()) {
            List<List<String>> results = table.getTableContent();
            String zipFileName = OffsetDateTime.now().format(AppUtils.DATE_TIME_FORMATTER) + ".zip";

            PrintWriter writer = new PrintWriter(AppUtils.TXT_FILE_NAME);
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
            ZipEntry ze = new ZipEntry(AppUtils.TXT_FILE_NAME);
            zos.putNextEntry(ze);

            FileInputStream in = new FileInputStream(AppUtils.TXT_FILE_NAME);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();
            zos.close();
            fos.close();

            mainPanel.setCommunicate("Pomyślnie zapisano do pliku: " + zipFileName);
        }


    }

    private Boolean validateData() {
        String probablyError = table.validateData();
        if (!probablyError.equals("")) {
            mainPanel.setCommunicate(probablyError);
            return false;
        } else return true;
    }


    private void readData() throws IOException, CsvValidationException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
        String fileName = "katalog.txt";
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(csvParser).build();
        String[] line;
        mainList = new ArrayList<>();
        int i = 1;
        while ((line = reader.readNext()) != null) {
            String diagonal;
            if (line[1] == null || line[1].equals("")) diagonal = line[1];
            else diagonal = line[1] + "\"";
            mainList.add(new Pair<>(new ComputerInfo(
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


    private void setTable() {
        tableModel.setRowCount(0);
        repaint();
        mainList.forEach(info -> tableModel.addRow(info.getLeft().toStringArray()));
        repaint();
    }

    private void readXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("katalog.xml");
            LaptopsXML LaptopsXML = (LaptopsXML) jaxbUnmarshaller.unmarshal(xmlFile);
            mainList = new ArrayList<>();
            mainList = LaptopsXML.getXMLInputFormats().stream().map(XMLInputFormat::toComputerInfo).map(e -> new Pair<ComputerInfo, RecordType>(e, NEW)).collect(Collectors.toList());
            setTable();
            isDataLoaded = true;
        } catch (JAXBException e) {
            mainPanel.setCommunicate(e.getMessage());
        }

    }


}
