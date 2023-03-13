package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OpticalDriveType {

    BLU_RAY("BluRay"), BRAK("brak"), DVD("DVD");

    @Getter
    public final String code;

}
