package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IsTouchable {

    YES("tak"), NO("nie");

    @Getter
    public final String code;

}
