package is.format.xml;

import is.model.ComputerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class XMLInputFormat {

    @XmlAttribute
    private String id;

    private String manufacturer;

    private Screen screen;

    private Processor processor;

    private String ram;

    private Disc disc;

    @XmlElement(name = "graphic_card")
    private GraphicCard graphicCard;

    @XmlElement(name = "os")
    private String operatingSystem;

    @XmlElement(name = "disc_reader")
    private String opticalDrive;


    public ComputerInfo toComputerInfo() {
        return new ComputerInfo(
                this.id,
                this.manufacturer,
                this.screen.getSize(),
                this.screen.getResolution(),
                this.screen.getType(),
                this.screen.getTouch(),
                this.processor.getName(),
                this.processor.getPhysicalCores(),
                this.processor.getClockSpeed(),
                this.ram,
                this.disc.getStorage(),
                this.disc.getType(),
                this.graphicCard.getName(),
                this.graphicCard.getMemory(),
                this.operatingSystem,
                this.opticalDrive
        );
    }


    public static XMLInputFormat convert(ComputerInfo computerInfo) {
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
