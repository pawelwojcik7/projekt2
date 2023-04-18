package is.components;

import is.model.ComputerInfo;
import is.model.Pair;
import is.validator.models.RecordType;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
public class MainPanel extends JPanel {

    private final JButton readDataTxt;
    private final JButton saveDataTxt;
    private final JButton readDataXml;
    private final JButton savaDataXml;
    private final JButton readDataFromDatabaseButton;
    private final JButton saveDataToDataBase;
    private final CommunicateTextArea communicateTextArea;

    public MainPanel() {
        super(new GridLayout(1, 7));

        this.readDataTxt = new JButton("Wczytaj dane TXT");
        this.saveDataTxt = new JButton("Zapisz dane do TXT");
        this.readDataXml = new JButton("Wczytaj dane XML");
        this.savaDataXml = new JButton("Zapisz dane do XML");
        this.readDataFromDatabaseButton = new JButton("Wczytaj z bazy");
        this.saveDataToDataBase = new JButton("Zapisz do bazy");
        this.communicateTextArea = new CommunicateTextArea();
        //setUpTxtButtons();
        //setUpXmlButtons();

        initializeComponents();
    }

    public void setCommunicate(String message){
        communicateTextArea.setCommunicate(message);
    }

    private void initializeComponents() {
        this.add(readDataTxt);
        this.add(saveDataTxt);
        this.add(readDataXml);
        this.add(savaDataXml);
        this.add(readDataFromDatabaseButton);
        this.add(saveDataToDataBase);
        this.add(communicateTextArea);
    }

//    private void setUpTxtButtons(){
//        readDataTxt.addActionListener(e -> readDataTxt());
//        saveDataTxt.addActionListener(e -> saveDataTxt());
//    }
//
//    private void setUpXmlButtons(){
//        readDataXml.addActionListener(e -> xmlInputFormatMenager.getRecords());
//        savaDataXml.addActionListener(e -> saveXml());
//    }
//
//    private void saveDataTxt(){
//        try {
//            saveData();
//        } catch (IOException ex) {
//            errorField.setText(ex.getMessage());
//            clearErrorField();
//        }
//    }
//
//    private void readDataTxt(){
//        try {
//            readData();
//            isDataLoaded = true;
//        } catch (CsvValidationException | IOException ex) {
//            communicateTextArea.setCommunicate(ex.getMessage());
//        }
//    }


}
