package is.menager;

import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.format.xml.LaptopsXML;
import is.format.xml.XMLInputFormat;

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
    public void saveRecords(List<XMLInputFormat> xmlInputFormats) throws SaveDataException {
        LaptopsXML laptopsXML = new LaptopsXML(xmlInputFormats);
        try {
         saveLaptopsXML(laptopsXML);
        } catch (JAXBException | IOException e) {
            throw new SaveDataException(e.getMessage());
        }
    }

    private void saveLaptopsXML(LaptopsXML record) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        String fileName = "laptops.xml";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        marshaller.marshal(record, outputStream);
        outputStream.close();
    }

}
