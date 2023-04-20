package is.format.txt;

import is.model.ComputerInfo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TxtInputFormat {

    private String index;
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


    public static ComputerInfo toComputerInfo(TxtInputFormat txtInputFormat) {
        return new ComputerInfo(
                txtInputFormat.index,
                txtInputFormat.producer,
                txtInputFormat.diagonal,
                txtInputFormat.resolution,
                txtInputFormat.screenType,
                txtInputFormat.isTouchable,
                txtInputFormat.processor,
                txtInputFormat.coreNumber,
                txtInputFormat.frequency,
                txtInputFormat.RAM,
                txtInputFormat.diskSize,
                txtInputFormat.diskType,
                txtInputFormat.graphicCard,
                txtInputFormat.graphicCardMemory,
                txtInputFormat.operatingSystem,
                txtInputFormat.opticalDrive
        );
    }
}
