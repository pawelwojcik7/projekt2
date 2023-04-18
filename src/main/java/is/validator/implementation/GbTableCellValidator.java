package is.validator.implementation;

import is.model.Either;
import is.validator.TableCellValidator;

public class GbTableCellValidator implements TableCellValidator {
    private final static String gbRegexRegex = "^[0-9]+GB$";

    private final static String message = " doesn't match regex: NUMBER + 'GB'";

    @Override
    public Either<String, Boolean> validate(String value) {
        if (value.matches(gbRegexRegex) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }
}
