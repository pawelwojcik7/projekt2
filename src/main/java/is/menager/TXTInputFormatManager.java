package is.menager;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import is.exception.ReadDataException;
import is.exception.SaveDataException;
import is.format.txt.TxtInputFormat;
import is.model.ComputerInfo;
import is.utils.AppUtils;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TXTInputFormatManager implements InputFormatManager{
    @Override
    public List<ComputerInfo> getRecords() throws ReadDataException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
        List<TxtInputFormat> mainList = new ArrayList<>();
        String[] line;
        int i = 1;
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(AppUtils.TXT_FILE_NAME)).withCSVParser(csvParser).build();
            while ((line = reader.readNext()) != null) {
                String diagonal;
                if (line[1] == null || line[1].equals("")) diagonal = line[1];
                else diagonal = line[1] + "\"";
                mainList.add(new TxtInputFormat(
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
                ));
                i++;
            }
            reader.close();
        }
        catch (IOException | CsvValidationException e){
            throw new ReadDataException(e.getMessage());
        }

        return mainList.stream().map(TxtInputFormat::toComputerInfo).toList();
    }

    @Override
    public void saveRecords(List<ComputerInfo> computerInfos) throws SaveDataException {
        String zipFileName = OffsetDateTime.now().format(AppUtils.DATE_TIME_FORMATTER) + ".zip";
        List<List<String>> results = computerInfos.stream().map(ComputerInfo::toStringArray).map(List::of).toList();
        try{
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
        fos.close();}
        catch (Exception e){
            throw new SaveDataException(e.getMessage());
        }
    }
}
