package is.validator.implementation;

import is.model.Either;
import is.validator.ColumnValidator;

public class FrequencyValidator implements ColumnValidator {
    @Override
    public Either<String, Boolean> validate(String value) {
        return null;
    }
}
