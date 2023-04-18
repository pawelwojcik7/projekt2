package is.model;

import is.format.xml.*;
import lombok.*;

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

    public ComputerInfo(String index, String producer, String diagonal, String resolution, String screenType,
                        String isTouchable, String processor, String coreNumber, String frequency, String RAM,
                        String diskSize, String diskType, String graphicCard, String graphicCardMemory,
                        String operatingSystem, String opticalDrive) {
        this.index = index == null ? "" : index;
        this.producer = producer == null ? "" : producer;
        this.diagonal = diagonal == null ? "" : diagonal;
        this.resolution = resolution == null ? "" : resolution;
        this.screenType = screenType == null ? "" : screenType;
        this.isTouchable = isTouchable == null ? "" : isTouchable;
        this.processor = processor == null ? "" : processor;
        this.coreNumber = coreNumber == null ? "" : coreNumber;
        this.frequency = frequency == null ? "" : frequency;
        this.RAM = RAM == null ? "" : RAM;
        this.diskSize = diskSize == null ? "" : diskSize;
        this.diskType = diskType == null ? "" : diskType;
        this.graphicCard = graphicCard == null ? "" : graphicCard;
        this.graphicCardMemory = graphicCardMemory == null ? "" : graphicCardMemory;
        this.operatingSystem = operatingSystem == null ? "" : operatingSystem;
        this.opticalDrive = opticalDrive == null ? "" : opticalDrive;
    }

    public XMLInputFormat toLaptop(){
        return new XMLInputFormat(
                index,
                producer,
                new Screen(
                        isTouchable,
                        diagonal,
                        resolution,
                        screenType
                ),
                new Processor(
                        processor,
                        coreNumber,
                        frequency
                ),
                RAM,
                new Disc(
                        diskType,
                        diskSize
                ),
                new GraphicCard(
                        graphicCard,
                        graphicCardMemory
                ),
                operatingSystem,
                opticalDrive
        );
    }

}
