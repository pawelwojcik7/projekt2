package is.menager;

import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.model.ComputerInfo;

import java.util.List;

public interface InputFormatManager {

    List<ComputerInfo> getRecords() throws ReadDataException;

    void saveRecords(List<ComputerInfo> computerInfos) throws SaveDataException;


}
