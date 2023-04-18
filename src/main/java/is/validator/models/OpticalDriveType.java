package is.validator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OpticalDriveType {

    BLU_RAY("Blu-Ray"), BRAK("brak"), DVD("DVD");

    @Getter
    public final String code;

}
