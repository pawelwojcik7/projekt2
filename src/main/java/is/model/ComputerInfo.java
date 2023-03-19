package is.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComputerInfo {

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

    public String[] toStringArray() {
        return new String[]{
                index,
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
