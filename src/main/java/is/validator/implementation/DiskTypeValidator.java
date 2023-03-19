package is.validator.implementation;

import is.model.DiskType;
import is.model.Either;
import is.validator.ColumnValidator;

import java.util.Arrays;

public class DiskTypeValidator implements ColumnValidator {

    private final String message = " doesn't exist in disk types: " + Arrays.toString(Arrays.stream(DiskType.values()).map(DiskType::getCode).toArray());

    @Override
    public Either<String, Boolean> validate(String value) {

        if (Arrays.stream(DiskType.values()).anyMatch(e -> e.getCode().equals(value)) || value.equals("")) {
            return new Either<String, Boolean>().right(true);
        } else return new Either<String, Boolean>().left("[ " + value + " ]" + message);
    }

}
