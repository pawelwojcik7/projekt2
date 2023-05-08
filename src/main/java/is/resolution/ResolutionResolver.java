package is.resolution;

import is.model.Pair;

public class ResolutionResolver {


    public static Pair<Integer, Integer> resolveResolution(String resolution) throws IllegalArgumentException {
        String[] parseResult = resolution.split("x");
        if (parseResult.length != 2) {
            throw new IllegalArgumentException("Invalid resolution format");
        }
        Integer firstNumber = Integer.parseInt(parseResult[0]);
        Integer secondNumber = Integer.parseInt(parseResult[1]);

        return new Pair<Integer, Integer>(firstNumber, secondNumber);
    }

}
