package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScreenType {

    MATTE("matowa"),
    MATTE1("matowy"),
    GLOSSY("blyszczaca");

    @Getter
    public final String code;

}
