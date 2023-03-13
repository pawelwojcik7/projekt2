package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public enum Headers {

    PRODUCER("Producent", 0),
    DIAGONAL("Wielkość matrycy", 1),
    RESOLUTION("Rozdzielczość", 2),
    SCREEN_TYPE("Typ matrycy", 3),
    IS_TOUCHABLE("Czy dotykowa", 4),
    PROCESSOR("Procesor", 5),
    CORE_NUMBER("Liczba rdzeni fizycznych", 6),
    FREQUENCY("Taktowanie", 7),
    RAM("RAM", 8),
    DISK_SIZE("Pojemność dysku", 9),
    DISK_TYPE("Typ dysku", 10),
    GRAPHIC_CARD("Karta graficzna", 11),
    GRAPHIC_CARD_MEMORY("Pamięć karty graficznej", 12),
    OPERATING_SYSTEM("System operacyjny", 13),
    OPTICAL_DRIVE("Napęd optyczny", 14);


    @Getter
    private String code;

    @Getter
    private Integer index;

    public List<Headers> sortedValues(){
        List<Headers> list = List.of(Headers.values());
        Arrays.sort(list, Comdisparator.comparingInt(Headers::getIndex));
        return list;
    }

}
