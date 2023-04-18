package is.menager;

import is.exception.ReadDataException;
import is.format.xml.*;
import is.model.ComputerInfo;
import is.model.Pair;
import is.validator.models.RecordType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class XMLInputFormatManager implements InputFormatManager<XMLInputFormat> {
    @Override
    public List<XMLInputFormat> getRecords() throws ReadDataException {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File("katalog.xml");
            LaptopsXML laptopsXML = (LaptopsXML) jaxbUnmarshaller.unmarshal(xmlFile);

            return laptopsXML.getXMLInputFormats();
        } catch (JAXBException e) {
            throw new ReadDataException(e.getMessage());
        }

    }

    @Override
    public void saveRecords(List<Pair<ComputerInfo, RecordType>> records) {
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
}
