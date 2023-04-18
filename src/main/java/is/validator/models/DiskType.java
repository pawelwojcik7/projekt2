package is.validator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DiskType {

    SSD("SSD"), HDD("HDD");

    @Getter
    public final String code;

}
