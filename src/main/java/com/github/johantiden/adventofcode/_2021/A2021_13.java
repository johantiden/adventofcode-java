package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.Input;
import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Lists;
import com.github.johantiden.adventofcode.common.Matrix;
import com.github.johantiden.adventofcode.common.Pair;
import com.github.johantiden.adventofcode.common.PointInt;

import java.util.Comparator;
import java.util.function.Function;

public class A2021_13 {

    static long a(String input) {
        Pair<JList<PointInt>, JList<Fold>> parsed = parse(input);
        Pair<JList<PointInt>, JList<Fold>> onlyOneFold = new Pair<>(parsed.left(), parsed.right().head(1));

        Matrix<Boolean> canvas = drawCanvas(onlyOneFold);

       canvas = fold(canvas, onlyOneFold.right());

        return canvas.countIf(pixel -> pixel);
    }

    private static Matrix<Boolean> drawCanvas(Pair<JList<PointInt>, JList<Fold>> parsed) {
        int width = parsed.left()
                .map(PointInt::x)
                .max(Comparator.naturalOrder()) + 1;

        int height = parsed.left()
                .map(PointInt::y)
                .max(Comparator.naturalOrder()) + 1;

        return parsed.left()
                .reduce(Matrix.repeat(false, width, height), (matrix, pointInt) -> matrix.with(pointInt, true));
    }

    private static Matrix<Boolean> fold(Matrix<Boolean> canvas, JList<Fold> folds) {
        if (folds.isEmpty()) {
            return canvas;
        } else {
            Matrix<Boolean> folded = fold(canvas, folds.head());
            return fold(folded, folds.tail());
        }
    }

    private static Matrix<Boolean> fold(Matrix<Boolean> canvas, Fold fold) {
        return switch (fold.dimension) {
            case UP -> fold(canvas::getRows, fold.index, canvas.height());
            case LEFT -> fold(i -> canvas.getColumns(i).transpose(), fold.index, canvas.width());
        };
    }

    private static Matrix<Boolean> fold(Function<JList<Integer>, Matrix<Boolean>> partGetter, int index, int dimensionSize) {
        Matrix<Boolean> top = partGetter.apply(Lists.range(index));
        Matrix<Boolean> bottomFlipped = partGetter.apply(Lists.range(dimensionSize-1, index, -1));

        return Matrix.blend(top, bottomFlipped, (a, b) -> a || b);
    }


    static long b(String input) {
        Pair<JList<PointInt>, JList<Fold>> parsed = parse(input);
        return -1;
    }

    private static Pair<JList<PointInt>, JList<Fold>> parse(String input) {
        JList<String> split = Input.split("\n\n", input);

        return new Pair<>(
                parsePoints(split.get(0)),
                parseFolds(split.get(1))
        );
    }

    private static JList<Fold> parseFolds(String rows) {
        return Input.splitRows(rows)
                .map(s -> s.replaceAll("fold along ", ""))
                .map(s -> Input.split("=", s))
                .map(split -> new Fold(parseDimention(split.get(0)), Integer.parseInt(split.get(1))));
    }

    private static JList<PointInt> parsePoints(String rows) {
        return Input.splitRows(rows)
                .map(Input::splitComma)
                .map(list -> list.map(Integer::parseInt))
                .map(split -> new PointInt(split.get(0), split.get(1)));
    }

    public static Fold.Dimension parseDimention(String s) {
        return switch (s) {
            case "x" -> Fold.Dimension.LEFT;
            case "y" -> Fold.Dimension.UP;
            default -> throw new IllegalArgumentException();
        };
    }

    private record Fold(Dimension dimension, int index) {
        enum Dimension {
            LEFT,
            UP;
        }
    }
}
