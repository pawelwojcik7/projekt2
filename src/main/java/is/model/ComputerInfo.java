package is.model;

import is.format.xml.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

    public static ComputerInfo fromStringList(List<String> row) {
        return new ComputerInfo(
                row.get(0),
                row.get(1),
                row.get(5),
                row.get(2),
                row.get(3),
                row.get(4),
                row.get(6),
                row.get(7),
                row.get(8),
                row.get(9),
                row.get(11),
                row.get(10),
                row.get(12),
                row.get(13),
                row.get(14),
                row.get(15)
        );
    }

    public static XMLInputFormat toXMLInputFormat(ComputerInfo computerInfo) {
        return new XMLInputFormat(
                computerInfo.getIndex(),
                computerInfo.getProducer(),
                new Screen(
                        computerInfo.getIsTouchable(),
                        computerInfo.getDiagonal(),
                        computerInfo.getResolution(),
                        computerInfo.getScreenType()
                ),
                new Processor(
                        computerInfo.getProcessor(),
                        computerInfo.getCoreNumber(),
                        computerInfo.getFrequency()
                ),
                computerInfo.getRAM(),
                new Disc(
                        computerInfo.getDiskType(),
                        computerInfo.getDiskSize()
                ),
                new GraphicCard(),
                computerInfo.getOperatingSystem(),
                computerInfo.getOpticalDrive()
        );
    }


}
