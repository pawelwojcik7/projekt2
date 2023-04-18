package is.menager;

import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.format.InputFormat;
import is.model.ComputerInfo;
import is.model.Pair;
import is.validator.models.RecordType;

import java.util.List;
public interface InputFormatManager<Format extends InputFormat<Format>>{

    List<Format> getRecords() throws ReadDataException;

    void saveRecords(List<Format> computerInfos) throws SaveDataException;



}
