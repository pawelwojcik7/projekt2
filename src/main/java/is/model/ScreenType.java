package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScreenType {

    MATTE("matowa"), GLOSSY("blyszczaca");

    @Getter
    public final String code;

}
