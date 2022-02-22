package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Matrix;
import com.github.johantiden.adventofcode.common.Pair;
import com.github.johantiden.adventofcode.common.PointInt;

public class A2021_11 {

    static final String SMALL_EXAMPLE =
"""
11111
19991
19191
19991
11111
""";

    static final String EXAMPLE =
"""
5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
""";

    static final String REAL =
"""
4575355623
3325578426
7885165576
4871455658
3722545312
8362663832
5562743324
4165776412
1817813675
4255524632
""";

    static long a(String input, int steps) {
        Matrix<Integer> matrix = A2021_09.parse(input);

        long countFlashers = 0;
        for (int i = 0; i < steps; i++) {
            matrix = step(matrix);
            countFlashers += matrix.filter(value -> value == 0).size();
        }

        return countFlashers;
    }

    static long b(String input) {
        Matrix<Integer> matrix = A2021_09.parse(input);
        int countSteps = 0;
        while (!matrix.allMatches(i -> i == 0)) {
            matrix = step(matrix);
            countSteps++;
        }
        return countSteps;
    }

    static Matrix<Integer> step(Matrix<Integer> matrix) {
        matrix = addOne(matrix);
        matrix = flashRecursive(matrix, Matrix.repeat(false, matrix.width(), matrix.height()));
        matrix = reduceFlashedToZero(matrix);
        return matrix;
    }

    private static Matrix<Integer> reduceFlashedToZero(Matrix<Integer> matrix) {
        return matrix.map(i -> i > 9 ? 0 : i);
    }

    private static Matrix<Integer> flashRecursive(Matrix<Integer> matrix, Matrix<Boolean> flashedAlready) {
        JList<PointInt> leftToFlash = matrix.mapWithCoordinates(p -> p)
                .filter(pair -> pair.right() > 9)
                .filter(pair -> !flashedAlready.get(pair.left()))
                .map(Pair::left);

        if (leftToFlash.isEmpty()) {
            return matrix;
        } else {
            PointInt flasher = leftToFlash.head();
            JList<PointInt> neighbors = flasher.neighbors9()
                    .filter(matrix::isInside);

            Matrix<Integer> newMatrix = neighbors
                    .reduce(matrix, (m, point) -> m.with(point, i -> i+1));
            return flashRecursive(newMatrix, flashedAlready.with(flasher, true));
        }
    }

    private static Matrix<Integer> addOne(Matrix<Integer> matrix) {
        return matrix.map(i -> i+1);
    }
}
