package is.validator.implementation;

import is.model.Either;
import is.validator.ColumnValidator;

public class DefaultValidator implements ColumnValidator {
    @Override
    public Either<String, Boolean> validate(String value) {
        return new Either<String, Boolean>().right(true);
    }
}
