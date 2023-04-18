package is.validator;

import is.model.Either;

public interface TableCellValidator {

    Either<String, Boolean> validate(String value);

}
