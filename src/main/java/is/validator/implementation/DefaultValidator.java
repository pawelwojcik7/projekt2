package is.validator.implementation;

import is.model.Either;
import is.validator.TableCellValidator;

public class DefaultValidator implements TableCellValidator {
    @Override
    public Either<String, Boolean> validate(String value) {
        return new Either<String, Boolean>().right(true);
    }
}
