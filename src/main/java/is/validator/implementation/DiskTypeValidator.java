package is.validator.implementation;

import is.model.Either;
import is.validator.ColumnValidator;

public class DiskTypeValidator implements ColumnValidator {
    @Override
    public Either<String, Boolean> validate(String value) {
        //return Arrays.stream(Disk.values()).anyMatch(disk -> disk.code.equals(value));
   return null;
    }
}
