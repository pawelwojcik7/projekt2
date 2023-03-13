package is.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComputerInfo {
    private String producer;
    private String diagonal;
    private String resolution;
    private String screenType;
    private String isTouchable;
    private String processor;
    private String coreNumber;
    private String frequency;
    private String RAM;
    private String diskSize;
    private String diskType;
    private String graphicCard;
    private String graphicCardMemory;
    private String operatingSystem;
    private String opticalDrive;

    public String[] toStringArray(){
        return new String[]{
                producer,
                diagonal,
                resolution,
                screenType,
                isTouchable,
                processor,
                coreNumber,
                frequency,
                RAM,
                diskSize,
                diskType,
                graphicCard,
                graphicCardMemory,
                operatingSystem,
                opticalDrive};
    }

}
