package is.menager;

import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.format.xml.LaptopsXML;
import is.format.xml.XMLInputFormat;
import is.model.ComputerInfo;
import is.utils.AppUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLInputFormatManager implements InputFormatManager {
    @Override
    public List<ComputerInfo> getRecords() throws ReadDataException {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File(AppUtils.XML_FILE_NAME);
            LaptopsXML laptopsXML = (LaptopsXML) jaxbUnmarshaller.unmarshal(xmlFile);

            return laptopsXML.getXMLInputFormats().stream().map(XMLInputFormat::toComputerInfo).toList();
        } catch (JAXBException e) {
            throw new ReadDataException(e.getMessage());
        }

    }

    @Override
    public void saveRecords(List<ComputerInfo> xmlInputFormats) throws SaveDataException {
        LaptopsXML laptopsXML = new LaptopsXML(xmlInputFormats.stream().map(XMLInputFormat::convert).toList());
        try {
         saveLaptopsXML(laptopsXML);
        } catch (JAXBException | IOException e) {
            throw new SaveDataException(e.getMessage());
        }
    }

    private void saveLaptopsXML(LaptopsXML record) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(LaptopsXML.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        FileOutputStream outputStream = new FileOutputStream(AppUtils.XML_FILE_NAME);
        marshaller.marshal(record, outputStream);
        outputStream.close();
    }

}
