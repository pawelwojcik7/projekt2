package is.format.database;


import is.format.InputFormat;
import is.model.ComputerInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DataBaseInputFormat extends InputFormat<DataBaseInputFormat> {

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

    @Override
    public ComputerInfo toComputerInfo() {
        return new ComputerInfo(
                this.index,
                this.producer,
                this.diagonal,
                this.resolution,
                this.screenType,
                this.isTouchable,
                this.processor,
                this.coreNumber,
                this.frequency,
                this.RAM,
                this.diskSize,
                this.diskType,
                this.graphicCard,
                this.graphicCardMemory,
                this.operatingSystem,
                this.opticalDrive
        );
    }

}
