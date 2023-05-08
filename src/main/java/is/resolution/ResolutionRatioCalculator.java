package is.resolution;

import is.model.Pair;

public class ResolutionRatioCalculator {


    public static Pair<Integer, Integer> findSmallestRatio(int firstNumber, int secondNumber) {
        int gcd = greatestCommonDivisor(firstNumber, secondNumber);

        return new Pair<>(firstNumber / gcd, secondNumber / gcd);
    }

    private static int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return greatestCommonDivisor(b, a % b);
        }
    }

}
