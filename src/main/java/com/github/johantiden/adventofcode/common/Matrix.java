package com.github.johantiden.adventofcode.common;

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
}
