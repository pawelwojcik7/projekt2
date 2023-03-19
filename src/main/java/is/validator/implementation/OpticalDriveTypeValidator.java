package is.validator.implementation;

import is.model.DiskType;
import is.model.Either;
import is.model.OpticalDriveType;
import is.validator.ColumnValidator;

import java.util.Arrays;

public class OpticalDriveTypeValidator implements ColumnValidator {
    private final String message = " doesn't exist in optical drive types: " + Arrays.toString(Arrays.stream(OpticalDriveType.values()).map(OpticalDriveType::getCode).toArray());

    @Override
    public Either<String, Boolean> validate(String value) {

        if (Arrays.stream(OpticalDriveType.values()).anyMatch(e -> e.getCode().equals(value)) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }
}
