package is.validator.implementation;

import is.model.Either;
import is.validator.ColumnValidator;

public class NumberColumnValidator implements ColumnValidator {
    private final static String onlyNumbersRegex = "^[0-9]+$";

    private final static String message = " doesn't match regex: only numbers";

    @Override
    public Either<String, Boolean> validate(String value) {
        if (value.matches(onlyNumbersRegex) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }
}
