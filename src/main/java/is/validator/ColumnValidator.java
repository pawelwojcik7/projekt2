package is.validator;

import is.model.Either;

public interface ColumnValidator {

    Either<String, Boolean> validate(String value);

}
