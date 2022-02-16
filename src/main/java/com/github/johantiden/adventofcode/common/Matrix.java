package com.github.johantiden.adventofcode.common;

import java.util.function.Function;
import java.util.function.Predicate;

public class Matrix<T> {

    private final JList<JList<T>> rows;

    protected Matrix(JList<JList<T>> rows) {
        this.rows = rows;
    }

    public Matrix<T> transpose() {
        JList<JList<T>> columns = getColumns();
        return new Matrix<>(columns);
    }

    private JList<JList<T>> getColumns() {
        int width = width();
        JList<Integer> columnIndices = Lists.range(width);

        return columnIndices
                .map(this::getColumn);
    }

    public JList<T> getColumn(int x) {
        int height = rows.size();
        JList<Integer> rowIndices = Lists.range(height);
        return rowIndices
                .map(y -> rows.get(y).get(x));
    }

    public <R> Matrix<R> map(Function<T, R> mapper) {
        return of(rows.map(row -> row.map(mapper)));
    }

    public static <T> Matrix<T> of(JList<JList<T>> inner) {
        return new Matrix<>(inner);
    }

    public <R> JList<R> reduceColumns(Function<JList<T>, R> reducer) {
        return getColumns().map(reducer);
    }

    public <R> JList<R> reduceRows(Function<JList<T>, R> reducer) {
        return rows.map(reducer);
    }

    public JList<T> flatten() {
        return rows.flatMap(row -> row);
    }

    public boolean anyRowMatches(Predicate<JList<T>> rowPredicate) {
        return rows.anyMatch(rowPredicate);
    }

    public boolean anyColumnMatches(Predicate<JList<T>> rowPredicate) {
        return getColumns().anyMatch(rowPredicate);
    }

    public int height() {
        return rows.size();
    }

    public int width() {
        return rows.get(0).size();
    }

    public JList<T> getRow(int index) {
        return rows.get(index);
    }

    public Matrix<T> filterRows(Predicate<JList<T>> rowPredicate) {
        return of(rows.filter(rowPredicate));
    }

    public T get(int x, int y) {
        return getRow(y).get(x);
    }
}
