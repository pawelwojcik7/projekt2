package is.validator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IsTouchable {

    TAK("tak"),
    YES("yes"),
    NO("no"),
    NIE("nie");

    @Getter
    public final String code;

}
