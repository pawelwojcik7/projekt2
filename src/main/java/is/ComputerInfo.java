package is;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComputerInfo {
    @CsvBindByPosition(position = 0)
    private String producer;
    @CsvBindByPosition(position = 1)
    private String diagonal;
    @CsvBindByPosition(position = 2)
    private String resolution;
    @CsvBindByPosition(position = 3)
    private String screenType;
    @CsvBindByPosition(position = 4)
    private String isTouchable;
    @CsvBindByPosition(position = 5)
    private String processor;
    @CsvBindByPosition(position = 6)
    private String coreNumber;
    @CsvBindByPosition(position = 7)
    private String frequency;
    @CsvBindByPosition(position = 8)
    private String RAM;
    @CsvBindByPosition(position = 9)
    private String diskSize;
    @CsvBindByPosition(position = 10)
    private String diskType;
    @CsvBindByPosition(position = 11)
    private String graphicCard;
    @CsvBindByPosition(position = 12)
    private String graphicCardMemory;
    @CsvBindByPosition(position = 13)
    private String operatingSystem;
    @CsvBindByPosition(position = 14)
    private String opticalDrive;

    public String[] toStringArray(){
        return new String[]{producer, diagonal, resolution, screenType, isTouchable, processor, coreNumber, frequency, RAM, diskSize, diskType, graphicCard, graphicCardMemory, operatingSystem, opticalDrive};
    }

}
