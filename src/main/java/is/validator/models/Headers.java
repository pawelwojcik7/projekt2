package is.validator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public enum Headers {
    INDEX("Index", 0),
    PRODUCER("Producent", 1),
    DIAGONAL("Wielkość matrycy", 2),
    RESOLUTION("Rozdzielczość", 3),
    SCREEN_TYPE("Typ matrycy", 4),
    IS_TOUCHABLE("Czy dotykowa", 5),
    PROCESSOR("Procesor", 6),
    CORE_NUMBER("Liczba rdzeni fizycznych", 7),
    FREQUENCY("Taktowanie", 8),
    RAM("RAM", 9),
    DISK_SIZE("Pojemność dysku", 10),
    DISK_TYPE("Typ dysku", 11),
    GRAPHIC_CARD("Karta graficzna", 12),
    GRAPHIC_CARD_MEMORY("Pamięć karty graficznej", 13),
    OPERATING_SYSTEM("System operacyjny", 14),
    OPTICAL_DRIVE("Napęd optyczny", 15);


    @Getter
    private String code;

    @Getter
    private Integer index;

    public static List<Headers> sortedValues() {
        Headers[] table = Headers.values();
        Arrays.sort(table, Comparator.comparingInt(Headers::getIndex));
        return List.of(table);
    }

}
