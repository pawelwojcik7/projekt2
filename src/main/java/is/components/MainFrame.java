package is.components;


import is.Merger;
import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.menager.DataBaseInputFormatManager;
import is.menager.TXTInputFormatManager;
import is.menager.XMLInputFormatManager;
import is.model.ComputerInfo;
import is.model.MergeStatistic;
import is.model.Pair;
import is.utils.AppUtils;
import is.validator.models.RecordType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private final JTextArea errorField;
    private final TableModel tableModel;
    private final Table table;
    private final JScrollPane content;
    private final MainPanel mainPanel;
    private final XMLInputFormatManager xmlInputFormatManager;
    private final DataBaseInputFormatManager dataBaseInputFormatManager;
    private final TXTInputFormatManager txtInputFormatManager;
    private List<Pair<ComputerInfo, RecordType>> mainList;

    private final Merger merger;
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
        dataBaseInputFormatManager = new DataBaseInputFormatManager();
        txtInputFormatManager = new TXTInputFormatManager();
        merger = new Merger();

        mainPanel.getReadDataTxt().addActionListener(e -> tryToReadDataTxt());
        mainPanel.getSaveDataTxt().addActionListener(e -> tryToSaveDataTxt());
        mainPanel.getReadDataXml().addActionListener(e -> tryToReadDataXml());
        mainPanel.getSavaDataXml().addActionListener(e -> tryToSavaDataXml());
        mainPanel.getReadDataFromDatabaseButton().addActionListener(e -> tryToReadDataFromDataBase());
        mainPanel.getSaveDataToDataBase().addActionListener(e -> tryToSaveDataToDataBase());

        init();
    }

    private void tryToSaveDataToDataBase() {
        if (!isDataLoaded) {
            mainPanel.setCommunicate(AppUtils.DATA_NOT_LOADED);
        } else if (validateData()) {
            try {
                dataBaseInputFormatManager.saveRecords(mainList.stream().map(Pair::getLeft).toList());
            } catch (SaveDataException e) {
                mainPanel.setCommunicate(e.getMessage());
            }
        }
    }

    private void tryToReadDataFromDataBase() {
        List<ComputerInfo> records = null;
        try {
            records = dataBaseInputFormatManager.getRecords();
            MergeStatistic statisitic = merger.compareAndMerge(records, mainList);
            mainPanel.setCommunicate(
                    "Znaleziono " + String.valueOf(statisitic.getNewRecords() + statisitic.getDuplicates()) + " z czego " + statisitic.getDuplicates() + " to duplikaty"
            );
            isDataLoaded = true;
            setTable();
        } catch (ReadDataException e) {
            mainPanel.setCommunicate(e.getMessage());
        }
    }

    private void init() {
        setSize(1000, 1000);
        add(content, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    private void tryToSaveDataTxt() {
        try {
            saveDataTxt();
        } catch (IOException ex) {
            errorField.setText(ex.getMessage());
        }
    }

    private void tryToSavaDataXml() {
        if (!isDataLoaded) {
            mainPanel.setCommunicate(AppUtils.DATA_NOT_LOADED);
        } else if (validateData()) {
            try {
                xmlInputFormatManager.saveRecords(mainList.stream().map(Pair::getLeft).toList());
            } catch (SaveDataException e) {
                mainPanel.setCommunicate(e.getMessage());
            }
        }
    }

    private void saveDataTxt() throws IOException {
        if (!isDataLoaded) {
            mainPanel.setCommunicate("Dane nie zostały wczytane");
        } else if (validateData()) {
            List<List<String>> results = table.getTableContent();
            List<ComputerInfo> list = results.stream().map(ComputerInfo::fromStringList).toList();
            try {
                txtInputFormatManager.saveRecords(list);
            } catch (SaveDataException e) {
                mainPanel.setCommunicate(e.getMessage());
            }
        }

    }

    private Boolean validateData() {
        String probablyError = table.validateData();
        if (!probablyError.equals("")) {
            mainPanel.setCommunicate(probablyError);
            return false;
        } else return true;
    }


    private void tryToReadDataTxt() {
        List<ComputerInfo> records = null;
        try {
            records = txtInputFormatManager.getRecords();
            MergeStatistic statisitic = merger.compareAndMerge(records, mainList);
            mainPanel.setCommunicate(
                    "Znaleziono " + String.valueOf(statisitic.getNewRecords() + statisitic.getDuplicates()) + " z czego " + statisitic.getDuplicates() + " to duplikaty"
            );
            isDataLoaded = true;
            setTable();
        } catch (ReadDataException e) {
            mainPanel.setCommunicate(e.getMessage());
        }
    }


    private void setTable() {
        //tableModel.setRowCount(0);
        repaint();
        mainList.forEach(info -> tableModel.addRow(info.getLeft().toStringArray()));
        repaint();
    }

    private void tryToReadDataXml() {

        List<ComputerInfo> records = null;
        try {
            records = xmlInputFormatManager.getRecords();
            MergeStatistic statisitic = merger.compareAndMerge(records, mainList);
            mainPanel.setCommunicate(
                    "Znaleziono " + String.valueOf(statisitic.getNewRecords() + statisitic.getDuplicates()) + " z czego " + statisitic.getDuplicates() + " to duplikaty"
            );
            isDataLoaded = true;
            setTable();
        } catch (ReadDataException e) {
            mainPanel.setCommunicate(e.getMessage());
        }

    }


}
