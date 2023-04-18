package is.validator.implementation;

import is.model.Either;
import is.validator.TableCellValidator;

public class LetterTableCellValidator implements TableCellValidator {

    private final static String onlyLettersRegex = "^[a-zA-Z]+$";

    private final static String message = " doesn't match regex: only letters";

    @Override
    public Either<String, Boolean> validate(String value) {
        if (value.matches(onlyLettersRegex) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }
}
