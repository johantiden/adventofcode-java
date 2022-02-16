package com.github.johantiden.adventofcode.common;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;

public class Lists {
    public static <T> Matrix<T> slidingWindows(JList<T> list, int windowSize) {
        JList<JList<T>> windows = JList.<JList<T>>of();
        for (int i = 0; i < list.size() - windowSize + 1; i++) {
            JList<T> window = JList.of();
            for (int j = 0; j < windowSize; j++) {
                window = window.plus(list.get(i + j));
            }
            windows = windows.plus(window);
        }
        return Matrix.of(windows);
    }

    public static JList<Integer> range(int endExclusive) {
        return new JList(IntStream.range(0, endExclusive).boxed().toList());
    }

    public static JList<Integer> ints(int start, UnaryOperator<Integer> next, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(start);

        int number = start;
        for (int i = 0; i < size - 1; i++) {
            number = next.apply(number);
            list.add(number);
        }
        return new JList<>(list);
    }

    @Nonnull
    public static Function<JList<Integer>, Integer> sum() {
        return list -> list.reduce(0, Integer::sum);
    }
}
