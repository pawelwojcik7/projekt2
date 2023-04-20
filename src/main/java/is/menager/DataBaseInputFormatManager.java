package is.menager;

import is.database.Connector;
import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.model.ComputerInfo;

import java.sql.SQLException;
import java.util.List;

public class DataBaseInputFormatManager implements InputFormatManager{
    @Override
    public List<ComputerInfo> getRecords() throws ReadDataException {
        return null;
    }

    @Override
    public void saveRecords(List<ComputerInfo> computerInfos) throws SaveDataException {
        Connector dataBaseConnector;
        try {
            dataBaseConnector = new Connector();
            if (!dataBaseConnector.isTableExist("laptops")) {
                dataBaseConnector.createLaptopsTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SaveDataException(e.getMessage());
        }
    }
}
