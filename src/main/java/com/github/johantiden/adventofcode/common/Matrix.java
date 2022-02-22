package com.github.johantiden.adventofcode.common;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Matrix<T> {

    private final JList<JList<T>> rows;

    protected Matrix(JList<JList<T>> rows) {
        this.rows = rows;
    }

    public static <T> Matrix<T> repeat(T value, int width, int height) {
        return of(JList.repeat(JList.repeat(value, width), height));
    }

    public  Matrix<PointInt> allCoordinates() {
        return of(rows.mapWithCoordinates(pair -> pair.right().mapWithCoordinates(pair2 -> {
            Integer x = pair2.left();
            Integer y = pair.left();
            return new PointInt(x, y);
        })));
    }

    public static <A, B> Matrix<Pair<A, B>> zip(Matrix<A> a, Matrix<B> b) {
        return a.allCoordinates()
                .map(coordinate -> new Pair<>(a.get(coordinate), b.get(coordinate)));
    }

    public T get(PointInt coordinate) {
        return get(coordinate.x(), coordinate.y());
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

    public <R> Matrix<R> mapWithCoordinates(Function<Pair<PointInt, T>, R> mapper) {
        // TODO: Maybe redo as zip(allCoordinates(), this).map(mapper)
        return zip(allCoordinates(), this).map(mapper);
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

    public <R> R reduce(Function<JList<T>, R> reducer) {
        return reducer.apply(flatten());
    }

    public <R> R reduce(R identity, Function<JList<T>, R> reducer) {
        return reducer.apply(flatten());
    }

    public static int sum(Matrix<Integer> intMatrix) {
        return intMatrix.reduceRows(row -> row.reduce(0, Integer::sum)).reduce(0, Integer::sum);
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

    public boolean allRowsMatches(Predicate<JList<T>> rowPredicate) {
        return rows.allMatches(rowPredicate);
    }

    public boolean allMatches(Predicate<T> predicate) {
        return allRowsMatches(row -> row.allMatches(predicate));
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

    public Matrix<T> getRows(JList<Integer> indices) {
        return of(indices.map(this::getRow));
    }

    public Matrix<T> filterRows(Predicate<JList<T>> rowPredicate) {
        return of(rows.filter(rowPredicate));
    }

    public JList<T> filter(Predicate<T> predicate) {
        return flatten().filter(predicate);
    }

    public T get(int x, int y) {
        return getRow(y).get(x);
    }

    public Matrix<T> with(int x, int y, T value) {
        return of(rows.with(y, row -> row.with(x, value)));
    }

    public Matrix<T> with(PointInt coordinate, T value) {
        return with(coordinate.x(), coordinate.y(), value);
    }

    public Matrix<T> with(int x, int y, UnaryOperator<T> valueReplacer) {
        return of(rows.with(y, row -> row.with(x, valueReplacer)));
    }

    /**
     * Analoguous to 'tail' but in the horizontal direction i.e. we will be dropping the leftmost column.
     */
    public Matrix<T> tailRight() {
        return of(rows.map(JList::tail));
    }

    public <R> Matrix<R> convolution(Matrix<BiFunction<T, T, R>> kernel, Convolution convolution, Function<Matrix<R>, R> windowReducer, R rZero, T tZero) {
        if (convolution != Convolution.PRESERVE_SIZE) {
            throw new UnsupportedOperationException();
        }

        int kernelRadiusX = kernel.width() / 2;
        int kernelRadiusY = kernel.height() / 2;

        Matrix<Matrix<R>> windows = mapWithCoordinates(pair -> {
            PointInt centerPositionInMatrix = pair.left();
            T centerValue = pair.right();

            Matrix<R> window = Matrix.repeat(rZero, kernel.width(), kernel.height());
            window = window.mapWithCoordinates(pair2 -> {
                PointInt coordinateInKernel = pair2.left();
                T neighborValue = getOrDefault(
                        centerPositionInMatrix.x() + coordinateInKernel.x() - kernelRadiusX,
                        centerPositionInMatrix.y() + coordinateInKernel.y() - kernelRadiusY,
                        tZero
                );
                return kernel.get(coordinateInKernel.x(), coordinateInKernel.y()).apply(centerValue, neighborValue);
            });
            return window;
        });

        return windows.map(windowReducer);
    }

    public T getOrDefault(PointInt coordinate, T defaultValue) {
        return getOrDefault(coordinate.x(), coordinate.y(), defaultValue);
    }

    private T getOrDefault(int x, int y, T defaultValue) {
        if (x < 0 || y < 0 || x >= width() || y >= height()) {
            return defaultValue;
        } else {
            return get(x, y);
        }
    }

    public int countIf(Predicate<T> predicate) {
        Matrix<Integer> map = map(predicate::test)
                .map(b -> b ? 1 : 0);
        return sum(map);
    }

    public enum Convolution {
        SHRINK_WITH_KERNEL,
        PRESERVE_SIZE
    }
}
