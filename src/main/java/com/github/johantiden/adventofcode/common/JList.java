package com.github.johantiden.adventofcode.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

public class JList<T> {

    private final List<T> inner;

    public JList(List<T> inner) {
        this.inner = Objects.requireNonNull(inner);
    }

    public static <L, R> JList<Pair<L, R>> zip(JList<L> left, JList<R> right) {
        if (left.size() != right.size()) {
            throw new IllegalArgumentException("zip requires both lists to be of equal length! "
                    + "left.size()="+left.size() + ", "
                    + "right.size()="+right.size());
        }

        ArrayList<Pair<L, R>> newInner = new ArrayList<>(left.size());

        for (int i = 0; i < left.size(); i++) {
            newInner.add(new Pair<>(left.get(i), right.get(i)));
        }

        return new JList<>(newInner);
    }

    public JList<T> with(int index, T value) {
        ArrayList<T> copy = new ArrayList<>(inner);
        copy.set(index, value);
        return new JList<>(copy);
    }

    public JList<T> with(int index, UnaryOperator<T> valueReplacer) {
        T newValue = valueReplacer.apply(inner.get(index));
        return with(index, newValue);
    }

    public static <T> JList<T> repeat(T t, int count) {
        ArrayList<T> list = new ArrayList<>(count);
        list.add(t);
        return new JList<>(list);
    }

    public static <T> JList<T> repeat(Supplier<T> tSupplier, int count) {
        ArrayList<T> list = new ArrayList<>(count);
        list.add(tSupplier.get());
        return new JList<>(list);
    }

    public int size() {
        return inner.size();
    }

    public boolean isEmpty() {
        return inner.isEmpty();
    }

    public boolean anyMatch(T o) {
        return inner.contains(o);
    }

    public boolean anyMatch(Predicate<T> predicate) {
        return inner.stream()
                .anyMatch(predicate);
    }

    public JList<T> plus(T t) {
        ArrayList<T> newInner = new ArrayList<>(inner.size() + 1);

        newInner.addAll(inner);
        newInner.add(t);

        return new JList<>(newInner);
    }

    public JList<T> plusFirst(T t) {
        ArrayList<T> newInner = new ArrayList<>(inner.size() + 1);

        newInner.add(t);
        newInner.addAll(inner);

        return new JList<>(newInner);
    }

    public JList<T> minus(Object o) {
        ArrayList<T> newInner = new ArrayList<>(inner.size() + 1);

        newInner.addAll(inner);
        newInner.remove(o);

        return new JList<>(newInner);
    }

    public JList<T> minusLast() {
        ArrayList<T> newInner = new ArrayList<>(inner.size() + 1);

        newInner.addAll(inner);
        newInner.remove(inner.size() - 1);

        return new JList<>(newInner);
    }

    public boolean containsAll(@Nonnull Collection<?> c) {
        return inner.containsAll(c);
    }

    public JList<T> plusAll(@Nonnull Collection<? extends T> c) {
        ArrayList<T> newInner = new ArrayList<>(inner.size() + 1);

        newInner.addAll(inner);
        newInner.addAll(c);

        return new JList<>(newInner);

    }

    public T get(int index) {
        return inner.get(index);
    }

    public int indexOf(Object o) {
        return inner.indexOf(o);
    }

    public static <T> JList<T> of(T... values) {
        return new JList<T>(List.<T>of(values));
    }

    public Stream<T> stream() {
        return inner.stream();
    }

    public void forEach(Consumer<T> consumer) {
        inner.forEach(consumer);
    }

    public JList<T> filter(Predicate<T> filter) {
        return new JList<>(inner.stream().filter(filter).toList());
    }

    public <R> JList<R> map(Function<T, R> mapper) {
        return new JList<>(inner.stream().map(mapper).toList());
    }

    public T reduce(T identity, BinaryOperator<T> mapper) {
        return inner.stream().reduce(identity, mapper);
    }

    public <U> U reduce(U identity, BiFunction<U, T, U> mapper) {
        U value = identity;

        for (T t : inner) {
            value = mapper.apply(value, t);
        }

        return value;
    }

    @Override
    public String toString() {
        return inner.toString();
    }

    public T findOne(Predicate<T> predicate) {
        JList<T> filtered = filter(predicate);
        if (filtered.size() != 1) {
            throw new IllegalStateException("findOne requires exactly one item to be found! size after filtering was " + filtered.size());
        }
        return filtered.get(0);
    }

    public <U extends Comparable<? super U>> T min(Function<T, U> keyExtractor) {
       return min(Comparator.comparing(keyExtractor));
    }

    public T min(Comparator<T> comparator) {
        return stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("There was no first!"));
    }

    public <U extends Comparable<? super U>> T max(Function<T, U> keyExtractor) {
       return max(Comparator.comparing(keyExtractor));
    }

    public T max(Comparator<T> comparator) {
        return stream()
                .max(comparator)
                .orElseThrow(() -> new RuntimeException("There was no first!"));
    }

    public <R> JList<R> flatMap(Function<T, JList<R>> mapper) {
        return new JList<>(
                inner.stream().flatMap(t -> mapper.apply(t).stream()).toList()
        );
    }

    public boolean allMatch(Predicate<T> predicate) {
        return !anyMatch(predicate.negate());
    }

    public JList<Pair<T, T>> slidingWindowPairs() {
        return Lists.slidingWindowPairs(this);
    }
}
