package is.validator.implementation;

import is.model.Either;
import is.model.ScreenType;
import is.validator.ColumnValidator;

import java.util.Arrays;

public class ScreenTypeValidator implements ColumnValidator {

    private final String message = " doesn't exist in screen types: " + Arrays.toString(Arrays.stream(ScreenType.values()).map(ScreenType::getCode).toArray());

    @Override
    public Either<String, Boolean> validate(String value) {

        if (Arrays.stream(ScreenType.values()).anyMatch(e -> e.getCode().equals(value)) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }

}
