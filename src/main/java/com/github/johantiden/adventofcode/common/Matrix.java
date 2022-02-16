package com.github.johantiden.adventofcode.common;

import java.util.function.Function;

public class Matrix {
    public static <T> JList<JList<T>> transpose(JList<JList<T>> matrix) {
        int width = matrix.get(0).size();
        JList<Integer> columnIndices = Lists.range(width);

        return columnIndices
                .map(c -> getColumn(c, matrix));
    }

    public static <T> JList<T> getColumn(int x, JList<JList<T>> matrix) {
        int height = matrix.size();
        JList<Integer> rowIndices = Lists.range(height);
        return rowIndices
                .map(y -> matrix.get(y).get(x));
    }

    public static <T, R> JList<JList<R>> map(JList<JList<T>> matrix, Function<T, R> mapper) {
        return matrix.map(row -> row.map(mapper));
    }
}
