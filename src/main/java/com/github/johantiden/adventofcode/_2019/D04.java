package com.github.johantiden.adventofcode._2019;

import java.util.ArrayList;
import java.util.List;

public class D04 {


    public static void main(String[] args) {

        int min = 256310;
        int max = 732736;

        int count = 0;
        for (int i = min; i <= max; i++) {
            boolean hasTwoAdjacentDigitsSame = hasAtLeastTwoAdjacentDigitsSame(i);
            boolean isOnlyIncreasing = isOnlyIncreasing(i);
            if (hasTwoAdjacentDigitsSame && isOnlyIncreasing) {
                count++;
            }
        }
        System.out.println("count: " + count);
    }

    static class PartTwo {


        public static void main(String[] args) {

            int min = 256310;
            int max = 732736;

            int count = 0;
            for (int i = min; i <= max; i++) {
                boolean hasTwoAdjacentDigitsSame = hasExactTwoAdjacentDigitsSame(i);
                boolean isOnlyIncreasing = isOnlyIncreasing(i);
                if (hasTwoAdjacentDigitsSame && isOnlyIncreasing) {
                    count++;
                }
            }
            System.out.println("count: " + count);
        }


        static boolean hasExactTwoAdjacentDigitsSame(int num) {
            List<Integer> digits = toArray(num);

            int i = 0;
            while (i < digits.size()) {
                int runLength = calculateRunLength(i, digits);
                if (runLength == 2) {
                    return true;
                }
                i+=runLength;
            }

            return false;
        }

        private static int calculateRunLength(int i, List<Integer> num) {
            int digit = num.get(i);

            int index = i;
            while (index < num.size() && num.get(index) == digit) {
                index++;
            }

            int runLength = index - i;
            return runLength;
        }
    }

    private static boolean isOnlyIncreasing(int num) {
        List<Integer> digits = toArray(num);

        for (int i = 0; i < digits.size() -1; i++) {
            if (digits.get(i) > digits.get(i+1)) {
                return false;
            }
        }

        return true;
    }

    private static List<Integer> toArray(int i) {
        List<Integer> digits = new ArrayList<>();

        while (i > 0) {
            digits.add(0, i % 10);
            i /= 10;
        }

        return digits;
    }

    private static boolean hasAtLeastTwoAdjacentDigitsSame(int i) {
        List<Integer> num = toArray(i);
        for (int i1 = 0; i1 < num.size() -1; i1++) {
            if (num.get(i1).equals(num.get(i1 +1))) {
                return true;
            }
        }

        return false;
    }

}
